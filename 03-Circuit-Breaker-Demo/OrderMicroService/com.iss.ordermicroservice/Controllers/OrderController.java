package com.iss.ordermicroservice.Controllers;

import com.iss.ordermicroservice.Models.ProductDTO;
import com.iss.ordermicroservice.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable int id) {
        return orderService.getProductById(id);
    }
    @GetMapping("/list")
    public List<ProductDTO> getProducts() {
        return orderService.getProduct();
    }
}
