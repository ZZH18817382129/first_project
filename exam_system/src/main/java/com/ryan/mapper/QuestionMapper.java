package com.ryan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ryan.entity.Question;
import com.ryan.entity.Topic;
import com.ryan.entity.ValueObj2;
import com.ryan.entity.ValueObject;

public interface QuestionMapper {
	   List<Question> selectAllQuestion(@Param("offset") int offset, @Param("size") int size);
	   int selectTotal();
	   List<Topic> selectAllQuestionTopic(@Param("offset") int offset, @Param("size") int size);
	   int selectTopicTotal();
	   List<ValueObject> selectOne(@Param("id") int id);
	   int deleteMore(@Param("sid") String sid);
	   int deleteOne(@Param("id") int id);
	   int updateQuestion0(@Param("q0") ValueObject Vobj);
	   int updateQuestion1(@Param("q1") ValueObject Vobj);
	   int updateQuestion2(@Param("q2") ValueObject Vobj);
	   int updateQuestion3(@Param("q3") ValueObject Vobj);
	   int updateQstem(@Param("q0") ValueObject Vobj);
	   int updateQtono(@Param("q0") ValueObject Vobj);
	   int updateino0(@Param("vo0") ValueObj2 valueObjR);
	   int updateino1(@Param("vo1") ValueObj2 valueObjR);
		int updateino2(@Param("vo2") ValueObj2 valueObjR);
		int updateino3(@Param("vo3") ValueObj2 valueObjR);
		int updateRino(@Param("voR") ValueObj2 valueObjR);
		int selectMaxItemId();
		int selectMaxQuesId();
		int insertItem(@Param("vit") ValueObject vOj);
		int insertQi(@Param("vqi") ValueObject vOj);
		int insertQstem(@Param("vqstem") ValueObject vOj2);
		int updateStatus(@Param("vstatus") ValueObj2 valueObj);
		List<Question> selectQuesion(@Param("q") Question q);
		String selectTono(@Param("toname") String toname);
		int updateChecked(@Param("t") String tono, @Param("lay") Boolean lAY_CHECKED);
		int updateLayChecked();
		List<Topic> selectTopics(@Param("t") Topic t);
		List<Topic> selectTopicCheck();
		int insertQueto(@Param("q") String qno, @Param("t") String tono);
		List<String> selectQue_toTono(@Param("q") String qno);
		int updateTopicChecked(@Param("to") String tono);
		int deleteQue_to(@Param("qno") String qno);
		List<String> selectQue_itemIno(@Param("qno") String qno);
		int deleteOneitem(@Param("ino") String ino);
		int deleteOneque_item(@Param("qno") String qno);
		int deleteOneque_to(@Param("qno") String qno);
		List<String> selectMoreQue_itemIno(@Param("qno") String qno);
		int deleteMoreitem(@Param("ino") String ino);
		int deleteMoreque_item(@Param("qno") String qno);
		int deleteMoreque_to(@Param("qno") String qno);
		int selectItemid();
		int insertItemid();
		int selectQuestionCount();
		int insertQuestionid();
		int deleteQuestionfirst();

		
		
}
