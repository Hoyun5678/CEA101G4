package com.emp.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;


public class EmpServlet extends HttpServlet {
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
				String str = req.getParameter("emp_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String emp_id = null;
				try {
					emp_id = str;
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(emp_id);
				if (empVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
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
				String emp_id = req.getParameter("emp_id");

				/*************************** 2.開始查詢資料 ****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(emp_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/emp/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String emp_id = req.getParameter("emp_id").trim();
				
				String emp_account = req.getParameter("emp_account").trim();
				String eaccountReg = "^[(a-zA-Z0-9)]{2,100}$";
				if (emp_account == null || emp_account.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				} else if (!emp_account.trim().matches(eaccountReg)) {
					errorMsgs.add("員工帳號: 只能是英文字母、數字和 , 且長度必需在2到10之間");
				}

				String emp_pwd = req.getParameter("emp_pwd").trim();
				String epwdReg = "^[(a-zA-Z0-9)]{2,100}$";
				if (emp_pwd == null || emp_pwd.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				} else if (!emp_pwd.trim().matches(epwdReg)) {
					errorMsgs.add("員工密碼錯誤");
				}
//				String emp_pwd = null;
//				String Letter = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//				for (int i = 0; i < 8; i++) {
//					emp_pwd+= Letter.toCharArray()[(int)(Math.random()*Letter.length())];
//				}
				
				String emp_name = req.getParameter("emp_name");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,100}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if (!emp_name.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				Integer emp_acc_status = null;
				try {
					emp_acc_status =new Integer(req.getParameter("emp_acc_status").trim());
					if (emp_acc_status != 0 && emp_acc_status != 1) {errorMsgs.add("請輸入0 or 1");}
				} catch (NumberFormatException e) {
					emp_acc_status = new Integer(0);
					errorMsgs.add("請輸入0 or 1");
				}
				Integer emp_gender = null;
				try {
					emp_gender =Integer.parseInt(req.getParameter("emp_gender").trim());
					if (emp_gender != 0 && emp_gender != 1) {errorMsgs.add("請輸入0 or 1");}
				} catch (NullPointerException e) {
					emp_gender = new Integer(0);
					errorMsgs.add("請輸入0 or 1.");
				}
				
				


				EmpVO empVO = new EmpVO();
				empVO.setEmp_id(emp_id);
				empVO.setEmp_account(emp_account);
				empVO.setEmp_pwd(emp_pwd);
				empVO.setEmp_name(emp_name);
				empVO.setEmp_acc_status(emp_acc_status);
				empVO.setEmp_gender(emp_gender);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/update_emp_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				EmpService empSvc = new EmpService();
				empVO = empSvc.updateEmp(emp_id, emp_account,emp_pwd,emp_name,emp_acc_status,emp_gender);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/update_emp_input.jsp");
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
				String emp_account = req.getParameter("emp_account").trim();
				String eaccountReg = "^[(a-zA-Z0-9)]{2,100}$";
				if (emp_account == null || emp_account.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				} else if (!emp_account.trim().matches(eaccountReg)) {
					errorMsgs.add("員工帳號: 只能是英文字母、數字和 , 且長度必需在2到10之間");
				}

//				String emp_pwd = req.getParameter("emp_pwd").trim();
//				String epwdReg = "^[(a-zA-Z0-9)]{2,100}$";
//				if (emp_pwd == null || emp_pwd.trim().length() == 0) {
//					errorMsgs.add("密碼請勿空白");
//				} else if (!emp_pwd.trim().matches(epwdReg)) {
//					errorMsgs.add("員工密碼錯誤");
//				}
				String emp_pwd = "";
				String Letter = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
				for (int i = 0; i < 8; i++) {
					emp_pwd+= Letter.toCharArray()[(int)(Math.random()*Letter.length())];
				}
				
				String emp_name = req.getParameter("emp_name");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,100}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if (!emp_name.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				Integer emp_acc_status = null;	
				
				try {
					emp_acc_status =new Integer(req.getParameter("emp_acc_status").trim());
					if (emp_acc_status != 0 && emp_acc_status != 1) {errorMsgs.add("請輸入0 or 1");}
				}catch (NumberFormatException e) {
					emp_acc_status = new Integer(0);
					errorMsgs.add("請輸入0 or 1.");
				}
				Integer emp_gender = null;
				try {
					emp_gender =Integer.parseInt(req.getParameter("emp_gender").trim());
					if (emp_gender != 0 && emp_gender != 1) {errorMsgs.add("請輸入0 or 1");}
				} catch (NullPointerException e) {
					emp_gender = new Integer(0);
					errorMsgs.add("請輸入0 or 1.");
				}
				
		

				EmpVO empVO = new EmpVO();
				empVO.setEmp_account(emp_account);
				empVO.setEmp_pwd(emp_pwd);
				empVO.setEmp_name(emp_name);
				empVO.setEmp_acc_status(emp_acc_status);
				empVO.setEmp_gender(emp_gender);
		
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				EmpService empSvc = new EmpService();
				empVO = empSvc.addEmp(emp_account,emp_pwd,emp_name,emp_acc_status,emp_gender);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/addEmp.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String emp_id = req.getParameter("emp_id");

				/*************************** 2.開始刪除資料 ***************************************/
				EmpService empSvc = new EmpService();
				empSvc.deleteEmp(emp_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView;
				failureView = req.getRequestDispatcher("/back-end/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		if ("emp_login".equals(action)) {
			res.setContentType("text/html; charset=UTF-8");
			HttpSession session = req.getSession();
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 ****************************************/
			String emp_account = req.getParameter("emp_account").trim();
			String emp_pwd = req.getParameter("emp_pwd").trim();
			if (emp_account.isEmpty() || emp_pwd.isEmpty()) {
				errorMsgs.add("請輸入帳號密碼");
			}

			/*************************** 2.開始查詢資料 ****************************************/
			EmpService empSvc = new EmpService();
			EmpVO empVO = empSvc.getOneEmpByAccount(emp_account);
			if (empVO == null) {
				errorMsgs.add("不存在的帳號");
			} else if (empVO.getEmp_account().equals(emp_account) && !(empVO.getEmp_pwd().equals(emp_pwd))) {
				errorMsgs.add("密碼錯誤唷");
			} else if (empVO.getEmp_account().equals(emp_account) && (empVO.getEmp_pwd().equals(emp_pwd))) {
				session.setAttribute("empVO", empVO);
				String url = "/back-end/back-index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/empLogin.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

		}
//		員工登出===============================================================
		if ("emp_log_out".equals(action)) {
			HttpSession session = req.getSession();
			session.invalidate();
//		重導至首頁=============================================================
			String url = "/back-end/back-index.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}
	}
}

