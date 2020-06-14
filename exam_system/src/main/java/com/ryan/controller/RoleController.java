package com.ryan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ryan.entity.Role;
import com.ryan.service.RoleService;
import com.ryan.service.UserService;

@Controller
public class RoleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService us;
	private static String uno=UserController.NOW_LOGINED_UNO;
	@RequestMapping(value = "role_lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> role_lists(int page, int limit) {
		List<Role> list = roleService.roleByPage(page, limit);
		roleService.roletotalsize();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", " ");
		map.put("data", list);
		map.put("count", roleService.roletotalsize());
		us.addDiary(uno, "�û�"+uno+"��������������б�ҳ��", us.getTime());
		return map;
	}

	@RequestMapping(value = "role_del", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> prev_role_del(int id) {
		int rs = roleService.removeRoleOne(id);
		Map<String, Object> map = new HashMap<String, Object>();
		if (rs > 0) {
			map.put("msg", "ɾ���ɹ�");
		} else {
			map.put("msg", "ɾ��ʧ��");
		}
		us.addDiary(uno, "�û�"+uno+"ɾ����idΪ"+id+"�����", us.getTime());
		return map;
	}

	@RequestMapping(value = "role_del", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> role_del(String id) {
		int rs = roleService.removerole(id);
		Map<String, Object> map = new HashMap<String, Object>();
		if (rs > 0) {
			map.put("msg", "����ɾ���ɹ�");
		} else {
			map.put("msg", "����ɾ��ʧ��");
		}
		us.addDiary(uno, "�û�"+uno+"ɾ����id��"+id+"�ڵ��������", us.getTime());
		return map;
	}

	@RequestMapping(value = "role_upd")
	public ModelAndView prev_role_upd(int id) {
		ModelAndView mv = new ModelAndView();
		Role rs = roleService.searchRoleOne(id);
		mv.addObject("Role", rs);
		mv.setViewName("/Views/role_oform.jsp");
		us.addDiary(uno, "�û�"+uno+"��ת���޸������Ϣҳ��", us.getTime());
		return mv;
	}

	@RequestMapping(value = "do_role_upd")
	@ResponseBody
	public Map<String, Object> role_upd(Role role) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (roleService.searchRoleByIden(role.getRidentity()) != null) {
			map.put("msg", "�Ѵ��ڸ��ֽ�ɫ!");
			map.put("have", "yes");
		} else {
			int rs = roleService.modifyRole(role);
			if (rs > 0) {
				map.put("msg", "�޸ĳɹ�");
			} else {
				map.put("msg", "�޸�ʧ��,�����³���!");
			}
		}
		us.addDiary(uno, "�û�"+uno+"�޸�idΪ"+role.getId()+"�������Ϊ:"+role.getRidentity(), us.getTime());
		return map;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public UserService getUs() {
		return us;
	}

	public void setUs(UserService us) {
		this.us = us;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
}
