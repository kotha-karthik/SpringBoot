package com.iss.productmicroservice.Services;

import com.iss.productmicroservice.Models.Product;
import com.iss.productmicroservice.Repositories.ProductRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
    @PostConstruct
    public void initDatabase() {
        productRepo.save(Product.builder().pid(1).pname("apple").price(1236.980).build());
        productRepo.save(Product.builder().pid(2).pname("samsung").price(23690.980).build());
        productRepo.save(Product.builder().pid(3).pname("lenovo").price(2035.980).build());
        productRepo.save(Product.builder().pid(4).pname("hp").price(1999.023).build());
        productRepo.save(Product.builder().pid(5).pname("microsoft").price(123600.00).build());
        productRepo.save(Product.builder().pid(6).pname("dell").price(13600.980).build());

        System.out.println("Data successfully saved to H2 Database!");
    }
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(int id) {
        return productRepo.findById(id).get();
    }
}
