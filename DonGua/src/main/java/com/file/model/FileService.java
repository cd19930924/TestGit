package com.file.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {
	
	@Autowired
	private FileDAO dao;
	
//	public FileService() {
//		dao = new FileDAOImpl();
//	}
	
	public FileVO getHeadShot(Integer carerId) {
		return dao.findHeadShotByCarerId(carerId);
	}
	
	public List<FileVO> getAll() {
		return dao.getAll();
	}
	
	public List<FileVO> getPic(Integer carerID) {
		return dao.getPic(carerID);
	}

}
