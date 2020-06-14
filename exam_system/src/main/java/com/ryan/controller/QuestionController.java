package com.ryan.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ryan.entity.Question;
import com.ryan.entity.Topic;
import com.ryan.entity.ValueObj2;
import com.ryan.entity.ValueObject;
import com.ryan.service.QuestionService;

@Controller
@SessionAttributes(value = { "topic"})

public class QuestionController {
	@Autowired
	private QuestionService questionservice;

	public QuestionController() {
		super();
	}

	public QuestionController(QuestionService questionervice) {
		super();
		this.questionservice = questionervice;
	}
    /**
     * 
     * @param page ��ǰ�ǵڼ�ҳ��ҳ��
     * @param limit ÿһҳ���ݵ�����
     * @return ������֪ʶ�������
     */
	@RequestMapping("Question_topic_list")
	@ResponseBody
	public Map<String, Object> question_topic_list(int page, int limit) {
		List<Topic> list = questionservice.showAllTopic(page, limit);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", questionservice.getTopicTotal());
		map.put("data", list);
		return map;
	}
    /**
     * 
     * @param page ��ǰ�ǵڼ�ҳ��ҳ��
     * @param limit ÿһҳ���ݵ�����
     * @return ���������������
     */
	@RequestMapping("Question_list")
	@ResponseBody
	public Map<String, Object> question_list(int page, int limit) {
		List<Question> list = questionservice.showAll(page, limit);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", questionservice.getTotal());
		map.put("data", list);
		return map;
	}
    /**
     * 
     * @param id ѡ�е���һ�����ݵ�id
     * @param qno ѡ�е���һ�����ݵ�qno
     * @return ɾ��һ������
     */
	@RequestMapping(value = "Question_del", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> question_del(int id,String qno) {

		int rs = questionservice.removeOne(id);//ɾ��һ����
		List<String> list=questionservice.getQue_itemIno(qno);
		String ino="";
		for(int i=0;i<list.size();i++) {
			ino+="'"+list.get(i)+"'"+",";
		}
		ino=ino.substring(0, ino.length()-1);
		int rs2=questionservice.removeOneitem(ino);//ɾ��item���е�4��ѡ��
		int rs3=questionservice.removeOneque_item(qno);//ɾ��que_item������
		int rs4=questionservice.removeOneque_to(qno);//ɾ��que_to������
		Map<String, Object> map = new HashMap<String, Object>();

		if (rs > 0)
			map.put("msg", "ɾ���ɹ���");
		else
			map.put("msg", "ɾ��ʧ�ܣ�");
		return map;
	}
    /**
     * 
     * @param id ѡ�еĶ������ݵ�id
     * @param qno ѡ�еĶ������ݵ�qno
     * @return ɾ����������
     */
	@RequestMapping(value = "Question_delmore", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> question_delmore(String id,String qno) {

		int rs = questionservice.removeMore(id);//ɾ�������
		List<String> list=questionservice.getMoreQue_itemIno(qno);
		String ino="";
		for(int i=0;i<list.size();i++) {
			ino+="'"+list.get(i)+"'"+",";
		}
		ino=ino.substring(0, ino.length()-1);
		int rs2=questionservice.removeMoreitem(ino);//ɾ��ѡ��
		int rs3=questionservice.removeMoreque_item(qno);//ɾ�������ѡ�������
		int rs4=questionservice.removeMoreque_to(qno);//ɾ�������֪ʶ��Ĺ�����
		Map<String, Object> map = new HashMap<String, Object>();

		if (rs > 0)
			map.put("msg", "ɾ���ɹ���");
		else
			map.put("msg", "ɾ��ʧ�ܣ�");
		return map;
	}
    /**
     * 
     * @param id ѡ�е���һ�����id
     * @return �༭����
     */
	@RequestMapping(value = "Question_upd", method = RequestMethod.GET)
	public ModelAndView prev_Question_upd(int id) {
		ModelAndView mv = new ModelAndView();
		int rs = questionservice.modifyLayChecked();
		List<ValueObject> c = questionservice.getOne(id);
		mv.addObject("c0", c.get(0));
		mv.addObject("c1", c.get(1));
		mv.addObject("c2", c.get(2));
		mv.addObject("c3", c.get(3));
		String qno=c.get(0).getQno();
		List<String> list=questionservice.getQue_toTono(qno);
		String tono="";
		for(int i=0;i<list.size();i++) {
			tono+=list.get(i)+",";
		}
		tono=tono.substring(0, tono.length()-1);
		int rs1=questionservice.modifyTopicChecked(tono);

		mv.setViewName("/Views/modify_question.jsp");
		return mv;
	}
    /**
     * 
     * @param valueObj
     * @return ���±༭������
     */
	@RequestMapping(value = "Question_upd", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> Question_upd(ValueObj2 valueObj) {
		Map<String, Object> map = new HashMap<String, Object>();
		String qno = valueObj.getQno();
		String qstem = valueObj.getQstem();
		String ino0 = valueObj.getIno0();
		String ino1 = valueObj.getIno1();
		String ino2 = valueObj.getIno2();
		String ino3 = valueObj.getIno3();
		String item0 = valueObj.getItem0();
		String item1 = valueObj.getItem1();
		String item2 = valueObj.getItem2();
		String item3 = valueObj.getItem3();
		String rino = valueObj.getRino();
		
		List<ValueObject> volist = new ArrayList<ValueObject>();
		volist.add(new ValueObject(qno, qstem, ino0, item0, null));
		volist.add(new ValueObject(qno, qstem, ino1, item1, null));
		volist.add(new ValueObject(qno, qstem, ino2, item2, null));
		volist.add(new ValueObject(qno, qstem, ino3, item3, null));
		ValueObject Vobj0 = volist.get(0);
		ValueObject Vobj1 = volist.get(1);
		ValueObject Vobj2 = volist.get(2);
		ValueObject Vobj3 = volist.get(3);//�൱�ڴ�����4������
		
		ValueObj2 valueObjR = new ValueObj2(qno, ino0, ino1, ino2, ino3, rino);
		int rs0 = questionservice.modifyQuestion0(Vobj0);
		int rs1 = questionservice.modifyQuestion1(Vobj1);
		int rs2 = questionservice.modifyQuestion2(Vobj2);
		int rs3 = questionservice.modifyQuestion3(Vobj3);// ����item���е��ĸ�ѡ��

		int rsino0 = questionservice.modifyino0(valueObjR);
		int rsino1 = questionservice.modifyino1(valueObjR);
		int rsino2 = questionservice.modifyino2(valueObjR);
		int rsino3 = questionservice.modifyino3(valueObjR);// ����que-item���е�ino
		
		int rsRino = questionservice.modifyRino(valueObjR);
		
		//ɾ��ԭ����que_to�Ĺ�ϵ
		int rs4=questionservice.removeQue_to(qno);
		//����µ�que_to��ϵ
		List<Topic>  Topiclist=questionservice.getTopicLayCheck();
		for(int i=0;i<Topiclist.size();i++) {
			String tono=Topiclist.get(i).getTono();
			int rs5 = questionservice.addqto(qno,tono);
		}
		
		String toname="";
		for(int i=0;i<Topiclist.size();i++) {
			toname += Topiclist.get(i).getToname() + ",";
		}
		toname=toname.substring(0,toname.length()-1);
		ValueObject voquest = new ValueObject(qno, qstem, toname, null);
		int rsqt = questionservice.modifyQstem(voquest);
		if (rs0 > 0 || rs1 > 0 || rs2 > 0 || rs3 > 0 || rsqt > 0) {
			map.put("msg", "�޸ĳɹ���");
		} else {
			map.put("msg", "�޸�ʧ�ܣ�");
		}
		return map;
	}
   /**
    * 
    * @return �Զ����ӵı��
    */
	public String createIno() {
		int rs=questionservice.getItemId();
		if(rs==0) {
			int rs1=questionservice.addItemId();
		}
		int num = questionservice.showMaxItemId() + 1;
		return "XTY-ITEM-" + getMedia(num) + num;
	}

	public String createQno() {
		int rs=questionservice.getQuestionCount();
		if(rs==0) {
			int rs1=questionservice.addQuestionId();//��Ϊ�ձ����һ��id=1������
		}
		int num = questionservice.showMaxQuesId() + 1;
		int rs2=questionservice.removeQuestionfirst();//��id=1����һ������ɾ��
		return "XTY-QUESTION-" + getMedia(num) + num;
	}

	private String getMedia(int num) {
		String media = "";
		if (num < 10) {
			media += "000";
		} else if (num < 100) {
			media += "00";
		} else if (num < 1000) {
			media += "0";
		}
		return media;
	}
   /**
    * 
    * @param valueObj
    * @return ��������
    */
	@RequestMapping(value = "Question_add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> question_add(ValueObj2 valueObj) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		list.add(valueObj.getItem0());
		list.add(valueObj.getItem1());
		list.add(valueObj.getItem2());
		list.add(valueObj.getItem3());
		String qno = createQno();
		int rs = 0;
		int rs1 = 0;
		int rinof = Integer.parseInt(valueObj.getRino());
		// ���ĸ�ѡ��ѭ������item����
		for (int i = 0; i < 4; i++) {
			String ino0 = createIno();
			if (i == rinof) {
				valueObj.setRino(ino0);
			}
			String item = list.get(i);
			ValueObject VOj = new ValueObject(qno, item, ino0);
			rs = questionservice.additem(VOj);
			rs1 = questionservice.addqi(VOj);
		}

		String qstem = valueObj.getQstem();
		
		List<Topic>  Topiclist=questionservice.getTopicLayCheck();
		String toname="";
		for(int i=0;i<Topiclist.size();i++) {
			toname += Topiclist.get(i).getToname() + ",";
		}
		toname=toname.substring(0,toname.length()-1);
		ValueObject VOj2 = new ValueObject(qno, qstem, toname, null);
		int rs2 = questionservice.addqstem(VOj2);
		
		for(int i=0;i<Topiclist.size();i++) {
			String tono=Topiclist.get(i).getTono();
			int rs3 = questionservice.addqto(qno,tono);
		}
		
		int rs4 = questionservice.addStatus(valueObj);
		if (rs > 0) {
			map.put("msg", "��ӳɹ���");
		} else {
			map.put("msg", "���ʧ�ܣ�");
		}
		return map;

	}
   /**
    * 
    * @param q
    * @return ����ؼ�����������
    */
	@RequestMapping(value = "question_find", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> searchExams(Question q) {
		List<Question> list = questionservice.listQues(q);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("count", questionservice.getTotal());
		map.put("data", list);
		return map;

	}
	/**
	 * 
	 * @param t
	 * @return ���Ӻͱ༭ҳ���֪ʶ����������
	 */
	@RequestMapping(value = "question_topic_find", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> searchTopic(Topic t) {
		List<Topic> list = questionservice.listTopic(t);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("count", questionservice.getTopicTotal());
		map.put("data", list);
		return map;

	}
    /**
     * 
     * @return ��������ӻ��߱༭��ť��ʱ�����̰�֪ʶ����е�LayCheckedȫ����Ϊ0
     */
	@RequestMapping(value = "get_topic", method = RequestMethod.GET)
	public ModelAndView getTopics() {
		ModelAndView mav = new ModelAndView();
		int rs = questionservice.modifyLayChecked();
		mav.setViewName("/Views/add_question.jsp");
		return mav;

	}
   /**
    * 
    * @param tono
    * @param LAY_CHECKED
    * @return  ֪ʶ�㸴ѡ�����¼�
    */
	@RequestMapping(value = "Checked_topic_Add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> question_checked_add(String tono,Boolean LAY_CHECKED) {
		Map<String, Object> map = new HashMap<String, Object>();
		int rs = questionservice.add_checked(tono,LAY_CHECKED);
		if (rs > 0) {
			map.put("msg", "��ӳɹ���");
		} else {
			map.put("msg", "���ʧ�ܣ�");
		}
		return map;

	}
}
