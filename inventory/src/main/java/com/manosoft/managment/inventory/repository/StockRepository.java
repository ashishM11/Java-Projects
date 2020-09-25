package com.manosoft.managment.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manosoft.managment.inventory.entites.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {

}
