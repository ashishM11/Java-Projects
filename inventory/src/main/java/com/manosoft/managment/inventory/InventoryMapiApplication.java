package com.manosoft.managment.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@ComponentScan
@SpringBootApplication
public class InventoryMapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryMapiApplication.class, args);
	}

}
