package com.ryan.entity;

public class JiLu {
	private String A;
	private String B;
	private String C;
	private String D;
	private int correct;//��ȷ����
	private int incorrect;//�������
	private double accu;//��ȷ��
	private int items;//��������
	private String ritem;//��ȷ��
	private String sitem;//����ѡ���
	private String cname;//�༶����
	private String sname;//ѧ������
	private String qno;//������
	private String item;//ѡ������
	
	/**
	 * @return the items
	 */
	public int getItems() {
		return items;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(int items) {
		this.items = items;
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
	/**
	 * @return the item
	 */
	public String getItem() {
		return item;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(String item) {
		this.item = item;
	}
	/**
	 * @return the qno
	 */
	public String getQno() {
		return qno;
	}
	/**
	 * @param qno the qno to set
	 */
	public void setQno(String qno) {
		this.qno = qno;
	}
	/**
	 * @return the ritem
	 */
	public String getRitem() {
		return ritem;
	}
	/**
	 * @param ritem the ritem to set
	 */
	public void setRitem(String ritem) {
		this.ritem = ritem;
	}
	/**
	 * @return the sitem
	 */
	public String getSitem() {
		return sitem;
	}
	/**
	 * @param sitem the sitem to set
	 */
	public void setSitem(String sitem) {
		this.sitem = sitem;
	}
	/**
	 * @return the sclass
	 */
	public String getSclass() {
		return cname;
	}
	/**
	 * @param sclass the sclass to set
	 */
	public void setSclass(String cname) {
		this.cname = cname;
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
	 * @return the a
	 */
	public String getA() {
		return A;
	}
	/**
	 * @param a the a to set
	 */
	public void setA(String a) {
		A = a;
	}
	/**
	 * @return the b
	 */
	public String getB() {
		return B;
	}
	/**
	 * @param b the b to set
	 */
	public void setB(String b) {
		B = b;
	}
	/**
	 * @return the c
	 */
	public String getC() {
		return C;
	}
	/**
	 * @param c the c to set
	 */
	public void setC(String c) {
		C = c;
	}
	/**
	 * @return the d
	 */
	public String getD() {
		return D;
	}
	/**
	 * @param d the d to set
	 */
	public void setD(String d) {
		D = d;
	}
	/**
	 * @return the correct
	 */
	public int getCorrect() {
		return correct;
	}
	/**
	 * @param correct the correct to set
	 */
	public void setCorrect(int correct) {
		this.correct = correct;
	}
	/**
	 * @return the incorrect
	 */
	public int getIncorrect() {
		return incorrect;
	}
	/**
	 * @param incorrect the incorrect to set
	 */
	public void setIncorrect(int incorrect) {
		this.incorrect = incorrect;
	}
	/**
	 * @return the accu
	 */
	public double getAccu() {
		return accu;
	}
	/**
	 * @param accu the accu to set
	 */
	public void setAccu(double accu) {
		this.accu = accu;
	}	
	public JiLu(String a, String b, String c, String d, int correct, int incorrect, double accu, int items,
			String ritem, String sitem, String cname, String sname, String qno, String item) {
		super();
		A = a;
		B = b;
		C = c;
		D = d;
		this.correct = correct;
		this.incorrect = incorrect;
		this.accu = accu;
		this.items = items;
		this.ritem = ritem;
		this.sitem = sitem;
		this.cname = cname;
		this.sname = sname;
		this.qno = qno;
		this.item = item;
	}
	public JiLu() {
		
	}
}
