package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("")
    public String advShop(Model model){
        return "advShop";
    }

    @GetMapping("/product/create")
    public String createProductPage(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/product/create")
    public String createProductPost(@ModelAttribute Product product, Model model){
        String id = service.generateId();
        product.setProductId(id);
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/product/edit")
    public String editProductPage(Model model){
        Product product = new Product();
        Product dummyProduct = new Product();
        dummyProduct.setProductId("null");
        dummyProduct.setProductName("-");
        List<Product> products = service.findAll();
        products.add(dummyProduct);
        model.addAttribute("product", product);
        model.addAttribute("products", products);
        return "editProduct";
    }

    @PostMapping("/product/edit")
    public String editProductPost(@ModelAttribute Product product, Model model){
        service.edit(product);
        return "redirect:list";
    }

    @GetMapping("/product/delete")
    public String deleteProductPage(Model model){
        Product product = new Product();
        Product dummyProduct = new Product();
        dummyProduct.setProductId("null");
        dummyProduct.setProductName("-");
        List<Product> products = service.findAll();
        products.add(dummyProduct);
        model.addAttribute("product", product);
        model.addAttribute("products", products);
        return "deleteProduct";
    }

    @PostMapping("/product/delete")
    public String deleteProductPost(@ModelAttribute Product product, Model model){
        service.delete(product);
        return "redirect:list";
    }

    @GetMapping("/product/list")
    public String productListPage(Model model){
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productList";
    }

}
