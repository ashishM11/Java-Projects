package com.manosoft.managment.inventory.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manosoft.managment.inventory.entites.Product;

@RestController
public class ProductController {

	public ProductController() {

	}

	@GetMapping("/productTest")
	public String testProductAPI() {
		return "Product API is getting hit";
	}
	
	@GetMapping("/products")
	public List<Product> getProductList(){
		return null;
	}
	
	@PostMapping("/product")
	public Product addNewProduct(Product productObj) {
		return null;
		
	}

}
