package com.manosoft.managment.inventory.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manosoft.managment.inventory.entites.Stock;
import com.manosoft.managment.inventory.repository.StockRepository;


@RestController
@RequestMapping("/api/stocks")
public class StockController {

	@Autowired
	StockRepository StockRepo;
	
	@GetMapping("/")
	public List<Stock> getStockList(){
		return StockRepo.findAll();
	}
	
	@GetMapping("/Stock/{id}")
	public Stock getStock(@PathVariable String stockId) {
		return StockRepo.getOne(Long.parseLong(stockId));
		
	}
	
	@PostMapping("/")
	public Stock addNewStock(@RequestBody Stock stockObj) {
		return StockRepo.save(stockObj);
		
	}
}
