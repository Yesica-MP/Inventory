package inventory.management.controller.model;

import inventory.management.entity.Brand;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BrandData {
	
	private Long brandId;
	
	private String brandName;
	private String brandDescription;
	private double brandPrice;
	
	
	public BrandData(Brand brand) {
		brandId = brand.getBrandId();
		brandName = brand.getBrandName();
		brandDescription = brand.getBrandDescription();
		brandPrice = brand.getBrandPrice();
		
	}
	

}
