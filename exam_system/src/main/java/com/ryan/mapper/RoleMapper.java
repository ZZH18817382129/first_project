package com.ryan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ryan.entity.Role;


public interface RoleMapper {

	List<Role> RoleByPage(@Param("offset") int offset, @Param("limit") int limit);
	int RoleTotal();
	List<Role> selectByUserId(@Param("id") Integer id);
	int dellotsRole(@Param("sid") String sid);
	int dellotsRoleOne(@Param("id") int id);
	Role selectRoleOne(@Param("id") int id);
	int UpdRoleOne(@Param("o") Role o);
	int insertOnerole(@Param("t") Role t);
	Role selectRoleByIden(@Param("ridentity") String ridentity);
	

}
