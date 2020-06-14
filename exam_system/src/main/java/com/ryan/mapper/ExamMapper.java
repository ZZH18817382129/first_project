package com.ryan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ryan.entity.Classes;
import com.ryan.entity.Exam;
import com.ryan.entity.Student;
import com.ryan.entity.Teacher;
import com.ryan.entity.TestPaper;

public interface ExamMapper {

	int selectTotal();
	List<Exam>  selectByPage(@Param("offset") int pagess, @Param("limit") int limit, String sclass);
	
	int deleteById(@Param("sid") String sid);
	int deleteOne(@Param("id") int id);
	
	Exam selectOne(@Param("id") int id);
	int updateOne(@Param("e") Exam e);
	
	//
	int selectTot();
	List<TestPaper>  selectByTest(@Param("offset") int offset, @Param("limit") int limit);
	int insertExam(@Param("e") Exam e);
	List<Exam> selectExams(@Param("e") Exam e);
	List<TestPaper> selectTests(@Param("t") TestPaper t);
	
	
	List<Classes> selectByClass(@Param("offset") int offset, @Param("limit") int limit);
	int selectTotalRows();
	//List<Classes> selectCla(@Param("cnos") String cnos);
	//int insertExamCla(@Param("eno") String eno, @Param("clas") List<Classes> clas);
	
	
	int selectMaxExamId();
	List<Classes> selectClasses(@Param("c") Classes c);
	
	List<Student> listEstu(@Param("offset") int offset, @Param("limit") int limit);
	int selectStuTotal();
	int insertEXamStu(@Param("eno") String eno, @Param("sno") String sno);
	int updateExamStu(@Param("eno") String eno, @Param("sno") String sno, @Param("LAY_CHECKED") String LAY_CHECKED);
	int selectEnoSno(@Param("eno") String eno, @Param("sno") String sno);
	List<Student> listEstus(@Param("offset") int offset, @Param("limit") int limit, @Param("eno") String eno);
	List<Student> questionlistLC(@Param("offset") int offset, @Param("limit") int limit, @Param("eno") String eno, @Param("sclass") String sclass);
	
	int deleteSstu(@Param("snos") String snos, @Param("eno") String eno);
	int deleteOstu(@Param("sno") String sno, @Param("eno") String eno);
	
	List<Student> selectEstu(@Param("offset") int offset, @Param("limit") int limit, @Param("eno") String eno);
	int selectEstuTotal(@Param("eno") String eno);
	List<Teacher> selectTeacher();
	List<Student> selectExamStu(@Param("offset") int offset, @Param("limit") int limit, @Param("sno") String sno, @Param("sclass") String sclass, @Param("eno") String eno);

	
}
