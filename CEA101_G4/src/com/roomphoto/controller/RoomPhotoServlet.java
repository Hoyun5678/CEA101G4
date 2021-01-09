package com.roomphoto.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.room.model.RoomService;
import com.roomphoto.model.*;

@MultipartConfig
public class RoomPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String roomPhotoId = req.getParameter("roomPhotoId");
				String roomPhotoIdReg = "^ROOMPH\\d{3}$";
				if (roomPhotoId == null || roomPhotoId.trim().length() == 0) {
					errorMsgs.add("房間編號: 請勿空白");
				} else if(!roomPhotoId.trim().matches(roomPhotoIdReg)) {
					errorMsgs.add("房間照片編號格式為: 前綴ROOMPH+數字三碼 ex.ROOMPH999");
	            }

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-sell-end/roomphoto/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/
				RoomPhotoService roomPhotoSvc = new RoomPhotoService();
				RoomPhotoVO roomPhotoVO = roomPhotoSvc.getOneRoomPhoto(roomPhotoId);
				if (roomPhotoVO == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-sell-end/roomphoto/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("roomPhotoVO", roomPhotoVO);
				String url = "/front-sell-end/roomphoto/listOneRoomPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-sell-end/roomphoto/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String roomPhotoId = req.getParameter("roomPhotoId");
								
				/***************************2.開始查詢資料****************************************/
				RoomPhotoService roomPhotoSvc = new RoomPhotoService();
				RoomPhotoVO roomPhotoVO = roomPhotoSvc.getOneRoomPhoto(roomPhotoId);
				
				if (roomPhotoVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-sell-end/roomphoto/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("roomPhotoVO", roomPhotoVO);       // 資料庫取出的roomVO物件,存入req
				String url = "/front-sell-end/roomphoto/update_roomphoto_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_room_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-sell-end/roomphoto/listAllRoomPhoto.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_room_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String roomPhotoId = req.getParameter("roomPhotoId");
				String roomPhotoIdReg = "^ROOMPH\\d{3}$";
				if (roomPhotoId == null || roomPhotoId.trim().length() == 0) {
					errorMsgs.add("房間編號: 請勿空白");
				} else if(!roomPhotoId.trim().matches(roomPhotoIdReg)) {
					errorMsgs.add("房間照片編號格式為: 前綴ROOMPH+數字三碼 ex.ROOMPH999");
	            }
				
				String roomId = req.getParameter("roomId").trim();
				String roomPhotoContent = req.getParameter("roomPhotoContent").trim();
					
				Collection<Part> parts = req.getParts();
				byte[] roomPhoto = null;
				for (Part part: parts) {
					if(part.getContentType() != null && part.getContentType().indexOf("image") != -1) {
						roomPhoto = getPartByteArray(part.getInputStream());
						break;
					}
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-sell-end/roomphoto/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				RoomPhotoVO roomPhotoVO = new RoomPhotoVO();
				roomPhotoVO.setRoomPhotoId(roomPhotoId);
				roomPhotoVO.setRoomId(roomId);
				roomPhotoVO.setRoomPhoto(roomPhoto);
				roomPhotoVO.setRoomPhotoContent(roomPhotoContent);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("roomPhotoVO", roomPhotoVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-sell-end/roomphoto/listAllRoomPhoto.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				RoomPhotoService roomPhotoSvc = new RoomPhotoService();
				roomPhotoVO = roomPhotoSvc.updateRoomPhoto(roomPhotoId, roomId, roomPhoto, roomPhotoContent);
	
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("roomPhotoVO", roomPhotoVO); // 資料庫update成功後,正確的的roomVO物件,存入req
				String url = "/front-sell-end/roomphoto/listAllRoomPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneRoom.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-sell-end/roomphoto/listAllRoomPhoto.jsp");
				failureView.forward(req, res);
			}
		}
		

        if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);


			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				String roomPhotoId = req.getParameter("roomPhotoId");
//				String roomPhotoIdReg = "^ROOMPH\\d{3}$";
//				if (roomPhotoId == null || roomPhotoId.trim().length() == 0) {
//					errorMsgs.add("房間編號: 請勿空白");
//				} else if(!roomPhotoId.trim().matches(roomPhotoIdReg)) {
//					errorMsgs.add("房間照片編號格式為: 前綴ROOMPH+數字三碼 ex.ROOMPH999");
//	            }
				
				String roomId = req.getParameter("roomId").trim();
				String roomIdReg = "^ROOM\\d{3}$";
				if (roomId == null || roomId.length() == 0) {
					errorMsgs.add("房間編號: 請勿空白");
				} else if(!roomId.matches(roomIdReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("roomId = " + roomId);
					errorMsgs.add("房間編號格式為: 前綴ROOM+數字三碼 ex.ROOM999");
	            }
								
//				String roomPhotoContent = req.getParameter("roomPhotoContent").trim();
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-sell-end/roomphoto/addRoomPhoto.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Collection<Part> parts = req.getParts();
				List<byte[]> roomPhotoList = new ArrayList();
				for (Part part: parts) {
					if(part.getContentType() != null && part.getContentType().indexOf("image") != -1) {
						byte[] roomPhoto = getPartByteArray(part.getInputStream()); 
						roomPhotoList.add(roomPhoto);
					}
				}
	
				for (byte[] pic : roomPhotoList) {

					RoomPhotoVO roomPhotoVO = new RoomPhotoVO();
					roomPhotoVO.setRoomId(roomId);
					roomPhotoVO.setRoomPhoto(pic);
					roomPhotoVO.setRoomPhotoContent("");

					if (!errorMsgs.isEmpty()) {
						req.setAttribute("roomPhotoVO", roomPhotoVO);
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-sell-end/roomphoto/addRoomPhoto.jsp");
						failureView.forward(req, res);
						return;
					}
					/***************************2.開始新增資料***************************************/
					RoomPhotoService roomPhotoSvc = new RoomPhotoService();
					roomPhotoVO = roomPhotoSvc.addRoomPhoto(roomId, pic, "");
				
				}
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-sell-end/room/listAllRoom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);				

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-sell-end/roomphoto/addRoomPhoto.jsp");
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
		
		if ("getOnePhoto".equals(action)) {
			res.setContentType("image/jpeg");
			ServletOutputStream out = res.getOutputStream();
			String roomPhotoId = req.getParameter("roomPhotoId");
			try {
				RoomPhotoService roomPhotoServiceSvc = new RoomPhotoService();
				byte[] buffer = roomPhotoServiceSvc.getOneRoomPhoto(roomPhotoId).getRoomPhoto();
				out.write(buffer);
			} catch (Exception e) {
				System.out.println(roomPhotoId + "沒圖片");
			}finally {
				out.close();
			}
		}
	
	if ("getOnePhotoByRoomId".equals(action)) {
		res.setContentType("image/*");
		ServletOutputStream out = res.getOutputStream();
		String roomId = req.getParameter("roomId");
			RoomPhotoService roomPhotoServiceSvc = new RoomPhotoService();
			byte[] buffer = roomPhotoServiceSvc.getByRoomId(roomId).get(0).getRoomPhoto();
			out.write(buffer);
			System.out.println(roomId + "沒圖片");
			out.close();
		}
	}
	
	
	public static byte[] getPartByteArray(InputStream fis) throws IOException {
		
		// available代表資料流源頭大小
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	

	
	

}
