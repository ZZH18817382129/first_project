package com.ryan.service;

import java.util.List;

import com.ryan.entity.Chengji;
import com.ryan.entity.Classes;


public interface ChengjiService {
	public List<Chengji> listByPage(int page, int limit);
	public int totalsize();
	public double searchBYCname(String cname);
	public List<Classes> searchAllClasses();
	public int searchClassCnum(String cname);
	public int searchClassCnum6(String cname);
	public int searchClassCnum9(String cname);
	public double selectMAX(String cname);
	public double selectMIN(String cname);
}
