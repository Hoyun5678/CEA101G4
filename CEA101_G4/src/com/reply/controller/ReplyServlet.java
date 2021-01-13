package com.reply.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import com.reply.model.*;

public class ReplyServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forEmp = req.getParameter("forEmp");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("replyId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入評論編號");
				}
//				 Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-mem-end/reply/front_select_reply.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String replyId = null;
				try {
					replyId = new String(str);
				} catch (Exception e) {
					errorMsgs.add("評論編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-mem-end/reply/front_select_reply.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ReplyService replySvc = new ReplyService();
				ReplyVO replyVO = replySvc.getOneReply(replyId);
				if (replyVO == null) {
					errorMsgs.add("請輸入有效評論編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-mem-end/reply/front_select_reply.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("replyVO", replyVO); // 資料庫取出的replyVO物件,存入req
				String url = "/front-mem-end/reply/front_listOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 front_listOne.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/reply/front_select_reply.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_By_ActId".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("actId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動編號");
				}
//				 Send the use back to the form, if there were errors				
				String actId = null;
				try {
					actId = new String(str);
				} catch (Exception e) {
					errorMsgs.add("活動編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-mem-end/reply/front_select_reply.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ReplyService replySvc = new ReplyService();
				List<ReplyVO> listReplyVO = replySvc.getReplyByActId(actId);
				if (listReplyVO == null) {
					errorMsgs.add("請輸入有效活動編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-mem-end/reply/front_select_reply.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("listReplyVO", listReplyVO); // 資料庫取出的replyVO物件,存入req
				req.setAttribute("act_id", actId);
				String url = "/front-mem-end/reply/listOneActP.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 front_listOne.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/reply/front_select_reply.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_By_MemId".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("memId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動編號");
				}
//				 Send the use back to the form, if there were errors				
				String memId = null;
				try {
					memId = new String(str);
				} catch (Exception e) {
					errorMsgs.add("活動編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-mem-end/reply/front_select_reply.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ReplyService replySvc = new ReplyService();
				List<ReplyVO> listReplyVO2 = replySvc.getReplyByActId(memId);
				if (listReplyVO2 == null) {
					errorMsgs.add("請輸入有效活動編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-mem-end/reply/front_select_reply.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				System.out.println(memId);
				req.setAttribute("listReplyVO2", listReplyVO2); // 資料庫取出的replyVO物件,存入req
				req.setAttribute("memId", memId);
				String url = "/front-mem-end/reply/listOneActP.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 front_listOne.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/reply/front_select_reply.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllReply.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String replyId = req.getParameter("replyId").trim();
				if (replyId == null || replyId.trim().length() == 0) {
					errorMsgs.add("評論編號: 請勿空白");
				}
				/*************************** 2.開始查詢資料 ****************************************/
				ReplyService replySvc = new ReplyService();
				ReplyVO replyVO = replySvc.getOneReply(replyId);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("replyVO", replyVO); // 資料庫取出的replyVO物件,存入req
				if ("forEmp".equals(forEmp)) {
					String url = "/back-end/reply/back_update_reply.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 front_update_replyreport.jsp
					successView.forward(req, res);
				} else {
					String url = "/front-mem-end/reply/front_update_reply.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_reply_input.jsp
					successView.forward(req, res);
				}

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/reply/front_AllReply.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_reply_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String replyId = req.getParameter("replyId");

				String actId = req.getParameter("actId");
				String actIdReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{5}$";
				if (actId == null || actId.trim().length() == 0) {
					errorMsgs.add("活動編號: 請勿空白");
				} else if (!actId.trim().matches(actIdReg)) {
					// 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("活動編號: 只能是大寫英文字母+數字 , 且長度必需是5");
				}

				String memId = req.getParameter("memId");
				String memIdReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{6}$";
				if (memId == null || memId.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} else if (!memId.trim().matches(memIdReg)) {
					// 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員編號: 只能是大寫英文字母+數字 , 且長度必需是5");
				}

				java.sql.Timestamp replyTime = java.sql.Timestamp.valueOf(req.getParameter("replyTime"));
				try {
					replyTime = java.sql.Timestamp.valueOf(req.getParameter("replyTime"));
				} catch (IllegalArgumentException e) {
					replyTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String replyContent = req.getParameter("replyContent");
//				if (replyContent == null || replyContent.trim().length() == 0) {
//					errorMsgs.add("空白");
//				}

				Integer replyVisible = null;
				try {
					replyVisible = new Integer(req.getParameter("replyVisible"));
					if (replyVisible != 0 && replyVisible != 1) {
						errorMsgs.add("請勾選評論狀態");
					}
				} catch (NumberFormatException e) {
					errorMsgs.add("請勾選評論狀態");

				}

				ReplyVO replyVO = new ReplyVO();
				replyVO.setReplyId(replyId);
				replyVO.setActId(actId);
				replyVO.setMemId(memId);
				replyVO.setReplyTime(replyTime);
				replyVO.setReplyVisible(replyVisible);
				replyVO.setReplyContent(replyContent);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("replyVO", replyVO); // 含有輸入格式錯誤的replyVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-mem-end/reply/front_update_reply.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				ReplyService replySvc = new ReplyService();
				replyVO = replySvc.updateReply(replyId, actId, memId, replyContent, replyTime, replyVisible);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("replyVO", replyVO); // 資料庫update成功後,正確的的replyVO物件,存入req
				String url = "/front-mem-end/reply/front_listOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交front_listOne.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/reply/front_update_reply.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addReply.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String actId = req.getParameter("actId");
				String actIdReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{6}$";
				if (actId == null || actId.trim().length() == 0) {
					errorMsgs.add("活動編號: 請勿空白");
				} else if (!actId.trim().matches(actIdReg)) {
					// 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("活動編號: 只能是大寫英文字母+數字 , 且長度必需是5");
				}

				String memId = req.getParameter("memId");
				String memIdReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{6}$";
				if (memId == null || memId.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} else if (!memId.trim().matches(memIdReg)) {
					// 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員編號: 只能是大寫英文字母+數字 , 且長度必需是5");
				}

//				java.sql.Timestamp replyTime = null;
//				try {
//					replyTime = java.sql.Timestamp.valueOf(req.getParameter("replyTime").trim());
//				} catch (IllegalArgumentException e) {
//					replyTime=new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}

				String replyContent = req.getParameter("replyContent");
//				if (replyContent == null || replyContent.trim().length() == 0) {
//					errorMsgs.add("空白");
//				}

				Integer replyVisible = null;
				try {
					replyVisible = new Integer(req.getParameter("replyVisible"));
					if (replyVisible != 0 && replyVisible != 1) {
						errorMsgs.add("請填選活動評論狀態");
					}
				} catch (NumberFormatException e) {
					errorMsgs.add("請填選活動評論狀態");

				}

				ReplyVO replyVO = new ReplyVO();
				replyVO.setActId(actId);
				replyVO.setMemId(memId);
				replyVO.setReplyContent(replyContent);
//				replyVO.setReplyTime(replyTime);
				replyVO.setReplyVisible(replyVisible);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("replyVO", replyVO); // 含有輸入格式錯誤的replyVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/reply/front_addReply.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ReplyService replySvc = new ReplyService();
				replyVO = replySvc.addReply(actId, memId, replyContent, replyVisible);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-mem-end/reply/front_AllReply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllReply.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/reply/front_addReply.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllReply.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String replyId = req.getParameter("replyId");

				/*************************** 2.開始刪除資料 ***************************************/
				ReplyService replySvc = new ReplyService();
				replySvc.deleteReply(replyId);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front-mem-end/reply/front_AllReply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/reply/front_AllReply.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
