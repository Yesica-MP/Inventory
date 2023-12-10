package inventory.management.controller.model;

import java.util.HashSet;
import java.util.Set;

import inventory.management.entity.Brand;
import inventory.management.entity.Category;
import inventory.management.entity.Supplier;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SupplierData {
	
	private Long supplierId;
	private String supplierName;
	private String supplierPhone;
	private String supplierAddress;
	
	private Set<BrandResponse> brands = new HashSet<>();
	
	private Set<CategoryResponse> categories = new HashSet<>();
	
	public SupplierData(Supplier supplier) {
		supplierId = supplier.getSupplierId();
		supplierName = supplier.getSupplierName();
		supplierPhone = supplier.getSupplierPhone();
		supplierAddress = supplier.getSupplierAddress();
		
		for (Brand brand : supplier.getBrands()) {
			brands.add(new BrandResponse(brand));
		}
		for (Category category : supplier.getCategories()) {
			categories.add(new CategoryResponse(category));
		}
	}
	@Data
	@NoArgsConstructor
	public static class BrandResponse{
		
		private Long brandId;
		
		private String brandName;
		private String brandDescription;
		private double brandPrice;
		private Long supplierId;
		private Long categoryId;
		
		public BrandResponse(Brand brand) {
			brandId = brand.getBrandId();
			brandName = brand.getBrandName();
			brandDescription = brand.getBrandDescription();
			brandPrice = brand.getBrandPrice();
			
			if(brand.getSupplier() != null) {
				supplierId = brand.getSupplier().getSupplierId();
			}
			
			if(brand.getCategory() != null) {
				categoryId = brand.getCategory().getCategoryId();
			}
		}
		
	}
	@Data
	@NoArgsConstructor
	public static class CategoryResponse {
		private Long categoryId;
		private String categoryName;
		
		
		public CategoryResponse(Category category) {
			categoryId = category.getCategoryId();
			categoryName = category.getCategoryName();
		}
	}

}
