package com.ryan.service.impl;

import java.util.List;

import com.ryan.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryan.entity.Major;
import com.ryan.entity.Topic;
import com.ryan.mapper.LessonMapper;

@Service
public class LessonServiceImpl implements LessonService {

	@Autowired 
    private LessonMapper lessonmapper;

	public LessonServiceImpl() {
		super();
	}
	public LessonServiceImpl(LessonMapper lessonmapper) {
		super();
		this.lessonmapper = lessonmapper;
	}
	
	
	public List<Major> listlesson(int page,int limit) {
		
		return lessonmapper.findless((page-1)*limit,limit);
	}
	
	public int addlesson(Major major,String ids) {
		int i = lessonmapper.insertless(major);
		//List<Topic> top = lessonmapper.seachtopic(ids);
		
		//int a = lessonmapper.inserttop(major,top);
		
		return i;
	}
	
	public int removelesson(String mno) {
		int i = lessonmapper.deleteless(mno);
		int a = lessonmapper.deletemajto(mno);
		return i+a;
	}
	
	public Major sellesson(int id) {
		
		return lessonmapper.selectless(id);
	}
	
	public int modifylesson(Major major) {
		
		return lessonmapper.updateless(major);
	}
	
	public List<Major> listLess(Major major) {
		if(major.getMname()!=null&&(!major.getMname().equals("null"))) {
			major.setMname("%"+major.getMname()+"%");
		}
		if(major.getMmodule()!=null&&(!major.getMmodule().equals("null"))) {
			major.setMmodule("%"+major.getMmodule()+"%");
		}
		if(major.getMlevel()!=null&&(!major.getMlevel().equals("null"))) {
			major.setMlevel("%"+major.getMlevel()+"%");
		}
		if(major.getMstem()!=null&&(!major.getMstem().equals("null"))) {
			major.setMstem("%"+major.getMstem()+"%");
		}
		return lessonmapper.selectlesson(major);
	}
	
	public List<Topic> listTopic() {
		
		return lessonmapper.selecttopic();
	}
	
	public int showMaxlessonId() {
	
		return lessonmapper.selectMaxlessonId();
	}
	
	public int totalRow() {
		
		return lessonmapper.findtotalrow();
	}
	
	public int removelessonone(String mno) {
		int i = lessonmapper.deleteonemaj(mno);
		int a = lessonmapper.deleteonemajto(mno);		
		return i+a;
	}
	
	public List<String> selectmajto(String mno) {
		
		return lessonmapper.findmajto(mno);
	}
	
	public int addlessontopic(String mno, String to) {
		int i = 0;
		int a = 0;
		if(to == "") {
			 a = lessonmapper.deletemajto1(mno);
			
		}else {
			List<Topic> t = lessonmapper.selectmajto(mno);
			if(t.size()>0) {
				 a = lessonmapper.deletemajto1(mno);
			}
			String tono1 = to.substring(0, to.length()-1);
			List<Topic> top = lessonmapper.findtopic1(tono1);
			if(top.size() > 0) {
				 i = lessonmapper.addmajto1(mno,top);
			}
		}
		
	
		return a+i;
	}
	
   
	
    
}
