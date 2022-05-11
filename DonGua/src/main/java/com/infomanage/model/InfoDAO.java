package com.infomanage.model;

import java.util.List;

public interface InfoDAO {
	
	//DASHBOARD:FAQ
	public void insertFAQ(InfoVO infoVO);
	public void updateFAQ(InfoVO infoVO);
	public void updateFAQStatus(InfoVO infoVO);
	public String getLatestFaqNo();
	public List<InfoVO> getAllFAQ();

	//DASHBOARD:INTRO
	public void insertIntro(InfoVO infoVO);
	public void updateIntro(InfoVO infoVO);
	public void updateIntroStatus(InfoVO infoVO);
	public String getLatestIntroNo();
	public List<InfoVO> getAllIntro();

	//DASHBOARD:NEWS
	public void insertNews(InfoVO infoVO);
	public void updateNews(InfoVO infoVO);
	public void updateNewsStatus(InfoVO infoVO);
	public String getLatestNewsNo();
	public List<InfoVO> getAllNews();

	//CLIENT:FAQ
	public InfoVO selectFAQByNo(String createTimeId);
	public List<InfoVO> getValidFAQ();
	//CLIENT:INTRO
	public InfoVO selectIntroByNo(String createTimeId);
	public List<InfoVO> getValidIntro();
	//CLIENT:NEWS
	public List<InfoVO> getValidNews();
	public InfoVO selectNewsByNo(String createTimeId);
	
}
