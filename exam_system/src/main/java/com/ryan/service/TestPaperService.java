package com.ryan.service;

import java.util.List;

import com.ryan.entity.Major;
import com.ryan.entity.Question;
import com.ryan.entity.TestPaper;
import com.ryan.entity.Topic;

public interface TestPaperService {
  List<TestPaper> ltp(int page, int limit);

  List<Question> questionlist(int page, int limit, String toname);

  int deletetp(String tpno);

  TestPaper beformodify(String tpno);

  int add1testpaper(String tpno, String pname, String pman, String ids);

  int removetestpaper(String tpno);

  List<Question> selectquestion(String tpno);

  List<Question> tpquestionlist(String tpno);

  TestPaper seltp(String tpno);

  List<Question> selque(String tpno);

  int modifytp(String tpno, String ids, String pman, String pname);

  List<TestPaper> listTp(TestPaper tp);

 
  int showMaxTpId();

  List<Question> listTp(String mname, String toname);

  List<Major> listmajor();

  int totalRow();

  int removetestpaperone(String tpno);

  List<String> selecttpque(String tpno);

  int addtransferquestion(String tpno, String data1);

  int questiontotalRow();

  List<Question> questionlist1();

  List<Topic> beforaddlisttopic();

int addTpQue(String qno, String tpno);

int removetestpaperquestion(String tpno, String qno);

List<Question> questionlistLC(int page, int limit, String tpno);

int modifyTpQue(String qno, String tpno);

int removetpque(String tpno);

int modifytpQue1(String qno, String tpno);

int selectTpnoQno(String tpno, String qno);

List<Question> questionlistLC1(int page, int limit, String tpno, String toname);

int searchQuesNumByTpno(String tpno);

int modifytpdisable(String tpno);

int modifytpstart(String tpno);

TestPaper selecttpname(String pname);

//int createTestPaperAtuo(String tono, String tpno);


  
  
}
