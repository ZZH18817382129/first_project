package com.ryan.entity;

public class TestPaper {

	private int id;
	private String tpno;
	private String tpname;
	private String tpteacher;
	private String disable;
	public String getDisable() {
		return disable;
	}
	public void setDisable(String disable) {
		this.disable = disable;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTpno() {
		return tpno;
	}
	public void setTpno(String tpno) {
		this.tpno = tpno;
	}
	public String getTpname() {
		return tpname;
	}
	public void setTpname(String tpname) {
		this.tpname = tpname;
	}
	public String getTpteacher() {
		return tpteacher;
	}
	public void setTpteacher(String tpteacher) {
		this.tpteacher = tpteacher;
	}
	public TestPaper() {
		super();
	}
	public TestPaper(String tpno, String tpname, String tpteacher,String disable) {
		super();
		this.tpno = tpno;
		this.tpname = tpname;
		this.tpteacher = tpteacher;
		this.disable = disable;
	}
	
}
