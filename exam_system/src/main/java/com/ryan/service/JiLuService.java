package com.ryan.service;

import java.util.List;

import com.ryan.entity.Exam;
import com.ryan.entity.Item;
import com.ryan.entity.Question;

public interface JiLuService {

	List<Exam> searchAllExams();

	List<Question> searchQuesByTpno(String tpno);

	List<Item> searchItemsByQno(String qno);

	int searchcorrect(String eno, String qno);

	int searchincorrect(String eno, String qno);

	String searchRitem(String qno);

	int searchCheckThis(String item, String qno, String eno);
	
	

}
