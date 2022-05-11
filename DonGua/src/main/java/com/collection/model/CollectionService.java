package com.collection.model;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionService {
	@Autowired
	private CollectionDAOImpl dao;
	
	
//	public CollectionService() {
//		dao=new CollectionDAOImpl();
//	}
	
	public List<CollectionVO> getCollectedCarer(Integer memId) throws IOException{
		return dao.findByMemId(memId);
	}
	
	public void deleteCollection(Integer memId,Integer carerId) {
		dao.deleteCollection(memId,carerId);
	}
	
	public CollectionVO addCollection(Integer memId, Integer carerId) {
		CollectionVO vo = new CollectionVO();

		vo.setMemId(memId);
		vo.setCarerId(carerId);
		dao.addCollection(vo);
		return vo;

	}

	public Integer getServiceTimes(Integer memId, Integer carerId) {
		return dao.findServiceTimes(memId,carerId);
	}
	
	//查詢是否收藏
	public Boolean isCollected(Integer memId, Integer carerId) {
		Integer check = dao.isCollected(memId, carerId);
		if (check==1) {
			return true;
		} else {
			return false;
		}
	}
	//查詢是否收藏並傳回字串
	public String isCollectedStr(Integer memId, Integer carerId) {
		Integer check = dao.isCollected(memId, carerId);
		if (check==1) {
			return "已收藏";
		} else {
			return "尚未收藏";
		}
	}
	
}
