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
import com.ryan.entity.Exam;
import com.ryan.entity.Student;
import com.ryan.entity.Teacher;
import com.ryan.entity.TestPaper;
import com.ryan.service.ExamService;


@Controller
@SessionAttributes(value = { "number", "teacher"})
public class ExamControl {

	@Autowired
	private ExamService examService;

	public ExamService getExamService() {
		return examService;
	}

	public void setExamService(ExamService examService) {
		this.examService = examService;
	}

	
	
	// ���ɱ�ź���
	public String createIno() {
		int num = examService.showMaxExamId() + 1;
		return "XTY-EXAM-" + getMedia(num) + num;
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
	 * @return ��������ǰ��ȡ��ŵ�exam_add.jsp
	 */
	
	@RequestMapping(value = "BeforAddExam")
	public ModelAndView LessNumber() {

		ModelAndView mo = new ModelAndView();
		
		List<Teacher> list = examService.searchTeacher();

		mo.addObject("number", createIno());
		mo.addObject("teacher",list);
		mo.setViewName("/Views/exam_add.jsp");

		return mo;
	}

	/**
	 * @param page
	 * @param limit
	 * @param sclass
	 * @return ��ѯ���п���
	 */
	@RequestMapping(value = "exam_list", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> exam_list(int page, int limit,String sclass) {

		List<Exam> list = examService.listBypage(page, limit, sclass);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", 0);
		map.put("data", list);
		map.put("count", examService.totalRow());
		return map;

	}

	/**
	 * 
	 * @param id
	 * @return ����idsɾ����������
	 */
	@RequestMapping(value = "exam_delm")
	@ResponseBody
	public Map<String, Object> stu_delm(String id) {
		int rs = examService.removeById(id);
		Map<String, Object> map = new HashMap<String, Object>();
		if (rs > 0) {
			map.put("code", rs);
			map.put("msg", "ɾ���ɹ�");

		} else {
			map.put("code", 0);
			map.put("msg", "ɾ��ʧ��");
		}
		return map;
	}

	/**
	 * 
	 * @param id
	 * @return ����idɾ��һ������
	 */
	@RequestMapping(value = "exam_delo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> topic_del(int id) {
		int rs = examService.removeOne(id);
		Map<String, Object> map = new HashMap<String, Object>();
		if (rs > 0) {
			map.put("code", 1);
			map.put("msg", "ɾ���ɹ�");

		} else {
			map.put("code", 0);
			map.put("msg", "ɾ��ʧ��");
		}
		return map;
	}

	/**
	 * 
	 * @param id
	 * @return ����id�õ�Ҫ���ĵ�ԭʼ����
	 */
	@RequestMapping(value = "exam_upd", method = RequestMethod.GET)
	public ModelAndView pre_stu_upd(int id) {
		ModelAndView mv = new ModelAndView();
		List<Teacher> list = examService.searchTeacher();
		Exam e = examService.searchExam(id);
		mv.addObject("exam", e);
		mv.addObject("teacher", list);
		mv.setViewName("Views/exam_edit.jsp");
		return mv;
	}

	/**
	 * 
	 * @param e
	 * @return ��Ҫ�޸ĵĿ���д�����ݿ�
	 */
	@RequestMapping(value = "exam_upd", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> exam_upd(Exam e) {
		int rs = examService.modify(e);
		Map<String, Object> map = new HashMap<String, Object>();
		if (rs > 0) {
			map.put("code", 1);
			map.put("msg", "�޸ĳɹ�");
		} else {
			map.put("code", 0);
			map.put("msg", "�޸�ʧ��");
		}
		return map;
	}

	/**
	 * 
	 * @param page
	 * @param limit
	 * @return ��ʾ�����Ծ�
	 */
	@RequestMapping(value = "test_list", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> test_list(int page, int limit) {

		List<TestPaper> list = examService.listTest(page, limit);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("count", examService.total());
		map.put("data", list);
		return map;

	}

	/**
	 * 
	 * @param e
	 * @return ��������
	 */
	@RequestMapping(value = "exam_add")
	@ResponseBody
	public Map<String, Object> exam_add(Exam e) {

		int i = examService.addExam(e);

		Map<String, Object> m = new HashMap<String, Object>();
		if (i > 0) {
			m.put("code", i);
			m.put("msg", "��ӳɹ�");

		} else {
			m.put("code", 0);
			m.put("msg", "���ʧ��");
		}

		return m;
	}

	/**
	 * 
	 * @param e
	 * @return ģ����ѯʱ��Ⱦ�������
	 */
	@RequestMapping(value = "exam_find", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> searchExams(Exam e) {
		List<Exam> list = examService.listExams(e);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("count", examService.total());
		map.put("data", list);
		return map;

	}

	/**
	 * 
	 * @param t
	 * @return
	 */
	@RequestMapping(value = "test_find", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> searchTests(TestPaper t) {
		List<TestPaper> list = examService.listTests(t);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("count", examService.total());
		map.put("data", list);
		return map;

	}

	@RequestMapping(value = "class_list", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> class_list(int page, int limit) {

		List<Classes> list = examService.listClass(page, limit);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("count", examService.totalRows());
		map.put("data", list);
		return map;

	}

	@RequestMapping(value = "class_find", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> searchClasses(Classes c) {
		List<Classes> list = examService.listClasses(c);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("count", examService.totalRows());
		map.put("data", list);
		return map;

	}
	//��ת����ӿ�������
	@RequestMapping("forward_to_examAddStu")
	public ModelAndView da(String eno) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("eno", eno);
		mav.setViewName("Views/exam_addstu.jsp");
		return mav;
	}
	
	//��ת���༭��������
	@RequestMapping("forward_to_examStu")
	public ModelAndView data(String eno) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("eno", eno);
		mav.setViewName("Views/exam_stu.jsp");
		return mav;
	}
	
	
	//��������ʱ����ѧ��
	@RequestMapping(value = "estu_list", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> estu_list(int page,int limit ,String eno,String sclass){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Student> list = examService.listEstu(page, limit);
		if(eno!=null && (!eno.equals(""))) {
			for(Student s : list) {
				if(examService.selectEnoSno(eno,s.getSno())>0) {
					continue;
				}
				examService.addExamStu(eno,s.getSno());
			}
			List<Student> slist = examService.listEstus(page,limit,eno);
			if(sclass==null||sclass.equals("")) {
				slist = examService.listEstus(page,limit,eno);
			} else {
				slist = examService.listExamS(page,limit,eno,sclass);
			}
			map.put("data", slist);
		} else {
			map.put("data", list);
		}
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", examService.StutotalRow());
		return map;
	}
		
		@RequestMapping(value="addExamStu")
		@ResponseBody
		public Map<String, Object> Selected(String eno,String sno,String LAY_CHECKED) {
			int i = examService.modifyExamStu(eno,sno,LAY_CHECKED);
			Map<String, Object> m = new HashMap<String, Object>();
			if(i>0) {
				m.put("code", 0);
				m.put("msg", "�޸ĳɹ�");
				
			}else {
				m.put("code", 0);
				m.put("msg", "�޸�ʧ��");
			}
			//m.put("que_nums",examService.searchQuesNumByTpno(eno));
			return m;
		}
		/**
		   * �����и�ѡ��Ĵ�����ѡ״̬��
		 * @return ������ʾ��Ϣ
		 */
		@RequestMapping(value="addExamStus")
		@ResponseBody
		public Map<String, Object> Selecteds(String eno,String snos,String LAY_CHECKED) {
			String[] strs = snos.split(",");
			int result = 0;
			for(int i=0;i<strs.length;i++) {
				result += examService.modifyExamStu(eno,strs[i],LAY_CHECKED);
			}
			Map<String, Object> m = new HashMap<String, Object>();
			if(result>0) {
				m.put("msg", "�޸ĳɹ�");
				
			}else {
				m.put("msg", "�޸�ʧ��");
			}
			return m;
		}
		
		//ɾ����������
		@RequestMapping(value = "estu_delm")
		@ResponseBody
		public Map<String, Object> estu_delm(String sno,String eno) {
			int rs = examService.removeSstu(sno,eno);
			Map<String, Object> map = new HashMap<String, Object>();
			if (rs > 0) {
				map.put("code", rs);
				map.put("msg", "ɾ���ɹ�");

			} else {
				map.put("code", 0);
				map.put("msg", "ɾ��ʧ��");
			}
			return map;
		}

		//ɾ��һ������
		@RequestMapping(value = "estu_delo", method = RequestMethod.GET)
		@ResponseBody
		public Map<String, Object> estu_del(String sno ,String eno) {
			int rs = examService.removeOstu(sno ,eno);
			Map<String, Object> map = new HashMap<String, Object>();
			if (rs > 0) {
				map.put("code", 1);
				map.put("msg", "ɾ���ɹ�");

			} else {
				map.put("code", 0);
				map.put("msg", "ɾ��ʧ��");
			}
			return map;
		}
		
		@RequestMapping(value = "show_estu", method = RequestMethod.GET)
		@ResponseBody
		public Map<String, Object> examstu_list(int page, int limit,String eno) {

			List<Student> list = examService.listEstu(page, limit, eno);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", 0);
			map.put("msg", 0);
			map.put("count", examService.stutotalRow(eno));
			map.put("data", list);
			return map;

		}
}
