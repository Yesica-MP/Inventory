package inventory.management.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import inventory.management.entity.Category;
/**
 * Spring Data JPA repository for managing Category entities.
 */
public interface CategoryDao extends JpaRepository<Category, Long> {
		
}
