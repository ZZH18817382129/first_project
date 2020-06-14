package com.ryan.service;

import java.util.List;

import com.ryan.entity.Role;

public interface RoleService {

	List<Role> getUserRoles(Integer id);

	List<Role> roleByPage(int page, int limit);

	int roletotalsize();

	int removerole(String sid);

	int removeRoleOne(int id);

	Role searchRoleOne(int id);

	int modifyRole(Role o);

	int addrole(Role t);

	Role searchRoleByIden(String ridentity);

	
	
}
