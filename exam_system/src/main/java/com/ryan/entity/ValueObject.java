package com.ryan.entity;

public class ValueObject {
	private String qno;
    private String qstem;
    private String item;
    private String ino;
    private String toname;
    private int istatus;
    private String tono;
	public ValueObject() {
		super();
	}
	public String getQstem() {
		return qstem;
	}
	public void setQstem(String qstem) {
		this.qstem = qstem;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getToname() {
		return toname;
	}
	public void setToname(String toname) {
		this.toname = toname;
	}
	public int getIstatus() {
		return istatus;
	}
	public void setIstatus(int istatus) {
		this.istatus = istatus;
	}
	public String getTono() {
		return tono;
	}
	public void setTono(String tono) {
		this.tono = tono;
	}
	public String getQno() {
		return qno;
	}
	public void setQno(String qno) {
		this.qno = qno;
	}
	public String getIno() {
		return ino;
	}
	public void setIno(String ino) {
		this.ino = ino;
	}
	public ValueObject(String qno, String qstem, String ino, String item, String tono) {
		super();
		this.qno = qno;
		this.qstem = qstem;
		this.ino = ino;
		this.item = item;
		this.tono = tono;
	}
	public ValueObject(String qno,String item, String ino) {
		super();
		this.qno = qno;
		this.item = item;
		this.ino = ino;
	}
	public ValueObject(String qno, String qstem, String toname, String tono) {
		super();
		this.qno = qno;
		this.qstem = qstem;
		this.toname = toname;
		this.tono = tono;
	}
	
}
