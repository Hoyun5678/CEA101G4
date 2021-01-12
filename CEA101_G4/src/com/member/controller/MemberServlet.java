package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.member.model.MemberService;
import com.member.model.MemberVO;

import mail.MailCertification;
import mail.MailService;

public class MemberServlet extends HttpServlet {
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
				String str = req.getParameter("mem_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String mem_id = null;
				try {
					mem_id = str;
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				MemberService memSvc = new MemberService();
				MemberVO memVO = memSvc.getOneMem(mem_id);
				if (memVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("memVO", memVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/member/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/select_page.jsp");
				failureView.forward(req, res);
			}
		}
//		修改密碼確認 for ajax==============================================================================
				if ("get_pwd_check".equals(action)) {
					res.setContentType("text/html; charset=UTF-8");
					String mem_pwd_old = req.getParameter("mem_pwd_old").trim();
					
					String mem_account=req.getParameter("mem_account").trim();
					MemberService memSvc = new MemberService();
					PrintWriter out = res.getWriter();
					MemberVO memVO=memSvc.getOneMemByAccount(mem_account);
					System.out.println(memVO.getMem_pwd()+"\n"+mem_pwd_old);
					JSONObject jobj=new JSONObject();
					try {
						if(memVO.getMem_pwd().equals(mem_pwd_old)){
							jobj.put("valid", true);
						}else {
							jobj.put("valid", false);
						}
						
					} catch (JSONException e) {
						e.printStackTrace();
					}
					out.print(jobj);
					out.flush();
					out.close();
				}

//		註冊的帳號確認 for ajax==============================================================================
		if ("get_account_check".equals(action)) {
			res.setContentType("text/html; charset=UTF-8");
			String mem_account = req.getParameter("mem_account").trim();
			MemberService memSvc = new MemberService();
			List<MemberVO> list = memSvc.getAll();
			PrintWriter out = res.getWriter();
			JSONObject jobj = new JSONObject();
			String check_result = "";
			for (MemberVO memVO : list) {
				if (memVO.getMem_account().equals(mem_account)) {
					check_result = "false";
				}
			}
			
			
			try {
				if (check_result.equals("false")) {
					jobj.put("valid", false);
				} else {
					jobj.put("valid", true);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.print(jobj);
			out.flush();
			out.close();

		}
		
		
//		註冊mail確認 for ajax==============================================================================
		if ("get_mail_check".equals(action)) {
			res.setContentType("text/html; charset=UTF-8");
			String mem_mail = req.getParameter("mem_mail").trim();
			MemberService memSvc = new MemberService();
			List<MemberVO> list = memSvc.getAll();
			PrintWriter out = res.getWriter();
			JSONObject jobj = new JSONObject();
			String check_result = "";
			for (MemberVO memVO : list) {
				if (memVO.getMem_mail().equals(mem_mail)) {
					check_result = "false";
				}
			}
			
			
			try {
				if (check_result.equals("false")) {
					jobj.put("valid", false);
				} else {
					jobj.put("valid", true);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.print(jobj);
			out.flush();
			out.close();

		}

//		會員登入撈帳號===========================================================================
		if ("mem_login".equals(action)) {
			res.setContentType("text/html; charset=UTF-8");
			HttpSession session = req.getSession();
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 ****************************************/
			String mem_account = req.getParameter("mem_account").trim();
			String mem_pwd = req.getParameter("mem_pwd").trim();
			if (mem_account.isEmpty() || mem_pwd.isEmpty()) {
				errorMsgs.add("請輸入帳號密碼");
			}

			/*************************** 2.開始查詢資料 ****************************************/
			MemberService memSvc = new MemberService();
			MemberVO memVO = memSvc.getOneMemByAccount(mem_account);
			if (memVO == null) {
				errorMsgs.add("不存在的帳號");
			} else if (memVO.getMem_account().equals(mem_account) && !(memVO.getMem_pwd().equals(mem_pwd))) {
				errorMsgs.add("密碼錯誤唷");
			} else if (memVO.getMem_account().equals(mem_account) && (memVO.getMem_pwd().equals(mem_pwd))) {
				session.setAttribute("memVO", memVO);
				String url = "/front-mem-end/front-index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/mem/memLogin.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

		}
//		會員登出===============================================================
		if ("log_out".equals(action)) {
			HttpSession session = req.getSession();
			session.invalidate();
//		重導至首頁=============================================================
			String url = "/front-mem-end/front-index.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String mem_id = req.getParameter("mem_id");

				/*************************** 2.開始查詢資料 ****************************************/
				MemberService memSvc = new MemberService();
				MemberVO memVO = memSvc.getOneMem(mem_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("memVO", memVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-mem-end/mem/update_mem_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem/listAllMem.jsp");
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
				HttpSession session = req.getSession();
				MemberVO memVO = (MemberVO) session.getAttribute("memVO");
				String mem_id=memVO.getMem_id();
				
//				String mem_account = req.getParameter("mem_account").trim();
//				String maccountReg = "^[(a-zA-Z0-9)]{2,100}$";
//				if (mem_account == null || mem_account.trim().length() == 0) {
//					errorMsgs.add("帳號請勿空白");
//				} else if (!mem_account.trim().matches(maccountReg)) {
//					errorMsgs.add("會員帳號: 只能是英文字母、數字和 , 且長度必需在2到10之間");
//				}

//				String mem_pwd = req.getParameter("mem_pwd").trim();
//				String mpwdReg = "^[(a-zA-Z0-9)]{2,100}$";
//				if (mem_pwd == null || mem_pwd.trim().length() == 0) {
//					errorMsgs.add("帳號請勿空白");
//				} else if (!mem_pwd.trim().matches(mpwdReg)) {
//					errorMsgs.add("會員帳號: 只能是英文字母、數字和 , 且長度必需在2到10之間");
//				}

				String mem_name = req.getParameter("mem_name");
				String mnameReg = "^[(\u4e00-\u9fa5)]{2,100}$";
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if (!mem_name.trim().matches(mnameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中文, 且長度必需在2到10之間");
				}

//				java.sql.Date mem_birth = null;
//				try {
//					mem_birth = java.sql.Date.valueOf(req.getParameter("mem_birth").trim());
//				} catch (IllegalArgumentException e) {
//					mem_birth = new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請填入生日");
//				}

				String mem_tel = null;
				try {
					mem_tel = req.getParameter("mem_tel").trim();
					if (!mem_tel.matches("^09[]0-9]{8}$")) {
						errorMsgs.add("手機輸入格式不正確");
					}
				} catch (NullPointerException e) {
					mem_tel = "";
					errorMsgs.add("手機號碼請勿空白");
				}
				String mem_address = null;
				try {
					mem_address = req.getParameter("mem_address").trim();
					if(mem_address.isEmpty()) {
						errorMsgs.add("請輸入地址");
					}
				} catch (NullPointerException e) {
					mem_address = "";
				}

//				String mem_mail = null;
//				try {
//					mem_mail = req.getParameter("mem_mail").trim();
//				} catch (NullPointerException e) {
//					mem_mail = "";
//					errorMsgs.add("請輸入信箱.");
//				}
//				String mem_id_number = null;
//				try {
//					mem_id_number = req.getParameter("mem_id_number").trim();
//				} catch (NullPointerException e) {
//					mem_id_number = "";
//					errorMsgs.add("請輸入身分證字號.");
//				}
//				Integer mem_acc_status = null;
//				try {
//					mem_acc_status = new Integer(req.getParameter("mem_acc_status").trim());
//				} catch (NumberFormatException e) {
//					mem_acc_status = new Integer(0);
//					errorMsgs.add("請輸入信箱.");
//				}
//				Integer mem_gender = null;
//				try {
//					mem_gender = Integer.parseInt(req.getParameter("mem_gender").trim());
//				} catch (NullPointerException e) {
//					mem_gender = new Integer(0);
//					errorMsgs.add("請輸入信箱.");
//				}

//				java.sql.Timestamp mem_jointime = null;
//
//				try {
//					String mj = req.getParameter("mem_jointime").trim();
//					mem_jointime = java.sql.Timestamp.valueOf(mj);
//				} catch (IllegalArgumentException e) {
//					mem_jointime = new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("請加入時間");
//				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/mem/listOneMem.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				MemberService memSvc=new MemberService();
				
				memVO = memSvc.updateMemNormal(mem_id,mem_name, mem_tel, mem_address);
				
//				memVO = memSvc.updateMem(mem_id, mem_account, mem_pwd, mem_name, mem_birth, mem_tel, mem_address,
//						mem_mail, mem_id_number, mem_acc_status, mem_gender, mem_jointime);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				session.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-mem-end/mem/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/mem/listOneMem.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自regis.jsp的請求 會員
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text; charset=utf-8");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			PrintWriter out = res.getWriter();
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String mem_account = req.getParameter("mem_account").trim();
				// 帳號限制不能數字開頭, 由數字英文跟底線組成, 總長度8~20
				String maccountReg = "^[a-zA-Z][a-zA-Z0-9_]{7,20}$";
				if (mem_account == null || mem_account.trim().length() == 0) {
					errorMsgs.add("會員帳號:請勿空白");
				} else if (!mem_account.trim().matches(maccountReg)) {
					errorMsgs.add("您輸入的會員帳號為 = " + mem_account);
					errorMsgs.add("帳號限制不能數字開頭, 由數字英文跟底線組成, 總長度8~20");
				}

				String mem_pwd = req.getParameter("mem_pwd").trim();
				String mpwdReg = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{8,}$";
				if (mem_pwd == null || mem_pwd.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				} else if (!mem_pwd.trim().matches(mpwdReg)) {
					errorMsgs.add("會員密碼:必須包含大寫 小寫 字母 與 數字 ");
				} else if (mem_pwd.length() < 8) {
					errorMsgs.add("密碼長度不夠");
				}

				String mem_pwd_re = req.getParameter("mem_pwd_re").trim();
				if (mem_pwd_re == null || mem_pwd_re.length() == 0) {
					errorMsgs.add("確認密碼欄位不得為空");
				} else if (!mem_pwd_re.equals(mem_pwd)) {
					errorMsgs.add("確認密碼與密碼不一致, 請重新輸入");
				}

				String mem_name = req.getParameter("mem_name");
				String mnameReg = "^[(\u4e00-\u9fa5)]{2,100}$";
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if (!mem_name.trim().matches(mnameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中文且長度必需在2到10之間");
				}

				java.sql.Date mem_birth = null;
				try {
					mem_birth = java.sql.Date.valueOf(req.getParameter("mem_birth").trim());
				} catch (IllegalArgumentException e) {
					mem_birth = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請填入生日");
				}

				String mem_tel = null;
				try {
					mem_tel = req.getParameter("mem_tel").trim();
					if (!mem_tel.matches("^09[]0-9]{8}$")) {
						errorMsgs.add("手機輸入格式不正確");
					}
				} catch (NullPointerException e) {
					mem_tel = "";
					errorMsgs.add("手機號碼請勿空白");
				}
				String mem_address = null;
				try {
					mem_address = req.getParameter("mem_address").trim();
				} catch (NullPointerException e) {
					mem_address = "";
					errorMsgs.add("請打入地址.");
				}

				String mem_mail = null;
				try {
					mem_mail = req.getParameter("mem_mail").trim();
				} catch (NullPointerException e) {
					mem_mail = "";
					errorMsgs.add("請輸入信箱.");
				}
				String mem_id_number = null;
				try {
					mem_id_number = req.getParameter("mem_id_number").trim();
				} catch (NullPointerException e) {
					mem_id_number = "";
					errorMsgs.add("請輸入身分證字號.");
				}
				Integer mem_acc_status = null;
				try {
					mem_acc_status = new Integer(req.getParameter("mem_acc_status").trim());
				} catch (NumberFormatException e) {
					mem_acc_status = new Integer(0);
				}
				Integer mem_gender = null;
				try {
					mem_gender = Integer.parseInt(req.getParameter("mem_gender").trim());
				} catch (NullPointerException e) {
					mem_gender = new Integer(0);
				}

				java.sql.Timestamp mem_jointime = new Timestamp(System.currentTimeMillis());

				MemberVO memVO = new MemberVO();
				memVO.setMem_account(mem_account);
				memVO.setMem_pwd(mem_pwd);
				memVO.setMem_name(mem_name);
				memVO.setMem_birth(mem_birth);
				memVO.setMem_tel(mem_tel);
				memVO.setMem_address(mem_address);
				memVO.setMem_mail(mem_mail);
				memVO.setMem_id_number(mem_id_number);
				memVO.setMem_acc_status(mem_acc_status);
				memVO.setMem_gender(mem_gender);
				memVO.setMem_jointime(mem_jointime);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/mem/regis.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				MemberService memSvc = new MemberService();
				memVO = memSvc.addMem(mem_account, mem_pwd, mem_name, mem_birth, mem_tel, mem_address, mem_mail,
						mem_id_number, mem_acc_status, mem_gender);
				
//				==============================送出email認證信===============================================
				MailService send_mail=new MailService(); //大吳的寄信JAVA
				MailCertification cer = new MailCertification();
				String mem_id=memSvc.getOneMemByAccount(mem_account).getMem_id();
//				String mailMsg = "宿購易 SuperGoing驗證信：請點擊網址。 https://34.80.93.191"+req.getContextPath()+"/member/member.do?action=certification&rand_num="
//						+ cer.insertCode(mem_id) + "&mem_id=" + mem_id; //雲端機用
				String mailMsg = "宿購易 SuperGoing驗證信：請點擊網址。 http://localhost:8081"+req.getContextPath()+"/member/member.do?action=certification&rand_num="
						+ cer.insertCode(mem_id) + "&mem_id=" + mem_id;
				send_mail.sendMail(mem_mail, "宿購易你最愛的旅遊平台", mailMsg);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//				String url = "/front-mem-end/front-index.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				req.setAttribute("success", "success");
//				successView.forward(req, res);
//				有表單的跳轉請用 sendRedirect 否則刷F5會有重複提交的動作
				
				String url = "/front-mem-end/front-index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				
//				String url="http://localhost:8081/CEA101G4/front-mem-end/front-index.jsp";
//				res.sendRedirect(url);
//				out.print("success");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/mem/regis.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("mem_forget_pwd".equals(action)) {
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text; charset=utf-8");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
//			準備接收參數=============================================================
			String mem_account = req.getParameter("mem_account").trim();
			// 帳號限制不能數字開頭, 由數字英文跟底線組成, 總長度8~20
			String maccountReg = "^[a-zA-Z][a-zA-Z0-9_]{7,20}$";
			if (mem_account == null || mem_account.trim().length() == 0) {
				errorMsgs.add("會員帳號:請勿空白");
			} else if (!mem_account.trim().matches(maccountReg)) {
				errorMsgs.add("帳號限制不能數字開頭, 由數字英文跟底線組成, 總長度8~20");
			}
			String mem_mail = null;
			try {
				mem_mail = req.getParameter("mem_mail").trim();
			} catch (NullPointerException e) {
				mem_mail = "";
				errorMsgs.add("請輸入信箱.");
			}
			
			java.sql.Date mem_birth = null;
			try {
				mem_birth = java.sql.Date.valueOf(req.getParameter("mem_birth").trim());
			} catch (IllegalArgumentException e) {
				mem_birth = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請填入生日");
			}
//			取出帳號並比對資料=========================================================================
			MemberService memSvc=new MemberService();
			MemberVO memVO=memSvc.getOneMemByAccount(mem_account);
			if(memVO ==null) {
				errorMsgs.add("不存在的帳號");
			}else if(memVO!=null && !memVO.getMem_mail().equals(mem_mail)) {
				errorMsgs.add("與註冊時的信箱不符，請洽客服");
			}else if(memVO!=null && !memVO.getMem_birth().equals(mem_birth)) {
				errorMsgs.add("生日資料錯誤");
			}
			
			if(!errorMsgs.isEmpty()) {
				
				RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/mem/memLogin.jsp");
				failureView.forward(req, res);
				return;
			}
//			確認資料無誤更新資料庫 亂數密碼 =========================================================
			String new_rand_pwd=MailCertification.getAuthCode();
			memSvc.upDateMemPwd(mem_account, new_rand_pwd);
//			寄出email 通知亂數的密碼 ===========================================================
			
			MailService send_mail=new MailService(); //大吳的寄信JAVA
			String mem_name=memVO.getMem_name();
			String mailMsg = "敬愛的"+mem_name+"會員您好:已將您的密碼更改為"+new_rand_pwd+
					"  請盡速登入更改您的密碼，宿購易 您最愛的旅遊平台 關心您";
			send_mail.sendMail(mem_mail, "宿購易 "+mem_name+"會員您好已更改您的密碼", mailMsg);
			return;

		}
		
		if("certification".equals(action)) { //點擊信箱認證信 更改會員狀態
			String rand_num = req.getParameter("rand_num");
			String mem_id = req.getParameter("mem_id");
			MailCertification cer = new MailCertification();
			if (cer.verifyCode(mem_id, rand_num)) {
				MemberService memSvc = new MemberService();
				memSvc.upDateStatus(mem_id, 1);
				
			}
			String url = "/front-mem-end/front-index.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		
		if("change_mem_pwd".equals(action)) { //點擊信箱認證信 更改會員狀態
			String mem_pwd = req.getParameter("mem_pwd");
			String mem_pwd_re = req.getParameter("mem_pwd_re");
			String mem_account=req.getParameter("mem_account");
			if(!mem_pwd.equals(mem_pwd_re))
				res.sendRedirect(req.getContextPath() +"/front-mem-end/mem/listOneMem.jsp");
			MemberService memSvc=new MemberService();
			memSvc.upDateMemPwd(mem_account, mem_pwd);
			res.sendRedirect(req.getContextPath() + "/front-mem-end/front-index.jsp");
			return;
		}
		

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String mem_id = req.getParameter("mem_id");

				/*************************** 2.開始刪除資料 ***************************************/
				MemberService memSvc = new MemberService();
				memSvc.deleteMem(mem_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/member/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView;
				failureView = req.getRequestDispatcher("/front-end/member/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
