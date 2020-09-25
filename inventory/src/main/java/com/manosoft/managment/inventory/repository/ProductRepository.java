package com.manosoft.managment.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manosoft.managment.inventory.entites.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
