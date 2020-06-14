package com.ryan.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ryan.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryan.entity.Classes;
import com.ryan.entity.Student;
import com.ryan.entity.TestPaper;
import com.ryan.mapper.StudentMapper;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper stuMapper;
	
	public StudentMapper getStuMapper() {
		return stuMapper;
	}

	public void setStuMapper(StudentMapper stuMapper) {
		this.stuMapper = stuMapper;
	}

	public StudentServiceImpl(StudentMapper stuMapper) {
		super();
		this.stuMapper = stuMapper;
	}

	
	public List<Student> listBypage(int page, int limit) {
		return stuMapper.selectByPage((page-1)*limit, limit);
	}

	
	public int totalRow() {
		return stuMapper.selectTotal();
	}

	
	public int removeById(String id) {
		return stuMapper.deleteById(id);
	}

	
	public int removeOne(int id) {
		return stuMapper.deleteOne(id);
	}

	
	public Student searchStu(int id) {
		return stuMapper.selectOne(id);
	}

	
	public int modify(Student s) {
		return stuMapper.updateOne(s);
	}

	
	public int addStu(Student s) {
		return stuMapper.insertStudent(s);
	}

	
	public List<TestPaper> listStus(Student s) {

		if(s.getSname() != null && (!s.getSname().equals(null))) {
		s.setSname("%"+s.getSname()+"%");}
		if(s.getSno() != null && (!s.getSno().equals(null))) {
		s.setSno("%"+s.getSno()+"%");}
		if(s.getSclass() != null && (!s.getSclass().equals(null))) {
		s.setSclass("%"+s.getSclass()+"%");}
		if(s.getSschool() != null && (!s.getSschool().equals(null))) {
		s.setSschool("%"+s.getSschool()+"%");}
		return stuMapper.selectStus(s);
	}

	
	public int showMaxStuId() {
		return stuMapper.selectMaxStuId();
	}

	
	public List<Classes> showClasses() {
		return stuMapper.selectClasses();
	}

	
	public void modifyStime(String sno) {
		Date date = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
		String now_time = dateFormat.format(date);
		stuMapper.updateStime(sno,now_time);
	}

}
