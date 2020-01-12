package com.logistics.pojo;

public class AdminUser {
	private int id;
	private String a_username;
	private String a_password;
	private String a_phone;
	private String a_email;
	private String a_date;
	private String a_state;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getA_username() {
		return a_username;
	}
	public void setA_username(String a_username) {
		this.a_username = a_username;
	}
	public String getA_password() {
		return a_password;
	}
	public void setA_password(String a_password) {
		this.a_password = a_password;
	}
	public String getA_phone() {
		return a_phone;
	}
	public void setA_phone(String a_phone) {
		this.a_phone = a_phone;
	}
	public String getA_email() {
		return a_email;
	}
	public void setA_email(String a_email) {
		this.a_email = a_email;
	}
	public String getA_date() {
		return a_date;
	}
	public void setA_date(String a_date) {
		this.a_date = a_date;
	}
	public String getA_state() {
		return a_state;
	}
	public void setA_state(String a_state) {
		this.a_state = a_state;
	}
	@Override
	public String toString() {
		return "AdminUser [id=" + id + ", a_username=" + a_username + ", a_password=" + a_password + ", a_phone="
				+ a_phone + ", a_email=" + a_email + ", a_date=" + a_date + ", a_state=" + a_state + "]";
	}
	
	
	
	
}
