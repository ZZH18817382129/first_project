package com.ryan.controller;

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

import com.ryan.entity.Classes;
import com.ryan.entity.Student;
import com.ryan.entity.TestPaper;
import com.ryan.service.StudentService;

@Controller
@SessionAttributes(value = {"number","classes"})
public class StudentControl {

	@Autowired
	private StudentService stuService;

	public StudentService getStuService() {
		return stuService;
	}

	public void setStuService(StudentService stuService) {
		this.stuService = stuService;
	}
	
	//���ɱ�ź���
		 public String createIno() {
	   	  int num = stuService.showMaxStuId()+1;
			return "2019"+getMedia(num)+num;
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

		 //��������ǰ��ȡ���
			@RequestMapping(value="BeforAddStu")
			public ModelAndView LessNumber() {
				
				ModelAndView mv = new ModelAndView();
	             
				List<Classes> list = stuService.showClasses();
				
					mv.addObject("number",createIno());
					mv.addObject("classes",list);
					mv.setViewName("/Views/stu_add.jsp");
				
				return mv;
			}
	
	@RequestMapping(value = "stu_list", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> stu_list(int page,int limit){
		
		List<Student> list = stuService.listBypage(page, limit);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", 0);
		map.put("count", stuService.totalRow());
		map.put("data", list);
		return map;
		
	}
	
	@RequestMapping(value="stu_delm")
	@ResponseBody
	public Map<String,Object>  stu_delm(String id){
		int rs = stuService.removeById(id);
		Map<String,Object> map = new HashMap<String, Object>();
		if(rs>0) {
			map.put("code", rs);
			map.put("msg","ɾ���ɹ�");
			
		}else {
			map.put("code", 0);
			map.put("msg","ɾ��ʧ��");
		}
		return map;
	}
	
	@RequestMapping(value="stu_delo",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object>  topic_del(int id){
		int rs =stuService.removeOne(id);
		Map<String,Object> map = new HashMap<String, Object>();
		if(rs>0) {
			map.put("code", 1);
			map.put("msg","ɾ���ɹ�");
			
		}else {
			map.put("code", 0);
			map.put("msg","ɾ��ʧ��");
		}
		return map;
	}
	
	@RequestMapping(value="stu_upd",method=RequestMethod.GET)
	public ModelAndView pre_stu_upd(int id) {
		ModelAndView mv = new ModelAndView();
		Student stu = stuService.searchStu(id);
	    mv.addObject("student" ,stu);
	    mv.setViewName("Views/stu_edit.jsp");
	    return mv;
	}
	
	@RequestMapping(value="stu_upd",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> stu_upd(Student s){
		int rs = stuService.modify(s);
		Map<String,Object> map = new HashMap<String, Object>();
		if(rs>0) {
			map.put("code", 1);
			map.put("msg", "�޸ĳɹ�");
		}else {
			map.put("code", 0);
			map.put("msg", "�޸�ʧ��");
		}
		return map;
	}
	
	/*
	 * @RequestMapping("stu_add") public ModelAndView stu_add(Student s){
	 * ModelAndView mv = new ModelAndView(); int rs=stuService.addStu(s);
	 * Map<String,Object> map = new HashMap<>(); if(rs>0) { map.put("code", rs);
	 * map.put("msg", "��ӳɹ�"); }else { map.put("code", 0); map.put("msg", "���ʧ��"); }
	 * mv.addObject("student" ,s); mv.setViewName("Views/stuAdd.jsp"); return mv; }
	 */
	
	@RequestMapping("stu_add")
	@ResponseBody
	public Map<String, Object> stu_add(Student s) {
		int i = stuService.addStu(s);
		stuService.modifyStime(s.getSno());
		Map<String, Object> m = new HashMap<String, Object>();
		if(i>0) {
			m.put("code", i);
			m.put("msg", "��ӳɹ�");
			
		}else {
			m.put("code", 0);
			m.put("msg", "���ʧ��");
		}
		return m;
	}
	
	@RequestMapping(value = "stu_find", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> searchStus(Student s){
		List<TestPaper> list = stuService.listStus(s);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("count", stuService.totalRow());
		map.put("data", list);
		return map;
		
	}

}
