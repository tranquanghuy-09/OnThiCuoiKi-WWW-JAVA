package vn.edu.iuh.fit.frontend.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.backend.enums.ProductStatus;
import vn.edu.iuh.fit.backend.models.Product;
import vn.edu.iuh.fit.backend.models.ProductType;
import vn.edu.iuh.fit.backend.repositories.ProductRepository;
import vn.edu.iuh.fit.backend.repositories.ProductTypeRepository;
import vn.edu.iuh.fit.backend.services.ProductService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.time.format.DateTimeFormatter;

//@Validated
@Controller
@RequestMapping("")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductTypeRepository productTypeRepository;
    @Autowired
    private ProductService productService;

    @GetMapping("/index")
    public ModelAndView showCategory(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        ModelAndView modelAndView = new ModelAndView();
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(3), Sort.by("id"));
        Page<ProductType> productTypePage = productTypeRepository.findAll(pageable);

        int length = productTypeRepository.findAll().size();
        List<Integer> pages = Arrays.stream(IntStream.range(0, length % size.orElse(3) == 0 ? length/size.orElse(3) : length/size.orElse(3) + 1).toArray())
                .boxed().toList();

        modelAndView.addObject("productTypePage", productTypePage);
        modelAndView.addObject("pages", pages);
        modelAndView.addObject("curPage", page.orElse(0)+1);

        modelAndView.setViewName("others/listing-pageable");
        return modelAndView;
    }


    @GetMapping("/products")
    public ModelAndView showProductByProductTypeId(@RequestParam("product_type_id") long productTypeId, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        List<Product> list = productService.getProductByProductType_Id(productTypeId);

        session.setAttribute("productTypeId", productTypeId);

        modelAndView.addObject("list", list);

        modelAndView.setViewName("products/listing");
        return modelAndView;
    }

    @GetMapping("/products/show-add-form")
    public ModelAndView showAddForm(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Product product = new Product();
        long proTypeId = 0;
        proTypeId = (long) session.getAttribute("productTypeId");
        session.setAttribute("productTypeId", proTypeId);

        modelAndView.addObject("product", product);
        modelAndView.setViewName("products/add-form");
        return modelAndView;
    }

    @GetMapping("products/show-update-form")
    public ModelAndView showUpdateForm(@RequestParam("product_id") long productId, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Product product = productRepository.findById(productId).get();
        List<ProductType> productTypeList = productTypeRepository.findAll();
        long proTypeId = 0;
        proTypeId = (long) session.getAttribute("productTypeId");
        session.setAttribute("productTypeId", proTypeId);

        LocalDateTime inputTime = product.getInputTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String formattedInputTime = inputTime.format(formatter);

        modelAndView.addObject("localDateTime", formattedInputTime);
        modelAndView.addObject("productTypeList",productTypeList);
        modelAndView.addObject("productStatuses", ProductStatus.values());
        modelAndView.addObject("product", product);
        modelAndView.setViewName("products/update");

        return modelAndView;
    }


    @Transactional
    @PostMapping("/products/add")
//    public ModelAndView addProduct(@Validated @ModelAttribute("product") Product product, HttpSession session, BindingResult bindingResult){
    public ModelAndView addProduct(@ModelAttribute("product") Product product, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();

//        if (bindingResult.hasErrors()) {
//            modelAndView.setViewName("products/add-form");
//            return modelAndView;
//        }

        long proTypeId = 0;
        Object obj_typeId = session.getAttribute("productTypeId");
        if (obj_typeId == null){
            modelAndView.setViewName("product_types/listing");
        }else{
            proTypeId = (long) obj_typeId;
            product.setProductType(productTypeRepository.findById(proTypeId).get());
            productRepository.save(product);
            modelAndView.setViewName("redirect:/products?product_type_id="+proTypeId);
        }

        return modelAndView;
    }

    @Transactional
    @PostMapping("/products/update")
    public ModelAndView updateProduct(@ModelAttribute("product") Product product){
        ModelAndView modelAndView = new ModelAndView();

        productRepository.save(product);
        modelAndView.setViewName("redirect:/products?product_type_id="+product.getProductType().getId());

        return modelAndView;
    }

    @Transactional
    @GetMapping("/products/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") long productId, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();

        long proTypeId = 0;
        Object obj_typeId = session.getAttribute("productTypeId");
        if (obj_typeId==null){

        }else {
            proTypeId = (long) obj_typeId;
            productService.deleteProduct(productId);
            modelAndView.setViewName("redirect:/products?product_type_id="+proTypeId);
        }
        return modelAndView;
    }
}
