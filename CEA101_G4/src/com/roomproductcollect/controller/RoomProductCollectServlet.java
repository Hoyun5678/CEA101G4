package com.roomproductcollect.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.roomproductcollect.model.RoomProductCollectService;
import com.roomproductcollect.model.RoomProductCollectVO;

public class RoomProductCollectServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
	
	req.setCharacterEncoding("UTF-8");
	PrintWriter out = res.getWriter();
	String action = req.getParameter("action");
	
	if ("showCollect".equals(action)) { // 來自select_page.jsp的請求

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String mem_id = req.getParameter("mem_id");
			
			/***************************2.開始查詢資料*****************************************/
			RoomProductCollectService rpcSvc = new RoomProductCollectService();
			List<String> rpcListMem = rpcSvc.getOneByMemId(mem_id);
System.out.println(rpcListMem);
System.out.println("111");
			if (rpcListMem == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/roomproductcollect/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			req.setAttribute("rpcListMem", rpcListMem); // 資料庫取出的rpcVO物件,存入req
			String url = "/front-mem-end/roomproductcollect/listOneRoomProductCollect.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneRoomProductCollect.jsp
System.out.println(JSON.toJSONString(rpcListMem)+"我是getmemid");
			successView.forward(req, res);


			/***************************其他可能的錯誤處理*************************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			System.out.println(e);
			RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/roomproductcollect/select_page.jsp");
			failureView.forward(req, res);
		}
	}
	if ("getOne_For_Display2".equals(action)) { // 來自select_page.jsp的請求

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String room_id = req.getParameter("room_id");


			/***************************2.開始查詢資料*****************************************/
			RoomProductCollectService rpcSvc = new RoomProductCollectService();
			List<String> rpcListRoom = rpcSvc.getOneByRoomId(room_id);
System.out.println(rpcListRoom);
System.out.println("222");
			if (rpcListRoom == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/roomproductcollect/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			//你看你在這邊SET
			req.setAttribute("rpcListRoom",rpcListRoom);// 資料庫取出的rpcVO物件,存入req
			String url = "/front-mem-end/roomproductcollect/listOneRoomProductCollect2.jsp";
System.out.println(JSON.toJSONString(rpcListRoom)+"我是getroomid");
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneRoomProductCollect.jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-mem-end/roomproductcollect/select_page.jsp");
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
			String mem_id = req.getParameter("mem_id");
			
			/***************************2.開始查詢資料****************************************/
			RoomProductCollectService rpcSvc = new RoomProductCollectService();
			List<RoomProductCollectVO> rpcVO = rpcSvc.getAll();
							
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("rpcVO", rpcVO);         // 資料庫取出的rpcVO物件,存入req
			String url = "/front-mem-end/roomproductcollect/update_roomproductcollect_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_roomproductcollect_input.jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/roomproductcollect/listAllRoomProductCollect.jsp");
			failureView.forward(req, res);
		}
	}

	if("addCollect".equals(action)){
		int collect = 0;
		String mem_id = req.getParameter("mem_id");
		String room_id = req.getParameter("room_id");
		JSONObject json = new JSONObject();
		RoomProductCollectService rpcSvc = new RoomProductCollectService();
		try {
			collect = rpcSvc.addCollect(mem_id, room_id);
			json.put("collect", collect);
		} catch (JSONException e) {
			collect = rpcSvc.getCountCollect(room_id);
			out.print(collect);
		}
		out.print(collect);
		out.flush();
		out.close();
		return;
	}
	
	if("deleteCollect".equals(action)){
		int collect = 0;
		String mem_id = req.getParameter("mem_id");
		String room_id = req.getParameter("room_id");
		JSONObject json = new JSONObject();
		RoomProductCollectService rpcSvc = new RoomProductCollectService();
		try {
			collect = rpcSvc.deleteCollect(mem_id, room_id);
			json.put("collect", collect);
		} catch (JSONException e) {
			collect = rpcSvc.getCountCollect(room_id);
			out.print(collect);
		}
		out.print(collect);
		out.flush();
		out.close();
		return;
	}
}
}
