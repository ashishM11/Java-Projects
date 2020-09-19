package com.project.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="tblconstants")
@DynamicUpdate
public class ConstantsEM {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="constId")
	private int constId;
	
	@Column(name="constCode",nullable=false,unique=true)
	private String constCode;
	
	@Column(name="constCodeDesc",nullable=false)
	private String constCodeDesc;
	
	@Column(name="constType",nullable=false)
	private String constType;
	
	@Column(name="constSubType")
	private String constSubType;
	
	public int getConstId() {
		return constId;
	}
	public void setConstId(int constId) {
		this.constId = constId;
	}
	public String getConstCode() {
		return constCode;
	}
	public void setConstCode(String constCode) {
		this.constCode = constCode;
	}
	public String getConstCodeDesc() {
		return constCodeDesc;
	}
	public void setConstCodeDesc(String constCodeDesc) {
		this.constCodeDesc = constCodeDesc;
	}
	public String getConstType() {
		return constType;
	}
	public void setConstType(String constType) {
		this.constType = constType;
	}
	public String getConstSubType() {
		return constSubType;
	}
	public void setConstSubType(String constSubType) {
		this.constSubType = constSubType;
	}
	
	public ConstantsEM(int constId, String constCode, String constCodeDesc, String constType, String constSubType) {
		super();
		this.constId = constId;
		this.constCode = constCode;
		this.constCodeDesc = constCodeDesc;
		this.constType = constType;
		this.constSubType = constSubType;
	}
	
	public ConstantsEM() {}
}
