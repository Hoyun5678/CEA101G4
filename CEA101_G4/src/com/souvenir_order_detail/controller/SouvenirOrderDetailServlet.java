package com.souvenir_order_detail.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.souvenir_order_detail.model.SouvenirOrderDetailService;
import com.souvenir_order_detail.model.SouvenirOrderDetailVO;

public class SouvenirOrderDetailServlet extends HttpServlet {
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
					errorMsgs.add("請輸入產品訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_order_detail/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_order_detail/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				SouvenirOrderDetailService sodSvc = new SouvenirOrderDetailService();
				SouvenirOrderDetailVO sodVO = sodSvc.getOneSouvenirOrderDetail(sou_order_id);
				if (sodVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_order_detail/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("sodVO", sodVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/souvenir_order_detail/listOneSouvenirOrderDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneSouvenirOrder.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_order_detail/select_page.jsp");
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
				SouvenirOrderDetailService sodSvc = new SouvenirOrderDetailService();
				SouvenirOrderDetailVO sodVO = sodSvc.getOneSouvenirOrderDetail(sou_order_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("sodVO", sodVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/souvenir_order_detail/update_souvenirorderdetail_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_order_detail/listSouvenirOrderDetail.jsp");
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
				String sou_id = req.getParameter("sou_id").trim();		
				Integer sou_order_amount = null;
				try {
					sou_order_amount =new Integer(req.getParameter("sou_order_amount").trim());
				} catch (NumberFormatException e) {
					sou_order_amount = new Integer(0);
					errorMsgs.add("請輸入數字.");
				}
				Integer sou_price = null;
				try {
					sou_price =new Integer(req.getParameter("sou_price").trim());
				} catch (NumberFormatException e) {
					sou_price = new Integer(0);
					errorMsgs.add("請輸入數字.");
				}
				String sou_order_id = req.getParameter("sou_order_id").trim();		
				

				SouvenirOrderDetailVO sodVO = new SouvenirOrderDetailVO();
				sodVO.setSou_id(sou_id);
				sodVO.setSou_order_amount(sou_order_amount);
				sodVO.setSou_price(sou_price);
				sodVO.setSou_order_id(sou_order_id);
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("sodVO", sodVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_order_detail/update_souvenirorderdetail_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				SouvenirOrderDetailService sodSvc = new SouvenirOrderDetailService();
				sodVO = sodSvc.updateSouvenirOrderDetail(sou_id, sou_order_amount, sou_price, sou_order_id);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("sodVO", sodVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/souvenir_order_detail/listOneSouvenirOrderDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_order_detail/update_souvenirorderdetail_input.jsp");
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
				String sou_order_id = req.getParameter("sou_order_id").trim();
				if (sou_order_id == null || sou_order_id.trim().length() == 0) {
					errorMsgs.add("訂單編號請勿空白");
				} 
				String sou_id = req.getParameter("sou_id").trim();
				if (sou_id == null || sou_id.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				} 
				if (sou_id == null || sou_id.trim().length() == 0) {
					errorMsgs.add("商品編號請勿空白");
				}
				Integer sou_order_amount = null;
				try {
					sou_order_amount =new Integer(req.getParameter("sou_order_amount").trim());
				} catch (NumberFormatException e) {
					sou_order_amount = new Integer(0);
					errorMsgs.add("請輸入數字.");
				}
				Integer sou_price = null;
				try {
					sou_price =new Integer(req.getParameter("sou_price").trim());
				} catch (NumberFormatException e) {
					sou_price = new Integer(0);
					errorMsgs.add("請輸入數字.");
				}
				
				SouvenirOrderDetailVO sodVO = new SouvenirOrderDetailVO();
				sodVO.setSou_order_id(sou_order_id);
				sodVO.setSou_id(sou_id);
				sodVO.setSou_order_amount(sou_order_amount);
				sodVO.setSou_price(sou_price);
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("sodVO", sodVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_order_detail/addSouvenirOrderDetail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*************************** 2.開始新增資料 ***************************************/
				SouvenirOrderDetailService sodSvc = new SouvenirOrderDetailService();
				sodVO = sodSvc.addSouvenirOrderDetail(sou_order_id, sou_id, sou_order_amount, sou_price);
				
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/souvenir_order_detail/listAllSouvenirOrderDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_order_detail/addSouvenirOrderDetail.jsp");
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
				String sou_id = req.getParameter("sou_id");
				
				/*************************** 2.開始刪除資料 ***************************************/
				SouvenirOrderDetailService sodSvc = new SouvenirOrderDetailService();
				sodSvc.deleteSouvenirOrderDetail(sou_order_id, sou_id);
				
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/souvenir_order_detail/listAllSouvenirOrderDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView;
				failureView = req.getRequestDispatcher("/back-end/souvenir_order_detail/listAllSouvenirOrderDetail.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
				
				
				
				
				
				

				
