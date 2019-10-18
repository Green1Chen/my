package com.etc.flowershop.entity;

public class FDetail {
	private int d_id;
	private int account;
	private double price;
	private int f_id;
	private Forder forder;
	private Flower flower;
	
	public FDetail() {
		
	}
	
	public FDetail(int account, double price, Forder forder,int f_id) {
		this.account = account;
		this.price = price;
		this.forder = forder;
		this.f_id=f_id;
	}

	public int getF_id() {
		return f_id;
	}

	public void setF_id(int f_id) {
		this.f_id = f_id;
	}

	public FDetail(int d_id, int account, double price, Forder forder, Flower flower) {
		super();
		this.d_id = d_id;
		this.account = account;
		this.price = price;
		this.forder = forder;
		this.flower = flower;
		this.forder.getDetails().add(this);
		this.flower.getDetails().add(this);
	}
	public int getD_id() {
		return d_id;
	}
	public void setD_id(int d_id) {
		this.d_id = d_id;
	}
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Forder getForder() {
		return forder;
	}
	public void setForder(Forder forder) {
		this.forder = forder;
	}
	public Flower getFlower() {
		return flower;
	}
	public void setFlower(Flower flower) {
		this.flower = flower;
	}
}
