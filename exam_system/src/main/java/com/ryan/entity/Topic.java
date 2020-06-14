package com.ryan.entity;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jiaoruoyu
 *
 */
public class Topic {

	private Integer id;
	private String toname; // ֪ʶ������
	private String tono; // ֪ʶ����
	private String tolevel; // ����
	private String tostage; // �׶�
	private String tomodule; // ģ��
	private Boolean LAY_CHECKED;

	@JsonProperty("LAY_CHECKED")
	public Boolean getLAY_CHECKED() {
		return LAY_CHECKED;
	}

	public void setLAY_CHECKED(Boolean lAY_CHECKED) {
		LAY_CHECKED = lAY_CHECKED;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the toname
	 */
	public String getToname() {
		return toname;
	}

	/**
	 * @param toname the toname to set
	 */
	public void setToname(String toname) {
		this.toname = toname;
	}

	/**
	 * @return the tono
	 */
	public String getTono() {
		return tono;
	}

	/**
	 * @param tono the tono to set
	 */
	public void setTono(String tono) {
		this.tono = tono;
	}

	/**
	 * @return the tolevel
	 */
	public String getTolevel() {
		return tolevel;
	}

	/**
	 * @param tolevel the tolevel to set
	 */
	public void setTolevel(String tolevel) {
		this.tolevel = tolevel;
	}

	/**
	 * @return the tostage
	 */
	public String getTostage() {
		return tostage;
	}

	/**
	 * @param tostage the tostage to set
	 */
	public void setTostage(String tostage) {
		this.tostage = tostage;
	}

	/**
	 * @return the tomodule
	 */
	public String getTomodule() {
		return tomodule;
	}

	/**
	 * @param tomodule the tomodule to set
	 */
	public void setTomodule(String tomodule) {
		this.tomodule = tomodule;
	}

	public Topic(String toname, String tono, String tolevel, String tostage, String tomodule) {
		super();
		this.toname = toname;
		this.tono = tono;
		this.tolevel = tolevel;
		this.tostage = tostage;
		this.tomodule = tomodule;
	}

	public Topic(Integer id, String toname, String tono, String tolevel, String tostage, String tomodule) {
		super();
		this.id = id;
		this.toname = toname;
		this.tono = tono;
		this.tolevel = tolevel;
		this.tostage = tostage;
		this.tomodule = tomodule;
	}

	public Topic() {

	}
}
