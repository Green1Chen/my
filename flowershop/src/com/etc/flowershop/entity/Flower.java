package com.etc.flowershop.entity;

 

import java.util.ArrayList;
import java.util.List;

public class Flower {
	private int f_id;
	private int t_id;
	private double price;
	private String f_name;
	private int count;
	private String pic;
	private FlowerType flowerType;
	private List<FDetail> details;
	private List<Car> cars;
	
	public Flower() {

	}

	public Flower(int f_id, double price, String f_name, 
			int count, String pic, FlowerType flowerType) {
		super();
		this.f_id = f_id;
		this.price = price;
		this.f_name = f_name;
		this.count = count;
		this.pic = pic;
		this.flowerType = flowerType;
		this.flowerType.getFlowers().add(this);
		details = new ArrayList<FDetail>();
		cars = new ArrayList<Car>();
	}

	public int getT_id() {
		return t_id;
	}

	public void setT_id(int t_id) {
		this.t_id = t_id;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public List<FDetail> getDetails() {
		return details;
	}

	public void setDetails(List<FDetail> details) {
		this.details = details;
	}

	public int getF_id() {
		return f_id;
	}

	public void setF_id(int f_id) {
		this.f_id = f_id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public FlowerType getFlowerType() {
		return flowerType;
	}

	public void setFlowerType(FlowerType flowerType) {
		this.flowerType = flowerType;
	}

}
