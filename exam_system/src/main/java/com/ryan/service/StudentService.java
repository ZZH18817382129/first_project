package com.ryan.service;

import java.util.List;

import com.ryan.entity.Classes;
import com.ryan.entity.Student;
import com.ryan.entity.TestPaper;

public interface StudentService {

	List<Student> listBypage(int page, int limit);
	int totalRow();
	
	int removeById(String id);
	int removeOne(int id);
	
	Student searchStu(int id);
	int modify(Student s);
	
	int addStu(Student s);
	List<TestPaper> listStus(Student s);
	int showMaxStuId();
	List<Classes> showClasses();
	void modifyStime(String sno);
	 
}
