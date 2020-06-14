package com.ryan.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ryan.entity.Major;
import com.ryan.entity.Topic;
import com.ryan.service.LessonService;

@Controller
@SessionAttributes(value = {"major","number","tlist","mno"})
public class LessonController {
	@Autowired
	private LessonService lessonservice;

	    //�����γ�
		@RequestMapping(value = "ListLesson")
		@ResponseBody
		public Map<String, Object> LessonList(int page,int limit) {

			List<Major> list = lessonservice.listlesson(page, limit);

			Map<String, Object> m = new HashMap<String, Object>();
			m.put("code", 0);
			m.put("msg", "");
			m.put("count", lessonservice.totalRow());
			m.put("data", list);
			return m;
		}
		//���ɱ�ź���
		 public String createIno() {
	    	  int num = lessonservice.showMaxlessonId()+1;
			return "XTY-MAJ"+getMedia(num)+num;
		}
	    private String getMedia(int num) {
			String media = "";
			if(num<10) {
				media += "000";
			}else if(num<100) {
				media += "00";
			}else if(num<1000) {
				media += "0";
			}
			return media;
		}

		//�����γ�ǰ��ȡ���
			@RequestMapping(value="BeforLesson")
			public ModelAndView LessNumber() {
				
				ModelAndView mo = new ModelAndView();
	              
					mo.addObject("number",createIno());
					mo.setViewName("/Views/add_lesson.jsp");
				
				return mo;
			}
		//�γ����
		@RequestMapping(value = "AddLesson")
		@ResponseBody
		public Map<String, Object> AddLesson(Major major,String ids) {
			int i = lessonservice.addlesson(major,ids);
			
			Map<String, Object> m = new HashMap<String, Object>();
			if(i>0) {
				m.put("code", 0);
				m.put("msg", "��ӳɹ�");
				
			}else {
				m.put("code", 0);
				m.put("msg", "���ʧ��");
			}
			return m;
		}

		//ɾ���γ̣��ࣩ
		@RequestMapping(value = "DeletesLesson")
		@ResponseBody
		public Map<String, Object> DelMany(String mno) {
			int i = lessonservice.removelesson(mno);
			Map<String, Object> m = new HashMap<String, Object>();
			if(i>0) {
				m.put("code", 0);
				m.put("msg", "ɾ���ɹ�");
				
			}else {
				m.put("code", 0);
				m.put("msg", "ɾ��ʧ��");
			}
			
			return m;
		}
		
		//ɾ���γ̣�����
				@RequestMapping(value = "delLesson")
				@ResponseBody
				public Map<String, Object> Delone(String mno) {
					int i = lessonservice.removelessonone(mno);
					Map<String, Object> m = new HashMap<String, Object>();
					if(i>0) {
						m.put("code", 0);
						m.put("msg", "ɾ���ɹ�");
						
					}else {
						m.put("code", 0);
						m.put("msg", "ɾ��ʧ��");
					}
					
					return m;
				}
		
		//�޸Ŀγ�
		@RequestMapping(value="ModifyLesson")
		public ModelAndView BeforModifyLesson(int id) {
	        Major mj = lessonservice.sellesson(id);
	        
			ModelAndView mo = new ModelAndView();
			    
				mo.addObject("major", mj);
				mo.setViewName("/Views/modify_lesson.jsp");
			
			return mo;
		}
		
		@RequestMapping(value="UpdateLesson")
		@ResponseBody
		public Map<String, Object> ModifyLesson(Major major) {
			int i = lessonservice.modifylesson(major);
			
			Map<String, Object> m = new HashMap<String, Object>();
			if(i>0) {
				m.put("code", 0);
				m.put("msg", "�޸ĳɹ�");
				
			}else {
				m.put("code", 0);
				m.put("msg", "�޸�ʧ��");
			}
			return m;
		}
		
		//������ѯ
		
		@RequestMapping(value = "FindLess")
		@ResponseBody
		public Map<String, Object> searchLess(Major major){
			List<Major> list = lessonservice.listLess(major);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", 0);
			map.put("count", "");
			map.put("data", list);
			return map;
			
		}
		
//		//��ѯ֪ʶ��
//				@RequestMapping(value = "ListTopic")
//				@ResponseBody
//				public Map<String, Object> searchtopic(){
//					List<Topic> list = lessonservice.listTopic();
//					Map<String, Object> map = new HashMap<>();
//					map.put("code", 0);
//					map.put("count", "");
//					map.put("data", list);
//					return map;
//					
//				}
				
		//֪ʶ�������ת&��ѯ����֪ʶ��
				@RequestMapping(value="knowLesson")
				public ModelAndView beforknowLesson(String mno) {
			       // List<Topic> list = lessonservice.selectmajto(mno);
					
					ModelAndView mo = new ModelAndView();
//					for(Topic t : list){
//						
//						mo.addObject("tlist", t.getTono());
//					}
					mo.addObject("mno",mno);
						mo.setViewName("/Views/know_lesson.jsp");
					
					return mo;
				}
				//��ѯ֪ʶ��
				@RequestMapping(value = "ListLessons")
				@ResponseBody
				public Map<String, Object> searchtopics(String mno){
					
					Map<String,Object> map = new HashMap<String, Object>();
					
					List<Topic> list = lessonservice.listTopic();
					List<String> ltono = lessonservice.selectmajto(mno);
					
				
					List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
					for(Topic t : list) {
						Map<String,Object> m = new HashMap<String, Object>();
						m.put("value", t.getTono());
						m.put("title", t.getToname());
						data.add(m);
					}
					map.put("data", data);
					map.put("value",ltono);
					return map;
					
				}
				//����֪ʶ��
				@RequestMapping(value="AddLessonTopic")
				@ResponseBody
				public Map<String, Object> addLessontopic(String mno,String data1) {
					
					int i = lessonservice.addlessontopic(mno,data1);
					
					Map<String, Object> m = new HashMap<String, Object>();
					if(i>0) {
						m.put("code", 0);
						m.put("msg", "�޸ĳɹ�");
						
					}else {
						m.put("code", 0);
						m.put("msg", "�޸�ʧ��");
					}
					return m;
				}
				
				
	public void setLessonservice(LessonService lessonservice) {
		this.lessonservice = lessonservice;
	}	

}
