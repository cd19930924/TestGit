package com.carermem.model;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careskills.model.CareSkillsVO;
import com.file.model.FileDAO;
import com.file.model.FileVO;
import com.member.model.vo.MemberVO;
@Service
public class CarerMemService {

	@Autowired
	private CarerMemDAO dao;
	@Autowired
	private FileDAO fdao;

//	public CarerMemService() {
//		dao = new CarerMemDAOImpl();
//		fdao = new FileDAOImpl();
//	}

	public CarerMemVO getOneCarer(Integer carerId) {
		return dao.findByCarerId(carerId);
	}

	public List<CarerMemVO> getAll() {
		return dao.getAll();
	}

	public List<CarerMemVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}

	public MemberVO getCarerInfo(Integer carerId) {
		return dao.getCarerInfoByCarerId(carerId);
	}

	public FileVO getCarerHeadShot(Integer carerId) {
		return dao.getHeadShotByCarerId(carerId);
	}

	public FileVO getCarerCertification(Integer carerId) {
		return dao.getCertificationByCarerId(carerId);
	}

	public CareSkillsVO getCarerSkills(Integer carerId) {
		return dao.getSkillsByCarerId(carerId);
	}

	// 證照=>顯示於瀏覽單一照護員，若有才顯示
	public String getCarerCerti(Integer carerId) {
		List<FileVO> certi = dao.findCertiByCarerId(carerId);
		String certiResult = "";
		if (certi.size() == 0) {
			return "尚無資料";
		} else {
			certiResult = "<ul>";
			for (FileVO vo : certi) {
				certiResult += "<li>" + vo.getFileTypeNo() + "</li>";
			}
			return certiResult + "</ul>";
		}
	}

	// 技能=>顯示於瀏覽單一照護員，若有才顯示
	public String getCarerSkills(Integer carerId, String skillType) {
		List<CareSkillsVO> skills = dao.findCarerSkills(carerId, skillType);
		String skillsResult = "";
		if (skills.size() != 0) {
			skillsResult = "<ul>";
			for (CareSkillsVO vo : skills) {
				skillsResult += "<li>" + vo.getSkillName() + "</li>";
			}
			return skillsResult + "</ul>";
		} else {
			return "尚無資料";
		}
	}

	// 後臺用:管理照護員
	public List<CarerMemVO> getAllCarers() {
		return dao.findAllCarer();
	}

	public CarerMemVO updateMemStatus(Integer id, String status, Timestamp updatetime) {
		CarerMemVO vo = new CarerMemVO();
		vo.setCarerID(id);
		vo.setCarerStatus(status);
		vo.setUpdateTime(updatetime);
		dao.updateMemStatus(vo);
		return vo;
	}

	// 傳永用
	public CarerMemVO memGetCarer(Integer id) {
		return dao.selectCarer(id);
	}

//	// 查詢該照護員若無基本技能(0)則不會顯示
//	public String getNormalSkill(Integer carerId) {
//		List<CareSkillsVO> normalSkill = dao.findCarerSkills(carerId, "0");
//		List<String> normalSkillResult = new ArrayList<>(normalSkill.size());
//		for (CareSkillsVO vo : normalSkill) {
//			normalSkillResult.add(vo != null ? vo.toString() : null);
//		}
//		if (!normalSkillResult.isEmpty()) {
//			return "<b>【基本技能】</b>";
//		} else {
//			return "<span style=\"display:none;\"></span>";
//		}
//
//	}
//
//	// 查詢該照護員若無進階技能(1)則不會顯示
//	public String getProSkill(Integer carerId) {
//		List<CareSkillsVO> proSkill = dao.findCarerSkills(carerId, "1");
//		List<String> proSkillResult = new ArrayList<>(proSkill.size());
//		for (CareSkillsVO vo : proSkill) {
//			proSkillResult.add(vo != null ? vo.toString() : null);
//		}
//		if (!proSkillResult.isEmpty()) {
//			return "<b>【進階技能】</b>";
//		} else {
//			return "<span style=\"display:none;\"></span>";
//		}
//	}

	// 0217new (CarerID) 搜尋照護員個人資料
	public CarerMemVO selectOneCarer(Integer carerID) {
		return dao.selectOneCarer(carerID);
	}

	// 更新照護員資料
	public void updateCarerMemData(String serviceType, String serviceDistNo, Double priceHour, Double priceHalfday,
			Double priceDay, String intro, String bankCode, String bankAcct, Integer carerID) {
		dao.updateCarerMemData(serviceType, serviceDistNo, priceHour, priceHalfday, priceDay, intro, bankCode, bankAcct,
				carerID);
	}

	// 更新照護員照片
	public int[] updateCarerPic(List<FileVO> fileVoList, List<InputStream> inputStreamList) {
		return fdao.updateCarerPic(fileVoList, inputStreamList);
	}

	public static void main(String[] args) {
		CarerMemService testsvc = new CarerMemService();
		String testCerti = testsvc.getCarerCerti(9);
		String testSkills1 = testsvc.getCarerSkills(9, "0");
		String testSkills2 = testsvc.getCarerSkills(9, "1");
		System.out.println(testCerti);
		System.out.println(testSkills1);
		System.out.println(testSkills2);
	}

}