package com.project.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="tblcomplaint")
@DynamicUpdate
public class ComplaintsEM {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="complaintID")
	private int complaintId;
	
	@Column(name="complaintSubject",nullable=false)
	private String complaintSub;
	
	@Column(name="complaintMsg",nullable=false)
	private String complaintMsg;
	
	@Column(name="complaintDate")
	@Temporal(TemporalType.DATE)
	private Date complaintDate;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userId",nullable=false)
	private UsersEM user;
	
	/**
	 * @param complaintId
	 * @param complaintSub
	 * @param complaintMsg
	 * @param complaintDate
	 * @param user
	 */
	public ComplaintsEM(int complaintId, String complaintSub, String complaintMsg, Date complaintDate, UsersEM user) {
		this.complaintId = complaintId;
		this.complaintSub = complaintSub;
		this.complaintMsg = complaintMsg;
		this.complaintDate = complaintDate;
		this.user = user;
	}
	
	
	public ComplaintsEM(){}


	public int getComplaintId() {
		return complaintId;
	}


	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}


	public String getComplaintSub() {
		return complaintSub;
	}


	public void setComplaintSub(String complaintSub) {
		this.complaintSub = complaintSub;
	}


	public String getComplaintMsg() {
		return complaintMsg;
	}


	public void setComplaintMsg(String complaintMsg) {
		this.complaintMsg = complaintMsg;
	}


	public Date getComplaintDate() {
		return complaintDate;
	}


	public void setComplaintDate(Date complaintDate) {
		this.complaintDate = complaintDate;
	}


	public UsersEM getUser() {
		return user;
	}


	public void setUser(UsersEM user) {
		this.user = user;
	}
	
}
