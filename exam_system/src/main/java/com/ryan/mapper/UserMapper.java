package com.ryan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ryan.entity.Exam;
import com.ryan.entity.Item;
import com.ryan.entity.Privilege;
import com.ryan.entity.Question;
import com.ryan.entity.Role;
import com.ryan.entity.User;

public interface UserMapper {
	/**
	 * 
	 * �û���¼����,�����û����������ж��Ƿ������¼
	 * 
	 * @param u �����û����������ֵ����
	 * @return ���ز�ѯ���
	 */
	List<User> userLogin(@Param("u") User u);

	/**
	 * 
	 * �г���¼��ѧ�������п�����Ϣ
	 * 
	 * @param sno   ��¼ѧ����ѧ��
	 * @param page  ҳ��
	 * @param limit ÿҳ��������
	 * @return �������������п��Լ���
	 */
	List<Exam> selectExams(@Param("sno") String sno, @Param("page") int page, @Param("limit") int limit);

	/**
	 * 
	 * ��ѯĳ���Ծ��ȫ������
	 * 
	 * @param tpno �Ծ���,һ���Ծ��Ψһ��ʶ
	 * @return ���Ծ��������Ŀ��Ϣ
	 */
	List<Question> selectQuesByTpno(@Param("tpno") String tpno);

	/**
	 * 
	 * ��������Ų�ѯ���������ѡ��
	 * 
	 * @param qno ������
	 * @return �����ѡ���
	 */
	List<Item> selectItemsByQno(@Param("qno") String qno);

	/**
	 * 
	 * ���ݿ��Ա�Ų�ѯ��Ӧ�Ŀ�����Ϣ
	 * 
	 * @param eno ���Ա��
	 * @return ��Ӧ�Ŀ��Ե���Ϣ
	 */
	Exam selectExamByEno(@Param("eno") String eno);

	/**
	 * 
	 * ���������Ų�ѯ��Ӧ��������Ϣ
	 * 
	 * @param qno ������
	 * @return ��Ӧ��������Ϣ
	 */
	Question selectQueByQno(@Param("qno") String qno);

	/**
	 * 
	 * ���������Ų�ѯ��Ӧ�������ȷѡ�����Ϣ
	 * 
	 * @param qno ������
	 * @return ��ȷ��ѡ�����Ϣ
	 */
	Item selectRightItemByQno(@Param("qno") String qno);

	/**
	 * 
	 * �����ݿ��в��뿼��-ѧ��-����Ĺ�����Ϣ
	 * 
	 * @param eno   ����Ŀ��Ա��
	 * @param sno   ѧ��ѧ��
	 * @param qno   ������
	 * @param ritem ������ȷ��
	 * @param sitem ���⿼����
	 * @return ��ѯ���Ľ��������
	 */
	int insertESQ(@Param("eno") String eno, @Param("sno") String sno, @Param("qno") String qno,
                  @Param("ritem") String ritem, @Param("sitem") String sitem);

	/**
	 * 
	 * ��ѯ�����û��ĵ�¼��Ϣ
	 * 
	 * @param offset ҳ��
	 * @param limit  ÿҳ��������
	 * @return �����û��ĵ�¼��Ϣ�ļ���
	 */
	List<User> selectAllUsers(@Param("offset") int offset, @Param("limit") int limit);

	/**
	 * 
	 * ��ѯ�û��Ļ�����Ϣ
	 * 
	 * @return �û��Ļ�����Ϣ�ļ���
	 */
	List<User> selectUserMsg();

	/**
	 * 
	 * ����ɾ���û�
	 * 
	 * @param ids ����Ҫ��ɾ�����û��Ĺ�/ѧ����Ϣ
	 * @return ɾ������������
	 */
	int deleteUser(@Param("ids") String ids);

	/**
	 * 
	 * ����ɾ���û�-��ݹ�����Ϣ
	 * 
	 * @param ids ����Ҫ��ɾ�����û��Ĺ�/ѧ����Ϣ
	 * @return ɾ������������
	 */
	int deleteUserRole(@Param("ids") String ids);

	/**
	 * 
	 * �����û���ź���ݲ�ѯ������Ϣ
	 * 
	 * @param uno      �û��Ĺ�/ѧ��
	 * @param identity �û����
	 * @return ���ҵ����û�����
	 */
	User selectUserByUnoIden(@Param("uno") String uno, @Param("identity") String identity);

	/**
	 * 
	 * �޸��û������Ϣ
	 * 
	 * @param u �����û��Ĺ�/ѧ�ź�Ҫ�޸�Ϊ�����
	 * @return �޸ĵ���������
	 */
	int updateUserRole(@Param("u") User u);

	/**
	 * 
	 * �Ƴ��û���ĳ�����
	 * 
	 * @param uno      Ҫ�Ƴ���ݵ��û��ı��
	 * @param identity Ҫ�Ƴ������
	 * @return ɾ������������
	 */
	int removeUser(@Param("uno") String uno, @Param("identity") String identity);

	/**
	 * 
	 * ��ѯû��idΪrid����ݵ��û�����
	 * 
	 * @param rid ��ݵ�id
	 * @return �����������û��ļ���
	 */
	List<User> selectTempUsers(@Param("rid") String rid);

	/**
	 * 
	 * ��ѯ���е������Ϣ
	 * 
	 * @return ���е������Ϣ
	 */
	List<Role> selectAllRoles();

	/**
	 * 
	 * ��ѯĳ�����ӵ�е�Ȩ��
	 * 
	 * @param identity Ҫ��ѯ�����
	 * @return ����ݵ�Ȩ�޼���
	 */
	List<Privilege> selectPrivsByIden(@Param("identity") String identity);

	/**
	 * 
	 * �����û�����������
	 * 
	 * @return �����û�����������
	 */
	int selectUsersCount();

	/**
	 * 
	 * ����ɾ���û�����ݵĹ�����ϵ
	 * 
	 * @param unos Ҫɾ���������û����
	 * @param id   ���id
	 * @return ɾ��������
	 */
	int deleteUserRoles(@Param("unos") String unos, @Param("id") int id);

	/**
	 * 
	 * ���û�uno���idΪrid�����
	 * 
	 * @param uno
	 * @param rid
	 * @return
	 */
	int addUserRole(@Param("uno") String uno, @Param("rid") int rid);

	int updateUser(@Param("uno") String uno, @Param("rid") int rid);

	List<Privilege> selectAllPrivs();

	List<Integer> selectPidsByRid(@Param("rid") int rid);

	int deletePrivRoleByRid(@Param("rid") int rid);

	int insertPrivRole(@Param("rid") int rid, @Param("pid") String pid);

	int updateRoleStatusDisable(@Param("rid") int rid);

	int updateRoleStatusAble(@Param("rid") int rid);

	int updateUserDisable(@Param("uno") String uno, @Param("ridentity") String ridentity);

	int updateUserAble(@Param("uno") String uno, @Param("ridentity") String ridentity);

	int insertUser(@Param("uno") String uno, @Param("utime") String utime);

	int insertUserIden(@Param("uno") String uno, @Param("rid") int rid);

	String selectIdenByRid(@Param("rid") int rid);

	int selectRidByIden(@Param("identity") String identity);

	int selectUserByRid(@Param("uno") String uno, @Param("rid") int rid);

	int updateUserPwd(@Param("uno") String uno);

	User selectUserByUno(@Param("uno") String uno);

	void insertDiary(@Param("uno") String uno, @Param("message") String message, @Param("time") String time);

	String selectRitemByQno(@Param("qno") String qno);

	int selectESQ(@Param("qno") String qno, @Param("eno") String eno, @Param("sno") String sno);

	int updateESQ(@Param("qno") String qno, @Param("eno") String eno, @Param("sno") String sno,
                  @Param("sitem") String sitem);

	int updateExamStu(@Param("eno") String eno, @Param("sno") String sno, @Param("now_time") String now_time,
                      @Param("end_time") String end_time);

	String selectSnameBySno(@Param("sno") String sno);

	String selectEndTime(@Param("sno") String sno, @Param("eno") String eno);

	void updateExamStuStatus(@Param("sno") String sno, @Param("eno") String eno, @Param("esgrade") int esgrade);

	int insertRole(@Param("ridentity") String ridentity);
}
