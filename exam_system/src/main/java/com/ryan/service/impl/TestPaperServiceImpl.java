package com.ryan.service.impl;

import java.util.List;

import com.ryan.service.TestPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryan.entity.Major;
import com.ryan.entity.Question;
import com.ryan.entity.TestPaper;
import com.ryan.entity.Topic;
import com.ryan.mapper.TestPaperMapper;

@Service
public class TestPaperServiceImpl implements TestPaperService {
	@Autowired
	private TestPaperMapper testpapermapper;

	public TestPaperServiceImpl() {
		super();
	}

	public TestPaperServiceImpl(TestPaperMapper testpapermapper) {
		super();
		this.testpapermapper = testpapermapper;
	}

	
	public List<TestPaper> ltp(int page,int limit) {
       List<TestPaper> list = testpapermapper.findtp((page-1)*limit,limit);
 		return list;
	}

	
	public List<Question> questionlist(int page,int limit,String toname) {
		return testpapermapper.findquestion((page-1)*limit,limit,toname);
	}

	
	public int deletetp(String tpno) {
		int i = testpapermapper.deletetestpaper(tpno);
		int d = testpapermapper.deletetpque(tpno);
		return i + d;
	}

	
	public TestPaper beformodify(String tpno) {
		return testpapermapper.beforedit(tpno);
	}

	
	public int add1testpaper(String tpno, String pname, String pman, String ids) {
		int i = testpapermapper.addtestpaper(tpno, pname, pman);
//		String idd = ids.substring(1, ids.length());
//		List<Question> tpque = testpapermapper.selectquestion(idd);
//		int a = testpapermapper.addquestion(tpque, tpno);
		return i;
	}

	
	public int removetestpaper(String tpno) {
		int i = testpapermapper.deletetptp(tpno);
		int d = testpapermapper.deletetpques(tpno);
		return i + d;
	}

	
	public List<Question> selectquestion(String tpno) {
		return testpapermapper.selquestion(tpno);
	}

	
	public List<Question> tpquestionlist(String tpno) {
		return testpapermapper.tpselquestion(tpno);
	}

	
	public TestPaper seltp(String tpno) {
		TestPaper tp = testpapermapper.findtestpaper(tpno);

		return tp;
	}

	
	public List<Question> selque(String tpno) {
		List<Question> que = testpapermapper.findque(tpno);
		return que;
	}

	
	public int modifytp(String tpno, String ids, String pman, String pname) {
		int i = testpapermapper.updatetp(pman, pname, tpno);

		return i;
	}

	
	public List<TestPaper> listTp(TestPaper tp) {
		if (tp.getTpname() != null && (!tp.getTpname().equals("null"))) {
			tp.setTpname("%" + tp.getTpname() + "%");
		}
        
		if (tp.getTpteacher() != null && (!tp.getTpteacher().equals("null"))) {
			tp.setTpteacher("%" + tp.getTpteacher() + "%");
		}
		return testpapermapper.selecttes(tp);
	}

	
	public int showMaxTpId() {

		return testpapermapper.selectmaxtp();
	}

	
	public List<Question> listTp(String mname, String toname) {

		String a = "%" + toname + "%";

		return testpapermapper.selectques(a);

	}

	
	public List<Major> listmajor() {

		return testpapermapper.listmajor1();
	}

	
	public int totalRow() {

		return testpapermapper.totalrowtp();
	}

	
	public int removetestpaperone(String tpno) {
		int i = testpapermapper.deltpone(tpno);
		int a = testpapermapper.deltponetpque(tpno);
		return i + a;
	}

	
	public List<String> selecttpque(String tpno) {

		return testpapermapper.findlisttpque(tpno);
	}

	
	public int addtransferquestion(String tpno, String data1) {
		int i = 0;
		int a = 0;
		System.out.println(data1.equals(""));
		if (data1.equals("") ) {
			a = testpapermapper.trdeltpque(tpno);

		} else {
			System.out.println(data1);
			List<TestPaper> t = testpapermapper.trseltpque(tpno);
			if (t.size() > 0) {
				a = testpapermapper.trdeltpque(tpno);
			}
			String tpno1 = data1.substring(0, data1.length() - 1);
			System.out.println("tpno1:"+tpno1);
			List<Question> top = testpapermapper.trfindquestion(tpno1);
			for(Question q:top) {
				System.out.println(q.getQno());
			}
			if (top.size() > 0) {
				i = testpapermapper.traddquetp(tpno, top);
			}
		}

		return a + i;
	}

	
	public int questiontotalRow() {
		
		return testpapermapper.totalrowques();
	}

	
	public List<Question> questionlist1() {
		
		return testpapermapper.findquestion1();
	}

	
	public List<Topic> beforaddlisttopic() {
		
		return testpapermapper.beforlisttopic();
	}

	
	public int addTpQue(String qno, String tpno) {
		return testpapermapper.addTpQue(qno,tpno);
	}

	
	public int removetestpaperquestion(String tpno, String qno) {
		
		return testpapermapper.removetestpaperquestion(tpno,qno);
	}

	
	public List<Question> questionlistLC(int page, int limit, String tpno) {
		return testpapermapper.questionlistLC((page-1)*limit,limit,tpno);
	}

	
	public int modifyTpQue(String qno, String tpno) {
		
		return testpapermapper.modifyTpQue(qno,tpno);
	}

	
	public int removetpque(String tpno) {
	
		return testpapermapper.removetpque(tpno);
	}

	
	public int modifytpQue1(String qno, String tpno) {
		return testpapermapper.modifytpQue1(qno,tpno);
	}

	
	public int selectTpnoQno(String tpno, String qno) {
		return testpapermapper.selectTpnoQno(tpno,qno);
	}

	
	public List<Question> questionlistLC1(int page, int limit, String tpno, String toname) {
		return testpapermapper.questionlistLC1((page-1)*limit,limit,tpno,toname);
	}

	
	public int searchQuesNumByTpno(String tpno) {
		return testpapermapper.searchQuesNumByTpno(tpno);
	}

	
	public int modifytpdisable(String tpno) {
		
		return testpapermapper.modifytpdisable(tpno);
	}

	
	public int modifytpstart(String tpno) {
		
		return testpapermapper.modifytpstart(tpno);
	}

	
	public TestPaper selecttpname(String pname) {
		
		return testpapermapper.selecttpname(pname);
	}


//	
//	public int createTestPaperAtuo(String tono, String tpno) {
//		int result = 0;
//		for (int i = 0; i < 50; i++) {// ����ѭ������
//			// �����ݿ��в�ѯ���в��ڴ��Ծ��ԇ�}
//			List<Question> ques = testpapermapper.findquestionByTono(tono, tpno);
//			int index = (int) (Math.random() * ques.size()); // ��������±�
//			result += testpapermapper.insertQno(ques.get(index).getQno(),tpno); // ��ȡ������Ⲣ����
//			result++;
//		}
//		//50�ζ�����ɹ��f��ԇ�����ɳɹ�
//		return result;
//	}

}
