package com.lti.fg.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="EMIHISTORY")
public class EmiHistory {

	@Id
	@Column(name="EMIHISTORYID")
	private int emiHistoryId;
	@Column(name="USERID")
	private int userId;
	@Column(name="ORDERID")
	private int orderId;
	@Column(name="EMIAMOUNT")
	private double emiAmount;
	@Column(name="DATEOFPAYMENT")
	private Date dateOfPayment;
	@Column(name="NEXTPAYDATE")
	private Date nextPayDate;
	
	public EmiHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmiHistory(int userId, int orderId, double emiAmount, Date nextPayDate) {
		super();
		this.userId = userId;
		this.orderId = orderId;
		this.emiAmount = emiAmount;
		this.nextPayDate = nextPayDate;
	}

	public int getEmiHistoryId() {
		return emiHistoryId;
	}

	public void setEmiHistoryId(int emiHistoryId) {
		this.emiHistoryId = emiHistoryId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getEmiAmount() {
		return emiAmount;
	}

	public void setEmiAmount(double emiAmount) {
		this.emiAmount = emiAmount;
	}

	public Date getDateOfPayment() {
		return dateOfPayment;
	}

	public void setDateOfPayment(Date dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}

	public Date getNextPayDate() {
		return nextPayDate;
	}

	public void setNextPayDate(Date nextPayDate) {
		this.nextPayDate = nextPayDate;
	}

	@Override
	public String toString() {
		return "EmiHistory [emiHistoryId=" + emiHistoryId + ", userId=" + userId + ", orderId=" + orderId
				+ ", emiAmount=" + emiAmount + ", dateOfPayment=" + dateOfPayment + ", nextPayDate=" + nextPayDate
				+ "]";
	}
	
	
}
