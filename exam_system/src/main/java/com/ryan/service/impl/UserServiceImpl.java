package com.ryan.service.impl;

import java.util.List;

import com.ryan.service.UserService;
import com.ryan.entity.Exam;
import com.ryan.entity.Item;
import com.ryan.entity.Privilege;
import com.ryan.entity.Question;
import com.ryan.entity.Role;
import com.ryan.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserService userservice;

	public UserServiceImpl(){};

	public UserServiceImpl(UserService userservice) {
		this.userservice = userservice;
	}

	public UserService getUserservice() {
		return userservice;
	}

	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}

	
	public List<User> usersLogin(User u) {
		return null;
	}

	
	public List<Exam> listExams(String sno, int page, int limit) {
		return null;
	}

	
	public List<Question> listQuesByTpno(String tpno) {
		return null;
	}

	
	public List<Item> listItemsByQno(String qno) {
		return userservice.listItemsByQno(qno);
	}

	
	public Exam listExamByEno(String eno) {
		return userservice.listExamByEno(eno);
	}

	
	public Question searchQueByQno(String qno) {
		return userservice.searchQueByQno(qno);
	}

	
	public Item searchRightItemByQno(String qno) {
		return userservice.searchRightItemByQno(qno);
	}

	
	public int addExam_Stu_Que(String eno, String sno, String qno, String ritem, String sitem) {
		return userservice.addExam_Stu_Que(eno, sno, qno, ritem, sitem);
	}

	
	public List<User> listAllUsers(int page, int limit) {
		return userservice.listAllUsers(page, limit);
	}

	
	public int addUser(String uno, int rid) {
		return userservice.addUser(uno, rid);
	}

	
	public List<User> listUserMsg() {
		return userservice.listUserMsg();
	}

	
	public int removeUser(String ids) {
		return userservice.removeUser(ids);
	}

	
	public User searchUserByUnoIden(String uno, String identity) {
		return userservice.searchUserByUnoIden(uno, identity);
	}

	
	public int removeUser(String uno, String identity) {
		return userservice.removeUser(uno, identity);
	}

	
	public List<User> listTempUsers(int rid) {
		return userservice.listTempUsers(rid);
	}

	
	public List<Role> listAllRoles() {
		return userservice.listAllRoles();
	}

	
	public List<Privilege> searchPrivsByIden(String identity) {
		return userservice.searchPrivsByIden(identity);
	}

	
	public int listUsersCount() {
		return userservice.listUsersCount();
	}

	
	public int addUserRole(String ids, String rid) {
		return userservice.addUserRole(ids, rid);
	}

	
	public int modifyUser(String uno, int rid) {
		return userservice.modifyUser(uno, rid);
	}

	
	public List<Privilege> listAllPrivs() {
		return userservice.listAllPrivs();
	}

	
	public List<Integer> listPidsByRid(int rid) {
		return userservice.listPidsByRid(rid);
	}

	
	public int modifyRolesPrivs(int rid, String pids) {
		return userservice.modifyRolesPrivs(rid, pids);
	}

	
	public int modifyRoleStatusDisable(int rid) {
		return userservice.modifyRoleStatusDisable(rid);
	}

	
	public int modifyRoleStatusAble(int rid) {
		return userservice.modifyRoleStatusAble(rid);
	}

	
	public int modifyUserDisable(String uno, String ridentity) {
		return userservice.modifyUserDisable(uno, ridentity);
	}

	
	public int modifyUserAble(String uno, String ridentity) {
		return userservice.modifyUserAble(uno, ridentity);
	}

	
	public String searchIdenByRid(int rid) {
		return userservice.searchIdenByRid(rid);
	}

	
	public int searchRidByIden(String identity) {
		return userservice.searchRidByIden(identity);
	}

	
	public int searchUserByRid(String uno, int rid) {
		return userservice.searchUserByRid(uno, rid);
	}

	
	public int modifyUserPwd(String uno) {
		return userservice.modifyUserPwd(uno);
	}

	
	public User searchUserByUno(String uno) {
		return userservice.searchUserByUno(uno);
	}

	
	public String searchRitemByQno(String qno) {
		return userservice.searchRitemByQno(qno);
	}

	
	public int searchESQ(String qno, String eno, String sno) {
		return userservice.searchESQ(qno, eno, sno);
	}

	
	public int modifyESQ(String qno, String eno, String sno, String sitem) {
		return userservice.modifyESQ(qno, eno, sno, sitem);
	}

	
	public int modifyExamStu(String eno, String sno, String now_time, String end_time) {
		return userservice.modifyExamStu(eno, sno, now_time, end_time);
	}

	
	public String searchSnameBySno(String sno) {
		return userservice.searchSnameBySno(sno);
	}

	
	public String searchEndTime(String sno, String eno) {
		return userservice.searchEndTime(sno, eno);
	}

	
	public void modifyExamStuStatus(String sno, String eno, int esgrade) {
		userservice.modifyExamStuStatus(sno, eno, esgrade);
	}

	
	public int addRole(String ridentity) {
		return userservice.addRole(ridentity);
	}

	
	public void addDiary(String uno, String message, String time) {
		userservice.addDiary(uno, message, time);
	}

	
	public String getTime() {
		return userservice.getTime();
	}

}
