package com.ryan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ryan.entity.Score;


/**
 * @author ����
 *
 */
public interface ScoreMapper {
	/**
	 * @Param offsetƫ����
	 * @Param limit ����ÿҳ���ݵ�����
	 *
	 */
	List<Score> selectByPage(@Param("offset") int offset, @Param("limit") int limit);
	int selectTotal();  
	List<Score> selectone(@Param("s") Score s);  //��ѯһ���ɼ��ļ���
	int updateOne(@Param("sno") String sno, @Param("ename") String ename, @Param("esgrade") int esgrade);
	Score selectscore(@Param("sno") String sno, @Param("ename") String ename);
	
}
