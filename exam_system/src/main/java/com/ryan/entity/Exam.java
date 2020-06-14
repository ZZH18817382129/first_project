package com.ryan.entity;

public class Exam {
	private int id;
	private String eno;
	private String ename;
	private String etype;
	private String estarttime;
	private String eendtime;
	private String etno;
	private String estatus;
	private String tpno;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEno() {
		return eno;
	}

	public void setEno(String eno) {
		this.eno = eno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEtype() {
		return etype;
	}

	public void setEtype(String etype) {
		this.etype = etype;
	}

	public String getEstarttime() {
		return estarttime;
	}

	public void setEstarttime(String estarttime) {
		this.estarttime = estarttime;
	}

	public String getEendtime() {
		return eendtime;
	}

	public void setEendtime(String eendtime) {
		this.eendtime = eendtime;
	}

	public String getEtno() {
		return etno;
	}

	public void setEtno(String etno) {
		this.etno = etno;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getTpno() {
		return tpno;
	}

	public void setTpno(String tpno) {
		this.tpno = tpno;
	}

	public Exam() {
		super();
	}

	public Exam(int id, String eno, String ename, String etype, String estarttime, String eendtime, String etno,
			String estatus, String tpno) {
		super();
		this.id = id;
		this.eno = eno;
		this.ename = ename;
		this.etype = etype;
		this.estarttime = estarttime;
		this.eendtime = eendtime;
		this.etno = etno;
		this.estatus = estatus;
		this.tpno = tpno;
	}


}
