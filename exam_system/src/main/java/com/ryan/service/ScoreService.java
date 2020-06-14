package com.ryan.service;

import java.util.List;

import com.ryan.entity.Score;


public interface ScoreService {
	/*
	 * @param page ҳ��
	 *
	 */
	public List<Score> listByPage(int page, int limit);
	public int totalsize();
	public List<Score> searchone(Score s);//��ѯ����ɼ�
	public int modifyScore(String sno, String ename, int esgrade);
	Score searchOne(String sno, String ename);
	
	
}
