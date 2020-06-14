package com.ryan.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ryan.entity.Exam;
import com.ryan.entity.Item;
import com.ryan.entity.Privilege;
import com.ryan.entity.Question;
import com.ryan.entity.Role;
import com.ryan.entity.User;
import com.ryan.service.RoleService;
import com.ryan.service.UserService;

@Controller
@SessionAttributes(value = { "user", "exam", "items", "now_id", "grades", "errorQues", "LOGINED_UNO" })
public class UserController {
	public static String NOW_LOGINED_UNO = "";
	@RequestMapping(value = "/user_login", method = RequestMethod.POST)
	public ModelAndView usersLogin() {
		String uno = "";
		String password="";
		User u = new User();
		u.setUno(uno);
		u.setPassword(password);
		List<User> users = us.usersLogin(u);// �����û���������ȥ���ݿ��в�ѯ,������صĽ�����ϴ�С����0��˵���ɵ�½
		ModelAndView mav = new ModelAndView();
		for (User temp : users) { // ȷ��û�б����õ��˺ŵĵ�¼����
			if (temp.getUstatus().equals("0")) {
				mav.addObject("msg", "���˺�������ʱ���ܵ�¼,����������ϵ������Ա!");
				mav.setViewName("Views/user_login.jsp");
			}
		}
		if (users.size() > 0) {
			NOW_LOGINED_UNO = u.getUno();
			us.addDiary(u.getUno(), "�û�" + u.getUno() + "��¼", us.getTime());// ������־
			User u1 = users.get(0);
			mav.addObject("LOGINED_UNO", u1.getUno());
			if (u1.getIdentity().equals("ѧ��")) { // �����֤Ϊѧ��
				u1.setUname(us.searchSnameBySno(u1.getUno()));
				mav.setViewName("Views/exam_index.jsp");
			} else {
				List<Privilege> privs = new ArrayList<Privilege>();
				for (User temp : users) {
					// ���ݽ�ɫ�б��ѯ��Ӧ��Ȩ���б�
					List<Privilege> privs_temp = us.searchPrivsByIden(temp.getIdentity());
					// ����ȥ��
					for (Privilege p : privs_temp) {
						if (!privs.contains(p)) {
							privs.add(p);
						}
					}
					mav.addObject("privs", privs);
					mav.setViewName("Views/test.jsp");
				}
			}
			mav.addObject("user", u1); // ���û���Ϣ�浽session��
		} else {
			mav.addObject("msg", "�˻������������,����������!");
			mav.setViewName("Views/user_login.jsp");
		}
		return mav;
	}

	@RequestMapping(value = "exam_lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listExams(String sno, int page, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Exam> exams = us.listExams(sno, page, limit);
		map.put("code", 0);
		map.put("count", exams.size());
		map.put("data", exams);
		if (exams.size() == 0) {
			map.put("msg", "����ʱû�п���");
		}
		us.addDiary(NOW_LOGINED_UNO, "�û�" + NOW_LOGINED_UNO + "��ѯ�����б�", us.getTime());
		return map;
	}

	@RequestMapping("start_exam")
	public ModelAndView startExam(String eno) {
		ModelAndView mav = new ModelAndView();
		try {
			eno = new String(eno.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Exam exam = us.listExamByEno(eno);
		mav.addObject("exam", exam);
		mav.setViewName("Views/exam_tips.jsp");
		mav.addObject("grades", -1);
		us.addDiary(NOW_LOGINED_UNO, "�û�" + NOW_LOGINED_UNO + "���ʿ�������ҳ", us.getTime());
		return mav;
	}

	@RequestMapping("get_items")
	public ModelAndView getItems(String tpno) {
		ModelAndView mav = new ModelAndView();
		// �����Ծ���õ���Ŀ�б�
		List<Question> ques = us.listQuesByTpno(tpno);
		Map<Question, Object> data = new HashMap<Question, Object>();
		for (Question q : ques) {
			// ����ÿ����Ŀ������õ���Ӧ��ѡ���б�
			List<Item> items = us.listItemsByQno(q.getQno());
			data.put(q, items);
		}
		mav.addObject("items", data);
		mav.setViewName("Views/exam_start.jsp");
		us.addDiary(NOW_LOGINED_UNO, "�û�" + NOW_LOGINED_UNO + "������ʽ���Խ���", us.getTime());
		return mav;
	}

	@RequestMapping("getQueStem")
	@ResponseBody
	public Map<String, Object> getQueStem(String qno, String now_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Question que = us.searchQueByQno(qno);
		// ������Ż��ѡ���б�
		List<Item> list = us.listItemsByQno(qno);
		ModelAndView mav = new ModelAndView();
		mav.addObject("now_id", now_id);
		map.put("tp_items", list);
		map.put("stem", que.getQstem());
		us.addDiary(NOW_LOGINED_UNO, "�û�" + NOW_LOGINED_UNO + "��ѯ���Ϊ" + qno + "�������ѡ���б�", us.getTime());
		return map;
	}

	/**
	 * ��õ�ǰʱ��������ʱ��ĺ�����
	 * 
	 * @param sno ����ѧ��
	 * @param eno ���Ա��
	 * @return ����ʣ��ʱ������
	 */
	@RequestMapping("get_endtime")
	@ResponseBody
	public Map<String, Object> getExamEndTime(String sno, String eno) {
		Map<String, Object> map = new HashMap<String, Object>();
		String endtime = us.searchEndTime(sno, eno);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date end = null;
		Date now = new Date();
		String nowtime = sdf.format(now);
		try {
			end = sdf.parse(endtime);
			now = sdf.parse(nowtime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Long endlong = end.getTime();
		Long nowlong = now.getTime();
		map.put("seconds", (endlong - nowlong) / 1000);
		us.addDiary(NOW_LOGINED_UNO, "�û�" + NOW_LOGINED_UNO + "��ÿ��Խ���ʱ��", us.getTime());
		return map;
	}

	@RequestMapping("exam_commit")
	@ResponseBody
	public Map<String, Object> examCommit(String all_items, String tpno, String eno, String sno) {
		Map<String, Object> map = new HashMap<String, Object>();
		// ��ѯ���Ծ��������ȷ��,�洢��������
		List<Question> ques = us.listQuesByTpno(tpno);
		// �����Ĵ��ַ�������Ϊ����,����ȷ�𰸽��бȽ�,�ж���ȷ������,���������ʹ���id
		// ��������ʽΪ ���:ѡ������,qno:item
		int right_count = 0;
		String[] ids = all_items.split(",");
		List<String> list = new ArrayList<String>(Arrays.asList(ids));
		List<Map<String, String>> errorQues = new ArrayList<Map<String, String>>();
		for (Question q : ques) {
			for (String s : list) {
				// ��s��:�ָ�,��0Ϊ���,1Ϊ���������ѡ��
				String[] k_m = s.split(":");
				// ������ƥ��,���жϴ𰸵�����
				if (q.getQno().equals(k_m[0])) {
					// �õ��������ȷ��
					Item temp = us.searchRightItemByQno(q.getQno());
					// �뿼����ƥ��,����ͬ������Ŀ������++
					if (temp.getItem().equals(k_m[1])) {
						right_count++;
					}
					// ����ź͸�����ȷ����map��ʽ�洢�����ص�ǰ��
					Map<String, String> errorQue = new HashMap<String, String>();
					errorQue.put(q.getQno(), temp.getItem());
					errorQues.add(errorQue);
					// ��exam_stu_que�в�������
					if (us.searchESQ(q.getQno(), eno, sno) > 0) {
						us.modifyESQ(q.getQno(), eno, sno, k_m[1]);
					} else {
						us.addExam_Stu_Que(eno, sno, q.getQno(), temp.getItem(), k_m[1]);
					}
				}
			}
		}
		us.modifyExamStuStatus(sno, eno, right_count * 2);// ���øÿ����ĸôο���Ϊ�ѿ�,����ɼ�
		map.put("errorQues", errorQues);
		map.put("grades", right_count * 2);
		us.addDiary(NOW_LOGINED_UNO, "�û�" + NOW_LOGINED_UNO + "�ڿ��Ա��Ϊ" + eno + "�Ŀ����н���", us.getTime());
		return map;
	}

	@RequestMapping("get_users")
	@ResponseBody
	public Map<String, Object> listUsers(int page, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> users = us.listAllUsers(page, limit);
		map.put("code", 0);
		map.put("count", us.listUsersCount());
		map.put("data", users);
		us.addDiary(NOW_LOGINED_UNO, "�û�" + NOW_LOGINED_UNO + "��ѯ�����û���Ϣ�б�", us.getTime());
		return map;
	}

	@RequestMapping("get_roles")
	public ModelAndView getRoles() {
		ModelAndView mav = new ModelAndView();
		List<Role> roles = us.listAllRoles();
		mav.addObject("roles", roles);
		mav.setViewName("Views/user_add.jsp");
		us.addDiary(NOW_LOGINED_UNO, "�û�" + NOW_LOGINED_UNO + "��ѯ���������Ϣ�б�", us.getTime());
		return mav;
	}

	@RequestMapping("get_privs")
	@ResponseBody
	public Map<String, Object> getPrivs(int rid) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		List<Privilege> list = us.listAllPrivs();
		List<Integer> values = us.listPidsByRid(rid);
		for (Privilege p : list) {
			Map<String, Object> temp = new HashMap<String, Object>();
			temp.put("value", p.getId());
			temp.put("title", p.getPname());
			data.add(temp);
		}
		map.put("data", data);
		map.put("values", values);
		us.addDiary(NOW_LOGINED_UNO, "�û�" + NOW_LOGINED_UNO + "��ѯ���idΪ" + rid + "������Ȩ�޼���", us.getTime());
		return map;
	}

	@RequestMapping("give_privs")
	@ResponseBody
	public Map<String, Object> modifyPrivs(int rid, String pids) {
		Map<String, Object> map = new HashMap<String, Object>();
		int result = us.modifyRolesPrivs(rid, pids);
		if (result > 0) {
			map.put("msg", "��Ȩ�ɹ�!");
		} else {
			map.put("msg", "��Ȩʧ��");
		}
		us.addDiary(NOW_LOGINED_UNO, "�û�" + NOW_LOGINED_UNO + "������rid��Ȩ��Ϊ" + pids + "��Ӧ������Ȩ��", us.getTime());
		return map;
	}

	@RequestMapping("forward_to_give_priv")
	public ModelAndView forwardPrivs() {
		ModelAndView mav = new ModelAndView();
		List<Role> roles = us.listAllRoles();
		mav.addObject("roles", roles);
		mav.setViewName("Views/give_priv.jsp");
		us.addDiary(NOW_LOGINED_UNO, "�û�" + NOW_LOGINED_UNO + "���������ɫȨ�޽���", us.getTime());
		return mav;
	}

	@RequestMapping("user_add")
	@ResponseBody
	public Map<String, Object> addUser(String uno, int rid) {
		Map<String, Object> map = new HashMap<String, Object>();
		int temp = us.searchUserByRid(uno, rid);
		if (temp > 0) {
			map.put("msg", "���û����д����!");
		} else {
			int result = us.addUser(uno, rid);
			if (result > 0) {
				map.put("msg", "��ӳɹ�!");
			} else {
				map.put("msg", "���ʧ��!");
			}
		}
		us.addDiary(NOW_LOGINED_UNO, "�û�" + NOW_LOGINED_UNO + "����/ѧ��Ϊ" + uno + "���û�������idΪ" + rid + "�����", us.getTime());
		return map;
	}

	@RequestMapping("user_delm")
	@ResponseBody
	public Map<String, Object> removeUser(String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		int result = us.removeUser(ids);
		if (result > 0) {
			map.put("msg", "��ӳɹ�!");
		} else {
			map.put("msg", "���ʧ��!");
		}
		us.addDiary(NOW_LOGINED_UNO, "�û�" + NOW_LOGINED_UNO + "����ɾ����uno������" + ids + "�ڵ��û�", us.getTime());
		return map;
	}

	/**
	 * 
	 * 
	 * @param uno
	 * @param identity
	 * @return
	 */
	@RequestMapping("user_modify")
	public ModelAndView showUserMsg(String uno, String identity) {
		ModelAndView mav = new ModelAndView();
		List<Role> roles = us.listAllRoles();
		int rid = us.searchRidByIden(identity);
		mav.addObject("roles", roles);
		mav.addObject("uno", uno);
		mav.addObject("rid", rid);
		mav.addObject("identity", identity);
		mav.setViewName("Views/user_add.jsp");
		us.addDiary(NOW_LOGINED_UNO, "�û�" + NOW_LOGINED_UNO + "��ѯ�����û���Ϣ�б�", us.getTime());
		return mav;
	}

	@RequestMapping("user_upd")
	@ResponseBody
	public Map<String, Object> modifyUser(String uno, int rid) {
		Map<String, Object> map = new HashMap<String, Object>();
		int result = us.modifyUser(uno, rid);
		if (result > 0) {
			map.put("msg", "�޸ĳɹ�!");
		} else {
			map.put("msg", "�޸�ʧ��!");
		}
		us.addDiary(NOW_LOGINED_UNO, "�û�" + NOW_LOGINED_UNO + "���û�" + uno + "����idΪ" + rid + "�����", us.getTime());
		return map;
	}

	@RequestMapping("removeUser")
	public ModelAndView removeUser(String uno, String identity) {
		ModelAndView mav = new ModelAndView();
		int result = us.removeUser(uno, identity);
		if (result > 0) {
			mav.addObject("msg", "ɾ���ɹ�!");
		} else {
			mav.addObject("msg", "ɾ��ʧ��!");
		}
		mav.addObject("code", 0);
		mav.setViewName("Views/power.jsp");
		us.addDiary(NOW_LOGINED_UNO, "�û�" + NOW_LOGINED_UNO + "�Ƴ��û�" + uno + "��" + identity + "���", us.getTime());
		return mav;
	}

	@RequestMapping("get_hnt_identity_users")
	@ResponseBody
	public Map<String, Object> listTempUsers(int rid) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> user_msgs = us.listTempUsers(rid);
		if (user_msgs.size() > 0) {
			map.put("user_msgs", user_msgs);
		} else {
			map.put("msg", "�����쳣,��ˢ�µ�ǰ����!");
		}
		map.put("rid", rid);
		map.put("code", 0);
		us.addDiary(NOW_LOGINED_UNO, "�û�" + NOW_LOGINED_UNO + "��ѯ������idΪ" + rid + "����ݵ��û��б�", us.getTime());
		return map;
	}

	@RequestMapping("forwardto_give_role")
	public ModelAndView forwardToGiveRole(String unos) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("unos", unos);
		mav.addObject("roles", us.listAllRoles());
		mav.setViewName("Views/give_role.jsp");
		us.addDiary(NOW_LOGINED_UNO, "�û�" + NOW_LOGINED_UNO + "��ѯ��������б�,��������Ȩ�޽���", us.getTime());
		return mav;
	}

	@RequestMapping("give_role")
	public ModelAndView giveRole(String unos, String rid) {
		ModelAndView mav = new ModelAndView();
		int result = us.addUserRole(unos, rid);
		if (result > 0) {
			mav.addObject("msg", "�����ɫ�ɹ�!");
		} else {
			mav.addObject("msg", "�����ɫʧ��!");
		}
		mav.setViewName("Views/give_role.jsp");
		us.addDiary(NOW_LOGINED_UNO, "�û�" + NOW_LOGINED_UNO + "���蹤/ѧ�Ű�����" + unos + "�ڵ��û���idΪ" + rid + "�����",
				us.getTime());
		return mav;
	}

	@RequestMapping("role_disable")
	@ResponseBody
	public Map<String, Object> modifyRoleStatusDisable(int rid) {
		Map<String, Object> map = new HashMap<String, Object>();
		int result = us.modifyRoleStatusDisable(rid);
		if (result > 0) {
			map.put("msg", "���û��ѱ�����!");
		} else {
			map.put("msg", "����ȷ����!");
		}
		us.addDiary(NOW_LOGINED_UNO, "�û�" + NOW_LOGINED_UNO + "����idΪ" + rid + "�����Ϊ������״̬", us.getTime());
		return map;
	}

	@RequestMapping("role_able")
	@ResponseBody
	public Map<String, Object> modifyRoleStatusAble(int rid) {
		Map<String, Object> map = new HashMap<String, Object>();
		int result = us.modifyRoleStatusAble(rid);
		if (result > 0) {
			map.put("msg", "�ý�ɫ�ѱ�����!");
		} else {
			map.put("msg", "�ý�ɫ�ѱ�����!");
		}
		us.addDiary(NOW_LOGINED_UNO, "�û�" + NOW_LOGINED_UNO + "����idΪ" + rid + "�����Ϊ����״̬", us.getTime());
		return map;
	}

	@RequestMapping("set_user_disable")
	@ResponseBody
	public Map<String, Object> modifyUserDisable(String uno, String ridentity) {
		Map<String, Object> map = new HashMap<String, Object>();
		int result = us.modifyUserDisable(uno, ridentity);
		if (result > 0) {
			map.put("msg", "���û��ѱ�����!");
		} else {
			map.put("msg", "����ʧ��,�����³���!");
		}
		us.addDiary(NOW_LOGINED_UNO, "�û�" + NOW_LOGINED_UNO + "�����û�" + uno + "��" + ridentity + "���", us.getTime());
		return map;
	}

	@RequestMapping("set_user_able")
	@ResponseBody
	public Map<String, Object> modifyUserAble(String uno, String ridentity) {
		Map<String, Object> map = new HashMap<String, Object>();
		int result = us.modifyUserAble(uno, ridentity);
		if (result > 0) {
			map.put("msg", "���û��Ѽ���!");
		} else {
			map.put("msg", "����ʧ��,�����³���!");
		}
		us.addDiary(NOW_LOGINED_UNO, "�û�" + NOW_LOGINED_UNO + "�����û�" + uno + "��" + ridentity + "���", us.getTime());
		return map;
	}

	@RequestMapping("reset_pwd")
	@ResponseBody
	public Map<String, Object> modifyUserPwd(String uno) {
		Map<String, Object> map = new HashMap<String, Object>();
		int result = us.modifyUserPwd(uno);
		if (result > 0) {
			map.put("msg", "���óɹ�!");
		} else {
			map.put("msg", "����ʧ��,�����³���!");
		}
		us.addDiary(NOW_LOGINED_UNO, "�û�" + NOW_LOGINED_UNO + "�������û�" + uno + "������", us.getTime());
		return map;
	}

	@RequestMapping("commitQue")
	@ResponseBody
	public Map<String, Object> commitQue(String qno, String eno, String sno, String sitem) {
		Map<String, Object> map = new HashMap<String, Object>();
		String ritem = us.searchRitemByQno(qno);// ������Ų�ѯ��ȷѡ��
		if (us.searchESQ(qno, eno, sno) > 0) {
			us.modifyESQ(qno, eno, sno, sitem);
		} else {
			us.addExam_Stu_Que(eno, sno, qno, ritem, sitem);// ���������ݲ��뵽���ݿ���
		}
		us.addDiary(NOW_LOGINED_UNO, "�û�" + NOW_LOGINED_UNO + "�ύ�˿���:" + eno + "����:" + qno + "��:" + sitem,
				us.getTime());
		return map;
	}

	@RequestMapping("set_exam_start")
	@ResponseBody
	public Map<String, Object> setStartExam(String eno, String sno, String now_time, String end_time) {
		Map<String, Object> map = new HashMap<String, Object>();
		us.modifyExamStu(eno, sno, now_time, end_time);
		return map;
	}

	@RequestMapping("forward_to_addrole")
	public ModelAndView forwardToAddRole() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Views/role_oform.jsp");
		us.addDiary(NOW_LOGINED_UNO, "�û�" + NOW_LOGINED_UNO + "���������ݽ���", us.getTime());
		return mav;
	}

	@RequestMapping("do_role_add")
	@ResponseBody
	public Map<String, Object> addRole(String ridentity) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (roleService.searchRoleByIden(ridentity) != null) {
			map.put("msg", "�Ѵ��ڸ��ֽ�ɫ!");
			map.put("have", "yes");
			us.addDiary(NOW_LOGINED_UNO, "�û�" + NOW_LOGINED_UNO + "������" + ridentity + "ʧ��", us.getTime());
		} else {
			int result = us.addRole(ridentity);
			if (result > 0) {
				map.put("msg", "��ӳɹ�!");
				us.addDiary(NOW_LOGINED_UNO, "�û�" + NOW_LOGINED_UNO + "��������" + ridentity, us.getTime());
			} else {
				map.put("msg", "���ʧ��,�����³���!");
				us.addDiary(NOW_LOGINED_UNO, "�û�" + NOW_LOGINED_UNO + "������" + ridentity + "ʧ��", us.getTime());
			}
		}
		return map;
	}

	@Autowired
	private UserService us;
	@Autowired
	private RoleService roleService;

	public UserController(UserService us) {
		this.us = us;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public UserController() {
	}

	public void setUs(UserService us) {
		this.us = us;
	}
}
