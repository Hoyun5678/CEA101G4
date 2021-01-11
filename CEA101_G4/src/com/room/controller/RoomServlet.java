package com.room.controller;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.*;
import javax.servlet.http.*;

import com.room.model.*;

public class RoomServlet extends HttpServlet {
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
				String roomId = req.getParameter("roomId");
				String roomIdReg = "^ROOM\\d{3}$";
				if (roomId == null || roomId.trim().length() == 0) {
					errorMsgs.add("房間編號: 請勿空白");
				} else if(!roomId.trim().matches(roomIdReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("房間編號格式為: 前綴ROOM+數字三碼 ex.ROOM999");
	            }

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-sell-end/room/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				RoomService roomSvc = new RoomService();
				RoomVO roomVO = roomSvc.getOneRoom(roomId);
				if (roomVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-sell-end/room/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("roomVO", roomVO); // 資料庫取出的roomVO物件,存入req
				String url = "/front-sell-end/room/listOneRoom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneRoom.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-sell-end/room/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getByMultiCondition".equals(action)) {
			System.out.println("test getByMulti");
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				RoomService roomSvc = new RoomService();
				List<RoomVO> dateFilterRoomList = null;
				List<RoomVO> conditionFilterRoomList = null;
				List<RoomVO> resultRoomList = null;
				Map<String, String[]> queryMap = new TreeMap<String, String[]>();
				
				
				
				// datefilter checkin&checkout 
				String datefilter = req.getParameter("datefilter");
				if(datefilter == null || datefilter.trim().length() < 1) {
					System.out.println("使用者未輸入日期");
					dateFilterRoomList = roomSvc.getAll();
				} else {
					// 處理日期格式
					String[] dateList = datefilter.split(" ~ ");
					dateFilterRoomList = roomSvc.getByDateRange(java.sql.Date.valueOf(dateList[0]), java.sql.Date.valueOf(dateList[1]));
				}
				
				// sel 關鍵字
				String sel = req.getParameter("sel");
				if(sel == null || sel.trim().length() < 1) {
					System.out.println("使用者未輸入地點");
				} else {
					queryMap.put("ROOM_NAME", new String[] { sel });
					queryMap.put("ROOM_DES", new String[] { sel });
					queryMap.put("SELL_MEM_ADDRESS", new String[] { sel });
				}
				
				// roomCapacity
				Integer roomCapacity = null;
				try {
					roomCapacity = Integer.parseInt(req.getParameter("roomCapacity"));
				} catch(Exception e) {
					errorMsgs.add("入住人數格式錯誤 " + e.getMessage());
				}
				queryMap.put("ROOM_CAPACITY", new String[] { roomCapacity.toString() });

//				 Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-index.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
//				
//				/***************************2.開始查詢資料*****************************************/
				roomSvc = new RoomService();
				conditionFilterRoomList = roomSvc.getAll(queryMap);
//				System.out.println("dateList = " + dateFilterRoomList);
//				System.out.println("conList = " + conditionFilterRoomList);
				
				List<String> dateStringList = new ArrayList<String>();
				dateFilterRoomList.forEach(e -> dateStringList.add(e.getRoomId()));
				
				resultRoomList = conditionFilterRoomList.stream()
						.filter(e -> dateStringList.contains(e.getRoomId()))
						.collect(Collectors.toList());
				
				
//				if (roomVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-sell-end/room/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", resultRoomList); // 資料庫取出的roomVO物件,存入req
//				System.out.println("對還不對啦 ..." + resultRoomList);
				if(resultRoomList.size() < 1 ) {
					req.setAttribute("noResult", "沒有完全符合條件的物件，請修改搜尋條件、或參考下列物件");
				}
				req.setAttribute("sel", sel);
				req.setAttribute("datefilter", datefilter);
				req.setAttribute("roomCapacity", roomCapacity);
				String url = "/front-mem-end/room/listAllRoom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-sell-end/room/select_page.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
		if ("checkRoomDetail".equals(action)) { // 來自mem/listAllRoom.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				RoomService roomSvc = new RoomService();

				
				// datefilter checkin&checkout 
				String datefilter = req.getParameter("datefilter");
			
					String[] dateList = datefilter.split(" ~ ");
					String checkInDate = dateList[0];    
					String checkOutDate = dateList[1]; 
				
				
				String roomId = req.getParameter("roomId");
				String roomIdReg = "^ROOM\\d{3}$";
				if (roomId == null || roomId.trim().length() == 0) {
					errorMsgs.add("房間編號: 請勿空白");
				} else if(!roomId.trim().matches(roomIdReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("房間編號格式為: 前綴ROOM+數字三碼 ex.ROOM999");
	            }

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-mem-end/room/listAllRoom.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				RoomVO roomVO = roomSvc.getOneRoom(roomId);
				if (roomVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-mem-end/room/listAllRoom.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("roomVO", roomVO); // 資料庫取出的roomVO物件,存入req
				req.setAttribute("checkInDate", checkInDate);
				req.setAttribute("checkOutDate", checkOutDate);
				String url = "/front-mem-end/room/listOneRoom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneRoom.jsp
				successView.forward(req, res);


				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-mem-end/room/listAllRoom.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllRoom.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String roomId = req.getParameter("roomId");
								
				/***************************2.開始查詢資料****************************************/
				RoomService roomSvc = new RoomService();
				RoomVO roomVO = roomSvc.getOneRoom(roomId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("roomVO", roomVO);         // 資料庫取出的roomVO物件,存入req
				String url = "/front-sell-end/room/update_room_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_room_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-sell-end/room/listAllRoom.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_room_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			RoomService roomSvc = new RoomService();
			RoomVO oriRoomVO = new RoomVO();
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String roomId = req.getParameter("roomId").trim();
				String roomIdReg = "^ROOM\\d{3}$";
				if (roomId == null || roomId.length() == 0) {
					errorMsgs.add("房間編號: 請勿空白");
				} else if(!roomId.matches(roomIdReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("roomId = " + roomId);
					errorMsgs.add("房間編號格式為: 前綴ROOM+數字三碼 ex.ROOM999");
	            }
				oriRoomVO = roomSvc.getOneRoom(roomId);
				
				String sellMemId = req.getParameter("sellMemId").trim();
				String sellMemIdReg = "^SELL\\d{3}$";
				if (sellMemId == null || sellMemId.length() == 0) {
					errorMsgs.add("民宿會員ID: 請勿空白");
				} else if(!sellMemId.matches(sellMemIdReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("sellMemId = " + sellMemId);
					errorMsgs.add("民宿會員ID格式為: 前綴SELL+數字三碼 ex.SELL999");
					sellMemId = oriRoomVO.getSellMemId();
	            }
				
				String roomName = req.getParameter("roomName").trim();
				if (roomName == null || roomName.length() == 0) {
					errorMsgs.add("請填寫房間名稱");
					roomName = oriRoomVO.getRoomName();
				}
				
				Integer roomPrice = null;
				try {
					roomPrice = new Integer(req.getParameter("roomPrice").trim());
				} catch (NumberFormatException e) {
					roomPrice = oriRoomVO.getRoomPrice();
					errorMsgs.add("房間價格請填入數字");					
				}
				
				Integer roomCapacity = null;
				try {
					roomCapacity = new Integer(req.getParameter("roomCapacity").trim());
				} catch (NumberFormatException e) {
					roomCapacity = oriRoomVO.getRoomCapacity();
					errorMsgs.add("房間容納人數請填入數字");					
				}				
				
				java.sql.Timestamp roomOnTime = null;
				try {
					roomOnTime = java.sql.Timestamp.valueOf(req.getParameter("roomOnTime").trim());
				} catch (IllegalArgumentException e) {
					roomOnTime=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入正確日期時間!");
				}

				String roomDes = req.getParameter("roomDes").trim();
				
				Integer roomCollect = null;
				try {
					roomCollect = new Integer(req.getParameter("roomCollect").trim());
				} catch (NumberFormatException e) {
					roomCollect = 0;
					errorMsgs.add("按讚次數請填數字.");
				}
				
				Integer roomStatus = null;
				String roomStatusStr = null;
				String roomStatusReg = "^[01]$";
				try {
					roomStatusStr = req.getParameter("roomStatus").trim();
					if(!roomStatusStr.matches(roomStatusReg)) { //以下練習正則(規)表示式(regular-expression)
						throw new NumberFormatException("房間狀態只能為0或1");
		            } else {
		            	roomStatus = new Integer(roomStatusStr);
		            }
				} catch (NumberFormatException e) {
					roomStatus = 0;
					errorMsgs.add("房間狀態只能為0或1");
				}

				// String sellMemId =req.getParameter("sellMemId").trim();

				RoomVO roomVO = new RoomVO();
				roomVO.setRoomId(roomId);
				roomVO.setSellMemId(sellMemId);
				roomVO.setRoomName(roomName);
				roomVO.setRoomPrice(roomPrice);
				roomVO.setRoomCapacity(roomCapacity);
				roomVO.setRoomOnTime(roomOnTime);
				roomVO.setRoomDes(roomDes);
				roomVO.setRoomCollect(roomCollect);
				roomVO.setRoomStatus(roomStatus);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("roomVO", roomVO); // 含有輸入格式錯誤的roomVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-sell-end/room/update_room_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				roomVO = roomSvc.updateRoom(roomId, sellMemId, roomName, roomPrice, roomCapacity, roomOnTime, roomDes, roomCollect, roomStatus);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("roomVO", roomVO); // 資料庫update成功後,正確的的roomVO物件,存入req
				String url = "/front-sell-end/room/listAllRoom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneRoom.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗: "+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-sell-end/room/update_room_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addRoom.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				String roomId = req.getParameter("roomId").trim();
//				String roomIdReg = "^ROOM\\d{3}$";
//				if (roomId == null || roomId.length() == 0) {
//					errorMsgs.add("房間編號: 請勿空白");
//				} else if(!roomId.matches(roomIdReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("roomId = " + roomId);
//					errorMsgs.add("房間編號格式為: 前綴ROOM+數字三碼 ex.ROOM999");
//	            }
				
				String sellMemId = req.getParameter("sellMemId").trim();
				String sellMemIdReg = "^SELL\\d{3}$";
				if (sellMemId == null || sellMemId.length() == 0) {
					errorMsgs.add("民宿會員ID: 請勿空白");
				} else if(!sellMemId.matches(sellMemIdReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("sellMemId = " + sellMemId);
					errorMsgs.add("民宿會員ID格式為: 前綴SELL+數字三碼 ex.SELL999");
	            }
				
				String roomName = req.getParameter("roomName").trim();
				if (roomName == null || roomName.trim().length() == 0) {
					errorMsgs.add("請填寫房間名稱");
				}
				
				Integer roomPrice = null;
				try {
					roomPrice = new Integer(req.getParameter("roomPrice").trim());
				} catch (NumberFormatException e) {
					roomPrice = 0;
					errorMsgs.add("房間價格請填入數字");					
				}
				
				Integer roomCapacity = null;
				try {
					roomCapacity = new Integer(req.getParameter("roomCapacity").trim());
				} catch (NumberFormatException e) {
					roomCapacity = 0;
					errorMsgs.add("房間容納人數請填入數字");					
				}				
				
//				java.sql.Timestamp roomOnTime = null;
//				try {
//					roomOnTime = java.sql.Timestamp.valueOf(req.getParameter("roomOnTime").trim());
//				} catch (IllegalArgumentException e) {
//					roomOnTime=new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("請輸入正確日期時間!");
//				}

				String roomDes = req.getParameter("roomDes").trim();
				
//				Integer roomCollect = null;
//				try {
//					roomCollect = new Integer(req.getParameter("roomCollect").trim());
//				} catch (NumberFormatException e) {
//					roomCollect = 0;
//					errorMsgs.add("按讚次數請填數字.");
//				}
				
				Integer roomStatus = null;
				String roomStatusStr = null;
				String roomStatusReg = "^[01]$";
				try {
					roomStatusStr = req.getParameter("roomStatus").trim();
					if(!roomStatusStr.matches(roomStatusReg)) { //以下練習正則(規)表示式(regular-expression)
						throw new NumberFormatException("房間狀態只能為0或1");
		            } else {
		            	roomStatus = new Integer(roomStatusStr);
		            }
				} catch (NumberFormatException e) {
					roomStatus = 0;
					errorMsgs.add("房間狀態只能為0或1");
				}

				// String sellMemId =req.getParameter("sellMemId").trim();
				RoomVO roomVO = new RoomVO();
//				roomVO.setRoomId(roomId);
				roomVO.setSellMemId(sellMemId);
				roomVO.setRoomName(roomName);
				roomVO.setRoomPrice(roomPrice);
				roomVO.setRoomCapacity(roomCapacity);
//				roomVO.setRoomOnTime(roomOnTime);
				roomVO.setRoomDes(roomDes);
//				roomVO.setRoomCollect(roomCollect);
				roomVO.setRoomStatus(roomStatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println(errorMsgs);
					req.setAttribute("roomVO", roomVO); // 含有輸入格式錯誤的roomVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-sell-end/room/addRoom.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				RoomService roomSvc = new RoomService();
				roomVO = roomSvc.addRoom(sellMemId, roomName, roomPrice, roomCapacity, roomDes, roomStatus);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-sell-end/roomphoto/addRoomPhoto.jsp";
				req.setAttribute("roomVO", roomVO);
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-sell-end/room/addRoom.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { // 來自listAllRoom.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String roomId = req.getParameter("roomId");
				
				/***************************2.開始刪除資料***************************************/
				RoomService roomSvc = new RoomService();
				roomSvc.deleteRoom(roomId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-sell-end/room/listAllRoom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-sell-end/room/listAllRoom.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
