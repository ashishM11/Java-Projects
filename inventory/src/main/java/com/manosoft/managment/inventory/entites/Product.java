package com.manosoft.managment.inventory.entites;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

	@Id
	@GeneratedValue()
	private Long productId;
	private String productName;
	private String productDescription;
	private Brand brandObj;
	private String productType;
	private LocalDate productReleaseDate;
	private LocalDate productTS;

	public Product() {

	}

	public Product(Long productId, String productName, String productDescription, Brand brandObj, int productPrice,
			String productType, LocalDate productReleaseDate, LocalDate productTS) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.brandObj = brandObj;
		this.setProductType(productType);
		this.productReleaseDate = productReleaseDate;
		this.productTS = productTS;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Brand getBrandObj() {
		return brandObj;
	}

	public void setBrandObj(Brand brandObj) {
		this.brandObj = brandObj;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public LocalDate getProductReleaseDate() {
		return productReleaseDate;
	}

	public void setProductReleaseDate(LocalDate productReleaseDate) {
		this.productReleaseDate = productReleaseDate;
	}

	public LocalDate getProductTS() {
		return productTS;
	}

	public void setProductTS(LocalDate productTS) {
		this.productTS = productTS;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brandObj == null) ? 0 : brandObj.hashCode());
		result = prime * result + ((productDescription == null) ? 0 : productDescription.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + ((productType == null) ? 0 : productType.hashCode());
		result = prime * result + ((productReleaseDate == null) ? 0 : productReleaseDate.hashCode());
		result = prime * result + ((productTS == null) ? 0 : productTS.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (brandObj == null) {
			if (other.brandObj != null)
				return false;
		} else if (!brandObj.equals(other.brandObj))
			return false;
		if (productDescription == null) {
			if (other.productDescription != null)
				return false;
		} else if (!productDescription.equals(other.productDescription))
			return false;
		if (productType == null) {
			if (other.productType != null)
				return false;
		} else if (!productType.equals(other.productType))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (productReleaseDate == null) {
			if (other.productReleaseDate != null)
				return false;
		} else if (!productReleaseDate.equals(other.productReleaseDate))
			return false;
		if (productTS == null) {
			if (other.productTS != null)
				return false;
		} else if (!productTS.equals(other.productTS))
			return false;
		return true;
	}
}
