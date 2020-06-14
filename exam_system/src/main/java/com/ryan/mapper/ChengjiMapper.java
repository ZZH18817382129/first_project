package com.ryan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ryan.entity.Chengji;
import com.ryan.entity.Classes;

public interface ChengjiMapper {
	List<Chengji> selectByPage(@Param("offset") int offset, @Param("limit") int limit);
	int selectTotal(); 
	double selectzf(@Param("sclass") String sclass);//�ܷ�
	int selectclassCnum(@Param("sclass") String sclass);//������
	int selectclassCnum6(@Param("sclass") String sclass);
	int selectclassCnum9(@Param("sclass") String sclass);
	double selectMax(@Param("sclass") String sclass);
	double selectMin(@Param("sclass") String sclass);
	List<Classes> selectClass();
}