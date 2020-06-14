package com.ryan.service;

import java.util.List;

import com.ryan.entity.Question;
import com.ryan.entity.Topic;
import com.ryan.entity.ValueObj2;
import com.ryan.entity.ValueObject;

public interface QuestionService {
	List<Question> showAll(int page, int size);
	int getTotal();
	List<Topic> showAllTopic(int page, int size);
	int getTopicTotal();
	List<ValueObject> getOne(int id);
	int modifyQuestion0(ValueObject Vobj);
	int modifyQuestion1(ValueObject Vobj);
	int modifyQuestion2(ValueObject Vobj);
	int modifyQuestion3(ValueObject Vobj);
	int modifyQstem(ValueObject Vobj);
	int modifyQno(ValueObject Vobj);
	int modifyino0(ValueObj2 valueObjR);
	int modifyino1(ValueObj2 valueObjR);
	int modifyino2(ValueObj2 valueObjR);
	int modifyino3(ValueObj2 valueObjR);
	int modifyRino(ValueObj2 valueObjR);
	int showMaxItemId();
	int showMaxQuesId();
	int additem(ValueObject vOj);
	int addqi(ValueObject vOj);
	int addqstem(ValueObject vOj2);
	int addStatus(ValueObj2 valueObj);
	int removeOne(int id);
	int removeMore(String id);
	List<Question> listQues(Question q);
	String getTono(String toname);
	int add_checked(String tono, Boolean LAY_CHECKED);
	int modifyLayChecked();
	List<Topic> listTopic(Topic t);
	List<Topic> getTopicLayCheck();
	int addqto(String qno, String tono);
	List<String> getQue_toTono(String qno);
	int modifyTopicChecked(String tono);
	int removeQue_to(String qno);
	List<String> getQue_itemIno(String qno);
	int removeOneitem(String ino);
	int removeOneque_item(String qno);
	int removeOneque_to(String qno);
	List<String> getMoreQue_itemIno(String qno);
	int removeMoreitem(String ino);
	int removeMoreque_item(String qno);
	int removeMoreque_to(String qno);
	int getItemId();
	int addItemId();
	int getQuestionCount();
	int addQuestionId();
	int removeQuestionfirst();
	
	
}
