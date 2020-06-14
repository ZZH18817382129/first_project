package com.ryan.service.impl;

import java.util.List;

import com.ryan.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryan.entity.Question;
import com.ryan.entity.Topic;
import com.ryan.entity.ValueObj2;
import com.ryan.entity.ValueObject;
import com.ryan.mapper.QuestionMapper;

@Service
public class QuestionServiceimpl implements QuestionService {
	@Autowired
	private QuestionMapper questionmapper;
	
	public QuestionServiceimpl() {
		super();
	}

	public QuestionServiceimpl(QuestionMapper questionmapper) {
		super();
		this.questionmapper = questionmapper;
	}

	
	public List<Question> showAll(int page, int size) {

		return questionmapper.selectAllQuestion((page-1)*size, size);
	}

	
	public int getTotal() {
		
		return questionmapper.selectTotal();
	}

	
	public List<ValueObject> getOne(int id) {
	
		return questionmapper.selectOne(id);
	}

	
	public int modifyQuestion0(ValueObject Vobj) {
		
		return questionmapper.updateQuestion0(Vobj);
	}

	
	public int modifyQuestion1(ValueObject Vobj) {
	
		return questionmapper.updateQuestion1(Vobj);
	}

	
	public int modifyQuestion2(ValueObject Vobj) {
		
		return questionmapper.updateQuestion2(Vobj);
	}

	
	public int modifyQuestion3(ValueObject Vobj) {
	
		return questionmapper.updateQuestion3(Vobj);
	}

	
	public int modifyQstem(ValueObject Vobj) {
		
		return questionmapper.updateQstem(Vobj);
	}

	
	public int modifyQno(ValueObject Vobj) {
		
		return questionmapper.updateQtono(Vobj);
	}

	
	public int modifyino0(ValueObj2 valueObjR) {
		return questionmapper.updateino0(valueObjR);
	}

	
	public int modifyino1(ValueObj2 valueObjR) {
		return questionmapper.updateino1(valueObjR);
	}

	
	public int modifyino2(ValueObj2 valueObjR) {
		return questionmapper.updateino2(valueObjR);
	}

	
	public int modifyino3(ValueObj2 valueObjR) {
		return questionmapper.updateino3(valueObjR);
	}

	
	public int modifyRino(ValueObj2 valueObjR) {
		return questionmapper.updateRino(valueObjR);
	}

	
	public int showMaxItemId() {
		
		return questionmapper.selectMaxItemId();
	}

	
	public int showMaxQuesId() {
		return questionmapper.selectMaxQuesId();
	}

	
	public int additem(ValueObject vOj) {
		return questionmapper.insertItem(vOj);
	}

	
	public int addqi(ValueObject vOj) {
		return questionmapper.insertQi(vOj);
	}

	
	public int addqstem(ValueObject vOj2) {
		return questionmapper.insertQstem(vOj2);
	}


	
	public int addStatus(ValueObj2 valueObj) {
		return questionmapper.updateStatus(valueObj);
	}

	
	public int removeOne(int id) {
		
		return questionmapper.deleteOne(id);
	}

	
	public int removeMore(String id) {
		return questionmapper.deleteMore(id);
	}

	
	public List<Question> listQues(Question q) {
		q.setQno("%"+q.getQno()+"%");
		q.setQstem("%"+q.getQstem()+"%");
		q.setToname("%"+q.getToname()+"%");
		return questionmapper.selectQuesion(q);
	}


	
	public String getTono(String toname) {
		return questionmapper.selectTono(toname);
	}

	
	public List<Topic> showAllTopic(int page, int size) {
		return questionmapper.selectAllQuestionTopic((page-1)*size, size);
	}

	
	public int getTopicTotal() {
		return questionmapper.selectTopicTotal();
	}

	
	public int add_checked(String tono,Boolean LAY_CHECKED) {
		return questionmapper.updateChecked(tono,LAY_CHECKED);
	}


	
	public int modifyLayChecked() {
		return questionmapper.updateLayChecked();
	}


	
	public List<Topic> listTopic(Topic t) {
		t.setToname("%"+t.getToname()+"%");
		return questionmapper.selectTopics(t);
	}

	
	public List<Topic> getTopicLayCheck() {
		return questionmapper.selectTopicCheck();
	}

	
	public int addqto(String qno, String tono) {
		return questionmapper.insertQueto(qno,tono);
	}

	
	public List<String> getQue_toTono(String qno) {
		// TODO Auto-generated method stub
		return questionmapper.selectQue_toTono(qno);
	}

	
	public int modifyTopicChecked(String tono) {
		// TODO Auto-generated method stub
		return questionmapper.updateTopicChecked(tono);
	}

	
	public int removeQue_to(String qno) {
		// TODO Auto-generated method stub
		return questionmapper.deleteQue_to(qno);
	}

	
	public List<String> getQue_itemIno(String qno) {
		// TODO Auto-generated method stub
		return questionmapper.selectQue_itemIno(qno);
	}

	
	public int removeOneitem(String ino) {
		// TODO Auto-generated method stub
		return questionmapper.deleteOneitem(ino);
	}

	
	public int removeOneque_item(String qno) {
		// TODO Auto-generated method stub
		return questionmapper.deleteOneque_item(qno);
	}

	
	public int removeOneque_to(String qno) {
		// TODO Auto-generated method stub
		return questionmapper.deleteOneque_to(qno);
	}

	
	public List<String> getMoreQue_itemIno(String qno) {
		// TODO Auto-generated method stub
		return questionmapper.selectMoreQue_itemIno(qno);
	}

	
	public int removeMoreitem(String ino) {
		// TODO Auto-generated method stub
		return questionmapper.deleteMoreitem(ino);
	}

	
	public int removeMoreque_item(String qno) {
		// TODO Auto-generated method stub
		return questionmapper.deleteMoreque_item(qno);
	}

	
	public int removeMoreque_to(String qno) {
		// TODO Auto-generated method stub
		return questionmapper.deleteMoreque_to(qno);
	}

	
	public int getItemId() {
		// TODO Auto-generated method stub
		return questionmapper.selectItemid();
	}

	
	public int addItemId() {
		// TODO Auto-generated method stub
		return questionmapper.insertItemid();
	}

	
	public int getQuestionCount() {
		// TODO Auto-generated method stub
		return questionmapper.selectQuestionCount();
	}

	
	public int addQuestionId() {
		// TODO Auto-generated method stub
		return questionmapper.insertQuestionid();
	}

	
	public int removeQuestionfirst() {
		// TODO Auto-generated method stub
		return questionmapper.deleteQuestionfirst();
	}


}
