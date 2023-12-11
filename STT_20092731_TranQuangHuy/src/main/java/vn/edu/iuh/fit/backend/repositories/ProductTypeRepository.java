package vn.edu.iuh.fit.backend.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.backend.models.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

}
