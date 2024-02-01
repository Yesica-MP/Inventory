package inventory.management.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import inventory.management.entity.Brand;

/**
 * Spring Data JPA repository for managing Brand entities.
 */

public interface BrandDao extends JpaRepository<Brand, Long> {

	List<Brand> findByBrandName(String brandName);

}
