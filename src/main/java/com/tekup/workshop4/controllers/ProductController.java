package com.tekup.workshop4.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tekup.workshop4.models.Product;
import com.tekup.workshop4.models.reaquests.ProductForm;

import jakarta.validation.Valid;

@RequestMapping("/products")
@Controller
public class ProductController {

    private static List<Product> products = new ArrayList<Product>();
    private static Long idCount = 0L;
    static {
        products.add(new Product(++idCount, "SS-S9", "Samsung Galaxy S9", 500D, 50, "samsung-s9.png"));
        products.add(new Product(++idCount, "NK-5P", "Nokia 5.1 Plus", 60D, 60, null));
        products.add(new Product(++idCount, "IP-7", "iPhone 7", 600D, 30, "iphone-7.png"));
        }
    

@GetMapping()
public String getAllProducts(Model model){
    model.addAttribute("products", products);
    return "list";
}

@GetMapping("/create")
public String showAddProduct(Model model){
    model.addAttribute("productForm", new ProductForm());
    return "create";
}

@PostMapping("/create")
public String addProduct(@Valid @ModelAttribute("productForm") ProductForm productForm, BindingResult bindingResult){
    if(bindingResult.hasErrors()){
        return "create";
    }
    products.add(new Product(++idCount, productForm.getCode(), productForm.getName(), productForm.getPrice(), productForm.getQuantity(), null));

    return "redirect:/products";
}

@GetMapping("/{id}/edit")
public String showUpdateProduct(@PathVariable("id") Long id,Model model){
    Product productUpdate = this.findProductById(id);
    if(productUpdate!= null){
        model.addAttribute("productForm", new ProductForm(productUpdate.getCode(),productUpdate.getName(),productUpdate.getPrice(),productUpdate.getQuantity(),productUpdate.getImage()));
        model.addAttribute("idP", id);
    }
    return"update";
}

@PostMapping("/{id}/edit")
public String updateProduct(@PathVariable("id") String id,@Valid @ModelAttribute("productForm")ProductForm productForm, BindingResult bindingResult){
    if(bindingResult.hasErrors()){
        return"update";
    }
    for (Product p: products){
        if(p.getId()==Integer.parseInt(id)){
          p.setCode(productForm.getCode());  
          p.setName(productForm.getName());
          p.setPrice(productForm.getPrice());
          p.setQuantity(productForm.getQuantity());
          p.setImage(productForm.getImage());
          break;
        }
    }
    return"redirect:/products";

}
@PostMapping("/{id}/delete")
public String deleteProduct(@PathVariable("id")Long id ,Model model){
    Product productdelete = this.findProductById(id);
    if(productdelete!=null){
        this.products.remove(productdelete);
    }
    return "redirect:/list";

}

private Product findProductById(Long id) {
    for(Product product:products){
        if(product.getId().equals(id))
        return product;
    }
    return null;
}

}


  