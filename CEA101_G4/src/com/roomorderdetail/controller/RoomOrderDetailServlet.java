package com.roomorderdetail.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.roomorderdetail.model.RoomOrderDetailService;
import com.roomorderdetail.model.RoomOrderDetailVO;

public class RoomOrderDetailServlet extends HttpServlet {
	
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
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String str = req.getParameter("room_order_id");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入房間訂單編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-sell-end/roomorderdetail/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			String room_order_id = null;
			try {
				room_order_id = str;
			} catch (Exception e) {
				errorMsgs.add("房間訂單編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-sell-end/roomorderdetail/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
						
			/***************************2.開始查詢資料*****************************************/
			RoomOrderDetailService rodSvc = new RoomOrderDetailService();
			RoomOrderDetailVO rodVO = rodSvc.getOneRoomOrderDetail(room_order_id);
			if (rodVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-sell-end/roomorderdetail/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			req.setAttribute("rodVO", rodVO); // 資料庫取出的rodVO物件,存入req
System.out.println(rodVO);
			String url = "/front-sell-end/roomorderdetail/listOneRoomOrderDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneRoomOrderDetail.jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-sell-end/roomorderdetail/listAllRoomOrderDetail.jsp");
			failureView.forward(req, res);
		}
	}
	
	
	if ("getOne_For_Update".equals(action)) { // 來自listAllRoomProductCollect.jsp的請求

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		
		try {
			/***************************1.接收請求參數****************************************/
			String room_order_id = req.getParameter("room_order_id");
			
			/***************************2.開始查詢資料****************************************/
			RoomOrderDetailService rodSvc = new RoomOrderDetailService();
			RoomOrderDetailVO rodVO = rodSvc.getOneRoomOrderDetail(room_order_id);
							
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("rodVO", rodVO);         // 資料庫取出的empVO物件,存入req
			String url = "/front-sell-end/roomorderdetail/update_roomorderdetail_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_roomorderdetail_input.jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front-sell-end/roomorderdetail/listAllRoomOrderDetail.jsp");
			failureView.forward(req, res);
		}
	}
	
	
	if ("update".equals(action)) { // 來自update_roomorderdetail_input.jsp的請求
		
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
	
		try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String room_order_id = req.getParameter("room_order_id").trim();
			
			String room_id = req.getParameter("room_id");
			String room_id_Reg = "^ROOM\\d{3}$";
			if (room_id == null || room_id.trim().length() == 0) {
				errorMsgs.add("房間編號 請勿空白");
			} else if(!room_id.trim().matches(room_id_Reg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("房間編號: 必須為ROOM開頭 ,後面加上三個0-9的數字");
            }
			
			Integer room_cur_price = null;
			try {
				room_cur_price =Integer.parseInt(req.getParameter("room_cur_price").trim());
			} catch (NullPointerException e) {
				room_cur_price = new Integer(0);
				errorMsgs.add("請輸入房間價格.");
			} catch(NumberFormatException nfe) {
				errorMsgs.add("請輸入數字");
			}
			
			String room_guest_name = req.getParameter("room_guest_name");
			
			String roomguestnameReg = "^[(\u4e00-\u9fa5)]{2,100}$";
			if (room_guest_name == null || room_guest_name.trim().length() == 0) {
				errorMsgs.add("姓名請勿空白");
			} else if (!room_guest_name.trim().matches(roomguestnameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("姓名只能是中文, 且長度必需在2到10之間");
			}
			
			String room_guest_mail = null;
			try {
				room_guest_mail = req.getParameter("room_guest_mail").trim();
			} catch (NullPointerException e) {
				room_guest_mail = "";
				errorMsgs.add("請輸入電子信箱.");
			}
			
			String room_guest_tel = null;
			try {
				room_guest_tel = req.getParameter("room_guest_tel").trim();
			} catch (NullPointerException e) {
				room_guest_tel = "";
				errorMsgs.add("請輸入連絡電話");
			}
			
			
			RoomOrderDetailVO rodVO = new RoomOrderDetailVO();
			rodVO.setRoom_order_id(room_order_id);
			rodVO.setRoom_id(room_id);
			rodVO.setRoom_cur_price(room_cur_price);
			rodVO.setRoom_guest_name(room_guest_name);
			rodVO.setRoom_guest_mail(room_guest_mail);
			rodVO.setRoom_guest_tel(room_guest_tel);
			
				
			
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("rodVO", rodVO); // 含有輸入格式錯誤的rodVO物件,也存入req

				RequestDispatcher failureView = req.getRequestDispatcher("/front-sell-end/roomorderdetail/update_roomorderdetail_input.jsp");
				failureView.forward(req, res);
				
				return; //程式中斷
				
			}
			
			/***************************2.開始修改資料*****************************************/

			RoomOrderDetailService rodSvc = new RoomOrderDetailService();
			rodVO = rodSvc.updateRoomOrderDetail(room_order_id, room_id, room_cur_price, room_guest_name, room_guest_mail, room_guest_tel);
			
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			req.setAttribute("rodVO", rodVO); // 資料庫update成功後,正確的的rodVO物件,存入req
			String url = "/front-sell-end/roomorderdetail/listAllRoomOrderDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
		} catch (Exception e) {
			errorMsgs.add("修改資料失敗:"+e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front-sell-end/roomorderdetail/update_roomorderdetail_input.jsp");
			
			failureView.forward(req, res);
		}
	}

    if ("insert".equals(action)) { // 來自addRoomorderdetail.jsp的請求  
		
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			String room_id = req.getParameter("room_id");
			String room_order_id = req.getParameter("room_order_id");
			String room_id_Reg = "^ROOM\\d{3}$";
			if (room_id == null || room_id.trim().length() == 0) {
				errorMsgs.add("房間編號請勿空白");
			} else if(!room_id.trim().matches(room_id_Reg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("房間編號: 必須為ROOM開頭 ,後面加上三個0-9的數字");
            }
			
			Integer room_cur_price = null;
			try {
				room_cur_price =Integer.parseInt(req.getParameter("room_cur_price").trim());
			} catch (NullPointerException e) {
				room_cur_price = new Integer(0);
				errorMsgs.add("請輸入房間價格.");
			}
String room_guest_name = req.getParameter("room_guest_name");
			
			String roomguestnameReg = "^[(\u4e00-\u9fa5)]{2,100}$";
			if (room_guest_name == null || room_guest_name.trim().length() == 0) {
				errorMsgs.add("姓名請勿空白");
			} else if (!room_guest_name.trim().matches(roomguestnameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("姓名只能是中文, 且長度必需在2到10之間");
			}
			
			String room_guest_mail = null;
			try {
				room_guest_mail = req.getParameter("room_guest_mail").trim();
			} catch (NullPointerException e) {
				room_guest_mail = "";
				errorMsgs.add("請輸入電子信箱.");
			}
			
			String room_guest_tel = null;
			try {
				room_guest_tel = req.getParameter("room_guest_tel").trim();
			} catch (NullPointerException e) {
				room_guest_tel = "";
				errorMsgs.add("請輸入連絡電話");
			}
			
			
			RoomOrderDetailVO rodVO = new RoomOrderDetailVO();
			rodVO.setRoom_order_id(room_order_id);
			rodVO.setRoom_id(room_id);
			rodVO.setRoom_cur_price(room_cur_price);
			rodVO.setRoom_guest_name(room_guest_name);
			rodVO.setRoom_guest_mail(room_guest_mail);
			rodVO.setRoom_guest_tel(room_guest_tel);


			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("rodVO", rodVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-sell-end/roomorderdetail/addRoomorderdetail.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/***************************2.開始新增資料***************************************/
			RoomOrderDetailService rodSvc = new RoomOrderDetailService();
			rodVO = rodSvc.addRoomOrderDetail(room_order_id, room_id, room_cur_price,room_guest_name,room_guest_mail,room_guest_tel);
			
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			String url = "/front-sell-end/roomorderdetail/listAllRoomOrderDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);				
			
			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front-sell-end/roomorderdetail/addRoomOrderDetail.jsp");
			failureView.forward(req, res);
		}
	}
	
	if ("delete".equals(action)) { // 來自listAllRoomOrderDetail.jsp

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1.接收請求參數***************************************/
			String room_order_id = req.getParameter("room_order_id");
			String room_id = req.getParameter("room_id");
			System.out.println(room_id);
			/***************************2.開始刪除資料***************************************/
			RoomOrderDetailService rodSvc = new RoomOrderDetailService();
			rodSvc.deleteRoomOrderDetail(room_order_id, room_id);
			
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
			String url = "/front-end/roomorderdetail/listAllRoomOrderDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
			
			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add("刪除資料失敗:"+e.getMessage());
			RequestDispatcher failureView;
			failureView = req.getRequestDispatcher("/front-end/roomorderdetail/listAllRoomOrderDetail.jsp");
			failureView.forward(req, res);
		}
	}
}

}
