package com.etc.flowershop.entity;



public class Car {
	private int c_id;
	private Tuser tuser;
	private Flower flower;

	public Car() {

	}

	public Car(int c_id, Tuser tuser, Flower flower) {
		super();
		this.c_id = c_id;
		this.tuser = tuser;
		this.flower = flower;
		this.tuser.getCars().add(this);
		this.flower.getCars().add(this);
	}

	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
	}

	public Tuser getTuser() {
		return tuser;
	}

	public void setTuser(Tuser tuser) {
		this.tuser = tuser;
	}

	public Flower getFlower() {
		return flower;
	}

	public void setFlower(Flower flower) {
		this.flower = flower;
	}
}
