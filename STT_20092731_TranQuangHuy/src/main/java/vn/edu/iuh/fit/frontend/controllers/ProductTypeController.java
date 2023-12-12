package vn.edu.iuh.fit.frontend.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.backend.models.ProductType;
import vn.edu.iuh.fit.backend.repositories.ProductTypeRepository;

import java.util.List;

@Controller
@RequestMapping("/")
public class ProductTypeController {
    @Autowired
    private ProductTypeRepository productTypeRepository;

    @GetMapping({"/","/product_types"})
    public ModelAndView showListProductType(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        List<ProductType> productTypeList = productTypeRepository.findAll();

        modelAndView.addObject("productTypeList", productTypeList);
        modelAndView.setViewName("product_types/listing");
        return modelAndView;
    }

}
