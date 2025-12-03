package com.waterpocket.backend.controller;

import com.waterpocket.backend.model.Product;
import com.waterpocket.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired private ProductRepository repo;

    @GetMapping
    public List<Product> getAll() { return repo.findAll(); }

    @PostMapping
    public Product add(@RequestBody Product p) { return repo.save(p); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) { repo.deleteById(id); }
    
    @PutMapping("/{id}")
    public Product update(@PathVariable String id, @RequestBody Product p) {
        p.setId(id);
        return repo.save(p);
    }
}