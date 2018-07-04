package com.crudexample.models;


public class ISGUser {

	private int id;
	private String name;
	private String date;
	private String updateDate;
	private String sicil;
	private int flag;

	public ISGUser() {
	}
	
	public ISGUser(int id, String name, String date, String updateDate,
			String sicil, int flag) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.updateDate = updateDate;
		this.sicil = sicil;
		this.flag = flag;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getSicil() {
		return sicil;
	}
	public void setSicil(String sicil) {
		this.sicil = sicil;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
}
