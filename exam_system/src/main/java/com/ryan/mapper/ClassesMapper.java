package com.ryan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ryan.entity.Classes;
import com.ryan.entity.Teacher;

public interface ClassesMapper {
   List<Classes> selectAllClasses(@Param("offset") int offset, @Param("size") int size);
   int selectTotal();
   Classes selectOne(@Param("id") int id);
   int deleteMore(@Param("sid") String sid);
   int deleteOne(@Param("id") int id);
   int updateOne(@Param("c") Classes c);
   int insertClass(@Param("c") Classes c);
   List<Classes> selectClass(@Param("c") Classes c);
   List<Teacher> selectTeacher();
   int selectMaxClassId();
   int selectCnames(@Param("c") Classes classes);
   int selectClassCount();
   int insertClassid();
   int deleteClassfirst();
}
