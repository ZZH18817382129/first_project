package com.ryan.service.impl;

import java.util.List;

import com.ryan.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryan.entity.Topic;
import com.ryan.mapper.TopicMapper;


/**
 * @author ����
 * @version 1.0
 *
 */

@Service
public class TopicServiceImpl implements TopicService {
	@Autowired
	private TopicMapper topicMapper;
	
	/**
	 * @return the topicMapper
	 */
	public TopicMapper getTopicMapper() {
		return topicMapper;
	}

	/**
	 * @param topicMapper 
	 */
	
	public void setTopicMapper(TopicMapper topicMapper) {
		this.topicMapper = topicMapper;
	}

	public TopicServiceImpl(TopicMapper topicMapper) {
		super();
		this.topicMapper = topicMapper;
	}
	public TopicServiceImpl() {
		
	}

	/**
	 *@Param page �õ�ҳ��
	 *@Param limit ����ҳ��
	 */
	
	public List<Topic> listByPage(int page, int limit) {
		return topicMapper.selectByPage((page-1)*limit,limit);
	}

	/**
	 *@return 
	 */
	
	public int totalsize() {
		return topicMapper.selectTotal();
	}

	/**
	 *@Param ����id�Ƴ�һ��֪ʶ��
	 */
	
	public int removeOne(int id) {
		return topicMapper.deleteOne(id);
	}

	/**
	 *@return    
	 */
	
	public Topic searchOne(int id) {
		return topicMapper.selectOne(id);
	}

	/**
	 *@return 
	 */
	
	
	public int modify(Topic o) {
		
		return topicMapper.updateOne(o);
	}

	/**
	 *@return ���һ��֪ʶ��
	 */
	
	
	public int addTopic(Topic t) {
		
		return topicMapper.insertOne(t);
	}

	/**
	 *@return 
	 */
	
	
	public List<Topic> searchone(Topic a) {
		return topicMapper.selectone(a);
	}

	/**
	 *@return 
	 */
	
	
	public List<String> listTomodules() {
		
		return topicMapper.listTomo();
	}

	/**
	 *@return 
	 */
	
	
	public int showMaxValue() {
		return topicMapper.showmaxValue();
	}

	
	public int removetopic(String id) {
		return topicMapper.deltopic(id);
	}

	
	public int getToname(Topic t) {
		return topicMapper.selectToname(t);
	}

	//
	//public int selectTopic(Topic t) {
	//	return topicMapper.selecttopic(t);
	//}

	
}
