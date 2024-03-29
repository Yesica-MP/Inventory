package inventory.management.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import inventory.management.controller.model.SupplierData;
import inventory.management.controller.model.SupplierData.BrandResponse;
import inventory.management.controller.model.SupplierData.CategoryResponse;
import inventory.management.dao.BrandDao;
import inventory.management.dao.CategoryDao;
import inventory.management.dao.SupplierDao;
import inventory.management.entity.Brand;
import inventory.management.entity.Category;
import inventory.management.entity.Supplier;



@Service
public class InventoryService {
	
	@Autowired
	private BrandDao brandDao;
	
	@Autowired
	private SupplierDao supplierDao;
	
	@Autowired
	private CategoryDao categoryDao;

	@Transactional(readOnly = false)
	public BrandResponse saveBrand(Long supplierId, Long categoryId, BrandResponse brandResponse) { 
		Supplier supplier = findSupplierById(supplierId);
		Category category = findCategoryById(categoryId);
		Long brandId = brandResponse.getBrandId();
		Brand brand = findOrCreateBrand(supplierId, brandId, brandResponse.getBrandName());
		
		setFieldsInBrand(brand, brandResponse);
		brand.setSupplier(supplier);
		brand.setCategory(category);
		supplier.getBrands().add(brand);
		
		Brand dBrand = brandDao.save(brand);
		return new BrandResponse (dBrand);
	}

	private void setFieldsInBrand(Brand brand, BrandResponse brandResponse) {
		brand.setBrandName(brandResponse.getBrandName());
		brand.setBrandDescription(brandResponse.getBrandDescription());
		brand.setBrandPrice(brandResponse.getBrandPrice());
		
	}
	
	private Brand findOrCreateBrand(Long supplierId, Long brandId, String brandName) {
		Brand brand;
		
		if(Objects.isNull(brandId)) {
			List <Brand> brands = brandDao.findByBrandName(brandName);
			
			if(!brands.isEmpty()) {
				throw new DuplicateKeyException("Brand " + brandName + " already exists.");
			}
				
			brand = new Brand();
		}
		else {
			brand = findBrandById(supplierId, brandId);
		}
		Supplier supplier = findSupplierById(supplierId);
		brand.setSupplier(supplier);
		return brand;
	}

	private Brand findBrandById(Long supplierId, Long brandId) {
		return brandDao.findById(brandId).orElseThrow(() -> new NoSuchElementException("Brand with ID=" + brandId + " was not found"));
	}
	@Transactional(readOnly = true)
	public List<BrandResponse> retrieveAllBrands() {
		List<Brand> brands = brandDao.findAll();
		List<BrandResponse> response = new LinkedList<>();
		
		for(Brand brand : brands) {
			BrandResponse bd = new BrandResponse(brand);
			
			response.add(bd);
		}
		return response;
	}

	@Transactional(readOnly = false)
	public SupplierData saveSupplier(SupplierData supplierData) {
		
		Long supplierId = supplierData.getSupplierId();
		Supplier supplier = findOrCreateSupplier(supplierId);
		
		copySupplierFields(supplier, supplierData);
		return new SupplierData(supplierDao.save(supplier));		
	}

	private Supplier findOrCreateSupplier(Long supplierId) {
		Supplier supplier;
		
		if(Objects.isNull(supplierId)) {
			
			supplier = new Supplier();
		}
		else {
			supplier = findSupplierById(supplierId);
		}
		return supplier;
	}

	private Supplier findSupplierById( Long supplierId) {
		return supplierDao.findById(supplierId)
				.orElseThrow(() -> new NoSuchElementException("Supplier with ID= " + supplierId + " was not found.") );
		} 
	private void copySupplierFields(Supplier supplier, SupplierData supplierData) {
		supplier.setSupplierId(supplierData.getSupplierId());
		supplier.setSupplierName(supplierData.getSupplierName());
		supplier.setSupplierPhone(supplierData.getSupplierPhone());
		supplier.setSupplierAddress(supplierData.getSupplierAddress());
	}
	@Transactional(readOnly = false)
	public CategoryResponse saveCategory(CategoryResponse categoryResponse) {
		Long supplierId = categoryResponse.getSupplierId();
		Supplier supplier = findSupplierById(supplierId);
		        
		// Create or retrieve the category
	    Category category = findOrCreateCategory(categoryResponse.getCategoryId());

	    // Set category details
	    copyCategoryFields(category, categoryResponse);

	    // Associate category with suppliers
	    category.getSuppliers().clear();  // Clear existing suppliers
	    category.getSuppliers().add(supplier);

	    
	    // Update categories in suppliers
	    supplier.getCategories().add(category);

	    // Save the category
	    Category savedCategory = categoryDao.save(category);

	    return new CategoryResponse(savedCategory);

		
		}

	private void copyCategoryFields(Category category, CategoryResponse categoryData) {
		category.setCategoryId(categoryData.getCategoryId());
		category.setCategoryName(categoryData.getCategoryName());
		category.getSuppliers().clear(); // Assuming you want to associate only one supplier
	    Supplier supplier = findSupplierById(categoryData.getSupplierId());
	    category.getSuppliers().add(supplier);
		
	}

	private Category findOrCreateCategory(Long categoryId) {
		
			
		if(categoryId != null) {
			return findCategoryById(categoryId);
		}else {
			return new Category();
		}
		
	}

	private Category findCategoryById(Long categoryId) {
		return categoryDao.findById(categoryId)
				.orElseThrow(() -> new NoSuchElementException("Category with ID " + categoryId + " was not found."));
	}
	@Transactional(readOnly = true)
	public List<SupplierData> retrieveSuppliers() {
		List <Supplier> suppliers = supplierDao.findAll();
		List<SupplierData> response = new LinkedList<>();
		
		for(Supplier supplier : suppliers) {
			SupplierData sd = new SupplierData(supplier);
			response.add(sd);
		}
		return response;
	}
	@Transactional(readOnly = true)
	public SupplierData retrieveSupplierById(Long supplierId) {

		Supplier supplier = findSupplierById(supplierId);
		return new SupplierData(supplier);
	}
	
	@Transactional(readOnly = false)
	public void deleteBrandById(Long supplierId, Long brandId) {
		Brand brand = findBrandById(supplierId, brandId);
		brandDao.delete(brand);
		
	}
	@Transactional(readOnly = false)
	public void deleteSupplierById(Long supplierId) {
		Supplier supplier = findSupplierById(supplierId);
		supplierDao.delete(supplier);
		
	}
	@Transactional
	public CategoryResponse associateSuppliersWithCategory(CategoryResponse categoryResponse) {
		Long supplierId = categoryResponse.getSupplierId();
		Long categoryId = categoryResponse.getCategoryId();
		 
	    Category category = findCategoryById(categoryId);

	    // Ensure the category exists
	    if (category == null) {
	    	throw new NoSuchElementException("Category with ID " + categoryId + " was not found.");
	    }
	    // Associate category with supplier
	    Supplier supplier = findSupplierById(supplierId); 
	    category.getSuppliers().add(supplier);

	    // Save the category
	    Category savedCategory = categoryDao.save(category);
	    
	    // Update categories in the associated supplier
	    supplier.getCategories().add(savedCategory);
	    supplierDao.save(supplier);

	    return new CategoryResponse(savedCategory);
		
	}
	@Transactional(readOnly = true)
	public List<CategoryResponse> retrieveCategories() {
		
		List <Category> categories = categoryDao.findAll();
		List<CategoryResponse> response = new LinkedList<>();
		
		for(Category category : categories) {
			CategoryResponse cd = new CategoryResponse(category);
			response.add(cd);
		}
		return response;
	}
}
