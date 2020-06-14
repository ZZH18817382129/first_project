package com.ryan.service.impl;

import java.util.List;

import com.ryan.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryan.entity.Classes;
import com.ryan.entity.Exam;
import com.ryan.entity.Student;
import com.ryan.entity.Teacher;
import com.ryan.entity.TestPaper;
import com.ryan.mapper.ExamMapper;

/**
 * ���ܱ���
 * 
 * @author ������   2019-10-24
 * @version 1.0
 * @see XxxService
 * @since exam-2.0
 */
@Service
public class ExamServiceImpl implements ExamService {

	@Autowired
	private ExamMapper examMapper;
	
	public ExamMapper getExamMapper() {
		return examMapper;
	}

	public void setExamMapper(ExamMapper examMapper) {
		this.examMapper = examMapper;
	}

	public ExamServiceImpl(ExamMapper examMapper) {
		super();
		this.examMapper = examMapper;
	}

	
	public List<Exam> listBypage(int page, int limit,String sclass) {
		return examMapper.selectByPage((page-1)*limit, limit,sclass);
	}

	
	public int totalRow() {
		return examMapper.selectTotal();
	}

	
	public int removeById(String id) {
		return examMapper.deleteById(id);
	}

	
	public int removeOne(int id) {
		return examMapper.deleteOne(id);
	}

	
	public Exam searchExam(int id) {
		return examMapper.selectOne(id);
	}

	
	public int modify(Exam e) {
		return examMapper.updateOne(e);
	}

	
	public int total() {
		return examMapper.selectTot();
	}

	
	public List<TestPaper> listTest(int page, int limit) {
		return examMapper.selectByTest((page-1)*limit, limit);
	}

	
	public int addExam(Exam e) {
		return examMapper.insertExam(e);
	}

	
	public List<Exam> listExams(Exam e) {
		e.setEname("%"+e.getEname()+"%");
		e.setEno("%"+e.getEno()+"%");
		return examMapper.selectExams(e);
	}

	
	public List<TestPaper> listTests(TestPaper t) {
		t.setTpname("%"+t.getTpname()+"%");
		return examMapper.selectTests(t);
	}

	
	public List<Classes> listClass(int page, int limit) {
		return examMapper.selectByClass((page-1)*limit, limit);
	}

	
	public int totalRows() {
		return examMapper.selectTotalRows();
	}

	
	public int showMaxExamId() {
		return examMapper.selectMaxExamId();
	}

	
	public List<Classes> listClasses(Classes c) {
		c.setCname("%"+c.getCname()+"%");
		return examMapper.selectClasses(c);
	}


	
	public List<Student> listEstu(int page, int limit) {
		return examMapper.listEstu((page-1)*limit, limit);
	}

	
	public List<Student> listEstus(int page, int limit, String eno) {
		return examMapper.listEstus((page-1)*limit,limit,eno);
	}


	
	public int modifyExamStus(String eno, String sno) {
		return 0;
	}

	
	public int selectEnoSno(String eno, String sno) {
		return examMapper.selectEnoSno(eno,sno);
	}

	
	public int addExamStu(String eno, String sno) {
		return examMapper.insertEXamStu(eno, sno);
	}

	
	public List<Student> listExamS(int page, int limit, String eno, String sclass) {
		return examMapper.questionlistLC((page-1)*limit,limit,eno,sclass);
	}

	
	public int modifyExamStu(String eno, String sno, String lAY_CHECKED) {
		return examMapper.updateExamStu(eno,sno,lAY_CHECKED);
	}

	
	public int StutotalRow() {
		return examMapper.selectStuTotal();
	}

	
	public int removeSstu(String sno,String eno) {
		return examMapper.deleteSstu(sno,eno);
	}

	
	public int removeOstu(String sno,String eno) {
		return examMapper.deleteOstu(sno,eno);
	}

	
	public List<Student> listEstu(int page, int limit, String eno) {
		Student s = new Student();
		s.setSclass("%"+s.getSclass()+"%");
		return examMapper.selectEstu((page-1)*limit,limit,eno);
	}

	
	public int stutotalRow(String eno) {
		return examMapper.selectEstuTotal(eno);
	}

	
	public List<Teacher> searchTeacher() {
		return examMapper.selectTeacher();
	}

	
	public List<Student> listExamStu(int page, int limit,String sno, String sclass, String sname, String eno) {
		Student s = new Student();
		s.setSclass("%"+s.getSclass()+"%");
		return examMapper.selectExamStu((page-1)*limit,limit,sno,sclass,eno);
	}


	/**
	 * �鿴ĳ�༶�μ�ĳ�����Ե�ѧ��
	 * @param eno ���Ա��
	 *
	 * 
	 * @return  �μ�ĳ�ο��Եİ༶���Ϊeno������ѧ��
	 */


}
