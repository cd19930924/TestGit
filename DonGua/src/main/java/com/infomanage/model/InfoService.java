package com.infomanage.model;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoService {
	@Autowired
	private InfoDAO dao;

//	public InfoService() {
//		dao = new InfoDAOImpl();
//	}

	//NEWS
	public List<InfoVO> getAllNews() {
		return dao.getAllNews();
	}

	public List<InfoVO> getValidNews() {
		return dao.getValidNews();
	}

	public InfoVO addNews(String no, String name, String content, Timestamp createTime, String status) {
		InfoVO vo = new InfoVO();

		vo.setNo(no);
		vo.setName(name);
		vo.setContent(content);
		vo.setCreateTime(createTime);
		vo.setStatus(status);
		dao.insertNews(vo);

		return vo;

	}

	public InfoVO updateNews(String no, String name, String content, String status, Timestamp updateTime) {
		InfoVO vo = new InfoVO();

		vo.setNo(no);
		vo.setName(name);
		vo.setContent(content);
		vo.setUpdateTime(updateTime);
		vo.setStatus(status);
		dao.updateNews(vo);

		return vo;

	}

	public InfoVO updateNewsStatus(String no, String status, Timestamp updateTime) {
		InfoVO vo = new InfoVO();

		vo.setNo(no);
		vo.setUpdateTime(updateTime);
		vo.setStatus(status);
		dao.updateNewsStatus(vo);

		return vo;

	}

	public String getNextNewsNo() {
		String latestNoStr = dao.getLatestNewsNo();
		int latestNoInt = Integer.parseInt(latestNoStr);

		if ((latestNoInt + 1) < 10) {
			String nextNoStr = "NEWS0" + String.valueOf(latestNoInt + 1);
			return nextNoStr;
		} else {
			String nextNoStr = "NEWS" + String.valueOf(latestNoInt + 1);
			return nextNoStr;
		}

	}

	public InfoVO getOneNews(String createTimeId) {
		return dao.selectNewsByNo(createTimeId);
	}

	
	//FAQ
	public List<InfoVO> getAllFAQ() {
		return dao.getAllFAQ();
	}
	
	public List<InfoVO> getValidFAQ() {
		return dao.getValidFAQ();
	}
	
	public InfoVO addFAQ(String no, String name, String content, Timestamp createTime, String status) {
		InfoVO vo = new InfoVO();
		
		vo.setNo(no);
		vo.setName(name);
		vo.setContent(content);
		vo.setCreateTime(createTime);
		vo.setStatus(status);
		dao.insertFAQ(vo);
		
		return vo;
		
	}
	
	public InfoVO updateFAQ(String no, String name, String content, String status, Timestamp updateTime) {
		InfoVO vo = new InfoVO();
		
		vo.setNo(no);
		vo.setName(name);
		vo.setContent(content);
		vo.setUpdateTime(updateTime);
		vo.setStatus(status);
		dao.updateFAQ(vo);
		
		return vo;
		
	}
	
	public InfoVO updateFAQStatus(String no, String status, Timestamp updateTime) {
		InfoVO vo = new InfoVO();
		
		vo.setNo(no);
		vo.setUpdateTime(updateTime);
		vo.setStatus(status);
		dao.updateFAQStatus(vo);
		
		return vo;
		
	}
	
	public String getNextFAQNo() {
		String latestNoStr = dao.getLatestFaqNo();
		int latestNoInt = Integer.parseInt(latestNoStr);
		
		if ((latestNoInt + 1) < 10) {
			String nextNoStr = "FAQ0" + String.valueOf(latestNoInt + 1);
			return nextNoStr;
		} else {
			String nextNoStr = "FAQ" + String.valueOf(latestNoInt + 1);
			return nextNoStr;
		}
		
	}
	
	
	
	//INTRO
	public List<InfoVO> getAllIntro() {
		return dao.getAllIntro();
	}
	
	public List<InfoVO> getValidIntro() {
		return dao.getValidIntro();
	}
	
	public InfoVO addIntro(String no, String name, String content, Timestamp createTime, String status) {
		InfoVO vo = new InfoVO();
		
		vo.setNo(no);
		vo.setName(name);
		vo.setContent(content);
		vo.setCreateTime(createTime);
		vo.setStatus(status);
		dao.insertIntro(vo);
		
		return vo;
		
	}
	
	public InfoVO updateIntro(String no, String name, String content, String status, Timestamp updateTime) {
		InfoVO vo = new InfoVO();
		
		vo.setNo(no);
		vo.setName(name);
		vo.setContent(content);
		vo.setUpdateTime(updateTime);
		vo.setStatus(status);
		dao.updateIntro(vo);
		
		return vo;
		
	}
	
	public InfoVO updateIntroStatus(String no, String status, Timestamp updateTime) {
		InfoVO vo = new InfoVO();
		
		vo.setNo(no);
		vo.setUpdateTime(updateTime);
		vo.setStatus(status);
		dao.updateIntroStatus(vo);
		
		return vo;
		
	}
	
	public String getNextIntroNo() {
		String latestNoStr = dao.getLatestIntroNo();
		int latestNoInt = Integer.parseInt(latestNoStr);
		
		if ((latestNoInt + 1) < 10) {
			String nextNoStr = "S0" + String.valueOf(latestNoInt + 1);
			return nextNoStr;
		} else {
			String nextNoStr = "S" + String.valueOf(latestNoInt + 1);
			return nextNoStr;
		}
		
	}

	public static void main(String[] args) {

		InfoService s = new InfoService();
		System.out.println(s.getNextNewsNo());

		String str1 = String.valueOf(123);
		System.out.println(str1);

		String latestNoStr = "08";
		int latestNoInt = Integer.parseInt(latestNoStr);

		if (latestNoInt < 10) {
			String nextNoStr = "S0" + String.valueOf(latestNoInt);
			System.out.println(nextNoStr);
		} else {
			String nextNoStr = String.valueOf(latestNoInt);
			System.out.println(nextNoStr);
		}

	}
}
