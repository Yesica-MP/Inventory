/**
 * Represents a supplier in the inventory management system.
 *
 * - supplierId: Unique identifier for the supplier.
 * - supplierName: The name of the supplier.
 * - supplierPhone: Contact phone number for the supplier.
 * - supplierAddress: Address of the supplier.
 * - brands: List of liquor brands supplied by this supplier.
 * - categories: List of categories supplied by this supplier.
 * 
 *  Relationships:
 * - Has a one-to-many relationship with Brand.
 * - Has a many-to-many relationship with Category.
 */


package inventory.management.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Supplier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long supplierId;
	
	private String supplierName;
	private String supplierPhone;
	private String supplierAddress;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
	private Set<Brand> brands = new HashSet<>();
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "supplier_category", joinColumns = @JoinColumn(name = "supplier_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Category> categories = new HashSet<>();

}
