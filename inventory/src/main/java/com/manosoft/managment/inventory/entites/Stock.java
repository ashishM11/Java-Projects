package com.manosoft.managment.inventory.entites;

import java.time.LocalDate;

public class Stock {

	private Long stockId;
	private Product productObj;
	private Long stockQuantity;
	private Integer stockProductPrice;
	private Boolean stockProductAvailable;
	private LocalDate stockFillDate;

	public Stock() {

	}

	public Stock(Long stockId, Product productObj, Long stockQuantity, Integer stockProductPrice,
			Boolean stockProductAvailable, LocalDate stockFillDate) {
		super();
		this.stockId = stockId;
		this.productObj = productObj;
		this.stockQuantity = stockQuantity;
		this.stockProductPrice = stockProductPrice;
		this.stockProductAvailable = stockProductAvailable;
		this.stockFillDate = stockFillDate;
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public Product getProductObj() {
		return productObj;
	}

	public void setProductObj(Product productObj) {
		this.productObj = productObj;
	}

	public Long getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Long stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public Integer getStockProductPrice() {
		return stockProductPrice;
	}

	public void setStockProductPrice(Integer stockProductPrice) {
		this.stockProductPrice = stockProductPrice;
	}

	public Boolean getStockProductAvailable() {
		return stockProductAvailable;
	}

	public void setStockProductAvailable(Boolean stockProductAvailable) {
		this.stockProductAvailable = stockProductAvailable;
	}

	public LocalDate getStockFillDate() {
		return stockFillDate;
	}

	public void setStockFillDate(LocalDate stockFillDate) {
		this.stockFillDate = stockFillDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productObj == null) ? 0 : productObj.hashCode());
		result = prime * result + ((stockFillDate == null) ? 0 : stockFillDate.hashCode());
		result = prime * result + ((stockId == null) ? 0 : stockId.hashCode());
		result = prime * result + ((stockProductAvailable == null) ? 0 : stockProductAvailable.hashCode());
		result = prime * result + ((stockProductPrice == null) ? 0 : stockProductPrice.hashCode());
		result = prime * result + ((stockQuantity == null) ? 0 : stockQuantity.hashCode());
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
		Stock other = (Stock) obj;
		if (productObj == null) {
			if (other.productObj != null)
				return false;
		} else if (!productObj.equals(other.productObj))
			return false;
		if (stockFillDate == null) {
			if (other.stockFillDate != null)
				return false;
		} else if (!stockFillDate.equals(other.stockFillDate))
			return false;
		if (stockId == null) {
			if (other.stockId != null)
				return false;
		} else if (!stockId.equals(other.stockId))
			return false;
		if (stockProductAvailable == null) {
			if (other.stockProductAvailable != null)
				return false;
		} else if (!stockProductAvailable.equals(other.stockProductAvailable))
			return false;
		if (stockProductPrice == null) {
			if (other.stockProductPrice != null)
				return false;
		} else if (!stockProductPrice.equals(other.stockProductPrice))
			return false;
		if (stockQuantity == null) {
			if (other.stockQuantity != null)
				return false;
		} else if (!stockQuantity.equals(other.stockQuantity))
			return false;
		return true;
	}
	
	
}
