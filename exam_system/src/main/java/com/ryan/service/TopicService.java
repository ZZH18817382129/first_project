package com.ryan.service;

import java.util.List;

import com.ryan.entity.Topic;

public interface TopicService {
	public List<Topic> listByPage(int page, int limit);
	public int totalsize();
	public int removeOne(int id);//ɾ��һ��֪ʶ��
	Topic searchOne(int id);//ͨ��id����һ��֪ʶ��
	public int modify(Topic o);//�޸�һ��֪ʶ��
	int addTopic(Topic t);//���֪ʶ��
	public List<Topic> searchone(Topic a);//���Ҷ��֪ʶ��
	public List<String> listTomodules();//�г�����ģ��
	public int showMaxValue();//��ʾ֪ʶ���������ݿ�������
	public int removetopic(String id);//����ɾ��֪ʶ��
	//public int selectTopic(Topic t);
	public int getToname(Topic t);//�����ݿ����õ�֪ʶ��
}
