package com.ryan.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Question {
	private int id;
	private String qno; 
	private String qstem;
	private String toname;
    private Boolean LAY_CHECKED;
	
	@JsonProperty("LAY_CHECKED")
	public Boolean getLAY_CHECKED() {
		return LAY_CHECKED;
	}
	public void setLAY_CHECKED(Boolean lAY_CHECKED) {
		LAY_CHECKED = lAY_CHECKED;
	}

	public String getToname() {
		return toname;
	}

	public void setToname(String toname) {
		this.toname = toname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQno() {
		return qno;
	}

	public void setQno(String qno) {
		this.qno = qno;
	}

	public String getQstem() {
		return qstem;
	}

	public void setQstem(String qstem) {
		this.qstem = qstem;
	}

}
