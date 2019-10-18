package com.etc.flowershop.entity;

import java.util.ArrayList;
import java.util.List;

public class FlowerType {
	private int t_id;
	private String t_name;
	private List<Flower> flowers;
	
	public FlowerType() {
		
	}
	
	public FlowerType(int t_id, String t_name) {
		super();
		this.t_id = t_id;
		this.t_name = t_name;
		flowers = new ArrayList<Flower>();
	}
	
	public List<Flower> getFlowers() {
		return flowers;
	}

	public void setFlowers(List<Flower> flowers) {
		this.flowers = flowers;
	}

	public int getT_id() {
		return t_id;
	}

	public void setT_id(int t_id) {
		this.t_id = t_id;
	}

	public String getT_name() {
		return t_name;
	}

	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	
	
}
