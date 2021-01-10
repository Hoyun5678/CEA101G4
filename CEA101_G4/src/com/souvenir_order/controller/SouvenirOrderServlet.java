package com.souvenir_order.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.souvenir_order.model.SouvenirOrderService;
import com.souvenir_order.model.SouvenirOrderVO;

public class SouvenirOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("sou_order_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_order/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String sou_order_id = null;
				try {
					sou_order_id = str;
				} catch (Exception e) {
					errorMsgs.add("訂單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_order/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				SouvenirOrderService soSvc = new SouvenirOrderService();
				SouvenirOrderVO soVO = soSvc.getOneSouvenirOrder(sou_order_id);
				if (soVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_order/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("soVO", soVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/souvenir_order/listOneSouvenirOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneSouvenirOrder.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_order/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String sou_order_id = req.getParameter("sou_order_id");

				/*************************** 2.開始查詢資料 ****************************************/
				SouvenirOrderService soSvc = new SouvenirOrderService();
				SouvenirOrderVO soVO = soSvc.getOneSouvenirOrder(sou_order_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("soVO", soVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/souvenir_order/update_souvenirorder_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_order/listAllSouvenirOrder.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_souvenirorder_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String sou_order_id = req.getParameter("sou_order_id").trim();
				String emp_id = req.getParameter("emp_id").trim();
				String empIdReg = "^[(a-zA-Z0-9)]{2,100}$";
				if (emp_id == null || emp_id.trim().length() == 0) {
					errorMsgs.add("員工編號請勿空白");
				} else if (!emp_id.trim().matches(empIdReg)) {
					errorMsgs.add("員工編號: 只能是英文字母、數字 , 且長度必需在2到10之間");
				}
				String mem_id = req.getParameter("mem_id").trim();
				String memIdReg = "^[(a-zA-Z0-9)]{2,100}$";
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				} else if (!mem_id.trim().matches(memIdReg)) {
					errorMsgs.add("會員編號: 只能是英文字母、數字 , 且長度必需在2到10之間");
				}
				java.sql.Timestamp sou_order_date = java.sql.Timestamp.valueOf(req.getParameter("sou_order_date").trim());
				String sou_receiver_name = req.getParameter("sou_receiver_name").trim();
//				if (sou_receiver_name == null || emp_id.trim().length() == 0) {
//					errorMsgs.add("收穫人姓名請勿空白");
//				} 
				String sou_receiver_address = req.getParameter("sou_receiver_address").trim();		
//				if (sou_receiver_name == null || emp_id.trim().length() == 0) {
//					errorMsgs.add("收穫人地址請勿空白");
//				} 
				
				String sou_receiver_phone = req.getParameter("sou_receiver_phone").trim();
				String sRPReg = "09\\d{8}";
				if (sou_receiver_phone == null || sou_receiver_phone.trim().length() == 0) {
					errorMsgs.add("收穫人電話請勿空白");
				} else if (!sou_receiver_phone.trim().matches(sRPReg)) {
					errorMsgs.add("收穫人電話: 只能是數字 ,開頭為09 且長度必需是10");
				}
				
				Integer sou_shipment_fee = null	;
				try {
					sou_shipment_fee =new Integer(req.getParameter("sou_shipment_fee").trim());
				} catch (NumberFormatException e) {
					sou_shipment_fee = new Integer(0);
					errorMsgs.add("請輸入數字.");
				}
				Integer sou_order_sum_price = null;
				try {
					sou_order_sum_price =new Integer(req.getParameter("sou_order_sum_price").trim());
				} catch (NumberFormatException e) {
					sou_order_sum_price = new Integer(0);
					errorMsgs.add("請輸入數字.");
				}
				String sou_order_remarks = req.getParameter("sou_order_remarks");

				Integer sou_shipping_method = null;
				try {
					sou_shipping_method =new Integer(req.getParameter("sou_shipping_method").trim());
				} catch (NumberFormatException e) {
					sou_shipping_method = new Integer(0);
					errorMsgs.add("請輸入數字.");
				}
				Integer sou_order_status = null;
				try {
					sou_order_status =new Integer(req.getParameter("sou_order_status").trim());
				} catch (NumberFormatException e) {
					sou_order_status = new Integer(0);
					errorMsgs.add("請輸入數字.");
				}
				Integer sou_payment_status = null;
				try {
					sou_payment_status =new Integer(req.getParameter("sou_payment_status").trim());
				} catch (NumberFormatException e) {
					sou_payment_status = new Integer(0);
					errorMsgs.add("請輸入數字.");
				}
				Integer sou_shipment_status = null;
				try {
					sou_shipment_status =new Integer(req.getParameter("sou_shipment_status").trim());
				} catch (NumberFormatException e) {
					sou_shipment_status = new Integer(0);
					errorMsgs.add("請輸入數字.");
				}
				
				
				


				SouvenirOrderVO soVO = new SouvenirOrderVO();
				soVO.setEmp_id(emp_id);
				soVO.setMem_id(mem_id);
				soVO.setSou_order_date(sou_order_date);
				soVO.setSou_receiver_name(sou_receiver_name);
				soVO.setSou_receiver_address(sou_receiver_address);
				soVO.setSou_receiver_phone(sou_receiver_phone);
				soVO.setSou_shipment_fee(sou_shipment_fee);
				soVO.setSou_order_sum_price(sou_order_sum_price);
				soVO.setSou_order_remarks(sou_order_remarks);
				soVO.setSou_shipping_method(sou_shipping_method);
				soVO.setSou_order_status(sou_order_status);
				soVO.setSou_payment_status(sou_payment_status);
				soVO.setSou_shipment_status(sou_shipment_status);
				soVO.setSou_order_id(sou_order_id);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("soVO", soVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_order/update_souvenirorder_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				SouvenirOrderService soSvc = new SouvenirOrderService();
				soVO = soSvc.updateSouvenirOrder(emp_id, mem_id, sou_order_date,
						sou_receiver_name, sou_receiver_address, sou_receiver_phone, sou_shipment_fee,
						 sou_order_sum_price, sou_order_remarks, sou_shipping_method,
						sou_order_status, sou_payment_status, sou_shipment_status, sou_order_id);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("soVO", soVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/souvenir_order/listOneSouvenirOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_order/update_souvenirorder_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String emp_id = req.getParameter("emp_id").trim();
				String empIdReg = "^[(a-zA-Z0-9)]{2,100}$";
				if (emp_id == null || emp_id.trim().length() == 0) {
					errorMsgs.add("員工編號請勿空白");
				} else if (!emp_id.trim().matches(empIdReg)) {
					errorMsgs.add("員工編號: 只能是英文字母、數字和 , 且長度必需在2到10之間");
				}
				String mem_id = req.getParameter("mem_id").trim();
				String memIdReg = "^[(a-zA-Z0-9)]{2,100}$";
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				} else if (!mem_id.trim().matches(memIdReg)) {
					errorMsgs.add("會員編號: 只能是英文字母、數字和 , 且長度必需在2到10之間");
				}
//				java.sql.Timestamp sou_order_date = null;
//				try {
//					String sod=req.getParameter("sou_order_date").trim();
//					sou_order_date = java.sql.Timestamp.valueOf(sod);
//				} catch (IllegalArgumentException e) {
//					sou_order_date = new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("請加入時間");
//				}
				String sou_receiver_name = req.getParameter("sou_receiver_name").trim();
				if (sou_receiver_name == null || emp_id.trim().length() == 0) {
					errorMsgs.add("收穫人姓名請勿空白");
				} 
				String sou_receiver_address = req.getParameter("sou_receiver_address").trim();
				if (sou_receiver_name == null || emp_id.trim().length() == 0) {
					errorMsgs.add("收穫人地址請勿空白");
				} 
				
				String sou_receiver_phone = req.getParameter("sou_receiver_phone").trim();
				String sRPReg = "09\\d{8}";
				if (sou_receiver_phone == null || sou_receiver_phone.trim().length() == 0) {
					errorMsgs.add("收穫人電話請勿空白");
				} else if (!sou_receiver_phone.trim().matches(sRPReg)) {
					errorMsgs.add("收穫人電話: 只能是數字 ,開頭為09 且長度必需是10喔");
				}
				
				
				Integer sou_shipment_fee = null;
				try {
					sou_shipment_fee =new Integer(req.getParameter("sou_shipment_fee").trim());
				} catch (NumberFormatException e) {
					sou_shipment_fee = new Integer(0);
					errorMsgs.add("請輸入數字.");
				}
				Integer sou_order_sum_price = null;
				try {
					sou_order_sum_price =new Integer(req.getParameter("sou_order_sum_price").trim());
				} catch (NumberFormatException e) {
					sou_order_sum_price = new Integer(0);
					errorMsgs.add("請輸入數字.");
				}
				String sou_order_remarks = req.getParameter("sou_order_remarks").trim();
				Integer sou_shipping_method = null;
				try {
					sou_shipping_method =new Integer(req.getParameter("sou_shipping_method").trim());
				} catch (NumberFormatException e) {
					sou_shipping_method = new Integer(0);
					errorMsgs.add("請輸入數字.");
				}
				Integer sou_order_status = null;
				try {
					sou_order_status =new Integer(req.getParameter("sou_order_status").trim());
				} catch (NumberFormatException e) {
					sou_order_status = new Integer(0);
					errorMsgs.add("請輸入數字.");
				}
				Integer sou_payment_status = null;
				try {
					sou_payment_status =new Integer(req.getParameter("sou_payment_status").trim());
				} catch (NumberFormatException e) {
					sou_payment_status = new Integer(0);
					errorMsgs.add("請輸入數字.");
				}
				Integer sou_shipment_status = null;
				try {
					sou_shipment_status =new Integer(req.getParameter("sou_shipment_status").trim());
				} catch (NumberFormatException e) {
					sou_shipment_status = new Integer(0);
					errorMsgs.add("請輸入數字.");
				}
		

				SouvenirOrderVO soVO = new SouvenirOrderVO();
				soVO.setEmp_id(emp_id);
				soVO.setMem_id(mem_id);
//				soVO.setSou_order_date(sou_order_date);
				soVO.setSou_receiver_name(sou_receiver_name);
				soVO.setSou_receiver_address(sou_receiver_address);
				soVO.setSou_receiver_phone(sou_receiver_phone);
				soVO.setSou_shipment_fee(sou_shipment_fee);
				soVO.setSou_order_sum_price(sou_order_sum_price);
				soVO.setSou_order_remarks(sou_order_remarks);
				soVO.setSou_shipping_method(sou_shipping_method);
				soVO.setSou_order_status(sou_order_status);
				soVO.setSou_payment_status(sou_payment_status);
				soVO.setSou_shipment_status(sou_shipment_status);
				
		
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("soVO", soVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_order/addSouvenirOrder.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				SouvenirOrderService soSvc = new SouvenirOrderService();
				soVO = soSvc.addSouvenirOrder(emp_id, mem_id,
						sou_receiver_name, sou_receiver_address, sou_receiver_phone, sou_shipment_fee,
						 sou_order_sum_price, sou_order_remarks, sou_shipping_method,
						sou_order_status, sou_payment_status, sou_shipment_status);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/souvenir_order/listAllSouvenirOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_order/addSouvenirOrder.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllSouvenir_Order.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String sou_order_id = req.getParameter("sou_order_id");

				/*************************** 2.開始刪除資料 ***************************************/
				SouvenirOrderService soSvc = new SouvenirOrderService();
				soSvc.deleteSouvenirOrder(sou_order_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/souvenir_order/listAllSouvenirOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView;
				failureView = req.getRequestDispatcher("/back-end/souvenir_order/listAllSouvenirOrder.jsp");
				failureView.forward(req, res);
			}
		}
		if ("listSouvenirOrders_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				Map<String, String[]> map = req.getParameterMap();
				
				/***************************2.開始複合查詢***************************************/
				SouvenirOrderService soSvc = new SouvenirOrderService();
				List<SouvenirOrderVO> list  = soSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listSouvenirOrders_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/souvenir_order/listSouvenirOrders_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/souvenir_order/select_page.jsp");
				failureView.forward(req, res);
			}
		}	
	}
}
