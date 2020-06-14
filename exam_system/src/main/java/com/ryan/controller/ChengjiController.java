package com.ryan.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryan.entity.Chengji;
import com.ryan.entity.Classes;
import com.ryan.service.ChengjiService;

@Controller
public class ChengjiController {
	@Autowired
	private ChengjiService chengjiService;

	/**
	 * @return the chengjiService
	 */
	public ChengjiService getChengjiService() {
		return chengjiService;
	}

	/**
	 * @param chengjiService the chengjiService to set
	 */
	public void setChengjiService(ChengjiService chengjiService) {
		this.chengjiService = chengjiService;
	}

	public ChengjiController(ChengjiService chengjiService) {
		super();
		this.chengjiService = chengjiService;
	}

	public ChengjiController() {

	}

	@RequestMapping(value = "score1_list", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> score1_list(int page, int limit) {
		List<Chengji> list = chengjiService.listByPage(page, limit);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", 0);
		map.put("count", chengjiService.totalsize());
		map.put("data", list);
		return map;
	}

	@RequestMapping(value = "max")
	@ResponseBody
	public Map<String, Object> pre_score_upd() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Classes> list = chengjiService.searchAllClasses();
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		for (Classes c : list) {
			// ���ݰ༶����ѯ��Ϣ
			// ��ѯƽ���� ��ѯ�ܷ�/�༶����

			double sum = chengjiService.searchBYCname(c.getCname());// �༶�ܷ�
			int cnum = c.getCnum();// �༶������
			int rnum = chengjiService.searchClassCnum6(c.getCname());// �ɼ�����60�ֵ�����
			int gnum = chengjiService.searchClassCnum9(c.getCname());// �ɼ�����85�ֵ�����
			double MAX = chengjiService.selectMAX(c.getCname());// �ɼ����ֵ
			double MIN = chengjiService.selectMIN(c.getCname());// �ɼ���Сֵ
			Map<String, Object> temp = new HashMap<String, Object>();
			temp.put("cname", c.getCname());
			temp.put("cnum", c.getCnum());
			temp.put("AVG", sum / cnum);
			temp.put("MAX", MAX);
			temp.put("MIN", MIN);
			temp.put("prate", cnum / cnum);
			temp.put("rnum", rnum);
			temp.put("yrate", gnum / cnum);
			temp.put("gnum", gnum);
			data.add(temp);
		}
		map.put("data", data);
		return map;
	}
}
