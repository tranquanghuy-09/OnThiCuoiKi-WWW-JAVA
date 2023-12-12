package vn.edu.iuh.fit.frontend.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.backend.models.Product;
import vn.edu.iuh.fit.backend.models.ProductType;
import vn.edu.iuh.fit.backend.repositories.ProductRepository;
import vn.edu.iuh.fit.backend.repositories.ProductTypeRepository;
import vn.edu.iuh.fit.backend.services.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class StatisticController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTypeRepository productTypeRepository;

    @GetMapping("/statistics/product_type")
    public ModelAndView showProductTypeStatistics(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        List<ProductType> productTypeList = productTypeRepository.findAll();
        List<Product> productList = new ArrayList<>();

        session.setAttribute("productTypeList", productTypeList);
        modelAndView.addObject("productTypeList", productTypeList);
        modelAndView.addObject("productList", productList);
        modelAndView.setViewName("statistics/statistic-product");
        return modelAndView;
    }

    @GetMapping("/statistics/product_types")
    public ModelAndView getProductByProductType(@RequestParam("product_type_id") long proTypeId, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        List<Product> list = productService.getProductByProductType_Id(proTypeId);
        List<ProductType> productTypeList = new ArrayList<>();

        Object obj_productTypeList = session.getAttribute("productTypeList");
        if (obj_productTypeList!=null){
            productTypeList = (List<ProductType>) obj_productTypeList;
        }
        modelAndView.addObject("productTypeList", productTypeList);
        modelAndView.addObject("productTypeId", proTypeId);
        modelAndView.addObject("listProduct", list);
        modelAndView.setViewName("statistics/statistic-product");

        return modelAndView;
    }
}
