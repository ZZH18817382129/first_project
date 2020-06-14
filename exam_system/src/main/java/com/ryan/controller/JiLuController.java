package com.ryan.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryan.entity.Exam;
import com.ryan.entity.Item;
import com.ryan.entity.Question;
import com.ryan.service.JiLuService;

@Controller
public class JiLuController {
	@Autowired
	private JiLuService jiluService;

	/**
	 * @return the jiluService
	 */
	public JiLuService getJiluService() {
		return jiluService;
	}

	/**
	 * @param jiluService the jiluService to set
	 */
	public void setJiluService(JiLuService jiluService) {
		this.jiluService = jiluService;
	}

	public JiLuController(JiLuService jiluService) {
		super();
		this.jiluService = jiluService;
	}

	@RequestMapping("items")
	@ResponseBody
	public Map<String, Object> items() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Exam> list = jiluService.searchAllExams();// �༶��
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		for (Exam e : list) {
			// ���ݰ༶����ѯ��Ϣ
			// ��ѯƽ���� ��ѯ�ܷ�/�༶����
			String tpno = e.getTpno();
			List<Question> ques = jiluService.searchQuesByTpno(tpno);
			for (Question q : ques) {
				Map<String, Object> temp = new HashMap<String, Object>();
				String qno = q.getQno();
				List<Item> items = jiluService.searchItemsByQno(qno);
				String eno = e.getEno();
				int A = jiluService.searchCheckThis(items.get(0).getItem(), qno, eno);
				int B = jiluService.searchCheckThis(items.get(1).getItem(), qno, eno);
				int C = jiluService.searchCheckThis(items.get(2).getItem(), qno, eno);
				int D = jiluService.searchCheckThis(items.get(3).getItem(), qno, eno);
				int correct = jiluService.searchcorrect(eno, qno);// ��ȷ����
				int incorrect = jiluService.searchincorrect(eno, qno);// ����ȷ����
				double accu = correct / (correct + incorrect);
				String ritem = jiluService.searchRitem(qno);
				if (ritem.equals(items.get(0).getItem())) {
					temp.put("ritem", "A");
				} else if (ritem.equals(items.get(1).getItem())) {
					temp.put("ritem", "B");
				} else if (ritem.equals(items.get(2).getItem())) {
					temp.put("ritem", "C");
				} else {
					temp.put("ritem", "D");
				}
				temp.put("A", A);
				temp.put("B", B);
				temp.put("C", C);
				temp.put("D", D);
				temp.put("correct", correct);
				temp.put("incorrect", incorrect);
				temp.put("accu", accu);
				data.add(temp);
			}
		}
		map.put("data", data);
		return map;
	}
}
