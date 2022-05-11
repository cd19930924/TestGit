package com.carermemapply.model;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careskills.model.CareSkillsDAO;
import com.careskills.model.CareSkillsVO;
import com.file.model.FileDAO;
import com.file.model.FileVO;

@Service
public class CarerMemApplyService {

	@Autowired
	private CarerMemApplyDAO cmadao;
	@Autowired
	private CareSkillsDAO csdao;
	@Autowired
	private FileDAO fdao;
	
//	public CarerMemApplyService() {
//		cmadao = new CarerMemApplyDAOImpl();
//		csdao = new CarerSkillsDAOImpl();
//		fdao = new FileDAOImpl();
//	}
	
	public CarerMemApplyVO insertData(Integer memID, String serviceDistNo, String bankCode, String bankAcct, String serviceType,
			String intro, Double priceHour, Double priceHalfday, Double priceDay) {
		
		CarerMemApplyVO carerMemApplyVo = new CarerMemApplyVO();
		
		carerMemApplyVo.setMemID(memID);
		carerMemApplyVo.setServiceDistNo(serviceDistNo);
		carerMemApplyVo.setBankCode(bankCode);
		carerMemApplyVo.setBankAcct(bankAcct);
		carerMemApplyVo.setServiceType(serviceType);
		carerMemApplyVo.setIntro(intro);
		carerMemApplyVo.setPriceHour(priceHour);
		carerMemApplyVo.setPriceHalfday(priceHalfday);
		carerMemApplyVo.setPriceDay(priceDay);
		cmadao.insertApplyData(carerMemApplyVo);
		
		return carerMemApplyVo;
	}
	
	public CareSkillsVO insertSkills(Integer carerID, String skillNo) {
		
		CareSkillsVO careSkillsVo = new CareSkillsVO();
		
		careSkillsVo.setCarerID(carerID);
		careSkillsVo.setSkillNo(skillNo);
		csdao.insertApplySkill(careSkillsVo);
		
		return careSkillsVo;
	}
	
	public FileVO insertApplyFile(Integer carerID, String fileTypeNo, InputStream fileContent) {
		FileVO fileVo = new FileVO();
		fileVo.setCarerID(carerID);
		fileVo.setFileTypeNo(fileTypeNo);
		fdao.insertApplyFile(fileVo,fileContent);
		return fileVo;
	}
	
	// executeBatch批量更新照片
	public int[] insertApplyFileBatch(List<FileVO> fileVoList, List<InputStream> inputStreamList) {
		return fdao.insertApplyFileBatch(fileVoList, inputStreamList);
	}
	
	public List<CarerMemApplyVO> getAll(){
		return cmadao.getAll();
	}
}
