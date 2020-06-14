package com.ryan.service;

import java.util.List;

import com.ryan.entity.Classes;
import com.ryan.entity.Exam;
import com.ryan.entity.Student;
import com.ryan.entity.Teacher;
import com.ryan.entity.TestPaper;

public interface ExamService {

	List<Exam> listBypage(int page, int limit, String sclass);
	int totalRow();
	
	int removeById(String id);
	int removeOne(int id);
	
	Exam searchExam(int id);
	int modify(Exam e);
	
	int total();
	List<TestPaper> listTest(int page, int limit);
	
	int addExam(Exam e);
	
	List<Exam> listExams(Exam e);
	
	List<TestPaper> listTests(TestPaper t);
	
	List<Classes> listClass(int page, int limit);
	int totalRows();
	
	int showMaxExamId();
	
	List<Classes> listClasses(Classes c);
	
	List<Student> listEstu(int page, int limit);//��������ѧ��
	int StutotalRow();
	int addExamStu(String eno, String sno);//��ӽ����ϵ��
	List<Student> listEstus(int page, int limit, String eno);//�ӹ�ϵ���ѧ����ͬ��Ⱦ����

	int modifyExamStu(String eno, String sno, String lAY_CHECKED);
	int modifyExamStus(String eno, String sno);
	int selectEnoSno(String eno, String sno);
	List<Student> listExamS(int page, int limit, String eno, String sclass);
	
	int removeSstu(String sno, String eno);
	int removeOstu(String sno, String eno);
	
	//�鿴�μ�ĳ���Ե����п���
	List<Student> listEstu(int page, int limit, String eno);
	int stutotalRow(String eno);
	List<Teacher> searchTeacher();
	
	//�༭����ʱ�༶��ѯ
	List<Student> listExamStu(int page, int limit, String sno, String sclass, String sname, String eno);
	
}
