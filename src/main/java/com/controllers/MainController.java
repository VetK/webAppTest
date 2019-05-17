package com.controllers;

import com.dao.impl.ProductDaoImpl;
import com.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private ProductDaoImpl productDaoImpl;

    @GetMapping("/")
    public String hello(Map<String, Object> model){
        return "hello";
    }

    @GetMapping("/admin")
    public String main(@RequestParam(value = "filter", required = false, defaultValue = "") String name, Model model){
        Iterable<Product> products;

        if(name!=null && !name.isEmpty()){
            products = productDaoImpl.getProductsFromTable(name);
        }
        else{
            products = productDaoImpl.getProductsFromTable("sortById");
        }

        model.addAttribute("products", products);
        model.addAttribute("filter", name);

        return "admin";
    }

    @PostMapping("/admin")
    public String add(Product product, Map<String, Object> model) throws IOException{
        productDaoImpl.saveProductInTable(product.getId(),product.getName(), product.getDescription(),
                product.getPrice(), product.getPic());
        Iterable<Product> products = productDaoImpl.getProductsFromTable("sortById");
        model.put("products", products);
        return "admin";
    }

    @PostMapping("/admin/{id}")
    public String updateProduct(@PathVariable Long id, Product product, Map<String, Object> model) throws IOException{
        productDaoImpl.updateProduct(id, product.getName(), product.getDescription(),
                product.getPrice(), product.getPic());
        return "redirect:/admin";
    }


}
