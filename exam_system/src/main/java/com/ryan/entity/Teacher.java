package com.ryan.entity;

public class Teacher {
	private Integer id;
	private String  tno;
	private String tname;
	private String tgender;
	private  Integer tage;
	private String tphone;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTno() {
		return tno;
	}
	public void setTno(String tno) {
		this.tno = tno;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTgender() {
		return tgender;
	}
	public void setTgender(String tgender) {
		this.tgender = tgender;
	}
	public Integer getTage() {
		return tage;
	}
	public void setTage(Integer tage) {
		this.tage = tage;
	}
	public String getTphone() {
		return tphone;
	}
	public void setTphone(String tphone) {
		this.tphone = tphone;
	}
	public Teacher() {
		super();
	}
	public Teacher(String tno, String tname, String tgender, Integer tage, String tphone) {
		super();
		this.tno = tno;
		this.tname = tname;
		this.tgender = tgender;
		this.tage = tage;
		this.tphone = tphone;
	}
	
}
