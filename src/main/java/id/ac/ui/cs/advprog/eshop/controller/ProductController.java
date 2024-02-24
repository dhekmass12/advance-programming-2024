package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService service;

    private static final String RE_LIST = "redirect:list";
    private static final String PRODUCT = "product";
    private static final String PRODUCTS = "products";

    @GetMapping("/create")
    public String createProductPage(Model model){
        Product product = new Product();
        model.addAttribute(PRODUCT, product);
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model){
        String id = service.generateId();
        product.setProductId(id);
        service.create(product);
        return RE_LIST;
    }

    @GetMapping("/edit")
    public String editProductPage(Model model){
        Product product = new Product();
        Product dummyProduct = new Product();
        editAndDeleteProductPageHelper(product, dummyProduct, model);
        return "editProduct";
    }

    @PostMapping("/edit")
    public String editProductPost(@ModelAttribute Product product, Model model){
        service.edit(product);
        return RE_LIST;
    }

    @GetMapping("/delete")
    public String deleteProductPage(Model model){
        Product product = new Product();
        Product dummyProduct = new Product();
        editAndDeleteProductPageHelper(product, dummyProduct, model);
        return "deleteProduct";
    }

    @PostMapping("/delete")
    public String deleteProductPost(@ModelAttribute Product product, Model model){
        service.delete(product);
        return RE_LIST;
    }

    @GetMapping("/list")
    public String productListPage(Model model){
        List<Product> allProducts = service.findAll();
        model.addAttribute(PRODUCTS, allProducts);
        return "productList";
    }

    private void editAndDeleteProductPageHelper(Product product, Product dummyProduct, Model model){
        dummyProduct.setProductId("null");
        dummyProduct.setProductName("-");
        List<Product> products = service.findAll();
        products.add(dummyProduct);
        model.addAttribute(PRODUCT, product);
        model.addAttribute(PRODUCTS, products);
    }
}