package com.ryan.entity;

public class ValueObj2 {
private String qno;
private String qstem;
private String tono;
private String ino0;
private String ino1;
private String ino2;
private String ino3;
private String item0;
private String item1;
private String item2;
private String item3;
private String rino;
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
public String getTono() {
	return tono;
}
public void setTono(String tono) {
	this.tono = tono;
}
public String getIno0() {
	return ino0;
}
public void setIno0(String ino0) {
	this.ino0 = ino0;
}
public String getIno1() {
	return ino1;
}
public void setIno1(String ino1) {
	this.ino1 = ino1;
}
public String getIno2() {
	return ino2;
}
public void setIno2(String ino2) {
	this.ino2 = ino2;
}
public String getIno3() {
	return ino3;
}
public void setIno3(String ino3) {
	this.ino3 = ino3;
}
public String getItem0() {
	return item0;
}
public void setItem0(String item0) {
	this.item0 = item0;
}
public String getItem1() {
	return item1;
}
public void setItem1(String item1) {
	this.item1 = item1;
}
public String getItem2() {
	return item2;
}
public void setItem2(String item2) {
	this.item2 = item2;
}
public String getItem3() {
	return item3;
}
public void setItem3(String item3) {
	this.item3 = item3;
}

public String getRino() {
	return rino;
}
public void setRino(String rino) {
	this.rino = rino;
}
public ValueObj2() {
	super();
}
public ValueObj2(String qno, String qstem, String tono, String ino0, String item0) {
	super();
	this.qno = qno;
	this.qstem = qstem;
	this.tono = tono;
	this.ino0 = ino0;
	this.item0 = item0;
}
public ValueObj2(String qno, String ino0, String ino1, String ino2, String ino3, String rino) {
	super();
	this.qno = qno;
	this.ino0 = ino0;
	this.ino1 = ino1;
	this.ino2 = ino2;
	this.ino3 = ino3;
	this.rino = rino;
}
public ValueObj2(String qstem, String tono, String item0, String item1, String item2, String item3, String rino) {
	super();
	this.qstem = qstem;
	this.tono = tono;
	this.item0 = item0;
	this.item1 = item1;
	this.item2 = item2;
	this.item3 = item3;
	this.rino = rino;
}

}
