package com.file.model;

import java.io.InputStream;
import java.util.List;

public interface FileDAO {

	// 新增申請者的照片
	public void insertApplyFile(FileVO fileVo, InputStream inputStream);
	
	// 新增申請者的照片executeBatch批量更新
	public int[] insertApplyFileBatch(List<FileVO> fileVoList, List<InputStream> inputStreamList);
	
	// 查詢檔案資料(照片)
	List<FileVO> getAll();
	
	// 根據CarerID 查詢圖片位元組流
	public List<FileVO> getPic(Integer carerID);

	// 馨慧
	public FileVO findHeadShotByCarerId(Integer carerId);
	public List<FileVO> selectAll();
	
	// 0218 更新照護員照片
	public int[] updateCarerPic(List<FileVO> fileVoList, List<InputStream> inputStreamList);
}
