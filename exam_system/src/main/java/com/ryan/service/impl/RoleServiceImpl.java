package com.ryan.service.impl;

import java.util.List;

import com.ryan.service.RoleService;
import com.ryan.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleService role_service;

	public RoleServiceImpl(){};
	
	public RoleServiceImpl(RoleService role_service) {
		this.role_service = role_service;
	}
	
	
	public List<Role> getUserRoles(Integer id) {
		return role_service.getUserRoles(id);
	}
	

	public RoleService getRole_service() {
		return role_service;
	}

	public void setRole_service(RoleService role_service) {
		this.role_service = role_service;
	}

	
	public List<Role> roleByPage(int page, int limit) {
		return role_service.roleByPage(page, limit);
	}

	
	public int roletotalsize() {
		return role_service.roletotalsize();
	}

	
	public int removerole(String sid) {
		return role_service.removerole(sid);
	}

	
	public int removeRoleOne(int id) {
		return role_service.removeRoleOne(id);
	}

	
	public Role searchRoleOne(int id) {
		return role_service.searchRoleOne(id);
	}

	
	public int modifyRole(Role o) {
		return role_service.modifyRole(o);
	}

	
	public int addrole(Role t) {
		return role_service.addrole(t);
	}

	
	public Role searchRoleByIden(String ridentity) {
		return role_service.searchRoleByIden(ridentity);
	}

}
