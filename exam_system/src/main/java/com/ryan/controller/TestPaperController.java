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

import com.ryan.entity.Major;
import com.ryan.entity.Question;
import com.ryan.entity.TestPaper;
import com.ryan.entity.Topic;
import com.ryan.service.TestPaperService;


@Controller
@SessionAttributes(value = {"tpno","tpno1","tp","question","number","mlist","managetpno","tlist"})
public class TestPaperController {
	@Autowired
	private TestPaperService testpaperservice;
    //�����Ծ�
	@RequestMapping(value = "ListTestPaper")
	@ResponseBody
	public Map<String, Object> TestPaperList(int page,int limit) {

		List<TestPaper> list = testpaperservice.ltp(page,limit);
        for(TestPaper t : list) {
        	if(t.getDisable().equals("1")) {
        		t.setDisable("������");
        	}else{
        		t.setDisable("����");
        	}
        }
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("code", 0);
		m.put("msg", "");
		m.put("count", testpaperservice.totalRow());
		m.put("data", list);
		return m;
	}
	
	
	 
    public String createIno() {
    	  int num = testpaperservice.showMaxTpId()+1;
		return "XTY-TP"+getMedia(num)+num;
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
   
	 //�����Ծ�ǰ��ȡ���&�γ��б�֪ʶ���б�
		@RequestMapping(value="BeforeTestPaper")
		public ModelAndView TpNumber() {
			List<Major> mlist = testpaperservice.listmajor();
			List<Topic> tlist = testpaperservice.beforaddlisttopic();
			ModelAndView mo = new ModelAndView();
              
				mo.addObject("mlist",mlist);
				mo.addObject("tlist",tlist);
				
				mo.addObject("number",createIno() );
				mo.setViewName("/Views/add_testpaper.jsp");
			
			return mo;
		}

    //����ʱ��������
	@RequestMapping(value = "ListTestQuestion")
	@ResponseBody
	public Map<String, Object> QuestionList(int page,int limit,String tpno,String toname) {
		List<Question> list_all = testpaperservice.questionlist(page,limit,toname);//���е�����
		for(Question q:list_all) {//����limit����������Ϊfalse���뵽��������
			if(testpaperservice.selectTpnoQno(tpno,q.getQno())>0) {
				continue;
			}
			testpaperservice.addTpQue(q.getQno(), tpno);
		}
		List<Question> list_ques = new ArrayList<Question>();
		if(toname==null||toname.equals("")) {
			list_ques = testpaperservice.questionlistLC(page,limit,tpno);
		} else {
			list_ques = testpaperservice.questionlistLC1(page,limit,tpno,toname);
		}
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("code", 0);
		m.put("msg", "");
		m.put("count", testpaperservice.questiontotalRow());
		m.put("data", list_ques);
		return m;
	}
	
    //ɾ���Ծ��ࣩ
	@RequestMapping(value = "DeletesTestPaper")
	@ResponseBody
	public Map<String, Object> DelMany(String tpno) {
		int i = testpaperservice.removetestpaper(tpno);
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
	
	

    //ɾ���Ծ�����
	@RequestMapping(value = "DelTestPaerOne")
	@ResponseBody
	public Map<String, Object> DelOne(String tpno) {
		int i = testpaperservice.removetestpaperone(tpno);
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

	//��֤��������
	@RequestMapping(value = "SelectTpname")
	@ResponseBody
	public boolean Findtpname(String tpname){
		
		
		TestPaper tp  = testpaperservice.selecttpname(tpname);
		String aa = tp.getTpname();
		
		if(aa.equals("")){
			return true;
		}else {
			return false;
		}
	}
    //�Ծ����
	@RequestMapping(value = "AddTestPaper")
	@ResponseBody
	public Map<String, Object> Addques(String tpno , String pname , String pman ,String ids) {
        
		int ii = testpaperservice.add1testpaper(tpno,pname,pman,ids);
		int i =testpaperservice.removetpque(tpno);
		Map<String, Object> m = new HashMap<String, Object>();
		if(i+ii>0) {
			m.put("code", 0);
			m.put("msg", "��ӳɹ�");
			
		}else {
			m.put("code", 0);
			m.put("msg", "���ʧ��");
		}
		return m;
	}

	 //�鿴�Ծ�
	@RequestMapping(value="LookTestPaper")
	public ModelAndView LookOver(String tpno) {

		ModelAndView mo = new ModelAndView();

			mo.addObject("tpno", tpno);
			mo.setViewName("/Views/check_testpaper.jsp");
		
		return mo;
	}


	//�����Ծ�����
	@RequestMapping(value = "ListQuestion")
	@ResponseBody
	public Map<String, Object> TpQuestionList(String tpno) {

		List<Question> list = testpaperservice.tpquestionlist(tpno);

		Map<String, Object> m = new HashMap<String, Object>();
		m.put("code", 0);
		m.put("msg", "");
		m.put("data", list);
		return m;
	}
	
	//�޸��Ծ�
	@RequestMapping(value="ModifyTestPaper")
	public ModelAndView ModifyTp(String tpno) {
        TestPaper tp = testpaperservice.seltp(tpno);
        List<Question> que = testpaperservice.selque(tpno);
		ModelAndView mo = new ModelAndView();
		    mo.addObject("question", que);
		    mo.addObject("tp", tp);
			mo.addObject("tpno1", tpno);
			mo.setViewName("/Views/modify_testpaper.jsp");
		
		return mo;
	}
	
	@RequestMapping(value="UpdateTestPaper")
	@ResponseBody
	public Map<String, Object> ModifyLesson(String tpno,String ids,String pman,String pname) {
		int i = testpaperservice.modifytp(tpno,ids,pman,pname);
		
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
	//�Ծ�������ѯ
	@RequestMapping(value = "FindTp", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> searchTp(TestPaper tp){
		List<TestPaper> list = testpaperservice.listTp(tp);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("count", "");
		map.put("data", list);
		return map;
		
	}
	
	
	//����������ѯ
		/*@RequestMapping(value = "FindQue")
		@ResponseBody
		public Map<String, Object> searchQue(String mname ,String toname){
			
			List<Question> list_all = testpaperservice.listTp(page,limit);//���е�����
			for(Question q:list_all) {//����limit����������Ϊfalse���뵽��������
				if(testpaperservice.selectTpnoQno(tpno,q.getQno())>0) {
					continue;
				}
				testpaperservice.addTpQue(q.getQno(), tpno);
			}
			List<Question> list_ques = testpaperservice.questionlistLC(page,limit,tpno);
			Map<String, Object> m = new HashMap<>();
			m.put("code", 0);
			m.put("msg", "");
			m.put("count", testpaperservice.questiontotalRow());
			m.put("data", list_ques);
			List<Question> list = testpaperservice.listTp(mname,toname);
			return map;
			
		}*/
		
		/**
		 * �����������ҳ�����ת
		 * @return ������ͼ
		 */
		@RequestMapping(value="ManageTestPaper")
		public ModelAndView ManageQuestion(String tpno) {
	       
			ModelAndView mo = new ModelAndView();
			mo.addObject("managetpno", tpno) ; 
		    mo.setViewName("/Views/manage_tpque.jsp");
			
			return mo;
		}
		
		/**
		 * ������������
		 * @return ���ش�����data����valueֵ��map��װ����
		 */
		@RequestMapping(value = "TransferQuestion")
		@ResponseBody
		public Map<String, Object> transferfind(String tpno){
			
			Map<String,Object> map = new HashMap<String, Object>();
			
			List<Question> list = testpaperservice.questionlist1();
			List<String> ltpno = testpaperservice.selecttpque(tpno);
			
		
			List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
			for(Question q : list) {
				Map<String,Object> m = new HashMap<String, Object>();
				m.put("value", q.getQno());
				m.put("title", q.getQstem());
				data.add(m);
			}
			map.put("data", data);
			map.put("value",ltpno);
			return map;
			
		}
		
		/**
		 * ���洩���Ĳ���
		 * @return ������ʾ��Ϣ
		 */
		
		@RequestMapping(value="TransferTpQuestion")
		@ResponseBody
		public Map<String, Object> SaveTransfer(String tpno,String data1) {
			
			int i = testpaperservice.addtransferquestion(tpno,data1);
			
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
		
//		@RequestMapping(value="TransferTpQuestion")
//		@ResponseBody
//		public Map<String, Object> createTestPaperAtuo(String tono,String tpno) {
//			
//			int result = testpaperservice.createTestPaperAtuo(tono,tpno);
//			Map<String, Object> m = new HashMap<>();
//			if(result>0) {
//				m.put("code", 0);
//				m.put("msg", "�޸ĳɹ�");
//				
//			}else {
//				m.put("code", 0);
//				m.put("msg", "�޸�ʧ��");
//			}
//			return m;
//		}
		/**
		 * �����Ծ��и�ѡ��Ĵ���ѡ��״̬��
		 * @return ������ʾ��Ϣ
		 */
		@RequestMapping(value="CheckedAdd")
		@ResponseBody
		public Map<String, Object> Selected(String qno,String tpno,String LAY_CHECKED) {
			int i = 0;
			if(LAY_CHECKED.equals("true")) {
				i = testpaperservice.modifyTpQue(qno,tpno);
			}else if(LAY_CHECKED.equals("false")) {
				i = testpaperservice.modifytpQue1(qno,tpno);
				
			}
			Map<String, Object> m = new HashMap<String, Object>();
			if(i>0) {
				m.put("code", 0);
				m.put("msg", "�޸ĳɹ�");
				
			}else {
				m.put("code", 0);
				m.put("msg", "�޸�ʧ��");
			}
			m.put("que_nums",testpaperservice.searchQuesNumByTpno(tpno));
			return m;
		}
		/**
		 * �����Ծ��и�ѡ��Ĵ�����ѡ״̬��
		 * @return ������ʾ��Ϣ
		 */
		@RequestMapping(value="CheckedAdds")
		@ResponseBody
		public Map<String, Object> Selecteds(String qnos,String tpno,String LAY_CHECKED) {
			String[] strs = qnos.split(",");
			int result = 0;
			if(LAY_CHECKED.equals("true")) {
				for(int i=0;i<strs.length;i++) {
					result += testpaperservice.modifyTpQue(strs[i],tpno);
				}
			} else if(LAY_CHECKED.equals("false")) {
				for(int i=0;i<strs.length;i++) {
					result += testpaperservice.modifytpQue1(strs[i],tpno);
				}
			}
			Map<String, Object> m = new HashMap<String, Object>();
			if(result>0) {
				m.put("code", 0);
				m.put("msg", "�޸ĳɹ�");
				
			}else {
				m.put("code", 0);
				m.put("msg", "�޸�ʧ��");
			}
			m.put("que_nums",testpaperservice.searchQuesNumByTpno(tpno));
			return m;
		}
		/**
		 * �����Ծ�
		 * @return ������ʾ��Ϣ
		 */
		@RequestMapping(value = "DisableTp")
		@ResponseBody
		public Map<String, Object> DisableTpOne(String tpno) {
			int i = testpaperservice.modifytpdisable(tpno);
			Map<String, Object> m = new HashMap<String, Object>();
			if(i>0) {
				m.put("code", 0);
				m.put("msg", "���óɹ�");
				
			}else {
				m.put("code", 0);
				m.put("msg", "����ʧ��");
			}
			
			return m;
		}
		
		/**
		 * �����Ծ�
		 * @return ������ʾ��Ϣ
		 */
		@RequestMapping(value = "StartTp")
		@ResponseBody
		public Map<String, Object> StartTpOne(String tpno) {
			int i = testpaperservice.modifytpstart(tpno);
			Map<String, Object> m = new HashMap<String, Object>();
			if(i>0) {
				m.put("code", 0);
				m.put("msg", "����ɹ�");
				
			}else {
				m.put("code", 0);
				m.put("msg", "����ʧ��");
			}
			
			return m;
		}
		
	public void setTestpaperservice(TestPaperService testpaperservice) {
		this.testpaperservice = testpaperservice;
	}
}
