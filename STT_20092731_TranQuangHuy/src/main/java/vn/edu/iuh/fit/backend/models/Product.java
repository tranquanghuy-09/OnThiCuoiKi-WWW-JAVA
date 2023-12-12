package vn.edu.iuh.fit.backend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import vn.edu.iuh.fit.backend.enums.ProductStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long id;
    @Column(name = "product_name", nullable = false)
    private String name;
    @Column(name = "price")
//    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private float price;

    @Column(name = "status")
    private ProductStatus status;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType productType;
    @Column(name = "input_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime inputTime;

    public Product(String name, float price, ProductStatus status, ProductType productType) {
        this.name = name;
        this.price = price;
        this.status = status;
        this.productType = productType;
    }

    public Product(String name, float price, ProductStatus status, ProductType productType, LocalDateTime inputTime) {
        this.name = name;
        this.price = price;
        this.status = status;
        this.productType = productType;
        this.inputTime = inputTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
