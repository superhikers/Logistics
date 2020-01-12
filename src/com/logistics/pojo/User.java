package com.logistics.pojo;

public class User {
	private int u_id;
	private String u_username;
	private String u_password;
	private String u_phone;
	private String u_address;
	private String u_sex;
	private String o_state;
	private String u_email;
	
	
	public String getU_email() {
		return u_email;
	}


	public void setU_email(String u_email) {
		this.u_email = u_email;
	}


	public String getO_state() {
		return o_state;
	}


	public void setO_state(String o_state) {
		this.o_state = o_state;
	}


	public int getU_id() {
		return u_id;
	}


	public void setU_id(int u_id) {
		this.u_id = u_id;
	}


	public String getU_username() {
		return u_username;
	}


	public void setU_username(String u_username) {
		this.u_username = u_username;
	}


	public String getU_password() {
		return u_password;
	}


	public void setU_password(String u_password) {
		this.u_password = u_password;
	}


	public String getU_phone() {
		return u_phone;
	}


	public void setU_phone(String u_phone) {
		this.u_phone = u_phone;
	}


	public String getU_address() {
		return u_address;
	}


	public void setU_address(String u_address) {
		this.u_address = u_address;
	}


	public String getU_sex() {
		return u_sex;
	}


	public void setU_sex(String u_sex) {
		this.u_sex = u_sex;
	}


	@Override
	public String toString() {
		return "User [u_id=" + u_id + ", u_username=" + u_username + ", u_password=" + u_password + ", u_phone="
				+ u_phone + ", u_address=" + u_address + ", u_sex=" + u_sex + ", o_state=" + o_state + ", u_email="
				+ u_email + "]";
	}


	
}
