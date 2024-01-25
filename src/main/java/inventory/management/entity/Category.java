/**
 * Represents a category of liquor in the inventory management system.
 *
 * - categoryId: Unique identifier for the category.
 * - categoryName: The name of the category.
 * - brands: List of brands belonging to this category.
 * - suppliers: List of suppliers providing liquor in this category.
 * 
 * * Relationships:
 * - Has a one-to-many relationship with Brand.
 * - Has a many-to-many relationship with Supplier.
 */

package inventory.management.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;
	
	private String categoryName;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
	private Set<Brand> brands = new HashSet<>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "categories",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Supplier> suppliers = new HashSet<>();

}
