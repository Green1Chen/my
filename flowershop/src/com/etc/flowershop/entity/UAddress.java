package com.etc.flowershop.entity;

public class UAddress {
	private int a_id;
	private String adress;
	private Tuser tuser;
	
	public UAddress() {
		
	}
	
	public UAddress(int a_id, String adress, Tuser tuser) {
		super();
		this.a_id = a_id;
		this.adress = adress;
		this.tuser = tuser;
		//this.tuser.getUaddress().add(this);
	}
	public int getA_id() {
		return a_id;
	}
	public void setA_id(int a_id) {
		this.a_id = a_id;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public Tuser getTuser() {
		return tuser;
	}
	public void setTuser(Tuser tuser) {
		this.tuser = tuser;
	}
	
	
}
