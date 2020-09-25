package com.manosoft.managment.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manosoft.managment.inventory.entites.Stock;

public interface StockRepository extends JpaRepository<Stock,Long> {

}
