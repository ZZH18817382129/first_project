package com.ryan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ryan.entity.Classes;
import com.ryan.entity.Student;
import com.ryan.entity.TestPaper;

public interface StudentMapper {

	int selectTotal();
	List<Student>  selectByPage(@Param("pagess") int pagess, @Param("limit") int limit);
	
	int deleteById(@Param("sid") String sid);
	int deleteOne(@Param("id") int id);
	
	Student selectOne(@Param("id") int id);
	int updateOne(@Param("s") Student s);
	
	int insertStudent(@Param("s") Student s);
	List<TestPaper> selectStus(@Param("s") Student s);
	int selectMaxStuId();
	List<Classes> selectClasses();
	void updateStime(@Param("sno") String sno, @Param("stime") String now_time);
}
