package com.etc.flowershop.entity; 

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Forder {
	private int o_id;
	private Date o_date;
	private double price;
	private Tuser tuser;
	private List<FDetail> details;
	private int u_id;

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public Forder() {

	}
	
	public Forder(Date o_date, double price) {
		this.o_date = o_date;
		this.price = price;
		details = new ArrayList<FDetail>();
	}

	public Forder(int o_id, Date o_date, int price, Tuser tuser) {
		super();
		this.o_id = o_id;
		this.o_date = o_date;
		this.price = price;
		this.tuser = tuser;
		this.tuser.getForders().add(this);
		details = new ArrayList<FDetail>();
	}

	
	public Tuser getTuser() {
		return tuser;
	}

	public void setTuser(Tuser tuser) {
		this.tuser = tuser;
	}

	public int getO_id() {
		return o_id;
	}

	public void setO_id(int o_id) {
		this.o_id = o_id;
	}

	public Date getO_date() {
		return o_date;
	}

	public void setO_date(Date o_date) {
		this.o_date = o_date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<FDetail> getDetails() {
		return details;
	}

	public void setDetails(List<FDetail> details) {
		this.details = details;
	}

}
