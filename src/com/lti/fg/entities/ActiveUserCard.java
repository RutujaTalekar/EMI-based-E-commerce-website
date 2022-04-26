package com.lti.fg.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ACTIVEUSERCARD")
public class ActiveUserCard {

	@Id
	@Column(name="CARDID")
	private long cardId;
	@Column(name="USERID")
	private int userId;
	@Column(name="USERSTATUS")
	private String userStatus;
	@Column(name="CARDVALIDITY")
	private Date cardValidity;
	@Column(name="CARDBALANCE")
	private double cardBalance;
	@Column(name="CARDTYPE")
	private String cardType;
	@Column(name="CARDSTATUS")
	private String cardStatus;
	@Column(name="LINKTOPDF")
	private String linkToPdf;
	
	public ActiveUserCard() 
	{
		super();
	}

	

	public ActiveUserCard(int userId, String userStatus, Date cardValidity, double cardBalance, String cardType,
			String cardStatus) {
		super();
		this.userId = userId;
		this.userStatus = userStatus;
		this.cardValidity = cardValidity;
		this.cardBalance = cardBalance;
		this.cardType = cardType;
		this.cardStatus = cardStatus;
	}



	public long getCardId() {
		return cardId;
	}

	public void setCardId(long cardId) {
		this.cardId = cardId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}

	public Date getCardValidity() {
		return cardValidity;
	}

	public void setCardValidity(Date cardValidity) {
		this.cardValidity = cardValidity;
	}

	public double getCardBalance() {
		return cardBalance;
	}

	public void setCardBalance(double cardBalance) {
		this.cardBalance = cardBalance;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	

	public String getUserStatus() {
		return userStatus;
	}



	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	

	public String getLinkToPdf() {
		return linkToPdf;
	}



	public void setLinkToPdf(String linkToPdf) {
		this.linkToPdf = linkToPdf;
	}



	@Override
	public String toString() {
		return "ActiveUserCard [userId=" + userId + ", userStatus=" + userStatus + ", cardValidity=" + cardValidity
				+ ", cardBalance=" + cardBalance + ", cardType=" + cardType + ", cardStatus=" + cardStatus + "]";
	}



	
	
}
