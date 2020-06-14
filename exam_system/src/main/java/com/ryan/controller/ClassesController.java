package com.ryan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ryan.entity.Classes;
import com.ryan.entity.Teacher;
import com.ryan.service.ClassesService;

/**
 * @author stu02
 *
 */
@Controller
@SessionAttributes(value = {"teachers"})
public class ClassesController {
	@Autowired
	private ClassesService classesService;

	public ClassesController() {
		super();
	}

	public ClassesController(ClassesService classesService) {
		super();
		this.classesService = classesService;
	}
	/**
	 * 
	 * @param page  ��ǰ��ҳ��ҳ�������ڼ�ҳ
	 * @param limit ÿҳ��ʾ���ݵ�����
	 * @return  ��ʾ�༶�������������
	 */
	@RequestMapping("Classes_list")
	@ResponseBody
	public Map<String, Object> classes_list(int page, int limit) {
		List<Classes> list = classesService.showAll(page, limit);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", classesService.getTotal());
		map.put("data", list);
		return map;
	}
/**
 * 
 * @param id  ����б�ѡ�е�������id
 * @return  ɾ��һ������
 * 
 */
	@RequestMapping(value = "Classes_del", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> classes_del(int id) {

		int rs =classesService.removeOne(id);
		Map<String, Object> map = new HashMap<String, Object>();

		if (rs > 0)
			map.put("msg", "ɾ���ɹ���");
		else
			map.put("msg", "ɾ��ʧ�ܣ�");
		return map;
	}
	
	/**
	 * 
	 * @param id ����б�ѡ�е�������id
	 * @return ɾ����������
	 */
	@RequestMapping(value = "Classes_delmore", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> classes_delmore(String id) {

		int rs =classesService.removeMore(id);
		Map<String, Object> map = new HashMap<String, Object>();

		if (rs > 0)
			map.put("msg", "ɾ���ɹ���");
		else
			map.put("msg", "ɾ��ʧ�ܣ�");
		return map;
	}
    /**
     * 
     * @return �༶�������������ʵ��
     */
	public String createCno() {
		int rs=classesService.getClassCount();
		if(rs==0) {
			int rs1=classesService.addClassid();//����Ϊ�ձ������һ��id=1������
		}
		int num = classesService.showMaxClassId()+1;
		int rs2=classesService.removeClassfirst();//ɾ��id=1����һ������
		return "XTY-Class-"+getMedia(num)+num;
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
	
	/**
	 * 
	 * @return ��������������ʾ����ʦ
	 */
	@RequestMapping(value = "get_teachers",method = RequestMethod.GET)
	public ModelAndView getTeachers(){
		List<Teacher> list = classesService.listTeachers();
		ModelAndView mav = new ModelAndView();
		mav.addObject("teachers", list);
		mav.setViewName("/Views/add_class.jsp");
		return mav;
		
	}
	/**
	 * 
	 * @param classes ǰ̨ҳ�洫���������ݷ�װ�İ༶����
	 * @return �����༶
	 */
	@RequestMapping(value = "Classes_add", method = RequestMethod.POST)
	@ResponseBody 
	public Map<String, Object> classes_add(Classes classes){
		Map<String, Object> map = new HashMap<String, Object>();
		String cno=createCno();
		classes.setCno(cno);
		int cxk=classesService.getCnames(classes);//�жϱ����Ƿ����н�Ҫ��ӵİ༶����
		
		if (cxk>0) {
			map.put("msg", "�༶���Ʋ����ظ�");
		}else {
			int rs = classesService.addOne(classes);
			map.put("msg", "��ӳɹ���");
		}
		return map;
		
	}
	/**
	 * 
	 * @param id
	 * @return �༭ҳ��
	 */
	@RequestMapping(value = "Classes_upd", method = RequestMethod.GET)
	public ModelAndView prev_classes_upd(int id) {
		ModelAndView mv = new ModelAndView();
		List<Teacher> list = classesService.listTeachers();
		mv.addObject("teachers", list);
		Classes c= classesService.getOne(id);

		mv.addObject("classes", c);
		mv.setViewName("/Views/modify_class.jsp");
		return mv;
	}
	 /**
	  * 
	  * @param classes
	  * @param result
	  * @return ҳ��༭��֮�󱣴����
	  */
	@RequestMapping(value = "Classes_upd", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> classes_upd(@Valid Classes classes,BindingResult result) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!result.hasErrors()) {
		int rs = classesService.modify(classes);

		if (rs > 0)
			map.put("msg", "�޸ĳɹ���");
		else
			map.put("msg", "�޸�ʧ�ܣ�");
		}else {
			StringBuilder sb=new StringBuilder();
			for(FieldError fe : result.getFieldErrors()) {
				sb.append(fe.getField());
			}
			map.put("msg",sb.toString() );
			map.put("error", true);
		}
		return map;
	}
	/**
	 * 
	 * @param c
	 * @return ҳ����������
	 */
	@RequestMapping(value = "classes_find", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> searchExams(Classes c){
		List<Classes> list = classesService.listClass(c);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("count", classesService.getTotal());
		map.put("data", list);
		return map;
		
	}
}
