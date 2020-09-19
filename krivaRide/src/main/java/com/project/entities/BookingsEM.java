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

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "tblBookingInfo")
@DynamicUpdate
public class BookingsEM {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookingId")
	private int bookingId;

	@Column(name = "bookingSource", nullable = false)
	private String source;

	@Column(name = "bookingDestination", nullable = false)
	private String destination;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", nullable = false)
	private UsersEM user;

	@Column(name = "bookingForWhen", nullable = false)
	private String strWhen;

	@Column(name = "bookingDate", nullable = false)
	private String cabDate;

	@Column(name = "bookingTime", nullable = false)
	private String cabTime;

	@Column(name = "bookingEntryCreation", nullable = false, unique = true)
	private Date bookingEntryCreation;

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public Date getBookingEntryCreation() {
		return bookingEntryCreation;
	}

	public void setBookingEntryCreation(Date bookingEntryCreation) {
		this.bookingEntryCreation = bookingEntryCreation;
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public UsersEM getUser() {
		return user;
	}

	public void setUser(UsersEM user) {
		this.user = user;
	}

	public String getStrWhen() {
		return strWhen;
	}

	public void setStrWhen(String strWhen) {
		this.strWhen = strWhen;
	}

	public String getCabDate() {
		return cabDate;
	}

	public void setCabDate(String cabDate) {
		this.cabDate = cabDate;
	}

	public String getCabTime() {
		return cabTime;
	}

	public void setCabTime(String cabTime) {
		this.cabTime = cabTime;
	}

	public BookingsEM() {
		super();
	}

	public BookingsEM(int bookingId, String source, String destination, UsersEM user, String strWhen, String cabDate,
			String cabTime, Date bookingEntryCreation) {
		super();
		this.bookingId = bookingId;
		this.source = source;
		this.destination = destination;
		this.user = user;
		this.strWhen = strWhen;
		this.cabDate = cabDate;
		this.cabTime = cabTime;
		this.bookingEntryCreation = bookingEntryCreation;
	}

	
	
}
