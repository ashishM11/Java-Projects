package com.manosoft.managment.inventory.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manosoft.managment.inventory.entites.Brand;
import com.manosoft.managment.inventory.repository.BrandRepository;


@RestController
@RequestMapping("/api/brands")
public class BrandController {
	
	@Autowired
	BrandRepository brandRepo;
	
	@GetMapping("/")
	public List<Brand> getbrandList(){
		return brandRepo.findAll();
	}
	
	@GetMapping("/brand/{id}")
	public Brand getbrand(@PathVariable String brandId) {
		return brandRepo.getOne(Long.parseLong(brandId));
		
	}
	
	@PostMapping("/")
	public Brand addNewbrand(@RequestBody Brand brandObj) {
		return brandRepo.save(brandObj);
		
	}

}
