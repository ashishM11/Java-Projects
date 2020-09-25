package com.manosoft.managment.inventory.entites;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "tblBrand")
public class Brand implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long brandId;
	private String brandName;
	private String brandLogo;
}
