package vn.edu.iuh.fit.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.enums.ProductStatus;
import vn.edu.iuh.fit.backend.models.Product;
import vn.edu.iuh.fit.backend.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProductByProductType_Id(long productTypeId){
        return productRepository.getProductByProductType_Id(productTypeId);
    }

    public Integer deleteProduct (long productId){
        return productRepository.updateStatus(productId, ProductStatus.TERMINATED);
    }
}
