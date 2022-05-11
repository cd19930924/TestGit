package com.meal.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.meal.model.service.MealService;
import com.meal.model.vo.MealVO;

@WebServlet("/back-end/meal/meal.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MealDataController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final String LIST_ALL_MEAL_URL = "listAllMeal.jsp";
	final String ADD_MEAL_URL = "addMeal.jsp";
	final String UPDATE_MEAL_URL = "modifyMeal.jsp";
 	

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");


		if ("insert".equals(action)) { // 靘addMeal.jsp�����  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.��隢�� - 頛詨�撘�隤方���************************/
				String mealNo = req.getParameter("mealno").trim();
				String mealNoReg = "^[(a-zA-Z0-9_)]{2,10}$";

				if (mealNo == null || mealNo.trim().length() == 0) {
					errorMsgs.add("擗�楊���: 隢蝛箇");
				} else if(mealNo.trim().length() > 5){
					errorMsgs.add("擗�楊���: 隢頛詨憭扳6蝣�");
				} else if(!mealNo.trim().matches(mealNoReg)) { //隞乩�毀蝧迤���(閬�)銵函內撘�(regular-expression)
					errorMsgs.add("擗�楊���: ������摮� , 銝摨血���2�10銋��");
	            }
				
				String mealName = req.getParameter("mealname").trim();
				String mealNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,30}$";
				
				if (mealName == null || mealName.trim().length() == 0) {
					errorMsgs.add("擗��迂: 隢蝛箇");
				}else if(!mealName.trim().matches(mealNameReg)) { //隞乩�毀蝧迤���(閬�)銵函內撘�(regular-expression)
					errorMsgs.add("擗��迂: ���銝准������摮� , 銝摨血���1�30銋��");
		        }

				BigDecimal mealPrice = new BigDecimal(req.getParameter("mealprice"));
				BigDecimal zero = new BigDecimal("0");
				if (mealPrice.compareTo(zero)<1) {
					errorMsgs.add("擗��: 隢頛詨撠���0����");
				}
				
				String mealIntroduce = req.getParameter("mealintro");
				if (mealIntroduce == null || mealIntroduce.trim().length() == 0) {
					errorMsgs.add("擗��晶: 隢蝛箇");
				}
				String savePath = this.getServletContext().getRealPath("Files");
				List<Part> mealPicList = (List<Part>) req.getParts();
				MealVO mealVO = new MealVO();
				List<byte[]> mealPicListString = mealPicList.stream().filter(part -> "pic".equals(part.getName()) && part.getSize() > 0)
						.map(part -> {
							byte[] data = null;
							try (InputStream inputStream = part.getInputStream()) {
								data =inputStream.readAllBytes();
							} catch (IOException e) {
								e.printStackTrace();
							}
							return data;
						}).filter(data -> data != null).collect(Collectors.toList());
				mealVO.setMealNo(mealNo);
				mealVO.setMealName(mealName);
				mealVO.setMealPrice(mealPrice);
				mealVO.setMealIntroduce(mealIntroduce);
				mealVO.setFileContent(mealPicListString);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mealVO", mealVO); // ���撓��撘隤斤�ealVO�隞�,銋�req
					RequestDispatcher failureView = req
							.getRequestDispatcher(ADD_MEAL_URL);
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.���憓���***************************************/
				MealService empSvc = new MealService();
				boolean isInsertSuccess = empSvc.insertMael(mealVO);
				System.out.println(isInsertSuccess);
				
				/***************************3.�憓���,皞��漱(Send the Success view)***********/
//				RequestDispatcher successView = req.getRequestDispatcher(url); // ����漱 listAllMeal.jsp
//				successView.forward(req, res);				
				res.sendRedirect(LIST_ALL_MEAL_URL);
				/***************************�隞���隤方���**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				System.out.println("銝憭望��");
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher(ADD_MEAL_URL);
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 靘listAllEmp.jsp�����

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.��隢��****************************************/
				String mealNo = new String(req.getParameter("mealno"));
				/***************************2.���閰Ｚ���****************************************/
				MealService mealSvc = new MealService();
				MealVO mealVO = mealSvc.getOneMeal(mealNo);
				System.out.println(mealVO.getMealNo());

				
				/***************************3.�閰Ｗ���,皞��漱(Send the Success view)************/ 
				req.setAttribute("mealVO", mealVO);         // 鞈�澈����ealVO�隞�,摮req	    
				RequestDispatcher successView = req.getRequestDispatcher(UPDATE_MEAL_URL);// ����漱 modifyMeal.jsp
				successView.forward(req, res);

				/***************************�隞���隤方���**********************************/
			} catch (Exception e) {
				errorMsgs.add("�瘜���耨������:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(LIST_ALL_MEAL_URL);
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) { // 靘modifyMeal.jsp�����  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.��隢�� - 頛詨�撘�隤方���************************/
				String mealNo = req.getParameter("mealno").trim();
				String mealName = req.getParameter("mealname").trim();
				String mealNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
				
				if (mealName == null || mealName.trim().length() == 0) {
					errorMsgs.add("擗��迂: 隢蝛箇");
				} else if(!mealName.trim().matches(mealNameReg)) { //隞乩�毀蝧迤���(閬�)銵函內撘�(regular-expression)
					errorMsgs.add("擗��迂: ���銝准������摮� , 銝摨血���1�10銋��");
	            }
				
				BigDecimal mealPrice = new BigDecimal(req.getParameter("mealprice"));
				BigDecimal zero = new BigDecimal("0");
				BigDecimal maxPrice =  new BigDecimal("10000000000"); 
				if (mealPrice.compareTo(zero)<1) {
					errorMsgs.add("擗��: 隢頛詨撠���0����");
				}else if(mealPrice.compareTo(maxPrice)>=1) {
					errorMsgs.add("擗��: 隢頛詨憭扳10雿銋��");
				}
				
				String mealIntroduce = req.getParameter("mealintro");
				if (mealIntroduce == null || mealIntroduce.trim().length() == 0) {
					errorMsgs.add("擗��晶: 隢蝛箇");
				}
				String mealStatus = req.getParameter("status");
				String savePath = this.getServletContext().getRealPath("Files");
				List<Part> mealPicList = (List<Part>) req.getParts();
				InputStream is = null;
				MealVO mealVO = new MealVO();
				List<byte[]> mealPicListString = mealPicList.stream().filter(part -> "pic".equals(part.getName()) && part.getSize() > 0)
						.map(part -> {
							byte[] data = null;
							try (InputStream inputStream = part.getInputStream()) {
								data =inputStream.readAllBytes();
							} catch (IOException e) {
								e.printStackTrace();
							}
							return data;
						}).filter(data -> data != null).collect(Collectors.toList());
				
				String changePics = req.getParameter("select_pic");
				System.out.println(changePics);
				String[] subs = new String[3];
				for(String changePic : changePics.split("\\|")) {
					if(!"".equals(changePic)) {
						int i = 0;
						System.out.println(changePic);
						subs[i]=changePic;
						i++;
					}
				}
				
				mealVO.setMealNo(mealNo);
				mealVO.setMealName(mealName);
				mealVO.setMealPrice(mealPrice);
				mealVO.setMealStatus(mealStatus);
				mealVO.setMealIntroduce(mealIntroduce);
				mealVO.setFileContent(mealPicListString);
				mealVO.setUpdatePics(subs);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mealVO", mealVO); // ���撓��撘隤斤�ealVO�隞�,銋�req
					RequestDispatcher failureView = req
							.getRequestDispatcher(UPDATE_MEAL_URL);
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.���憓���***************************************/
				MealService mealSvc = new MealService();
				int isUpdateSuccess = mealSvc.updateOneMeal(mealVO);
				
				/***************************3.�憓���,皞��漱(Send the Success view)***********/
				RequestDispatcher successView = req.getRequestDispatcher(LIST_ALL_MEAL_URL); // ����漱 listAllMeal.jsp
				successView.forward(req, res);				
//				res.sendRedirect(LIST_ALL_MEAL_URL);
				/***************************�隞���隤方���**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(UPDATE_MEAL_URL);
				failureView.forward(req, res);
			}
		}
		if ("delete".equals(action)) { // 靘modifyMeal.jsp�����  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.��隢�� - 頛詨�撘�隤方���************************/
				String mealNo = req.getParameter("mealno").trim();

			
				/***************************2.����鞈��***************************************/
				MealService mealSvc = new MealService();
				int isUpdateSuccess = mealSvc.deleteOneMeal(mealNo);
				
				/***************************3.�憓���,皞��漱(Send the Success view)***********/
//				RequestDispatcher successView = req.getRequestDispatcher(url); // ����漱 listAllMeal.jsp
//				successView.forward(req, res);	
				res.sendRedirect("/CFA104G6/back-end/meal/listAllMeal.jsp");
				
				
				/***************************�隞���隤方���**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(LIST_ALL_MEAL_URL);
				failureView.forward(req, res);
			}
		}
	
	}
}
