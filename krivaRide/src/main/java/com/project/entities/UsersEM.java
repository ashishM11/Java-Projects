package com.project.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "tblUser")
@DynamicUpdate
public class UsersEM {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private int userId;

	@Column(name = "userFname", length = 20, nullable = false)
	private String userFname;

	@Column(name = "userLname", length = 20, nullable = false)
	private String userLname;

	@Column(name = "userMailId", length = 38, unique = true, nullable = false)
	private String userMailId;

	@Column(name = "userMobileNo", length = 10, unique = true, nullable = false)
	private String userMobileNo;

	@Column(name = "userType", length = 10)
	private String userType;

	@Column(name = "userCreationDT", unique = true, nullable = true)
	private Date userCreationDT;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	List<FeedbacksEM> feedbackList;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	List<ComplaintsEM> complaintList;

	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserFname() {
		return userFname;
	}

	public void setUserFname(String userFname) {
		this.userFname = userFname;
	}

	public String getUserLname() {
		return userLname;
	}

	public void setUserLname(String userLname) {
		this.userLname = userLname;
	}

	public String getUserMailId() {
		return userMailId;
	}

	public void setUserMailId(String userMailId) {
		this.userMailId = userMailId;
	}

	public String getUserMobileNo() {
		return userMobileNo;
	}

	public void setUserMobileNo(String userMobileNo) {
		this.userMobileNo = userMobileNo;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Date getUserCreationDT() {
		return userCreationDT;
	}

	public void setUserCreationDT(Date userCreationDT) {
		this.userCreationDT = userCreationDT;
	}
	
	public UsersEM(String userFname, String userLname, String userMailId, String userMobileNo, Date userCreationDT) {
		super();
		this.userFname = userFname;
		this.userLname = userLname;
		this.userMailId = userMailId;
		this.userMobileNo = userMobileNo;
		this.userCreationDT = userCreationDT;

	}

	public UsersEM() {
	}

}
