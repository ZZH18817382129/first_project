package com.ryan.service;

import java.util.List;

import com.ryan.entity.Classes;
import com.ryan.entity.Teacher;

public interface ClassesService {
	List<Classes> showAll(int page, int size);
	int getTotal();
	Classes getOne(int id);
	int removeMore(String sid);
	int removeOne(int id);
	int modify(Classes c);
	int addOne(Classes c);
	List<Classes> listClass(Classes c);
	List<Teacher> listTeachers();
	int showMaxClassId();
	int getCnames(Classes classes);
	int getClassCount();
	int addClassid();
	int removeClassfirst();
}
