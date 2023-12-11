package vn.edu.iuh.fit.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.ProductType;
import vn.edu.iuh.fit.backend.repositories.ProductTypeRepository;

import java.util.List;

@Service
public class ProductTypeService {
    @Autowired
    private ProductTypeRepository productTypeRepository;

//    public List<ProductType> productTypes = productTypeRepository.findAll();
}
