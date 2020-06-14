package com.ryan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ryan.entity.Major;
import com.ryan.entity.Question;
import com.ryan.entity.TestPaper;
import com.ryan.entity.Topic;

public interface TestPaperMapper {

	List<TestPaper> findtp(@Param("page") int page, @Param("limit") int limit);

	List<Question> findquestion(@Param("page") int page, @Param("limit") int limit, @Param("toname") String toname);

	int deletetestpaper(@Param("tpno") String tpno);

	TestPaper beforedit(@Param("tpno") String tpno);

	int insertques(@Param("qno") String qno, @Param("number") String number);

	int addtestpaper(@Param("tpno") String tpno, @Param("pname") String pname, @Param("pman") String pman);

	int addquestion(@Param("tpque") List<Question> tpque, @Param("tpno") String tpno);

	int deletetpque(@Param("tpno") String tpno);

	List<Question> selectquestion(@Param("ids") String ids);

	int deletetptp(@Param("tpno") String tpno);

	int deletetpques(@Param("tpno") String tpno);

	List<Question> selquestion(@Param("tpno") String tpno);

	List<Question> tpselquestion(@Param("tpno") String tpno);

	TestPaper findtestpaper(@Param("tpno") String tpno);

	List<Question> findque(@Param("tpno") String tpno);

	int updatetp(@Param("pman") String pman, @Param("pname") String pname, @Param("tpno") String tpno);

	List<TestPaper> selecttes(@Param("tp") TestPaper tp);

	List<Question> selectques(@Param("toname") String toname);

	int selectmaxtp();

	List<Major> listmajor1();

	int totalrowtp();

	int deltpone(@Param("tpno") String tpno);

	int deltponetpque(@Param("tpno") String tpno);

	List<String> findlisttpque(@Param("tpno") String tpno);

	int trdeltpque(@Param("tpno") String tpno);

	List<TestPaper> trseltpque(@Param("tpno") String tpno);

	List<Question> trfindquestion(@Param("tpno1") String tpno1);

	int traddquetp(@Param("tpno") String tpno, @Param("top") List<Question> top);

	List<Question> findquestionByTono(@Param("tono") String tono, @Param("tpno") String tpno);

	int totalrowques();

	List<Question> findquestion1();

	List<Topic> beforlisttopic();

	int addTpQue(@Param("qno") String qno, @Param("tpno") String tpno);

	int removetestpaperquestion(@Param("tpno") String tpno, @Param("qno") String qno);

	List<Question> questionlistLC(@Param("offset") int offset, @Param("limit") int limit, @Param("tpno") String tpno);

	int modifyTpQue(@Param("qno") String qno, @Param("tpno") String tpno);

	int removetpque(@Param("tpno") String tpno);

	int modifytpQue1(@Param("qno") String qno, @Param("tpno") String tpno);

	int selectTpnoQno(@Param("tpno") String tpno, @Param("qno") String qno);

	List<Question> questionlistLC1(@Param("page") int page, @Param("limit") int limit, @Param("tpno") String tpno, @Param("toname") String toname);

	int searchQuesNumByTpno(@Param("tpno") String tpno);

	int modifytpdisable(@Param("tpno") String tpno);

	int modifytpstart(@Param("tpno") String tpno);

	TestPaper selecttpname(@Param("pname") String pname);

	

	

	

	
    
}
