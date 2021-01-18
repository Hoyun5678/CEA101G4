package com.foodspot.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.foodspot.model.FoodSpotService;
import com.foodspot.model.FoodSpotVO;

@MultipartConfig
public class FoodSpotServlet extends HttpServlet {
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

			
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("fas_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入美食與景點編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-sell-end/foodspot/listAllFoodSpot.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String fas_id = null;
				try {
					fas_id = str;
				} catch (Exception e) {
					errorMsgs.add("美食與景點編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-sell-end/foodspot/listAllFoodSpot.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				FoodSpotService fsSvc = new FoodSpotService();
				FoodSpotVO fsVO = fsSvc.getOneFoodSpot(fas_id);
				if (fsVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-sell-end/foodspot/listAllFoodSpot.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//				req.setAttribute("fsVO", fsVO); // 資料庫取出的rodVO物件,存入req
//				System.out.println(fsVO);
//							String url = "/front-sell-end/foodspot/listOneFoodSpot.jsp";
//							RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneRoomOrderDetail.jsp
//							successView.forward(req, res);
//
//							/***************************其他可能的錯誤處理*************************************/
//						} catch (Exception e) {
//							errorMsgs.add("無法取得資料:" + e.getMessage());
//							RequestDispatcher failureView = req
//									.getRequestDispatcher("/front-sell-end/roomorderdetail/listAllRoomOrderDetail.jsp");
//							failureView.forward(req, res);
//						}
//					}
//					
				// 資料庫取出的fsVO物件,存入req
				FoodSpotVO fsVO1 = (FoodSpotVO) req.getAttribute("fsVO");
				
			    //更改成功轉交AJAX
				JSONObject obj = new JSONObject();
				try {
					obj.put("fas_id", fsVO.getFas_id());
					obj.put("sell_mem_id", fsVO.getSell_mem_id());
					obj.put("fas_spot_name", fsVO.getFas_spot_name());
					obj.put("fas_add", fsVO.getFas_add());
					obj.put("fas_des", fsVO.getFas_des());
					
					obj.put("fas_latitude", fsVO.getFas_latitude());
					obj.put("fas_longitud", fsVO.getFas_longitud());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.print(obj);
				out.flush();
				out.close();
				return;
				
		}

				

		if ("getOne_For_Update".equals(action)) { // 來自listAllFoodSpot.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String fas_id = req.getParameter("fas_id");

				/*************************** 2.開始查詢資料 ****************************************/
				FoodSpotService fsSvc = new FoodSpotService();
				FoodSpotVO fsVO = fsSvc.getOneFoodSpot(fas_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("fsVO", fsVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-sell-end/foodspot/update_foodspot_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_foodspot_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-sell-end/foodspot/listAllFoodSpot.jsp");
				failureView.forward(req, res);
			}
		}
		
		
//		if("getFSPhoto".equals(action)) {
//			String fas_id = req.getParameter("fas_id");
//			res.setContentType("images/*");
//			FoodSpotService fsSvc = new FoodSpotService();
//			FoodSpotVO fsVO = fsSvc.getOneFoodSpot(fas_id);
//			
//			byte[] fas_photo=fsVO.getFas_photo();
//			res.getOutputStream().write(fas_photo);		
//		}
		
		
		
		if("getFSPhoto".equals(action)) {
			String fas_id = req.getParameter("fas_id");
			res.setContentType("images/*");
			FoodSpotService fsSvc = new FoodSpotService();
			FoodSpotVO fsVO = fsSvc.getOneFoodSpot(fas_id);
			InputStream  in= null;
			ServletOutputStream out = null ;
			byte[] fas_photo=fsVO.getFas_photo();
			if (!(fas_photo == null)) {
				fas_photo=fsVO.getFas_photo();
				res.getOutputStream().write(fas_photo);
			}
			if (fas_photo == null || fas_photo.length <=1) {
				fas_photo=fsVO.getFas_photo();
				in = getServletContext().getResourceAsStream("/image/No_image.png");
				byte[] buf = new byte[in.available()];
				in.read(buf);
				out = res.getOutputStream();
				out.write(buf);
				out.close();
				in.close();		
			}
		}
			
		
			
		
		if ("update".equals(action)) { // 來自update_foodspot_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			FoodSpotVO fsVO = new FoodSpotVO();

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String fas_id = req.getParameter("fas_id").trim();
				String sell_mem_id = req.getParameter("sell_mem_id").trim();
				String sell_mem_id_Reg = "^SELL\\d{3}$";
				if (sell_mem_id == null || sell_mem_id.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				} else if (!sell_mem_id.trim().matches(sell_mem_id_Reg)) {
					errorMsgs.add("民宿會員帳號: SELL開頭接三個可以是0-9的數字");
				}

				String fas_spot_name = req.getParameter("fas_spot_name").trim();
				String fs_name_Reg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,}$";
				if (fas_spot_name == null || fas_spot_name.trim().length() == 0) {
					errorMsgs.add("美食與景點名稱請勿空白");
				} else if (!fas_spot_name.trim().matches(fs_name_Reg)) {
					errorMsgs.add("美食與景點名稱: 只能是中、英文字母、數字和_ , 且至少需輸入一個字以上");
				}
				
				String fas_add = req.getParameter("fas_add");
				String fas_add_Reg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,300}$";
				
				if (fas_add == null || fas_add.trim().length() == 0) {
					errorMsgs.add("美食與景點地址: 請勿空白");
				} else if (!fas_add.trim().matches(fas_add_Reg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("美食與景點地址: 只能是中、英文字母、數字和_ , 且長度必需在2到300之間");
				}
				
				String fas_des = req.getParameter("fas_des");

				
				
				byte[] fas_photo = null;
				Part part = req.getPart("fas_photo");
				InputStream in = part.getInputStream();
				
				if (in.available() == 0) {
					FoodSpotService fsSvc = new FoodSpotService();
					fsVO = fsSvc.getOneFoodSpot(fas_id);
					fas_photo = fsVO.getFas_photo();
				
				}else{
					fas_photo=new byte[in.available()];
					in.read(fas_photo);
					in.close();
				}
				
		
				Double fas_latitude = null;
					try {
						fas_latitude = new Double(req.getParameter("fas_latitude").trim());
					} catch (NumberFormatException e) {
						fas_latitude = 0.0;
						errorMsgs.add("美食景點經度請填數字.");
				}
					
				Double fas_longitud = null;
					try {
						fas_longitud = new Double(req.getParameter("fas_longitud").trim());
					} catch (NumberFormatException e) {
						fas_longitud = 0.0;
						errorMsgs.add("美食景點緯度請填數字.");
				
					}
				fsVO.setFas_id(fas_id);
				fsVO.setSell_mem_id(sell_mem_id);
				fsVO.setFas_spot_name(fas_spot_name);
				fsVO.setFas_add(fas_add);
				fsVO.setFas_des(fas_des);
				fsVO.setFas_photo(fas_photo);
				fsVO.setFas_latitude(fas_latitude);
				fsVO.setFas_latitude(fas_latitude);
			
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("fsVO", fsVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-sell-end/foodspot/update_foodspot_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				FoodSpotService fsSvc = new FoodSpotService();
				fsVO = fsSvc.updateFoodSpot(fas_id, sell_mem_id, fas_spot_name, fas_add, fas_des, fas_photo, fas_latitude,fas_longitud);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("fsVO", fsVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-sell-end/foodspot/listAllFoodSpot.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/front-sell-end/foodspot/update_foodspot_input.jsp");
				failureView.forward(req, res);

			}
		}

	if("insert".equals(action))

	{ // 來自addFoodSpot.jsp的請求

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String sell_mem_id = req.getParameter("sell_mem_id").trim();
			System.out.println(sell_mem_id);
			if (sell_mem_id == null || sell_mem_id.trim().length() == 0) {
				errorMsgs.add("帳號請勿空白");
			} 

			String fas_spot_name = req.getParameter("fas_spot_name").trim();
			String fs_name_Reg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,}$";
			if (fas_spot_name == null || fas_spot_name.trim().length() == 0) {
				errorMsgs.add("美食與景點名稱請勿空白");
			} else if (!fas_spot_name.matches(fs_name_Reg)) {
				errorMsgs.add("美食與景點名稱: 只能是中、英文字母、數字和_ , 且至少要輸入一個字以上");
			}

			String fas_add = req.getParameter("fas_add");
			String fas_add_Reg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,300}$";
			if (fas_add == null || fas_add.trim().length() == 0) {
				errorMsgs.add("美食與景點地址: 請勿空白");
			} else if (!fas_add.trim().matches(fas_add_Reg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("美食與景點地址: 只能是中、英文字母、數字和_ , 且長度必需在2到300之間");
			}

			String fas_des = req.getParameter("fas_des");

			
			byte[] fas_photo = null;
			Part part = req.getPart("fas_photo");
			InputStream in = part.getInputStream();
			fas_photo = new byte[in.available()];
			
				in.read(fas_photo);
				in.close();
			
				
			
			Double fas_latitude = null;
			try {
				fas_latitude = new Double(req.getParameter("fas_latitude").trim());
			} catch (NumberFormatException e) {
				fas_latitude = 0.0;
				errorMsgs.add("美食景點經度請填數字.");
			}

			Double fas_longitud = null;
			try {
				fas_longitud = new Double(req.getParameter("fas_longitud").trim());
			} catch (NumberFormatException e) {
				fas_longitud = 0.0;
				errorMsgs.add("美食景點緯度請填數字.");

			}

			FoodSpotVO fsVO = new FoodSpotVO();
			fsVO.setSell_mem_id(sell_mem_id);
			fsVO.setFas_spot_name(fas_spot_name);
			fsVO.setFas_add(fas_add);
			fsVO.setFas_des(fas_des);
			fsVO.setFas_photo(fas_photo);
			fsVO.setFas_latitude(fas_latitude);
			fsVO.setFas_longitud(fas_longitud);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("fsVO", fsVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/front-sell-end/foodspot/addFoodSpot.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
		FoodSpotService fsSvc = new FoodSpotService();
			fsVO = fsSvc.addFoodSpot(sell_mem_id, fas_spot_name, fas_add, fas_des, fas_photo, fas_latitude,
					fas_longitud);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/front-sell-end/foodspot/listAllFoodSpot.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
		} catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front-sell-end/foodspot/addFoodSpot.jsp");
			failureView.forward(req, res);
		}
	}

	if("delete".equals(action))
	{ // 來自listAllEmp.jsp

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/*************************** 1.接收請求參數 ***************************************/
			String fas_id = req.getParameter("fas_id");

			/*************************** 2.開始刪除資料 ***************************************/
			FoodSpotService fsSvc = new FoodSpotService();
			fsSvc.deleteFoodSpot(fas_id);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/front-sell-end/foodspot/listAllFoodSpot.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
		} catch (Exception e) {
			errorMsgs.add("刪除資料失敗:" + e.getMessage());
			RequestDispatcher failureView;
			failureView = req.getRequestDispatcher("/front-sell-end/foodspot/listAllFoodSpot.jsp");
			failureView.forward(req, res);
		}
	}
}
}
