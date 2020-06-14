package com.ryan.service;

import java.util.List;

import com.ryan.entity.Major;
import com.ryan.entity.Topic;

public interface LessonService {

	List<Major> listlesson(int page, int limit);

	int addlesson(Major major, String ids);

	int removelesson(String id);

	Major sellesson(int id);

	int modifylesson(Major major);

	List<Major> listLess(Major major);

	List<Topic> listTopic();

	int showMaxlessonId();

	int totalRow();

	int removelessonone(String mno);

	List<String> selectmajto(String mno);

	int addlessontopic(String mno, String to);

	
 
}
