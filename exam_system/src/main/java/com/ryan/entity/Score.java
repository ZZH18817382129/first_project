package com.ryan.entity;

/**
 * @author ����
 * 
 *
 */
public class Score {
	private int id;
	private String sname;   //ѧ������
	private String sno;    //ѧ�����
	private String tpname;   //�Ծ�����
	private String esgrade;   //�ɼ�
	private String cname;    //�༶����
	
	public Score(String sname, String sno, String tpname, String esgrade, String cname) {
		super();
		this.sname = sname;
		this.sno = sno;
		this.tpname = tpname;
		this.esgrade = esgrade;
		this.cname = cname;
	}

	public Score(int id, String sname, String sno, String tpname, String esgrade, String cname) {
		super();
		this.id = id;
		this.sname = sname;
		this.sno = sno;
		this.tpname = tpname;
		this.esgrade = esgrade;
		this.cname = cname;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the sname
	 */
	public String getSname() {
		return sname;
	}

	/**
	 * @param sname the sname to set
	 */
	public void setSname(String sname) {
		this.sname = sname;
	}

	/**
	 * @return the sno
	 */
	public String getSno() {
		return sno;
	}

	/**
	 * @param sno the sno to set
	 */
	public void setSno(String sno) {
		this.sno = sno;
	}

	/**
	 * @return the tpname
	 */
	public String getTpname() {
		return tpname;
	}

	/**
	 * @param tpname the tpname to set
	 */
	public void setTpname(String tpname) {
		this.tpname = tpname;
	}

	/**
	 * @return the esgrade
	 */
	public String getEsgrade() {
		return esgrade;
	}

	/**
	 * @param esgrade the esgrade to set
	 */
	public void setEsgrade(String esgrade) {
		this.esgrade = esgrade;
	}

	/**
	 * @return the cname
	 */
	public String getCname() {
		return cname;
	}

	/**
	 * @param cname the cname to set
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}

	public Score() {
		
	}
}
