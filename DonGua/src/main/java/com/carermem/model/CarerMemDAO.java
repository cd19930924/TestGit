package com.carermem.model;

import java.util.List;
import java.util.Map;

import com.careskills.model.CareSkillsVO;
import com.file.model.FileVO;
import com.member.model.vo.MemberVO;

public interface CarerMemDAO {

	// 新增照護會員資料
	public void insert(CarerMemVO carerMemVO);

	// 修改照護會員資料
	public void update(CarerMemVO carerMemVO);

	// 刪除照護會員資料
	public void delete(Integer carerID);

	// 查詢一筆照護員資料(memID)
	CarerMemVO findByCarerId(Integer deptno);

	// 查詢全部照護員的資料
	List<CarerMemVO> getAll();

	// search carer with conditions
	public List<CarerMemVO> getAll(Map<String, String[]> map);

	public MemberVO getCarerInfoByCarerId(Integer carerId);

	public FileVO getHeadShotByCarerId(Integer carerId);

	public FileVO getCertificationByCarerId(Integer carerId);

	public CareSkillsVO getSkillsByCarerId(Integer carerId);

	public long isMemberExist(String memberAccount, String password) throws ClassNotFoundException;

	public boolean isCarerIdExist(long carerId) throws ClassNotFoundException;

	public boolean activeCarerStatus(String carerAccount) throws ClassNotFoundException;

	public boolean isCarerAcctExist(String carerAccount) throws ClassNotFoundException;

	// List包證照=>顯示於瀏覽單一照護員
	public List<FileVO> findCertiByCarerId(Integer carerId);

	// List包技能=>顯示於瀏覽單一照護員
	public List<CareSkillsVO> findCarerSkills(Integer carerId, String skillType);

	// 後臺用:管理照護員
	public List<CarerMemVO> findAllCarer();

	// 後臺用：更新狀態
	public void updateMemStatus(CarerMemVO vo);

	// 傳永(照護員ID)
	public CarerMemVO selectCarer(Integer id);

	// 0217new (CarerID) 搜尋照護員個人資料
	public CarerMemVO selectOneCarer(Integer carerID);

	// 0218new (carerID) 更新照護員資料
	public void updateCarerMemData(String serviceType, String serviceDistNo, Double priceHour, Double priceHalfday,
			Double priceDay, String intro, String bankCode, String bankAcct, Integer carerID);
}
