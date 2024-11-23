package com.telusko.springecom.service;

import com.telusko.springecom.model.Product;
import com.telusko.springecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
    return productRepository.findAll();
    }

    public void load() {
        // ArrayList to store Product objects
        List<Product> products = new ArrayList<>(List.of(
                new Product(1, "Smartphone", "Latest smartphone with advanced features", "TechBrand", BigDecimal.valueOf(699.99), "Electronics", new Date(), true, 50),
                new Product(2, "Laptop", "Lightweight laptop for professionals", "CompuTech", BigDecimal.valueOf(1299.99), "Computers", new Date(), true, 30),
                new Product(3, "Headphones", "Noise-cancelling headphones", "Soundify", BigDecimal.valueOf(199.99), "Audio", new Date(), true, 100),
                new Product(4, "Smartwatch", "Track your fitness with style", "WearTech", BigDecimal.valueOf(249.99), "Wearables", new Date(), true, 75),
                new Product(5, "4K TV", "Ultra HD TV with vibrant colors", "ViewMore", BigDecimal.valueOf(999.99), "Home Appliances", new Date(), true, 20)
        ));

        // Save all products to the database
        productRepository.saveAll(products);
    }

    public Product addOrUpdateProduct(Product product , MultipartFile imageFile) throws IOException {
       product.setImageName(imageFile.getOriginalFilename());
       product.setImageType(imageFile.getContentType());
       product.setImageData(imageFile.getBytes());

        return productRepository.save(product);
    }

    public Product getProductBYId(int id) {
           return productRepository.findById(id).orElse(null);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public List<Product> searchProduct(String keyword) {
     return productRepository.searchProduct(keyword);
    }

//    public Product updateProduct(Product product, MultipartFile imageFile) throws IOException {
//        product.setImageName(imageFile.getOriginalFilename());
//        product.setImageType(imageFile.getContentType());
//        product.setImageData(imageFile.getBytes());
//
//        return productRepository.save(product);
//    }
}
