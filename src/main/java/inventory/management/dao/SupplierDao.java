package inventory.management.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import inventory.management.entity.Supplier;

/**
 * Spring Data JPA repository for managing Supplier entities.
 */

public interface SupplierDao extends JpaRepository<Supplier, Long> {

}
