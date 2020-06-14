package com.ryan.service.impl;

import java.util.List;

import com.ryan.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryan.entity.Classes;
import com.ryan.entity.Teacher;
import com.ryan.mapper.ClassesMapper;

@Service
public class ClassesServiceimpl implements ClassesService {
	@Autowired
	private ClassesMapper classesmapper;
	

	public ClassesServiceimpl() {
		super();
	}

	public ClassesServiceimpl(ClassesMapper classesmapper) {
		super();
		this.classesmapper = classesmapper;
	}

	
	public List<Classes> showAll(int page, int size) {
		
		return classesmapper.selectAllClasses((page-1)*size, size);
	}

	
	public int getTotal() {
		
		return classesmapper.selectTotal();
	}

	
	public Classes getOne(int id) {
	
		return classesmapper.selectOne(id);
	}

	
	public int removeMore(String sid) {
		
		return classesmapper.deleteMore(sid);
	}

	
	public int removeOne(int id) {
		
		return classesmapper.deleteOne(id);
	}

	
	public int modify(Classes c) {
	
		return classesmapper.updateOne(c);
	}

	
	public int addOne(Classes c) {
		
		return classesmapper.insertClass(c);
	}

	
	public List<Classes> listClass(Classes c) {
		c.setCname("%"+c.getCname()+"%");
        c.setCadviser("%"+c.getCadviser()+"%");
        c.setCteacher("%"+c.getCteacher()+"%");
		return classesmapper.selectClass(c);
	}

	
	public List<Teacher> listTeachers() {
		return classesmapper.selectTeacher();
	}

	
	public int showMaxClassId() {
		return classesmapper.selectMaxClassId();
	}

	
	public int getCnames(Classes classes) {
		return classesmapper.selectCnames(classes);
	}

	
	public int getClassCount() {
		// TODO Auto-generated method stub
		return classesmapper.selectClassCount();
	}

	
	public int addClassid() {
		// TODO Auto-generated method stub
		return classesmapper.insertClassid();
	}

	
	public int removeClassfirst() {
		// TODO Auto-generated method stub
		return classesmapper.deleteClassfirst();
	}

}
