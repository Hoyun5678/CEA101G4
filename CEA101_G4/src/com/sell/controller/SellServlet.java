package com.sell.controller;
import util.map.getpos.*;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.sell.model.*;

public class SellServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String sellMemId = req.getParameter("sellMemId");
				String sellMemIdReg = "^SELL\\d{3}$";
				if (sellMemId == null || sellMemId.trim().length() == 0) {
					errorMsgs.add("民宿會員編號: 請勿空白");
				} else if(!sellMemId.trim().matches(sellMemIdReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("民宿會員編號格式為: 前綴SELL+數字三碼 ex.SELL999");
	            }

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-sell-end/sell/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				SellService sellSvc = new SellService();
				SellVO sellVO = sellSvc.getOneSell(sellMemId);
				if (sellVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-sell-end/sell/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("sellVO", sellVO); // 資料庫取出的sellVO物件,存入req
				String url = "/front-sell-end/sell/listOneRoom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneRoom.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-sell-end/sell/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String sellMemId = req.getParameter("sellMemId").trim();
								
				/***************************2.開始查詢資料****************************************/
				SellService sellSvc = new SellService();
				SellVO sellVO = sellSvc.getOneSell(sellMemId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("sellVO", sellVO);         // 資料庫取出的sellVO物件,存入req
				String url = "/front-sell-end/sell/update_sell_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_sell_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-sell-end/sell/listAllSell.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_sell_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				// SELL_MEM_ID
				String sellMemId = req.getParameter("sellMemId").trim();
				String sellMemIdReg = "^SELL\\d{3}$";
				if (sellMemId == null || sellMemId.length() == 0) {
					errorMsgs.add("民宿會員編號: 請勿空白");
				} else if(!sellMemId.matches(sellMemIdReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("sellMemId = " + sellMemId);
					errorMsgs.add("民宿會員編號格式為: 前綴SELL+數字三碼 ex.SELL999");
	            }
				
				// SELL_MEM_ACCOUNT不能變更
//				String sellMemAccount = req.getParameter("sellMemAccount").trim();
//				if (sellMemAccount == null || sellMemAccount.length() == 0) {
//					
//				}
				
				// SELL_MEM_PWD
				String sellMemPwd = req.getParameter("sellMemPwd").trim();
				if (sellMemPwd == null || sellMemPwd.length() == 0) {
					errorMsgs.add("請填寫帳號密碼");
				}
				
				String sellMemPwdRe = req.getParameter("sellMemPwdRe").trim();
				if (sellMemPwdRe == null || sellMemPwdRe.length() == 0) {
					errorMsgs.add("確認密碼欄位不得為空");
				} else if (!sellMemPwd.equals(sellMemPwdRe)) {
					errorMsgs.add("確認密碼與密碼不一致, 請重新輸入");
				}
								
				// SELL_MEM_NAME
				String sellMemName = req.getParameter("sellMemName").trim();
				if (sellMemName == null || sellMemName.length() == 0) {
					errorMsgs.add("請輸入民宿會員名稱");
				}
				
				// SELL_MEM_BIRTH 生日不能變更
				
				// SELL_MEM_TEL
				String sellMemTel = req.getParameter("sellMemTel").trim();
				if (sellMemTel == null || sellMemTel.length() == 0) {
					errorMsgs.add("手機號碼請勿空白");
				} else if (!sellMemTel.matches("^0[0-9]{9}$")) {
					errorMsgs.add("手機號碼格式有誤, 請輸入手機號碼共10碼");
				}
				
				// SELL_ROOM_NAME
				String sellRoomName = req.getParameter("sellRoomName").trim();
				if (sellRoomName == null || sellRoomName.length() == 0) {
					errorMsgs.add("民宿名稱請勿空白");
				} 
				
				// SELL_MEM_ADDRESS
				String sellMemAddress = req.getParameter("sellMemAddress").trim();
				if (sellMemAddress == null || sellMemAddress.length() == 0) {
					errorMsgs.add("民宿地址請勿空白");
				} 

				// 地址轉經緯度
				JsonObject location = GetLatLng.transAddToPos(sellMemAddress);
				// SELL_LATITUDE NUMBER(12, 8),
				Double sellLatitude = 0.0;
				Double sellLongitud = 0.0;
				if(location != null) {
					sellLatitude = location.get("lat").getAsDouble();
//					System.out.println("lat = " + sellLatitude);
					sellLongitud = location.get("lng").getAsDouble();
//					System.out.println("lng = " + sellLongitud);
				}
				
				// SELL_MEM_MAIL VARCHAR2(100) NOT NULL,
				String sellMemMail = req.getParameter("sellMemMail").trim();
				if (sellMemMail == null || sellMemMail.length() == 0) {
					errorMsgs.add("E-mail請勿空白");
				} 
				
				// SELL_MEM_ID_NUMBER 不開放變更
				// SELL_GENDER 不開放變更
				
				SellVO sellVO = new SellVO();
//				sellVO.setSellMemAccount(sellMemAccount);
				sellVO.setSellMemPwd(sellMemPwd);
				sellVO.setSellMemName(sellMemName);
//				sellVO.setSellMemBirth(sellMemBirth);
				sellVO.setSellMemTel(sellMemTel);
				sellVO.setSellRoomName(sellRoomName);
				sellVO.setSellMemAddress(sellMemAddress);
				sellVO.setSellLatitude(sellLatitude);
				sellVO.setSellLongitud(sellLongitud);
				sellVO.setSellMemMail(sellMemMail);
//				sellVO.setSellMemIdNumber(sellMemIdNumber);
//				sellVO.setSellGender(sellGender);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("sellVO", sellVO); // 含有輸入格式錯誤的sellVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-sell-end/sell/updateSellInfo.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				SellService sellSvc = new SellService();
//				sellVO = sellSvc.updateSell(sellMemId, sellMemAccount, sellMemPwdRe, sellMemName, sellMemBirth, sellMemTel, sellRoomName, sellMemAddress, sellLatitude, sellLongitud, sellMemMail, sellMemIdNumber, sellAccStatus, sellGender, sellJointime)
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("sellVO", sellVO); // 資料庫update成功後,正確的的sellVO物件,存入req
				String url = "/front-sell-end/sell/listOneSell.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneRoom.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-sell-end/sell/updateSellInfo.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自sellLogin.jsp & addSellMem.jsp的請求  
        	System.out.println("呼叫insert方法");
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				String sellMemId = req.getParameter("sellMemId").trim();
//				String sellMemIdReg = "^SELL\\d{3}$";
//				if (sellMemId == null || sellMemId.length() == 0) {
//					errorMsgs.add("房間編號: 請勿空白");
//				} else if(!sellMemId.matches(sellMemIdReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("sellMemId = " + sellMemId);
//					errorMsgs.add("房間編號格式為: 前綴ROOM+數字三碼 ex.ROOM999");
//	            }
				
				String sellMemAccount = req.getParameter("sellMemAccount").trim();
				// 帳號限制不能數字開頭, 由數字英文跟底線組成, 總長度8~20
				String sellMemAccReg = "^[a-zA-Z][a-zA-Z0-9_]{7,20}$";
				if (sellMemAccount == null || sellMemAccount.length() == 0) {
					errorMsgs.add("民宿會員帳號: 請勿空白");
				} else if(!sellMemAccount.matches(sellMemAccReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("您輸入的會員帳號為 = " + sellMemAccount);
					errorMsgs.add("帳號限制由數字英文跟底線組成, 總長度8~20");
	            }

				String sellMemPwd = req.getParameter("sellMemPwd").trim();
				if (sellMemPwd == null || sellMemPwd.length() == 0) {
					errorMsgs.add("請填寫帳號密碼");
				}

				
				String sellMemPwdRe = req.getParameter("sellMemPwdRe").trim();
				if (sellMemPwdRe == null || sellMemPwdRe.length() == 0) {
					errorMsgs.add("確認密碼欄位不得為空");
				} else if (!sellMemPwd.equals(sellMemPwdRe)) {
					errorMsgs.add("確認密碼與密碼不一致, 請重新輸入");
				}
					
				// SELL_MEM_NAME
				String sellMemName = req.getParameter("sellMemName").trim();
				if (sellMemName == null || sellMemName.length() == 0) {
					errorMsgs.add("請輸入民宿會員名稱");
				}
				
				// SELL_MEM_BIRTH
				java.sql.Date sellMemBirth = null;
				try {
					sellMemBirth = java.sql.Date.valueOf(req.getParameter("sellMemBirth"));
				} catch (IllegalArgumentException e) {
					errorMsgs.add("生日格式錯誤");					
				}
				
				// SELL_MEM_TEL
				String sellMemTel = req.getParameter("sellMemTel").trim();
				if (sellMemTel == null || sellMemTel.length() == 0) {
					errorMsgs.add("手機號碼請勿空白");
				} else if (!sellMemTel.matches("^0[0-9]{9}$")) {
					errorMsgs.add("手機號碼格式有誤, 請輸入手機號碼共10碼");
				}
				
				// SELL_ROOM_NAME VARCHAR2(100) NOT NULL,
				String sellRoomName = req.getParameter("sellRoomName").trim();
				if (sellRoomName == null || sellRoomName.length() == 0) {
					errorMsgs.add("民宿名稱請勿空白");
				} 
				
				// SELL_MEM_ADDRESS VARCHAR2(100) NOT NULL,
				String sellMemAddress = req.getParameter("sellMemAddress").trim();
				if (sellMemAddress == null || sellMemAddress.length() == 0) {
					errorMsgs.add("民宿地址請勿空白");
				} 

				// 地址轉經緯度
				JsonObject location = GetLatLng.transAddToPos(sellMemAddress);
				// SELL_LATITUDE NUMBER(12, 8),
				Double sellLatitude = 0.0;
				Double sellLongitud = 0.0;
				if(location != null) {
					sellLatitude = location.get("lat").getAsDouble();

					// SELL_LONGITUD NUMBER(12, 8),
					sellLongitud = location.get("lng").getAsDouble();

				}

				// SELL_MEM_MAIL VARCHAR2(100) NOT NULL,
				String sellMemMail = req.getParameter("sellMemMail").trim();
				if (sellMemMail == null || sellMemMail.length() == 0) {
					errorMsgs.add("E-mail請勿空白");
				} 
				
				// SELL_MEM_ID_NUMBER VARCHAR2(10) NOT NULL,
				String sellMemIdNumber = req.getParameter("sellMemIdNumber").trim();
				if (sellMemIdNumber == null || sellMemIdNumber.length() == 0) {
					errorMsgs.add("身分證號請勿空白");
				} else if (!sellMemIdNumber.matches("^[a-zA-Z]\\d{9}$")) {
					errorMsgs.add("身分證號格式有誤, 請重新輸入");					
				}
				
//				// SELL_ACC_STATUS DEFAULT 0 NOT NULL, 0: 帳號未啟用(DEFAULT)/ 1: 帳號已啟用/ 2: 帳號失效
//				Integer sellAccStatus = null;
//				try {
//					sellAccStatus = new Integer(req.getParameter("sellAccStatus").trim());
//				} catch (NumberFormatException e) {
//					sellAccStatus = 0;
//					errorMsgs.add("房間容納人數請填入數字");					
//				}
				
				// SELL_GENDER NOT NULL, 0: MALE/ 1: FEMALE
				Integer sellGender = null;
				try {
					sellGender = new Integer(req.getParameter("sellGender").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("性別輸入有誤");					
				}				
				
				SellVO sellVO = new SellVO();
				sellVO.setSellMemAccount(sellMemAccount);
				sellVO.setSellMemPwd(sellMemPwd);
				sellVO.setSellMemName(sellMemName);
				sellVO.setSellMemBirth(sellMemBirth);
				sellVO.setSellMemTel(sellMemTel);
				sellVO.setSellRoomName(sellRoomName);
				sellVO.setSellMemAddress(sellMemAddress);
				sellVO.setSellLatitude(sellLatitude);
				sellVO.setSellLongitud(sellLongitud);
				sellVO.setSellMemMail(sellMemMail);
				sellVO.setSellMemIdNumber(sellMemIdNumber);
				sellVO.setSellGender(sellGender);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("errorMsgs = " + errorMsgs);
					req.setAttribute("sellVO", sellVO); // 含有輸入格式錯誤的sellVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-sell-end/sellMemRegis.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				SellService sellSvc = new SellService();
				sellVO = sellSvc.addSell(sellMemAccount, sellMemPwd, sellMemName, sellMemBirth, sellMemTel, sellRoomName, sellMemAddress, sellLatitude, sellLongitud, sellMemMail, sellMemIdNumber, sellGender);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-sell-end/sell/sellMemIndex.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllRoom.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				System.out.println("catch錯誤 = " + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-sell-end/sellMemRegis.jsp");
				failureView.forward(req, res);
			}
		}
        
        if ("checkIsExist".equals(action)) {
        	
			PrintWriter out = res.getWriter();
			String colName = req.getParameter("colName").trim();
			String checkValue = req.getParameter("checkValue").trim();

			SellService sellSvc = new SellService();
			SellVO sellVO = sellSvc.getSellByCol(colName, checkValue);

			JSONObject obj = new JSONObject();
			
			if(sellVO == null) {
				obj.put("valid", true);
				System.out.println("outprint true");
			} else {
				obj.put("valid", false);
				System.out.println("outprint false");
			}
			out.print(obj);
		}

        if ("updateInfo".equals(action)) { // 來自sellLogin.jsp & addSellMem.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				// SELL_MEM_ID
				String sellMemId = req.getParameter("sellMemId").trim();
				String sellMemIdReg = "^SELL\\d{3}$";
				if (sellMemId == null || sellMemId.length() == 0) {
					errorMsgs.add("民宿會員編號: 請勿空白");
				} else if(!sellMemId.matches(sellMemIdReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("sellMemId = " + sellMemId);
					errorMsgs.add("民宿會員編號格式為: 前綴SELL+數字三碼 ex.SELL999");
	            }
								
				// SELL_MEM_NAME
				String sellMemName = req.getParameter("sellMemName").trim();
				if (sellMemName == null || sellMemName.length() == 0) {
					errorMsgs.add("請輸入民宿會員姓名");
				}
				
				// SELL_MEM_TEL
				String sellMemTel = req.getParameter("sellMemTel").trim();
				if (sellMemTel == null || sellMemTel.length() == 0) {
					errorMsgs.add("手機號碼請勿空白");
				} else if (!sellMemTel.matches("^0[0-9]{9}$")) {
					errorMsgs.add("手機號碼格式有誤, 請輸入手機號碼共10碼");
				}
				
				// SELL_ROOM_NAME
				String sellRoomName = req.getParameter("sellRoomName").trim();
				if (sellRoomName == null || sellRoomName.length() == 0) {
					errorMsgs.add("民宿名稱請勿空白");
				} 
				
				// SELL_MEM_ADDRESS
				String sellMemAddress = req.getParameter("sellMemAddress").trim();
				if (sellMemAddress == null || sellMemAddress.length() == 0) {
					errorMsgs.add("民宿地址請勿空白");
				} 

				// 地址轉經緯度
				JsonObject location = GetLatLng.transAddToPos(sellMemAddress);
				// SELL_LATITUDE NUMBER(12, 8),
				Double sellLatitude = 0.0;
				Double sellLongitud = 0.0;
				if(location != null) {
					sellLatitude = location.get("lat").getAsDouble();

					// SELL_LONGITUD NUMBER(12, 8),
					sellLongitud = location.get("lng").getAsDouble();

				}
				
				// SELL_MEM_MAIL VARCHAR2(100) NOT NULL,
				String sellMemMail = req.getParameter("sellMemMail").trim();
				if (sellMemMail == null || sellMemMail.length() == 0) {
					errorMsgs.add("E-mail請勿空白");
				} 
				
				SellVO sellVO = new SellVO();
				sellVO.setSellMemName(sellMemName);
				sellVO.setSellMemTel(sellMemTel);
				sellVO.setSellRoomName(sellRoomName);
				sellVO.setSellMemAddress(sellMemAddress);
				sellVO.setSellLatitude(sellLatitude);
				sellVO.setSellLongitud(sellLongitud);
				
				sellVO.setSellMemMail(sellMemMail);
				sellVO.setSellMemId(sellMemId);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("sellVO", sellVO); // 含有輸入格式錯誤的sellVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-sell-end/sell/updateSellInfo.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				SellService sellSvc = new SellService();
				sellVO = sellSvc.updateSell(sellMemId, sellMemName, sellMemTel, sellRoomName, sellMemAddress, sellLatitude, sellLongitud, sellMemMail);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				HttpSession session = req.getSession();
				session.setAttribute("sellVO", sellVO); // 資料庫update成功後,正確的的sellVO物件,存入req
//				System.out.println(sellVO);
				String url = "/front-sell-end/sell/listOneSell.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneRoom.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-sell-end/sell/updateSellInfo.jsp");
				failureView.forward(req, res);
			}


		}
        
        if ("updatePassword".equals(action)) { // 來自sellLogin.jsp & addSellMem.jsp的請求  
        	
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			SellService sellSvc = new SellService();
			SellVO oriSellVO = new SellVO();
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				// SELL_MEM_ID
				String sellMemId = req.getParameter("sellMemId").trim();
				oriSellVO = sellSvc.getOneSell(sellMemId);
				
				// 確認密碼與原本一致
				String oriSellMemPwd = req.getParameter("oriSellMemPwd").trim();
				if(!oriSellMemPwd.equals(oriSellVO.getSellMemPwd())) {
					errorMsgs.add("原始密碼輸入錯誤，更新失敗");
				}
				
				// SELL_MEM_PWD
				String sellMemPwd = req.getParameter("sellMemPwd").trim();
				String sellMemPwdReg = "\\w{8,20}";
				if (sellMemPwd == null || sellMemPwd.length() == 0) {
					errorMsgs.add("請填寫新密碼");
				} else if(!sellMemPwd.matches(sellMemPwdReg)) {
					errorMsgs.add("密碼限制為英數字或下底線，且長度需為8~20碼");
				}
				
				String sellMemPwdRe = req.getParameter("sellMemPwdRe").trim();
				if (sellMemPwdRe == null || sellMemPwdRe.length() == 0) {
					errorMsgs.add("確認密碼欄位不得為空");
				} else if (!sellMemPwd.equals(sellMemPwdRe)) {
					errorMsgs.add("確認密碼與密碼不一致, 請重新輸入");
				}
				
				
				SellVO sellVO = new SellVO();
				sellVO.setSellMemId(sellMemId);
				sellVO.setSellMemPwd(sellMemPwd);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("sellVO", sellVO); // 含有輸入格式錯誤的sellVO物件,也存入req
					System.out.println("errorMsgs = " + errorMsgs);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-sell-end/sell/editSellPwd.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				sellSvc = new SellService();
				sellVO = sellSvc.updateSell(sellMemId, sellMemPwd);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("sellVO", sellVO); // 資料庫update成功後,正確的的sellVO物件,存入req
				String url = "/front-sell-end/sell/listOneSell.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneRoom.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-sell-end/sell/editSellPwd.jsp");
				failureView.forward(req, res);
			}


		}
		
//		if ("delete".equals(action)) { // 來自listAllRoom.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.接收請求參數***************************************/
//				String sellMemId = req.getParameter("sellMemId");
//				
//				/***************************2.開始刪除資料***************************************/
//				SellService roomSvc = new SellService();
//				roomSvc.deleteRoom(sellMemId);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/front-sell-end/room/listAllRoom.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-sell-end/room/listAllRoom.jsp");
//				failureView.forward(req, res);
//			}
//		}
        
        if("sellMemLogin".equals(action)) {
        	System.out.println("action = " + action);
        	
			HttpSession session = req.getSession();
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			SellService sellSvc = new SellService();
			
			try {
				String sellMemAccount = req.getParameter("sellMemAccount").trim();

				SellVO sellVO = sellSvc.getSellByCol("SELL_MEM_ACCOUNT", sellMemAccount);
				String sellMemPwd = req.getParameter("sellMemPwd").trim();
				System.out.println("sellMemAccount = " + sellMemAccount);
				System.out.println("sellMemPwd = " + sellMemPwd);
				
				if (sellVO == null || !sellMemPwd.equals(sellVO.getSellMemPwd())) {
					errorMsgs.add("帳號或密碼錯誤，請重新登入！");
				} 
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("sellVO", sellVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-sell-end/sellMemLogin.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				// set login
				session.setAttribute("sellVO", sellVO);
//				String location = (String) session.getAttribute("location");
//
				String url = "/front-sell-end/sell/sellMemIndex.jsp";
//				if(location != null) {
//					url = location;
//				}
//				session.removeAttribute("location");
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				res.sendRedirect(url);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
        	
        	
        	
        }
        
        if("sellMemLogout".equals(action)) {
			HttpSession session = req.getSession();
			session.invalidate();
			req.removeAttribute("sellVO");
			res.sendRedirect(req.getContextPath() + "/front-sell-end/sellMemLogin.jsp");        	
        	
        }
        
        
        
        
        
        
        
        
        
        
	}
	
	

}
