package com.ryan.controller;

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

import com.ryan.entity.Topic;
import com.ryan.service.TopicService;

/**
 * @author ����
 *
 */
@Controller
@SessionAttributes(value = { "tomodule" })
public class TopicController {
	@Autowired
	private TopicService topicService;

	/**
	 * @return the topicService
	 */
	public TopicService getTopicService() {
		return topicService;
	}

	/**
	 * @param topicService the topicService to set
	 */
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}
	/**
	 * ֪ʶ�����������
	 *
	 */
	public String createTono() {
		int num = topicService.showMaxValue();
		return "xty_exam" + getMedia(num) + num;
	}

	private String getMedia(int num) {
		String media = "";
		if (num < 10) {
			media += "000";

		} else if (num < 100) {
			media += "00";

		} else if (num < 1000) {
			media += "0";
		}
		return media;
	}
	/**
	 * �г�����ģ��
	 *
	 */
	@RequestMapping(value = "get_topics")
	public ModelAndView getTopics() {
		ModelAndView mv = new ModelAndView();
		List<String> tomodule = topicService.listTomodules();
		mv.addObject("tomodule", tomodule);
		mv.setViewName("Views/addzsd_oform.jsp");
		return mv;
	}
	
	@RequestMapping(value = "topic_list", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> topic_list(int page, int limit) {
		List<Topic> list = topicService.listByPage(page, limit);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", 0);
		map.put("count", topicService.totalsize());
		map.put("data", list);
		return map;
	}
	/**
	 * ɾ��һ��֪ʶ��
	 *
	 */
	@RequestMapping(value = "topic_del", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> topic_del(int id) {
		int rs = topicService.removeOne(id);
		Map<String, Object> map = new HashMap<String, Object>();
		if (rs > 0) {
			map.put("msg", "ɾ���ɹ�");

		} else {
			map.put("msg", "ɾ��ʧ��");
		}
		return map;
	}
	/**
	 * ����ɾ��֪ʶ��
	 *
	 */
	@RequestMapping(value = "zsd_del", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> zsd_del(String id) {

		int rs = topicService.removetopic(id);
		Map<String, Object> map = new HashMap<String, Object>();
		if (rs > 0) {
			map.put("code", rs);
			map.put("msg", "����ɾ���ɹ�");
		} else {
			map.put("code", 0);
			map.put("msg", "����ɾ��ʧ��");
		}
		return map;

	}
	/**
	 * �༭һ��֪ʶ��
	 *
	 */
	@RequestMapping(value = "topic_upd", method = RequestMethod.GET)
	public ModelAndView pre_topic_upd(int id) {
		ModelAndView mv = new ModelAndView();
		Topic o = topicService.searchOne(id);
		mv.addObject("Topic", o);
		mv.setViewName("Views/zsdoform.jsp");
		return mv;
	}

	@RequestMapping(value = "topic_upd", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> topic_upd(Topic topic) {
		int rs = topicService.modify(topic);
		Map<String, Object> map = new HashMap<String, Object>();
		if (rs > 0) {
			map.put("msg", "�޸ĳɹ�");
		} else {
			map.put("msg", "�޸�ʧ��");
		}
		return map;
	}
	/**
	 * ���֪ʶ��
	 *
	 */
	@RequestMapping("topic_add")
	
	public ModelAndView topic_add(Topic t) {
		ModelAndView mv = new ModelAndView();
		String tono = createTono();
		t.setTono(tono);
		Map<String, Object> map = new HashMap<String, Object>();
		int t1 = topicService.getToname(t);
		//if((t1.getTono()==null)){System.out.println("���벻��ȷ");}else {
		//int rs = topicService.addTopic(t1);
		if(t1>0) {
			map.put("msg","���벻��ȷ������������");
		}else {
			int rs = topicService.addTopic(t);
		mv.addObject("Topic", t);
		}
		mv.setViewName("Views/addzsd_oform.jsp");
		return mv;
		
	}
	/**
	 * ����������ѯ֪ʶ��
	 *
	 */
	@RequestMapping(value = "topic_cx", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> topic_cx(Topic a) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Topic> list = topicService.searchone(a);

		map.put("code", 0);
		map.put("data", list);
		map.put("count", list.size());
		map.put("msg", "��ѯ�ɹ�");
		return map;
	}

}
