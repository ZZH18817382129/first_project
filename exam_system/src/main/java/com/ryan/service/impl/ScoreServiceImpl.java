package com.ryan.service.impl;

import java.util.List;

import com.ryan.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryan.entity.Score;
import com.ryan.mapper.ScoreMapper;

/**
 * @author ����
 *
 */

@Service
public class ScoreServiceImpl implements ScoreService {
	@Autowired
	private ScoreMapper scoreMapper;

	/**
	 * @return the scoreMapper
	 */
	public ScoreMapper getScoreMapper() {
		return scoreMapper;
	}

	/**
	 * @param scoreMapper the scoreMapper to set
	 */
	public void setScoreMapper(ScoreMapper scoreMapper) {
		this.scoreMapper = scoreMapper;
	}

	public ScoreServiceImpl(ScoreMapper scoreMapper) {
		super();
		this.scoreMapper = scoreMapper;
	}

	public ScoreServiceImpl() {

	}

	
	/**
	 * @Param  
	 *@return ���ز�ѯ�����Ķ���ɼ�
	 */
	
	public List<Score> searchone(Score s) {

		return scoreMapper.selectone(s);
	}

	
	public List<Score> listByPage(int page, int limit) {
		
		return scoreMapper.selectByPage((page-1)*limit,limit);
	}

	
	public int totalsize() {
		
		return scoreMapper.selectTotal();
	}

	
	public int modifyScore(String sno,String ename,int esgrade) {
		 return scoreMapper.updateOne(sno,ename,esgrade);
		 
	}

	
	public Score searchOne(String sno,String ename ) {
		return scoreMapper.selectscore(sno,ename);
	}


}
