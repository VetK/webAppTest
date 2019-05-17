package com.controllers;

import com.dao.impl.ProductDaoImpl;
import com.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class ListController {

    @Autowired
    private ProductDaoImpl productDaoImpl;

    @GetMapping("/list")
    public String getAllProducts(Map<String, Object> model){
        Iterable<Product> products = productDaoImpl.getProductsFromTable("sortById");
        model.put("products", products);
        return "list";
    }

    @PostMapping("/list")
    public String sort(@RequestParam("sortByPrice") String sortByPrice,
                       Map<String, Object> model){
        List<Product> newProductsList = productDaoImpl.getProductsFromTable(sortByPrice);
        model.put("products", newProductsList);
        return "/list";
    }
}
