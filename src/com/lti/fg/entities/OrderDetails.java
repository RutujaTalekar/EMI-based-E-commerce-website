package com.lti.fg.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "ORDERDETAILS")
public class OrderDetails 
{
	@Id
	@Column(name="ORDERID")
	private int orderId;
	@Column(name="USERID")
	private int userId;
	@Column(name="EMITENURE")
	private int emiTenure;
	@Column(name="MONTHLYEMI")
	private double monthlyEmi;
	@Column(name="ORDERDATE")
	private Date orderDate;
	@Column(name="NUMBEROFEMIPAID")
	private int numberOfEmiPaid; 
	
	public OrderDetails() {
		super();
	}

	

	public OrderDetails(int userId, int emiTenure, double monthlyEmi, Date orderDate, int numberOfEmiPaid) {
		super();
		this.userId = userId;
		this.emiTenure = emiTenure;
		this.monthlyEmi = monthlyEmi;
		this.orderDate = orderDate;
		this.numberOfEmiPaid = numberOfEmiPaid;
	}



	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCartId() {
		return userId;
	}

	public void setCartId(int cartId) {
		this.userId = cartId;
	}

	public int getEmiTenure() {
		return emiTenure;
	}

	public void setEmiTenure(int emiTenure) {
		this.emiTenure = emiTenure;
	}

	public double getMonthlyEmi() {
		return monthlyEmi;
	}

	public void setMonthlyEmi(double monthlyEmi) {
		this.monthlyEmi = monthlyEmi;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	
	
	public int getNumberOfEmiPaid() {
		return numberOfEmiPaid;
	}



	public void setNumberOfEmiPaid(int numberOfEmiPaid) {
		this.numberOfEmiPaid = numberOfEmiPaid;
	}



	@Override
	public String toString() {
		return "OrderDetails [orderId=" + orderId + ", cartId=" + userId + ", emiTenure=" + emiTenure + ", monthlyEmi="
				+ monthlyEmi + ", orderDate=" + orderDate + "]";
	}
}
