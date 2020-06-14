package com.ryan.entity;

public class Classes {
	private int id;
	private String cno;
	private String cname;
	private String ctime;
	private int cnum;
	private String cadviser;
	private String cteacher;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCno() {
		return cno;
	}
	public void setCno(String cno) {
		this.cno = cno;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	public int getCnum() {
		return cnum;
	}
	public void setCnum(int cnum) {
		this.cnum = cnum;
	}
	public String getCadviser() {
		return cadviser;
	}
	public void setCadviser(String cadviser) {
		this.cadviser = cadviser;
	}
	public String getCteacher() {
		return cteacher;
	}
	public void setCteacher(String cteacher) {
		this.cteacher = cteacher;
	}
	public Classes() {
		super();
	}
	public Classes(String cname, String ctime, int cnum, String cadviser, String cteacher) {
		super();
		this.cname = cname;
		this.ctime = ctime;
		this.cnum = cnum;
		this.cadviser = cadviser;
		this.cteacher = cteacher;
	}
	

}
