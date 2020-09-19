package com.project.entities;

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
@Table(name="tblPassword")
@DynamicUpdate
public class PasswordEM {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pwdId")
	private int pwdId;
	
	@Column(name="pwdChar",nullable=false)
	private String pwdChar;
	
	@Column(name="usrPwd",nullable=false)
	private String usrPwd;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userId")
	private UsersEM user;
	
	public String getPwdChar() {
		return pwdChar;
	}
	public void setPwdChar(String pwdChar) {
		this.pwdChar = pwdChar;
	}
	public int getPwdId() {
		return pwdId;
	}
	public void setPwdId(int pwdId) {
		this.pwdId = pwdId;
	}
	public String getUsrPwd() {
		return usrPwd;
	}
	public void setUsrPwd(String usrPwd) {
		this.usrPwd = usrPwd;
	}
	public UsersEM getUser() {
		return user;
	}
	public void setUser(UsersEM user) {
		this.user = user;
	}
	
}
