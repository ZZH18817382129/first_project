package com.ryan.service.impl;

import java.util.List;

import com.ryan.service.JiLuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryan.entity.Exam;
import com.ryan.entity.Item;
import com.ryan.entity.Question;
import com.ryan.mapper.JiLuMapper;

@Service
public class JiLuServiceImpl implements JiLuService {
	@Autowired
	private JiLuMapper jiliMapper;

	/**
	 * @return the jiliMapper
	 */
	public JiLuMapper getJiliMapper() {
		return jiliMapper;
	}

	/**
	 * @param jiliMapper the jiliMapper to set
	 */
	public void setJiliMapper(JiLuMapper jiliMapper) {
		this.jiliMapper = jiliMapper;
	}

	public JiLuServiceImpl() {

	}

	
	public List<Exam> searchAllExams() {
		return jiliMapper.selectAllExams();
	}

	
	public List<Question> searchQuesByTpno(String tpno) {
		return jiliMapper.selectQuesByTpno(tpno);
	}

	
	public List<Item> searchItemsByQno(String qno) {
		return jiliMapper.selectItemsByQno(qno);
	}

	
	public int searchcorrect(String eno, String qno) {
		return jiliMapper.selectcorrect(eno, qno);
	}

	
	public int searchincorrect(String eno, String qno) {
		return jiliMapper.selectincorrect(eno, qno);
	}

	
	public String searchRitem(String qno) {
		return jiliMapper.selectRitem(qno);
	}

	
	public int searchCheckThis(String item, String qno, String eno) {
		return jiliMapper.selectCheckThis(item, qno, eno);
	}

}
