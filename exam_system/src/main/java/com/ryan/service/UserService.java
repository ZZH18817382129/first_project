package com.ryan.service;

import java.util.List;

import com.ryan.entity.Exam;
import com.ryan.entity.Item;
import com.ryan.entity.Privilege;
import com.ryan.entity.Question;
import com.ryan.entity.Role;
import com.ryan.entity.User;

public interface UserService {
	/**
	 * 
	 * �û���¼����
	 * 
	 * @param u
	 * @return
	 */
	List<User> usersLogin(User u);

	/**
	 * 
	 * �г�ѧ��Ҫ���ԵĿ����б�
	 * 
	 * @param limit
	 * @param page
	 * 
	 * @return
	 */
	List<Exam> listExams(String sno, int page, int limit);

	/**
	 * �����Ծ��Ų����Ծ�������б�
	 * 
	 * @param tpno
	 * @return
	 */
	List<Question> listQuesByTpno(String tpno);

	/**
	 * 
	 * ����������qno��ѯ�����ѡ���б�
	 * 
	 * @param qno
	 * @return
	 */
	List<Item> listItemsByQno(String qno);

	/**
	 * 
	 * ���ݿ��Ա��eno���ҵ��ôο�����Ϣ
	 * 
	 * @param eno
	 * @return
	 */
	Exam listExamByEno(String eno);

	/**
	 * 
	 * ����
	 * 
	 * @param qno
	 * @return
	 */
	Question searchQueByQno(String qno);

	/**
	 * 
	 * ��ѯ���Ϊqno����Ŀ����ȷ��
	 * 
	 * @param qno
	 * @return
	 */
	Item searchRightItemByQno(String qno);

	/**
	 * 
	 * �����ݿ����һ�ο�����Ϣ
	 * 
	 * @param eno
	 * @param sno
	 * @param qno
	 * @param ritem
	 * @param sitem
	 * @return
	 */
	int addExam_Stu_Que(String eno, String sno, String qno, String ritem, String sitem);

	/**
	 * 
	 * 
	 * 
	 * @param page
	 * @param limit
	 * @return
	 */
	List<User> listAllUsers(int page, int limit);

	/**
	 * 
	 * ����û����������
	 * 
	 * @param uno
	 * @return
	 */
	int addUser(String uno, int rid);

	/**
	 * 
	 * ��ѯ�û���Ϣ
	 * 
	 * @return
	 */
	List<User> listUserMsg();

	/**
	 * 
	 * ����ɾ���û�
	 * 
	 * @param ids
	 * @return
	 */
	int removeUser(String ids);

	User searchUserByUnoIden(String uno, String identity);

	int removeUser(String uno, String identity);

	List<User> listTempUsers(int rid);

	List<Role> listAllRoles();

	List<Privilege> searchPrivsByIden(String identity);

	int listUsersCount();

	int addUserRole(String ids, String rid);

	int modifyUser(String uno, int rid);

	List<Privilege> listAllPrivs();

	List<Integer> listPidsByRid(int rid);

	int modifyRolesPrivs(int rid, String pids);

	int modifyRoleStatusDisable(int rid);

	int modifyRoleStatusAble(int rid);

	int modifyUserDisable(String uno, String ridentity);

	int modifyUserAble(String uno, String ridentity);

	String searchIdenByRid(int rid);

	int searchRidByIden(String identity);

	int searchUserByRid(String uno, int rid);

	int modifyUserPwd(String uno);

	User searchUserByUno(String uno);

	String searchRitemByQno(String qno);

	int searchESQ(String qno, String eno, String sno);

	int modifyESQ(String qno, String eno, String sno, String sitem);

	int modifyExamStu(String eno, String sno, String now_time, String end_time);

	String searchSnameBySno(String sno);

	String searchEndTime(String sno, String eno);

	void modifyExamStuStatus(String sno, String eno, int esgrade);

	int addRole(String ridentity);

	void addDiary(String uno, String message, String time);

	String getTime();
}
