package com.ryan.service.impl;

import java.util.List;

import com.ryan.service.ChengjiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryan.entity.Chengji;
import com.ryan.entity.Classes;
import com.ryan.mapper.ChengjiMapper;

@Service
public class ChengjiServiceImpl implements ChengjiService {
	@Autowired
	private ChengjiMapper chengjiMapper;

	/**
	 * @return the chengjiMapper
	 */
	public ChengjiMapper getChengjiMapper() {
		return chengjiMapper;
	}

	/**
	 * @param chengjiMapper the chengjiMapper to set
	 */
	public void setChengjiMapper(ChengjiMapper chengjiMapper) {
		this.chengjiMapper = chengjiMapper;
	}

	public ChengjiServiceImpl(ChengjiMapper chengjiMapper) {
		super();
		this.chengjiMapper = chengjiMapper;
	}
	public ChengjiServiceImpl() {
		
	}

	public List<Chengji> listByPage(int page, int limit) {
		return chengjiMapper.selectByPage(page,limit);
	}

	
	public int totalsize() {
		return chengjiMapper.selectTotal();
	}

	
	public double searchBYCname(String cname) {
		return chengjiMapper.selectzf(cname);
	}

	
	public List<Classes> searchAllClasses() {
		return chengjiMapper.selectClass();
		
	}

	
	public int searchClassCnum(String cname) {
		return chengjiMapper.selectclassCnum(cname);
	}

	
	public int searchClassCnum6(String cname) {
		return chengjiMapper.selectclassCnum6(cname);
	}

	
	public int searchClassCnum9(String cname) {
		return chengjiMapper.selectclassCnum9(cname);
	}

	
	public double selectMAX(String sclass) {
		return chengjiMapper.selectMax(sclass);
	}

	
	public double selectMIN(String sclass) {
		return chengjiMapper.selectMin(sclass);
	}



}
