package com.ryan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ryan.entity.Exam;
import com.ryan.entity.Item;
import com.ryan.entity.Question;

public interface JiLuMapper {

	List<Exam> selectAllExams();

	List<Question> selectQuesByTpno(@Param("tpno") String tpno);

	List<Item> selectItemsByQno(@Param("qno") String qno);

	int selectcorrect(@Param("eno") String eno, @Param("qno") String qno);

	int selectincorrect(@Param("eno") String eno, @Param("qno") String qno);

	String selectRitem(@Param("qno") String qno);

	int selectCheckThis(@Param("item") String item, @Param("qno") String qno, @Param("eno") String eno);
	
}
