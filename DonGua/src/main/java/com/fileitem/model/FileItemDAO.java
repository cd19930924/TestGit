package com.fileitem.model;

import java.util.List;

import com.file.model.FileVO;


public interface FileItemDAO {

	// 查詢照護技能資料
	List<FileItemVO> getAll();
	
	  public FileVO findHeadShotByCarerId(Integer carerId);
}
