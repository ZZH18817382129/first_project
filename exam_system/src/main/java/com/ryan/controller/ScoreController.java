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

import com.ryan.entity.Score;
import com.ryan.service.ScoreService;

@Controller
public class ScoreController {
	@Autowired
	private ScoreService scoreService;

	/**
	 * @return the scoreService
	 */
	public ScoreService getScoreService() {
		return scoreService;
	}

	/**
	 * @param scoreService the scoreService to set
	 */
	public void setScoreService(ScoreService scoreService) {
		this.scoreService = scoreService;
	}

	public ScoreController(ScoreService scoreService) {
		super();
		this.scoreService = scoreService;
	}
	public ScoreController() {
		
	}
	@RequestMapping(value = "score_list", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> topic_list(int page, int limit) {
		List<Score> list = scoreService.listByPage(page, limit);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", 0);
		map.put("count", scoreService.totalsize());
		map.put("data", list);
		return map;
	}
	
	@RequestMapping(value="score_cx",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> score_cx(Score s){
		Map<String,Object> map = new HashMap<String, Object>();
		List<Score> list = scoreService.searchone(s);
		map.put("code",0);
		map.put("data",list);
		map.put("count",list.size());
		map.put("msg","��ѯ�ɹ�");
		return map;
	}
	@RequestMapping(value = "score_upd", method = RequestMethod.GET)
	public ModelAndView pre_score_upd(String sno,String ename) {
		ModelAndView mv = new ModelAndView();
		Score o = scoreService.searchOne(sno,ename);
		
		mv.addObject("Score", o);
		
		mv.setViewName("Views/updcjoform.jsp");
		return mv;
	}

	@RequestMapping(value = "score_upd", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> score_upd(String sno,String ename,int esgrade) {
		int rs = scoreService.modifyScore(sno,ename,esgrade);
		
		Map<String, Object> map = new HashMap<String, Object>();
		if (rs > 0) {
			map.put("msg", "�޸ĳɹ�");
		} else {
			map.put("msg", "�޸�ʧ��");
		}
		return map;
	}
}
