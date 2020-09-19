package com.project.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="tblcabcaptain")
@DynamicUpdate
public class CabCaptainEM {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cabCaptainId")
	private int cabCaptID;
	
	@Column(name="cabCaptJoinDate",nullable=false)
	private Date cabCaptJoinDate;
	
	@Column(name="cabCaptQuitDate")
	private Date cabCaptQuitDate;
	
	@Column(name="cabCaptLevel")
	private String cabCaptLevel;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userId")
	private UsersEM user;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cabId")
	private CabsEM cab;

	public int getCabCaptID() {
		return cabCaptID;
	}

	public void setCabCaptID(int cabCaptID) {
		this.cabCaptID = cabCaptID;
	}

	public Date getCabCaptJoinDate() {
		return cabCaptJoinDate;
	}

	public void setCabCaptJoinDate(Date cabCaptJoinDate) {
		this.cabCaptJoinDate = cabCaptJoinDate;
	}

	public Date getCabCaptQuitDate() {
		return cabCaptQuitDate;
	}

	public void setCabCaptQuitDate(Date cabCaptQuitDate) {
		this.cabCaptQuitDate = cabCaptQuitDate;
	}

	public String getCabCaptLevel() {
		return cabCaptLevel;
	}

	public void setCabCaptLevel(String cabCaptLevel) {
		this.cabCaptLevel = cabCaptLevel;
	}

	public UsersEM getUser() {
		return user;
	}

	public void setUser(UsersEM user) {
		this.user = user;
	}

	public CabsEM getCab() {
		return cab;
	}

	public void setCab(CabsEM cab) {
		this.cab = cab;
	}

	public CabCaptainEM(Date cabCaptJoinDate, String cabCaptLevel, UsersEM user, CabsEM cab) {
		super();
		this.cabCaptJoinDate = cabCaptJoinDate;
		this.cabCaptLevel = cabCaptLevel;
		this.user = user;
		this.cab = cab;
	}
	
	
	public CabCaptainEM() {}
	
		
}
