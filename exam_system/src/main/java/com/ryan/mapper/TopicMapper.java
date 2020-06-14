package com.ryan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ryan.entity.Topic;

/**
 * @author ����
 * @version 1.0
 *
 */

public interface TopicMapper {
	/**
	 * @Param offsetƫ����
	 * @Param limit ����ÿҳ���ݵ�����
	 *
	 */
	List<Topic> selectByPage(@Param("offset") int offset, @Param("limit") int limit);
	int selectTotal();
	int deleteOne(@Param("id") int id);      //ͨ��idɾ��һ��֪ʶ��
	Topic selectOne(@Param("id") int id);      //ͨ��id��ѯһ��֪ʶ��
	int updateOne(@Param("o") Topic o);         //�޸�һ��֪ʶ��
	int insertOne(@Param("t") Topic t);         //����һ��֪ʶ��
	List<Topic> selectone(@Param("a") Topic a);  //��ѯ���֪ʶ��
	List<String> listTomo();           //�г�����ģ��
	int showmaxValue();     //��ʾ������ֵ
	int deltopic(@Param("sid") String sid);   //����ɾ��֪ʶ��
	//Topic selecttopic(@Param("t") Topic t);
	int selectToname(@Param("t") Topic t);        //��ѯ���ݿ��Ƿ����Ѿ����ڵ�֪ʶ��
}
