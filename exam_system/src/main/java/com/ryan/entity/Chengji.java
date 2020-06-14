package com.ryan.entity;

public class Chengji {
	private double esgrade;   //成绩
	private String cnum;   //班级人数
	private double AVG;    //平均成绩
	private double MAX;    //最大值
	private double Min;     //最小值
	private double prate;    //及格率
	private int rnum;   //及格人数
	private double yrate;   //优秀率
	private int gnum;   //优秀人数
	private String cname;    //班级名称
	/**
	 * @return the esgrade
	 * 
	 */
	
	public String getCnum() {
		return cnum;
	}
	/**
	 * @param cnum the cnum to set
	 */
	public void setCnum(String cnum) {
		this.cnum = cnum;
	}
	/**
	 * @return the aVG
	 */
	public Double getAVG() {
		return AVG;
	}
	/**
	 * @param aVG the aVG to set
	 */
	public void setAVG(Double aVG) {
		AVG = aVG;
	}
	/**
	 * @return the mAX
	 */
	
	public Double getPrate() {
		return prate;
	}
	/**
	 * @return the mAX
	 */
	public Double getMAX() {
		return MAX;
	}
	/**
	 * @param mAX the mAX to set
	 */
	public void setMAX(Double mAX) {
		MAX = mAX;
	}
	/**
	 * @return the min
	 */
	public Double getMin() {
		return Min;
	}
	/**
	 * @param min the min to set
	 */
	public void setMin(Double min) {
		Min = min;
	}
	/**
	 * @param prate the prate to set
	 */
	public void setPrate(Double prate) {
		this.prate = prate;
	}
	/**
	 * @return the rnum
	 */
	public int getRnum() {
		return rnum;
	}
	/**
	 * @param rnum the rnum to set
	 */
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	/**
	 * @return the yrate
	 */
	public Double getYrate() {
		return yrate;
	}
	/**
	 * @param yrate the yrate to set
	 */
	public void setYrate(Double yrate) {
		this.yrate = yrate;
	}
	/**
	 * @return the gnum
	 */
	public int getGnum() {
		return gnum;
	}
	/**
	 * @param gnum the gnum to set
	 */
	public void setGnum(int gnum) {
		this.gnum = gnum;
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
	
	
	
	public Chengji(Double esgrade, String cnum, Double aVG, Double mAX, Double min, Double prate, int rnum,
			Double yrate, int gnum, String cname) {
		super();
		this.esgrade = esgrade;
		this.cnum = cnum;
		AVG = aVG;
		MAX = mAX;
		Min = min;
		this.prate = prate;
		this.rnum = rnum;
		this.yrate = yrate;
		this.gnum = gnum;
		this.cname = cname;
	}
	/**
	 * @return the esgrade
	 */
	public Double getEsgrade() {
		return esgrade;
	}
	/**
	 * @param esgrade the esgrade to set
	 */
	public void setEsgrade(Double esgrade) {
		this.esgrade = esgrade;
	}
	public Chengji() {
		
	}
}
