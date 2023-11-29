package com.tekup.workshop4.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tekup.workshop4.models.Product;


@Controller
public class ProductController {

    private static List<Product> products = new ArrayList<Product>();
    private static Long idCount = 0L;
@RequestMapping("/")
public String getProduct(Model model){
    products.add(new Product(++idCount, "SS-S9", "Samsung Galaxy S9", 500D, 50, "samsung-s9.png"));
    products.add(new Product(++idCount, "NK-5P", "Nokia 5.1 Plus", 60D, 60, null)); 
    products.add(new Product(++idCount, "IP-7", "iPhone 7", 600D, 30, "iphone-7.png"));
    model.addAttribute("products", products);
    return "list";
}

}


  