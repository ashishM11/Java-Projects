package com.manosoft.managment.inventory.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manosoft.managment.inventory.entites.Product;
import com.manosoft.managment.inventory.repository.ProductRepository;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	ProductRepository productRepo;
	
	@GetMapping("/")
	public List<Product> getProductList(){
		return null;
	}
	
	@GetMapping("/product/{id}")
	public Product getProduct(@PathVariable String productId) {
		return null;
		
	}
	
	@PostMapping("/")
	public Product addNewProduct(@RequestBody Product productObj) {
		return null;
		
	}

}
