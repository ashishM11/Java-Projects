package com.project.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="tblcab")
@DynamicUpdate
public class CabsEM {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cabID",nullable=false)
	private int cabId;
	
	@Column(name="cabRegisterNo",nullable=false)
	private String cabRegNo;
	
	@Column(name="cabBrand",nullable=false)
	private String cabBrand;
	
	@Column(name="cabType",nullable=false)
	private String cabType;
	
	@Column(name="cabColor",nullable=false)
	private String cabColor;
	
	@Column(name="isCabInsured",nullable=false)
	private String isCabInsured;
	
	@Column(name="cabRegisterDate",nullable=false)
	@Temporal(TemporalType.DATE)
	private Date cabRegisterDate;
	
	@Column(name="isActive",nullable=false)
	private String isCabActive;
	
	@Column(name="isAllocated",nullable=false)
	private String isAllocated;
	
	public int getCabId() {
		return cabId;
	}
	public void setCabId(int cabId) {
		this.cabId = cabId;
	}
	public String getCabRegNo() {
		return cabRegNo;
	}
	public void setCabRegNo(String cabRegNo) {
		this.cabRegNo = cabRegNo;
	}
	public String getCabBrand() {
		return cabBrand;
	}
	public void setCabBrand(String cabBrand) {
		this.cabBrand = cabBrand;
	}
	public String getCabType() {
		return cabType;
	}
	public void setCabType(String cabType) {
		this.cabType = cabType;
	}
	public String getCabColor() {
		return cabColor;
	}
	public void setCabColor(String cabColor) {
		this.cabColor = cabColor;
	}
	public String isCabInsured() {
		return isCabInsured;
	}
	public void setCabInsured(String isCabInsured) {
		this.isCabInsured = isCabInsured;
	}
	public Date getCabRegisterDate() {
		return cabRegisterDate;
	}
	public void setCabRegisterDate(Date cabRegisterDate) {
		this.cabRegisterDate = cabRegisterDate;
	}
	
	public String getIsCabInsured() {
		return isCabInsured;
	}
	public void setIsCabInsured(String isCabInsured) {
		this.isCabInsured = isCabInsured;
	}
	public String getIsCabActive() {
		return isCabActive;
	}
	public void setIsCabActive(String isCabActive) {
		this.isCabActive = isCabActive;
	}
	public String getIsAllocated() {
		return isAllocated;
	}
	public void setIsAllocated(String isAllocated) {
		this.isAllocated = isAllocated;
	}
	public CabsEM(){}
	
	public CabsEM(int cabId, String cabRegNo, String cabBrand, String cabType, String cabColor, String isCabInsured,
			Date cabRegisterDate, String isCabActive) {
		super();
		this.cabId = cabId;
		this.cabRegNo = cabRegNo;
		this.cabBrand = cabBrand;
		this.cabType = cabType;
		this.cabColor = cabColor;
		this.isCabInsured = isCabInsured;
		this.cabRegisterDate = cabRegisterDate;
		this.isCabActive = isCabActive;
	}
}
