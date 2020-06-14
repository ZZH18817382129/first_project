package com.ryan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ryan.entity.Major;
import com.ryan.entity.Topic;

/**
 * @author ���
 * @see 
 * @since
 */
public interface LessonMapper {
   
	List<Major> findless(@Param("offset") int pagess, @Param("limit") int limit);
    
	int insertless(@Param("major") Major major);

	int deleteless(@Param("mno") String mno);

	Major selectless(@Param("id") int id);

	int updateless(@Param("major") Major major);

	List<Major> selectlesson(@Param("major") Major major);

	List<Topic> selecttopic();

	List<Topic> seachtopic(@Param("ids") String ids);

	int inserttop(@Param("major") Major major, @Param("top") List<Topic> top);

	int selectMaxlessonId();

	int findtotalrow();

	int deleteonemaj(@Param("mno") String mno);

	int deleteonemajto(@Param("mno") String mno);

	int deletemajto(@Param("mno") String mno);

	List<String> findmajto(@Param("mno") String mno);

	int addmajto1(@Param("mno") String mno, @Param("tono") List<Topic> top);

	int deletemajto1(@Param("mno") String mno);

	List<Topic> selectmajto(@Param("mno") String mno);

	List<Topic> findtopic1(@Param("tono1") String tono1);

	
    
    
}