package com.etc.flowershop.entity;

import java.util.List;

public class Tuser {
	  private int u_id   ;              
	  private String u_name     ;           
	  private String  email   ;              
	  private String pwd    ;                
	  private String phone  ;               
	  private int account    ;
	  private List<UAddress> uaddress;
	  private List<Car> cars;
	  private List<Forder> forders;
	  private String role;
	  
	public List<UAddress> getUaddress() {
		return uaddress;
	}
	public void setUaddress(List<UAddress> uaddress) {
		this.uaddress = uaddress;
	}
	public List<Car> getCars() {
		return cars;
	}
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	public List<Forder> getForders() {
		return forders;
	}
	public void setForders(List<Forder> forders) {
		this.forders = forders;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}  
	  
}
