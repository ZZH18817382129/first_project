package com.ryan.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Student {

	private int id;
	private String sname;
	private String sphone;
	private String sclass;
	private String sgender;
	private String sno;
	private String sschool;
	private String smajor;
	private String stime;
	private boolean LAY_CHECKED;
	
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	@JsonProperty("LAY_CHECKED")
	public boolean getLAY_CHECKED() {
		return LAY_CHECKED;
	}
	public void setLAY_CHECKED(boolean lAY_CHECKED) {
		LAY_CHECKED = lAY_CHECKED;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSphone() {
		return sphone;
	}
	public void setSphone(String sphone) {
		this.sphone = sphone;
	}
	public String getSclass() {
		return sclass;
	}
	public void setSclass(String sclass) {
		this.sclass = sclass;
	}
	public String getSgender() {
		return sgender;
	}
	public void setSgender(String sgender) {
		this.sgender = sgender;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getSschool() {
		return sschool;
	}
	public void setSschool(String sschool) {
		this.sschool = sschool;
	}
	public String getSmajor() {
		return smajor;
	}
	public void setSmajor(String smajor) {
		this.smajor = smajor;
	}
	
	
	public Student() {
		super();
	}
	public Student(String sname, String sphone, String sclass, String sgender, String sno, String sschool,String smajor ,boolean LAY_CHECKED) 
	{
		super();
		this.sname = sname;
		this.sphone = sphone;
		this.sclass = sclass;
		this.sgender = sgender;
		this.sno = sno;
		this.sschool = sschool;
		this.smajor = smajor;
		this.LAY_CHECKED = LAY_CHECKED;
	}
}
