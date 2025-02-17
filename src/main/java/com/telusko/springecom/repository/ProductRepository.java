package com.telusko.springecom.repository;

import com.telusko.springecom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

//    We can here use DSL
//    List<Product> findByName(String keyword);

//    Or we can use quey
@Query("SELECT p from Product p WHERE " +
        "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
        "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
        "LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
        "LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
  List<Product> searchProduct(String keyword);

}
