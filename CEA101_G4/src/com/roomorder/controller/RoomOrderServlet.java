package com.roomorder.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.room.model.RoomService;
import com.roomorder.model.*;
import com.roomorderdetail.model.RoomOrderDetailVO;
import com.roomphoto.model.RoomPhotoService;


public class RoomOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("roomOrderId");
				if (str == null || str.trim().length() == 0) {
					errorMsgs.add("請輸入訂單編號");
				}

				String roomOrderId = null;
				try {
					roomOrderId = str;
				} catch (Exception e) {
					errorMsgs.add("訂單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/roomorder/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				RoomOrderService roSvc = new RoomOrderService();
				RoomOrderVO roomOrderVO = roSvc.getOneRoomOrder(roomOrderId);
				if (roomOrderVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/roomorder/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("roomOrderVO", roomOrderVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-mem-end/roomorder/listOneRoomOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneRoomOrder.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/roomorder/select_page.jsp.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllRoomOrder.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String roomOrderId = req.getParameter("roomOrderId");

				/*************************** 2.開始查詢資料 ****************************************/
				RoomOrderService roSvc = new RoomOrderService();
				RoomOrderVO roomOrderVO = roSvc.getOneRoomOrder(roomOrderId);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("roomOrderVO", roomOrderVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-mem-end/roomorder/update_roomorder_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/roomorder/listAllRoomOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("updateOrderStatus".equals(action)) { // 來自update_souvenirorder_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String roomOrderId = req.getParameter("roomOrderId").trim();
				
				Integer roomOrderStatus = null;
				try {
					roomOrderStatus = new Integer (req.getParameter("roomOrderStatus").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("請輸入數字.");
				}

				RoomOrderVO roomOrderVO = new RoomOrderVO();
				roomOrderVO.setRoomOrderId(roomOrderId);
				roomOrderVO.setRoomOrderStatus(roomOrderStatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("roomOrderVO", roomOrderVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/roomorder/update_roomorder_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				RoomOrderService roomOrderSvc = new RoomOrderService();
				roomOrderVO = roomOrderSvc.updateRoomOrder(roomOrderId, roomOrderStatus);
			
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("roomOrderVO", roomOrderVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-mem-end/roomorder/listOneRoomOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/roomorder/update_roomorder_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("updatePaymentStatus".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String roomOrderId = req.getParameter("roomOrderId").trim();
				
				Integer roomOrderStatus = null;
				try {
					roomOrderStatus = new Integer (req.getParameter("roomOrderStatus").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("請輸入數字.");
				}
				
				Integer roomPaymentStatus = null;
				try {
					roomPaymentStatus = new Integer (req.getParameter("roomPaymentStatus").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("請輸入數字.");
				}

				RoomOrderVO roomOrderVO = new RoomOrderVO();
				roomOrderVO.setRoomOrderId(roomOrderId);
				roomOrderVO.setRoomOrderStatus(roomOrderStatus);
				roomOrderVO.setRoomPaymentStatus(roomPaymentStatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("roomOrderVO", roomOrderVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/roomorder/update_roomorder_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				RoomOrderService roomOrderSvc = new RoomOrderService();
				roomOrderVO = roomOrderSvc.updateRoomOrder(roomOrderId, roomOrderStatus, roomPaymentStatus);
			
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("roomOrderVO", roomOrderVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-mem-end/roomorder/listOneRoomOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/roomorder/update_roomorder_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		

//		if ("update".equals(action)) { // 來自update_souvenirorder_input.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//				String roomOrderId = req.getParameter("roomOrderId").trim();
//				
//				String sellMemId = req.getParameter("sellMemId").trim();
//				String sellMemIdReg = "^[(a-zA-Z0-9)]{2,100}$";
//				if (sellMemId == null || sellMemId.trim().length() == 0) {
//					errorMsgs.add("民宿會員編號請勿空白");
//				} else if (!sellMemId.trim().matches(sellMemIdReg)) {
//					errorMsgs.add("民宿會員編號: 只能是英文字母、數字 , 且長度必需在2到10之間");
//				}
//				
//				String memId = req.getParameter("memId").trim();
//				String memIdReg = "^[(a-zA-Z0-9)]{2,100}$";
//				if (memId == null || memId.trim().length() == 0) {
//					errorMsgs.add("會員編號請勿空白");
//				} else if (!memId.trim().matches(memIdReg)) {
//					errorMsgs.add("會員編號: 只能是英文字母、數字 , 且長度必需在2到10之間");
//				}
//				java.sql.Timestamp roomOrderTime = java.sql.Timestamp.valueOf(req.getParameter("roomOrderTime").trim());
//				
//				java.sql.Timestamp expectArrTime = java.sql.Timestamp.valueOf(req.getParameter("expectArrTime").trim());			
//				
//				Date checkInDate = Date.valueOf(req.getParameter("checkInDate"));
//				
//				Date checkOutDate = Date.valueOf(req.getParameter("checkOutDate"));
//				
//				Integer roomOrderSum = null;
//				try {
//					roomOrderSum =new Integer(req.getParameter("roomOrderSum").trim());
//				} catch (NumberFormatException e) {
//					roomOrderSum = new Integer(0);
//					errorMsgs.add("請輸入數字.");
//				}
//				
//				String roomOrderRemarks = req.getParameter("roomOrderRemarks");
//
//				
//				Integer roomOrderStatus = null;
//				try {
//					roomOrderStatus =new Integer(req.getParameter("roomOrderStatus").trim());
//				} catch (NumberFormatException e) {
//					roomOrderStatus = new Integer(0);
//					errorMsgs.add("請輸入數字.");
//				}
//				Integer roomPaymentStatus = null;
//				try {
//					roomPaymentStatus =new Integer(req.getParameter("roomPaymentStatus").trim());
//				} catch (NumberFormatException e) {
//					roomPaymentStatus = new Integer(0);
//					errorMsgs.add("請輸入數字.");
//				}
//				
//
//				RoomOrderVO roVO = new RoomOrderVO();
//				roVO.setRoomOrderId(roomOrderId);
//				roVO.setSellMemId(sellMemId);
//				roVO.setMemId(memId);
//				roVO.setRoomOrderTime(roomOrderTime);
//				roVO.setCheckInDate(checkInDate);
//				roVO.setCheckOutDate(checkOutDate);
//				roVO.setExpectArrTime(expectArrTime);
//				roVO.setRoomOrderRemarks(roomOrderRemarks);
//				roVO.setRoomOrderSum(roomOrderSum);
//				roVO.setRoomOrderStatus(roomOrderStatus);
//				roVO.setRoomPaymentStatus(roomPaymentStatus);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("roVO", roVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/roomorder/update_roomorder_input.jsp");
//					failureView.forward(req, res);
//					return; // 程式中斷
//				}
//
//				/*************************** 2.開始修改資料 *****************************************/
//				RoomOrderService roSvc = new RoomOrderService();
//				roVO = roSvc.updateRoomOrder(roomOrderId, sellMemId, memId, roomOrderTime, checkInDate
//						,checkOutDate,  expectArrTime, roomOrderRemarks,  roomOrderSum,  roomOrderStatus
//						, roomPaymentStatus);
//			
//
//				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//				req.setAttribute("roVO", roVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/front-mem-end/roomorder/listOneRoomOrder.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/roomorder/update_roomorder_input.jsp");
//				failureView.forward(req, res);
//			}
//		}
		if ("fillorderinfo".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
								
				String sellMemId = req.getParameter("sellMemId").trim();
				
				String sellMemIdReg = "^[(a-zA-Z0-9)]{2,100}$";
				if (sellMemId == null || sellMemId.trim().length() == 0) {
					errorMsgs.add("民宿會員編號請勿空白");
				} else if (!sellMemId.trim().matches(sellMemIdReg)) {
					errorMsgs.add("民宿會員編號: 只能是英文字母、數字 , 且長度必需在2到10之間");
				}
				
				String memId = req.getParameter("memId").trim();
				String memIdReg = "^[(a-zA-Z0-9)]{2,100}$";
				if (memId == null || memId.trim().length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				} else if (!memId.trim().matches(memIdReg)) {
					errorMsgs.add("會員編號: 只能是英文字母、數字 , 且長度必需在2到10之間");
				}
				
				
				java.sql.Date checkInDate = null;
				try {
					checkInDate = java.sql.Date.valueOf(req.getParameter("checkInDate"));
				} catch (IllegalArgumentException e) {
					errorMsgs.add("check in 日期格式有誤");					
				}
				
				java.sql.Date checkOutDate = null;
				try {
					checkOutDate = java.sql.Date.valueOf(req.getParameter("checkOutDate"));
				} catch (IllegalArgumentException e) {
					errorMsgs.add("check out 日期格式有誤");					
				}
								
//				java.sql.Timestamp expectArrTime = null;
//				try {
//					expectArrTime = java.sql.Timestamp.valueOf(req.getParameter("expectArrTime"));
//				} catch (IllegalArgumentException e) {
//					errorMsgs.add("expectArrTime 時間格式有誤");					
//				}
				
//				String roomOrderRemarks = req.getParameter("roomOrderRemarks");
				
				Integer roomOrderSum = null;
				try {
					roomOrderSum =new Integer(req.getParameter("roomOrderSum").trim());
				} catch (NumberFormatException e) {
					roomOrderSum = new Integer(0);
					errorMsgs.add("請輸入數字.");
				}
				

				
//				Integer roomOrderStatus = new Integer(0);
//				try {
//					roomOrderStatus =new Integer(req.getParameter("roomOrderStatus").trim());
//				} catch (NumberFormatException e) {
//					roomOrderStatus = new Integer(0);
//					errorMsgs.add("請輸入數字.");
//				}
//				
//				
//				Integer roomPaymentStatus = new Integer(0);
//				try {
//					roomPaymentStatus = new Integer(req.getParameter("roomPaymentStatus").trim());
//				} catch (NumberFormatException e) {
//					roomPaymentStatus = new Integer(0);
//					errorMsgs.add("請輸入數字.");
//				}
							
				
				RoomOrderVO roomOrderVO = new RoomOrderVO();
				roomOrderVO.setSellMemId(sellMemId);
				roomOrderVO.setMemId(memId);
				roomOrderVO.setCheckInDate(checkInDate);
				roomOrderVO.setCheckOutDate(checkOutDate);
//				roomOrderVO.setExpectArrTime(expectArrTime);
//				roomOrderVO.setRoomOrderRemarks(roomOrderRemarks);
				roomOrderVO.setRoomOrderSum(roomOrderSum);
//				roomOrderVO.setRoomOrderStatus(roomOrderStatus);
//				roomOrderVO.setRoomPaymentStatus(roomPaymentStatus);
				
//				List<RoomOrderDetailVO> list = new ArrayList<RoomOrderDetailVO>();
//				RoomOrderDetailVO roomOrderDetailVO = null;
//				while() {
//					roomOrderDetailVO = new RoomOrderDetailVO();
//					
//					String roomId = null;
//					try {
//						roomId = req.getParameter("roomId").trim();
//					} catch(Exception e) {
//						errorMsgs.add("房間編號room_id 格式有誤");
//					}
					
//					Integer room_cur_price = null;
//					try {
//						room_cur_price = new Integer(req.getParameter("room_cur_price").trim());
//
//					} catch (NumberFormatException e) {
//						room_cur_price = new Integer(0);
//						errorMsgs.add("房間價格請輸入數字.");
//					}
					
					
//					String room_guest_name = null;
//					try {
//						room_guest_name = req.getParameter("room_guest_name").trim();
//					} catch(Exception e) {
//						errorMsgs.add("訂購人姓名room_guest_name 格式有誤");
//					}
					
//					String room_guest_mail = null;
//					try {
//						room_guest_mail = req.getParameter("room_guest_mail").trim();
//					} catch(Exception e) {
//						errorMsgs.add("訂購人email room_guest_mail 格式有誤");
//					}
//					
//					String room_guest_tel = null;
//					try {
//						room_guest_tel = req.getParameter("room_guest_tel").trim();
//					} catch(Exception e) {
//						errorMsgs.add("訂購人電話room_guest_tel 格式有誤");
//					}

//					roomOrderDetailVO.setRoom_id(roomId);
//					roomOrderDetailVO.setRoom_cur_price(room_cur_price);
//					roomOrderDetailVO.setRoom_guest_name(room_guest_name);
//					roomOrderDetailVO.setRoom_guest_mail(room_guest_mail);
//					roomOrderDetailVO.setRoom_guest_tel(room_guest_tel);
//					
//					list.add(roomOrderDetailVO);
//				}
				
				String roomId = req.getParameter("roomId");
				System.out.println(roomId);
				String roomIdReg = "^ROOM\\d{3}$";
				if (roomId == null || roomId.trim().length() == 0) {
					errorMsgs.add("房間編號: 請勿空白");
				} else if(!roomId.trim().matches(roomIdReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("房間編號格式為: ROOM+數字三碼 ex.ROOM999");
	            }
				


				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("roomOrderVO", roomOrderVO); // 含有輸入格式錯誤的empVO物件,也存入req
					System.out.println("fill roomOrder錯誤 :" + errorMsgs);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/room/listOneRoom.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始新增資料 ***************************************/
				RoomOrderService roomOrderService = new RoomOrderService();
				roomOrderVO = roomOrderService.fillRoomOrderInfo(sellMemId, memId, checkInDate, checkOutDate, 
						 roomOrderSum);
				
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-mem-end/roomorder/fillRoomOrder.jsp";
				req.setAttribute("roomOrderVO", roomOrderVO);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				System.out.println("roomOrderServlet 聽說是額外的錯誤.. " + errorMsgs);
				RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/roomorder/listOneRoom.jsp");
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

				
				String sellMemId = req.getParameter("sellMemId").trim();
				String sellMemIdReg = "^[(a-zA-Z0-9)]{2,100}$";
				if (sellMemId == null || sellMemId.trim().length() == 0) {
					errorMsgs.add("民宿會員編號請勿空白");
				} else if (!sellMemId.trim().matches(sellMemIdReg)) {
					errorMsgs.add("民宿會員編號: 只能是英文字母、數字 , 且長度必需在2到10之間");
				}
				
				String memId = req.getParameter("memId").trim();
				String memIdReg = "^[(a-zA-Z0-9)]{2,100}$";
				if (memId == null || memId.trim().length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				} else if (!memId.trim().matches(memIdReg)) {
					errorMsgs.add("會員編號: 只能是英文字母、數字 , 且長度必需在2到10之間");
				}
				
				
				java.sql.Date checkInDate = null;
				try {
					checkInDate = java.sql.Date.valueOf(req.getParameter("checkInDate"));
				} catch (IllegalArgumentException e) {
					errorMsgs.add("check in 日期格式有誤");					
				}
				
				java.sql.Date checkOutDate = null;
				try {
					checkOutDate = java.sql.Date.valueOf(req.getParameter("checkOutDate"));
				} catch (IllegalArgumentException e) {
					errorMsgs.add("check out 日期格式有誤");					
				}
				if(checkOutDate.getTime() < checkInDate.getTime())
					errorMsgs.add("check out 日期需大於check in 日期");
								
				java.sql.Timestamp expectArrTime = null;
				try {
					expectArrTime = java.sql.Timestamp.valueOf(req.getParameter("expectArrTime"));
				} catch (IllegalArgumentException e) {
					errorMsgs.add("expectArrTime 時間格式有誤");					
				}
				
				String roomOrderRemarks = req.getParameter("roomOrderRemarks");
				
				Integer roomOrderSum = null;
				try {
					roomOrderSum =new Integer(req.getParameter("roomOrderSum").trim());
				} catch (NumberFormatException e) {
					roomOrderSum = new Integer(0);
					errorMsgs.add("請輸入數字.");
				}
				
				
				Integer roomOrderStatus = new Integer(0);
//				try {
//					roomOrderStatus =new Integer(req.getParameter("roomOrderStatus").trim());
//				} catch (NumberFormatException e) {
//					roomOrderStatus = new Integer(0);
//					errorMsgs.add("請輸入數字.");
//				}
//				
//				
				Integer roomPaymentStatus = new Integer(0);
//				try {
//					roomPaymentStatus = new Integer(req.getParameter("roomPaymentStatus").trim());
//				} catch (NumberFormatException e) {
//					roomPaymentStatus = new Integer(0);
//					errorMsgs.add("請輸入數字.");
//				}
							
				
				RoomOrderVO roomOrderVO = new RoomOrderVO();
				roomOrderVO.setSellMemId(sellMemId);
				roomOrderVO.setMemId(memId);
				roomOrderVO.setCheckInDate(checkInDate);
				roomOrderVO.setCheckOutDate(checkOutDate);
				roomOrderVO.setExpectArrTime(expectArrTime);
				roomOrderVO.setRoomOrderRemarks(roomOrderRemarks);
				roomOrderVO.setRoomOrderSum(roomOrderSum);
				roomOrderVO.setRoomOrderStatus(roomOrderStatus);
				roomOrderVO.setRoomPaymentStatus(roomPaymentStatus);
								
				List<RoomOrderDetailVO> list = new ArrayList<RoomOrderDetailVO>();
				RoomOrderDetailVO roomOrderDetailVO = null;
//				while() {
					roomOrderDetailVO = new RoomOrderDetailVO();
					
					String roomId = null;
					try {
						roomId = req.getParameter("roomId").trim();
					} catch(Exception e) {
						errorMsgs.add("房間編號room_id 格式有誤");
					}
					
					Integer room_cur_price = null;
					try {
						room_cur_price = new Integer(req.getParameter("room_cur_price").trim());

					} catch (NumberFormatException e) {
						room_cur_price = new Integer(0);
						errorMsgs.add("房間價格請輸入數字.");
					}
					
					
					String room_guest_name = null;
					try {
						room_guest_name = req.getParameter("room_guest_name").trim();
					} catch(Exception e) {
						errorMsgs.add("訂購人姓名room_guest_name 格式有誤");
					}
					
					String room_guest_mail = null;
					try {
						room_guest_mail = req.getParameter("room_guest_mail").trim();
					} catch(Exception e) {
						errorMsgs.add("訂購人email room_guest_mail 格式有誤");
					}
					
					String room_guest_tel = null;
					try {
						room_guest_tel = req.getParameter("room_guest_tel").trim();
					} catch(Exception e) {
						errorMsgs.add("訂購人電話room_guest_tel 格式有誤");
					}

					roomOrderDetailVO.setRoom_id(roomId);
					roomOrderDetailVO.setRoom_cur_price(room_cur_price);
					roomOrderDetailVO.setRoom_guest_name(room_guest_name);
					roomOrderDetailVO.setRoom_guest_mail(room_guest_mail);
					roomOrderDetailVO.setRoom_guest_tel(room_guest_tel);
					
					list.add(roomOrderDetailVO);
//				}
					
					
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("roomOrderVO", roomOrderVO); // 含有輸入格式錯誤的empVO物件,也存入req
					System.out.println("insert roomOrder錯誤 :" + errorMsgs);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/room/listOneRoom.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始新增資料 ***************************************/
				RoomOrderService roomOrderService = new RoomOrderService();
				roomOrderVO = roomOrderService.addRoomOrder(sellMemId, memId, checkInDate, checkOutDate, 
						expectArrTime, roomOrderRemarks, roomOrderSum, roomOrderStatus, roomPaymentStatus, list);
				roomOrderVO = roomOrderService.getOneRoomOrder(roomOrderVO.getRoomOrderId());
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-mem-end/roomorder/listOneRoomOrder.jsp";
				req.setAttribute("roomOrderVO", roomOrderVO);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				System.out.println("roomOrderServlet 聽說是額外的錯誤.. " + errorMsgs);
				RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/roomorder/addRoomOrder.jsp");
				failureView.forward(req, res);
			}
		}

//		if ("delete".equals(action)) { // 來自listAllSouvenir_Order.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數 ***************************************/
//				String roomOrderId = req.getParameter("roomOrderId");
//
//				/*************************** 2.開始刪除資料 ***************************************/
//				RoomOrderService roSvc = new RoomOrderService();
//				roSvc.deleteRoomOrder(roomOrderId);
//
//				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
//				String url = "/front-mem-end/roomorder/listAllRoomOrder.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:" + e.getMessage());
//				RequestDispatcher failureView;
//				failureView = req.getRequestDispatcher("/front-mem-end/roomorder/listAllRoomOrder.jsp");
//				failureView.forward(req, res);
//			}
//		}

	}

}
