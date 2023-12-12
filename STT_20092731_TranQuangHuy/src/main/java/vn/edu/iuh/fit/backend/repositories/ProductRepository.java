package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.iuh.fit.backend.enums.ProductStatus;
import vn.edu.iuh.fit.backend.models.Product;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> getProductByProductType_Id(long productTypeId);

    @Query(value = "SELECT * FROM products WHERE input_time  < :inputTime", nativeQuery = true)
    List<Product> getProductsByInputTime2(@Param("inputTime") LocalDateTime inputTime);

    @Modifying
    @Query("update Product set status= :status where id = :productId ")
    Integer updateStatus (long productId, ProductStatus status);
}
