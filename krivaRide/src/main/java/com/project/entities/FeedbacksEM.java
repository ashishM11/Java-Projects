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
@Table(name="tblfeedback")
@DynamicUpdate
public class FeedbacksEM {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="feedBkID")
	private int feedbackID;
	@Column(name="feedBkSubject",nullable=false)
	private String feedbackSub;
	@Column(name="feedBkMsg",nullable=false)
	private String feedbackMsg;
	@Column(name="feedBkDate")
	@Temporal(TemporalType.DATE)
	private Date feedbackDate;
	@Column(name="feedBkRating",nullable=false)
	private int feedbackRating;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userId",nullable=false)
	private UsersEM user;

	@Column(name="displayAtIndex")
	private int feedbackVisibility; 
	
	public FeedbacksEM() {
	}

	/**
	 * @param feedbackID
	 * @param feedbackSub
	 * @param feedbackMsg
	 * @param feedbackDate
	 * @param feedbackRating
	 * @param user
	 */
	public FeedbacksEM(int feedbackID, String feedbackSub, String feedbackMsg, Date feedbackDate, short feedbackRating,
			UsersEM user,short feedbackVisibility) {
		this.feedbackID = feedbackID;
		this.feedbackSub = feedbackSub;
		this.feedbackMsg = feedbackMsg;
		this.feedbackDate = feedbackDate;
		this.feedbackRating = feedbackRating;
		this.user = user;
		this.feedbackVisibility = feedbackVisibility;
	}

	public int getFeedbackID() {
		return feedbackID;
	}

	public void setFeedbackID(int feedbackID) {
		this.feedbackID = feedbackID;
	}

	public String getFeedbackSub() {
		return feedbackSub;
	}

	public void setFeedbackSub(String feedbackSub) {
		this.feedbackSub = feedbackSub;
	}

	public String getFeedbackMsg() {
		return feedbackMsg;
	}

	public void setFeedbackMsg(String feedbackMsg) {
		this.feedbackMsg = feedbackMsg;
	}

	public Date getFeedbackDate() {
		return feedbackDate;
	}

	public void setFeedbackDate(Date feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

	public int getFeedbackRating() {
		return feedbackRating;
	}

	public void setFeedbackRating(int feedbackRating) {
		this.feedbackRating = feedbackRating;
	}

	public UsersEM getUser() {
		return user;
	}

	public void setUser(UsersEM user) {
		this.user = user;
	}

	public int getFeedbackVisibility() {
		return feedbackVisibility;
	}

	public void setFeedbackVisibility(int feedbackVisibility) {
		this.feedbackVisibility = feedbackVisibility;
	}
}
