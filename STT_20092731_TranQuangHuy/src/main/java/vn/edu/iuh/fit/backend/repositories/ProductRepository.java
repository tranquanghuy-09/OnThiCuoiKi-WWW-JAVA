package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import vn.edu.iuh.fit.backend.enums.ProductStatus;
import vn.edu.iuh.fit.backend.models.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> getProductByProductType_Id(long productTypeId);

    @Modifying
    @Query("update Product set status= :status where id = :productId ")
    Integer updateStatus (long productId, ProductStatus status);
}
