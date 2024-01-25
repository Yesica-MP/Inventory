
/**
 * Represents a liquor brand in the inventory management system.
 *
 * - brandId: Unique identifier for the brand.
 * - brandName: The name of the brand.
 * - brandDescription: Description of the brand.
 * - brandPrice: Price of the liquor from this brand.
 * - Supplier: The supplier providing this brand of liquor.
 * - Category: The category to which the liquor belongs.
 * 
 * Relationships:
 * - Belongs to a single Supplier.
 * - Belongs to a single Category.
 */

package inventory.management.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Brand {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long brandId;
	
	private String brandName;
	private String brandDescription;
	private double brandPrice;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "category_id", nullable = true)
	private Category category;

}
