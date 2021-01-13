package com.activity_product.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.activity_product.model.*;
import com.sell.model.SellVO;


public class ActivityProductServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String act_id = req.getParameter("act_id");
				if (act_id == null || (act_id.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-sell-end/activity_product/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				/***************************2.開始查詢資料*****************************************/
				ActivityProductService actproSvc = new ActivityProductService();
				ActivityProductVO actproVO = actproSvc.getOneActPro(act_id);
				if (actproVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-sell-end/activity_product/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("actproVO", actproVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-sell-end/activity_product/listOneActivityProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-sell-end/activity_productselect_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("get_ActivityProduct_From_Sellmem".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				HttpSession session = req.getSession();
				String sell_mem_id=((SellVO) session.getAttribute("sellVO")).getSellMemId();
				System.out.println(sell_mem_id);
				if (sell_mem_id == null || (sell_mem_id.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-sell-end/activity_product/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				/***************************2.開始查詢資料*****************************************/
				ActivityProductService actproSvc = new ActivityProductService();
				List<ActivityProductVO>list = actproSvc.getAllbySellMemId(sell_mem_id);
				if (list.isEmpty()) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-sell-end/activity_product/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("actprolist", list); // 資料庫取出的empVO物件,存入req
				String url = "/front-sell-end/activity_product/listOneActivityProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-sell-end/activity_productselect_page.jsp");
				failureView.forward(req, res);
			}
		}
	
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String act_id = req.getParameter("act_id");
				
				/***************************2.開始查詢資料****************************************/
				ActivityProductService actproSvc = new ActivityProductService();
				ActivityProductVO actproVO = actproSvc.getOneActPro(act_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("actproVO", actproVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front-sell-end/activity_product/update_ActivityProduct_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-sell-end/activity_product/listOneActivityProduct.jsp");
				failureView.forward(req, res);
			}
		}
	
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String act_id =req.getParameter("act_id").trim();
				
				String sell_mem_id = req.getParameter("sell_mem_id").trim();
				
				String act_type_id = req.getParameter("act_type_id").trim();
				if (act_type_id == null || act_type_id.trim().length() == 0) {
					errorMsgs.add("活動種類編號請勿空白");
				}	
				
				String act_name= req.getParameter("act_name").trim();
				if (act_name == null || act_name.trim().length() == 0) {
					errorMsgs.add("活動名稱請勿空白");
				}	
				

				Double act_price = null;
				try {
					act_price = new Double(req.getParameter("act_price").trim());
					if(act_price<0) errorMsgs.add("請填入正整數");
				} catch (NumberFormatException e) {
					act_price = 0.0;
					errorMsgs.add("活動價格請填數字.");
				}
				
				String act_des = req.getParameter("act_des").trim();
				if (act_des == null || act_name.trim().length() == 0) {
					errorMsgs.add("活動敘述請勿空白");
				}	
				
				String act_add = req.getParameter("act_add").trim();
				if (act_add == null || act_name.trim().length() == 0) {
					errorMsgs.add("活動地點請勿空白");
				}	
				

				

				ActivityProductVO actproVO = new ActivityProductVO();
				actproVO.setAct_id(act_id);
				actproVO.setSell_mem_id(sell_mem_id);;
				actproVO.setAct_type_id(act_type_id);;
				actproVO.setAct_name(act_name);
				actproVO.setAct_price(act_price);;
				actproVO.setAct_des(act_des);;
				actproVO.setAct_add(act_add);;

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actproVO", actproVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-sell-end/activity_product/update_ActivityProduct_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ActivityProductService actproSvc = new ActivityProductService();
				actproSvc.update(actproVO);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("actproVO", actproVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-sell-end/activity_product/listOneActivityProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-sell-end/activity_product/update_ActivityProduct_input.jsp");
				failureView.forward(req, res);
			}
		}
	

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String sell_mem_id = req.getParameter("sell_mem_id").trim();
				
				String act_type_id = req.getParameter("act_type_id").trim();
				if (act_type_id == null || act_type_id.trim().length() == 0) {
					errorMsgs.add("活動種類編號請勿空白");
				}	
				
				String act_name= req.getParameter("act_name").trim();
				if (act_name == null || act_name.trim().length() == 0) {
					errorMsgs.add("活動名稱請勿空白");
				}	
				

				Double act_price = null;
				try {
					act_price = new Double(req.getParameter("act_price").trim());
				} catch (NumberFormatException e) {
					act_price = 0.0;
					errorMsgs.add("活動價格請填數字.");
				}
				String act_des = req.getParameter("act_des").trim();
				if (act_des == null || act_name.trim().length() == 0) {
					errorMsgs.add("活動敘述請勿空白");
				}	
				
				String act_add = req.getParameter("act_add").trim();
				if (act_add == null || act_name.trim().length() == 0) {
					errorMsgs.add("活動地點請勿空白");
				}	
				

				

				ActivityProductVO actproVO = new ActivityProductVO();
				actproVO.setSell_mem_id(sell_mem_id);;
				actproVO.setAct_type_id(act_type_id);;
				actproVO.setAct_name(act_name);
				actproVO.setAct_price(act_price);;
				actproVO.setAct_des(act_des);;
				actproVO.setAct_add(act_add);;

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actproVO", actproVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-sell-end/activity_product/addActivityProduct.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ActivityProductService actproSvc = new ActivityProductService();
				actproSvc.insert(actproVO);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-sell-end/activity_product/listOneActivityProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-sell-end/activity_product/addActivityProduct.jsp");
				failureView.forward(req, res);
			}
		}
//		
//		
//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.接收請求參數***************************************/
//				Integer empno = new Integer(req.getParameter("empno"));
//				
//				/***************************2.開始刪除資料***************************************/
//				EmpService empSvc = new EmpService();
//				empSvc.deleteEmp(empno);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/emp/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/listAllEmp.jsp");
//				failureView.forward(req, res);
//			}
//		}
//	}

}}
