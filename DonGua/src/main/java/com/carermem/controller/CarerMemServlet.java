package com.carermem.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.carermem.model.CarerMemService;
import com.carermem.model.CarerMemVO;
import com.carermemapply.model.CarerMemApplyService;
import com.careskills.model.CareSkillsService;
import com.careskills.model.CareSkillsVO;
import com.file.model.FileService;
import com.file.model.FileVO;
import com.skill.model.SkillService;

@WebServlet("/front-end/updatecarerdata/showCarerData.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class CarerMemServlet extends HttpServlet {

	
	
	InputStream inputStream;

	private static final long serialVersionUID = 6591260162571820412L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		List<String> errorMsgs = new ArrayList<>();

		// 送出查看照護員資料請求
		if ("showCarerData".equals(action)) {
			try {
				req.setAttribute("errorMsgs", errorMsgs);
				Integer carerID = Integer.valueOf(req.getParameter("carerID"));

				CarerMemService caSvc = new CarerMemService();
				SkillService sSvc = new SkillService();
				FileService fileSvc = new FileService();

				// 讀取單一照護員資料
				caSvc.selectOneCarer(carerID);
				// 讀取單一照護員技能
				sSvc.getOneCarerSkills(carerID);

				// 讀取照片
				List<FileVO> list = fileSvc.getPic(carerID);
				req.setAttribute("photoresult", list);

				RequestDispatcher success = req.getRequestDispatcher("/front-end/updatecarerdata/UpdateCarerData.jsp");
				success.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("失敗1：" + e.getMessage());

				e.printStackTrace(System.err);

				RequestDispatcher fail = req.getRequestDispatcher("/front-end/updatecarerdata/UpdateCarerData.jsp");
				fail.forward(req, res);
			}
		}

		// 送出修改照護員資料的請求
		if ("update".equals(action)) {

			try {
				req.setAttribute("errorMsgs", errorMsgs);
				Integer carerID = Integer.valueOf(req.getParameter("carerID"));
				
				// 1.接收請求參數 - 輸入格式的錯誤處理
				Double priceHour = null;
				Double priceHalfDay = null;
				Double priceDay = null;
				try {
					priceHour = Double.valueOf((req.getParameter("priceHour").trim()));
					priceHalfDay = Double.valueOf((req.getParameter("priceHalfDay").trim()));
					priceDay = Double.valueOf((req.getParameter("priceDay").trim()));
				} catch (NumberFormatException e) {
					priceHour = 0.0;
					errorMsgs.add("價錢請填數字.");
				}

				String bankAcct = req.getParameter("bankAcct");
				String bankAcctReg = "^[0-9]{10,14}$";
				if (bankAcct == null || bankAcct.trim().length() == 0) {
					errorMsgs.add("銀行帳號請勿空白");
				} else if (!bankAcct.trim().matches(bankAcctReg)) {
					errorMsgs.add("銀行帳號輸入錯誤");
				}
				CarerMemVO carerMemVo2 = new CarerMemVO();
				CareSkillsVO carerSkillsVo = new CareSkillsVO();

				CarerMemService caSvc = new CarerMemService();
				CarerMemApplyService carerMemApplySvc = new CarerMemApplyService();
				CareSkillsService csSvc = new CareSkillsService();

				FileService fileSvc = new FileService();
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					// 含有輸入格式錯誤的CarerMemVO物件,也存入req
					req.setAttribute("CarerMemVO", carerMemVo2);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/updatecarerdata/UpdateCarerData.jsp");
					List<FileVO> list = fileSvc.getPic(carerID);
					req.setAttribute("photoresult", list);
					failureView.forward(req, res);
					return;
				}

				// 2.開始新增資料 存入申請照護員表格
				// 更新照護員技能前先刪除所有技能
				csSvc.deleteCarerSkills(carerID);

				// 更新照護員的基本資料
				caSvc.updateCarerMemData(req.getParameter("serviceTpye"), req.getParameter("district"), priceHour,
						priceHalfDay, priceDay, req.getParameter("intro"), req.getParameter("bankCode"), bankAcct,
						carerID);

				// 更新照護員技能
				for (String skill : req.getParameterValues("commSkill")) {
					carerSkillsVo = carerMemApplySvc.insertSkills(Integer.parseInt(req.getParameter("carerID")), skill);
				}
				for (String skill : req.getParameterValues("ProSkill")) {
					carerSkillsVo = carerMemApplySvc.insertSkills(Integer.parseInt(req.getParameter("carerID")), skill);
				}

				// 更新有變動過的照片
				FileVO fileVo = new FileVO();
				List<FileVO> fileVoList = new ArrayList<>();
				List<InputStream> inputStreamList = new ArrayList<InputStream>();
				// 拿到各個照片的ID
				for (int i = 1; i <= 5; i++) {
					String fileTypeNo = req.getParameter("P0" + i);
					Part filePart1 = req.getPart("photoP0" + i);
					// 判斷未更新照片時的filename是否為null
					String filename = getFileNameFromPart(filePart1);

//					System.out.println(filename);
					// 如果沒更新照片 就不會洗掉原本的照片
					if (filename != null) {
						inputStream = filePart1.getInputStream();
						fileVo = new FileVO();
						fileVo.setCarerID(carerID);
						fileVo.setFileTypeNo(fileTypeNo);
						fileVoList.add(fileVo);
						inputStreamList.add(inputStream);
					}
				}

				for (int i = 1; i <= 2; i++) {
					String fileTypeNo = req.getParameter("C0" + i);
					Part filePart1 = req.getPart("photoC0" + i);
					String filename = getFileNameFromPart(filePart1);
					if (filename != null) {
						inputStream = filePart1.getInputStream();
						fileVo = new FileVO();
						fileVo.setCarerID(carerID);
						fileVo.setFileTypeNo(fileTypeNo);
						fileVoList.add(fileVo);
						inputStreamList.add(inputStream);
					}
				}

				String fileTypeNo = req.getParameter("C07");
				Part filePart1 = req.getPart("photoC07");
				String filename = getFileNameFromPart(filePart1);
				if (filename != null) {
					inputStream = filePart1.getInputStream();
					fileVo = new FileVO();
					fileVo.setCarerID(carerID);
					fileVo.setFileTypeNo(fileTypeNo);
					fileVoList.add(fileVo);
					inputStreamList.add(inputStream);
				}

				int[] result = caSvc.updateCarerPic(fileVoList, inputStreamList);
//				System.out.println(result);

				List<FileVO> list = fileSvc.getPic(carerID);
				req.setAttribute("photoresult", list);

				// 3.新增完成,準備轉交(Send the Success view)
				RequestDispatcher success = req.getRequestDispatcher("/front-end/updatecarerdata/UpdateCarerData.jsp");
				success.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("失敗2：" + e.getMessage());

				e.printStackTrace(System.err);

				RequestDispatcher fail = req.getRequestDispatcher("/front-end/updatecarerdata/UpdateCarerData.jsp");
				fail.forward(req, res);
			}
		}

	}

	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

}
