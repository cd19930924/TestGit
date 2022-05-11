package com.carermemapply.controller;

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

import com.carermemapply.model.*;
import com.careskills.model.*;
import com.file.model.*;

@WebServlet("/front-end/carermemapply/carerMemApply.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
//當數據量大於fileSizeThreshold值時，內容將被寫入磁碟
//上傳過程中無論是單個文件超過maxFileSize值，或者上傳的總量大於maxRequestSize 值都會拋出IllegalStateException 異常
public class CarerApplyMemServlet extends HttpServlet {

	private static final long serialVersionUID = 4219954416922366765L;

	InputStream inputStream;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// 送出申請的請求
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
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
					e.printStackTrace();
				}
				
				String bankAcct = req.getParameter("bankAcct");
				String bankAcctReg = "^[0-9]{10,14}$";
				if (bankAcct == null || bankAcct.trim().length() == 0) {
					errorMsgs.add("銀行帳號請勿空白");
				} else if(!bankAcct.trim().matches(bankAcctReg)) { 
					errorMsgs.add("銀行帳號輸入錯誤");
	            }

				CarerMemApplyVO carerMemApplyVo = new CarerMemApplyVO();
				carerMemApplyVo.setPriceHour(priceHour);
				carerMemApplyVo.setPriceHalfday(priceHalfDay);
				carerMemApplyVo.setPriceDay(priceDay);
				carerMemApplyVo.setBankAcct(bankAcct);
				
				CareSkillsVO carerSkillsVo = new CareSkillsVO();
				
				FileVO fileVo = new FileVO();
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					// 含有輸入格式錯誤的CarerMemApplyVO物件,也存入req
					req.setAttribute("CarerMemApplyVO", carerMemApplyVo);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/carermemapply/CarerMemApplyPage.jsp");
					failureView.forward(req, res);
					return;
				}

				// 2.開始新增資料 存入申請照護員表格
				CarerMemApplyService carerMemApplySvc = new CarerMemApplyService();
				
				carerMemApplyVo = carerMemApplySvc.insertData(Integer.parseInt(req.getParameter("memID")),
						req.getParameter("district"), req.getParameter("bankCode"), bankAcct,
						req.getParameter("serviceTpye"), req.getParameter("intro"), priceHour, priceHalfDay, priceDay);
				// 存入技能表格
				for(String skill : req.getParameterValues("commSkill")) {
					carerSkillsVo = carerMemApplySvc.insertSkills(Integer.parseInt(req.getParameter("memID")), skill);
				}
				for(String skill : req.getParameterValues("ProSkill")) {
					carerSkillsVo = carerMemApplySvc.insertSkills(Integer.parseInt(req.getParameter("memID")), skill);
				}
				
				// 存入檔案照片表格
//				Part filePart1 = req.getPart("photoP01");
//		        if (filePart1 != null) {
//		            // prints out some information for debugging
//		            System.out.println(filePart1.getContentType());
//		            // obtains input stream of the upload file
//		            inputStream = filePart1.getInputStream();
//		        }
//		        fileVo = carerMemApplySvc.insertApplyFile(Integer.parseInt(req.getParameter("memID")), 
//		        		req.getParameter("P01"), inputStream);
//				
//				Part filePart2 = req.getPart("photoP02");
//		        if (filePart2 != null) {
//		            inputStream = filePart2.getInputStream();
//		        }
//				fileVo = carerMemApplySvc.insertApplyFile(Integer.parseInt(req.getParameter("memID")), 
//						req.getParameter("P02"), inputStream);
//				
//				Part filePart3 = req.getPart("photoP03");
//		        if (filePart3 != null) {
//		            inputStream = filePart3.getInputStream();
//		        }
//				fileVo = carerMemApplySvc.insertApplyFile(Integer.parseInt(req.getParameter("memID")), 
//						req.getParameter("P03"), inputStream);
//				
//				Part filePart4 = req.getPart("photoP04");
//		        if (filePart4 != null) {
//		            inputStream = filePart4.getInputStream();
//		        }
//				fileVo = carerMemApplySvc.insertApplyFile(Integer.parseInt(req.getParameter("memID")), 
//						req.getParameter("P04"), inputStream);
//				
//				Part filePart5 = req.getPart("photoP05");
//		        if (filePart5 != null) {
//		            inputStream = filePart5.getInputStream();
//		        }
//				fileVo = carerMemApplySvc.insertApplyFile(Integer.parseInt(req.getParameter("memID")), 
//						req.getParameter("P05"), inputStream);
//				
//				Part filePart6 = req.getPart("photoC01");
//		        if (filePart6 != null) {
//		            inputStream = filePart6.getInputStream();
//		        }
//				fileVo = carerMemApplySvc.insertApplyFile(Integer.parseInt(req.getParameter("memID")), 
//						req.getParameter("C01"), inputStream);
//				
//				Part filePart7 = req.getPart("photoC02");
//		        if (filePart7 != null) {
//		            inputStream = filePart7.getInputStream();
//		        }
//				fileVo = carerMemApplySvc.insertApplyFile(Integer.parseInt(req.getParameter("memID")), 
//						req.getParameter("C02"), inputStream);
//				
//				Part filePart8 = req.getPart("photoC07");
//		        if (filePart8 != null) {
//		            inputStream = filePart8.getInputStream();
//		        }
//				fileVo = carerMemApplySvc.insertApplyFile(Integer.parseInt(req.getParameter("memID")), 
//						req.getParameter("C07"), inputStream);
				
				
				// 使用executeBatch批量更新照片
				List<FileVO> fileVoList = new ArrayList<>();
				List<InputStream> inputStreamList = new ArrayList<InputStream>();
				
				Integer carerID = Integer.parseInt(req.getParameter("memID"));
				for(int i = 1; i <= 5; i++) {
					String fileTypeNo = req.getParameter("P0" + i);
					Part filePart1 = req.getPart("photoP0" + i);
			        if (filePart1 != null) {
			            inputStream = filePart1.getInputStream();
			        }
			        fileVo = new FileVO();
					fileVo.setCarerID(carerID);
					fileVo.setFileTypeNo(fileTypeNo);
					fileVoList.add(fileVo);
					inputStreamList.add(inputStream);
				}
				
				for(int i = 1; i <= 2; i++) {
					String fileTypeNo = req.getParameter("C0" + i);
					Part filePart1 = req.getPart("photoC0" + i);
			        if (filePart1 != null) {
			            inputStream = filePart1.getInputStream();
			        }
			        fileVo = new FileVO();
					fileVo.setCarerID(carerID);
					fileVo.setFileTypeNo(fileTypeNo);
					fileVoList.add(fileVo);
					inputStreamList.add(inputStream);
				}
		      
				String fileTypeNo = req.getParameter("C07");
				Part filePart1 = req.getPart("photoC07");
		        if (filePart1 != null) {
		            inputStream = filePart1.getInputStream();
		        }
		        fileVo = new FileVO();
				fileVo.setCarerID(carerID);
				fileVo.setFileTypeNo(fileTypeNo);
				fileVoList.add(fileVo);
				inputStreamList.add(inputStream);
				
				int[] result = carerMemApplySvc.insertApplyFileBatch(fileVoList, inputStreamList);
				System.out.println(result);
				
				
				
				// 3.新增完成,準備轉交(Send the Success view)
//				String url = "/front-end/AfterLogin.html";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交AfterLogin.html
//				successView.forward(req, res);
				
				res.sendRedirect("../afterlogin.jsp");

				// 4.其他可能的錯誤處理
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/carermemapply/CarerMemApplyPage.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
			}

		}

	}

}
