package com.souvenir_product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.souvenir_photo.model.SouvenirPhotoService;
import com.souvenir_photo.model.SouvenirPhotoVO;
import com.souvenir_product.model.SouvenirProductService;
import com.souvenir_product.model.SouvenirProductVO;

@MultipartConfig
public class SouvenirProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// 查1
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("sou_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入特產編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String sou_id = null;
				try {
					sou_id = str;
				} catch (Exception e) {
					errorMsgs.add("特產編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				SouvenirProductService souSvc = new SouvenirProductService();
				SouvenirProductVO soupVO = souSvc.getOneSou(sou_id);
				if (soupVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("soupVO", soupVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/souvenir/listOneSou.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("checkSouvenirDetail".equals(action)) { // 來自front-mem-end/souvenir.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String sou_id = req.getParameter("sou_id");
				if (sou_id == null || (sou_id.trim()).length() == 0) {
					errorMsgs.add("請輸入特產編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
			
				/*************************** 2.開始查詢資料 *****************************************/
				SouvenirProductService souSvc = new SouvenirProductService();
				SouvenirProductVO soupVO = souSvc.getOneSou(sou_id);
				if (soupVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/souvenir/souvenir.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("soupVO", soupVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-mem-end/souvenir/souvenir_detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/souvenir/souvenir.jsp");
//				failureView.forward(req, res);
//			}
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String sou_id = req.getParameter("sou_id");

				/*************************** 2.開始查詢資料 ****************************************/
				SouvenirProductService souSvc = new SouvenirProductService();
				SouvenirProductVO soupVO = souSvc.getOneSou(sou_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("soupVO", soupVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/souvenir/update_sou_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir/listAllSou.jsp");
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
				String sou_id = req.getParameter("sou_id").trim();

				String sou_type_id = req.getParameter("sou_type_id").trim();

				String sou_name = req.getParameter("sou_name").trim();
				String sou_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
				if (sou_name == null || sou_name.trim().length() == 0) {
					errorMsgs.add("特產名稱: 請勿空白");
				} else if (!sou_name.trim().matches(sou_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("特產名稱: 只能是中、英文字母、數字和_ , 且長度必需在1到10之間");
				}

				Integer sou_price = null;
				try {
					sou_price = new Integer(req.getParameter("sou_price").trim());
					if(sou_price < 0) {
						errorMsgs.add("特產價錢要是正整數");
					}
				} catch (NumberFormatException e) {
					sou_price = 0;
					errorMsgs.add("價錢請填整數.");
				}
				
				java.sql.Timestamp sou_on_date = null;
				try {
					String ondate = req.getParameter("sou_on_date").trim();
					sou_on_date = java.sql.Timestamp.valueOf(ondate);
				} catch (IllegalArgumentException e) {
					sou_on_date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請加入時間");
				}
				
				java.sql.Timestamp sou_off_date = null;
				try {
					String offdate = req.getParameter("sou_off_date").trim();
					sou_off_date = java.sql.Timestamp.valueOf(offdate);
				} catch (IllegalArgumentException e) {
					sou_off_date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請加入時間");
				}

				
				Integer sou_like_count = null;
				try {
					sou_like_count = new Integer(req.getParameter("sou_like_count").trim());
					if(sou_like_count < 0) {
						errorMsgs.add("按讚次數要是正整數");
					}
				} catch (NumberFormatException e) {
					sou_like_count = 0;
					errorMsgs.add("按讚次數請填數字.");
				}
				
				String sou_des = req.getParameter("sou_des").trim();

				Integer sou_status = null;
				
				try {
					sou_status = new Integer(req.getParameter("sou_status").trim());
					if(sou_status < 0) {
						errorMsgs.add("狀態碼要是正整數");
					}
				} catch (NumberFormatException e) {
					sou_status = 0;
					errorMsgs.add("狀態請填整數.");
				}


				
				
				SouvenirProductVO soupVO = new SouvenirProductVO();
				soupVO.setSou_id(sou_id);
				soupVO.setSou_type_id(sou_type_id);
				soupVO.setSou_name(sou_name);
				soupVO.setSou_price(sou_price);
				soupVO.setSou_on_date(sou_on_date);
				soupVO.setSou_off_date(sou_off_date);
				soupVO.setSou_like_count(sou_like_count);
				soupVO.setSou_des(sou_des);
				soupVO.setSou_status(sou_status);

				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("soupVO", soupVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir/update_sou_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
			
				/*************************** 2.開始修改資料 *****************************************/
				SouvenirProductService souSvc = new SouvenirProductService();
				soupVO = souSvc.updateSou(sou_id, sou_type_id, sou_name, sou_price, sou_on_date, sou_off_date, sou_like_count,
						sou_des, sou_status);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("soupVO", soupVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/souvenir/listOneSou.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir/update_sou_input.jsp");
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
				String sou_type_id = req.getParameter("sou_type_id").trim();

				String sou_name = req.getParameter("sou_name").trim();
				String sou_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
				if (sou_name == null || sou_name.trim().length() == 0) {
					errorMsgs.add("特產名稱: 請勿空白");
				} else if (!sou_name.trim().matches(sou_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("特產名稱: 只能是中、英文字母、數字和_ , 且長度必需在1到10之間");
				}

				Integer sou_price = null;
				try {
					sou_price = new Integer(req.getParameter("sou_price").trim());
					if(sou_price < 0) {
						errorMsgs.add("特產價錢要是正整數");
					}
				} catch (NumberFormatException e) {
					sou_price = 0;
					errorMsgs.add("價錢請填整數.");
				}

				
				java.sql.Timestamp sou_on_date = null;
				try {
					String ondate = req.getParameter("sou_on_date").trim();
					sou_on_date = java.sql.Timestamp.valueOf(ondate);
				} catch (IllegalArgumentException e) {
					sou_on_date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請加入時間");
				}
				
				java.sql.Timestamp sou_off_date = null;
				try {
					String offdate = req.getParameter("sou_off_date").trim();
					sou_off_date = java.sql.Timestamp.valueOf(offdate);
				} catch (IllegalArgumentException e) {
					sou_off_date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請加入時間");
				}

				
				Integer sou_like_count = null;
				try {
					sou_like_count = new Integer(req.getParameter("sou_like_count").trim());
					if(sou_like_count < 0) {
						errorMsgs.add("按讚次數要是正整數");
					}
				} catch (NumberFormatException e) {
					sou_like_count = 0;
					errorMsgs.add("按讚次數請填數字.");
				}
				
				String sou_des = req.getParameter("sou_des").trim();

				Integer sou_status = null;
			
				try {
					sou_status = new Integer(req.getParameter("sou_status").trim());
					if(sou_status < 0) {
						errorMsgs.add("狀態碼要是正整數");
					}
				} catch (NumberFormatException e) {
					sou_status = 0;
					errorMsgs.add("狀態請填整數.");
				}

				
				SouvenirProductVO soupVO = new SouvenirProductVO();
				soupVO.setSou_type_id(sou_type_id);
				soupVO.setSou_name(sou_name);
				soupVO.setSou_price(sou_price);
				soupVO.setSou_on_date(sou_on_date);
				soupVO.setSou_off_date(sou_off_date);
				soupVO.setSou_like_count(sou_like_count);
				soupVO.setSou_des(sou_des);
				soupVO.setSou_status(sou_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("soupVO", soupVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir/addSou.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				SouvenirProductService souSvc = new SouvenirProductService();
				soupVO = souSvc.addSou(sou_type_id, sou_name, sou_price, sou_on_date, sou_off_date, sou_like_count,
						sou_des, sou_status);

				
				
//				///////////////////////////////////////////////////////
				
				byte[] sou_photo = null;
				Part part = req.getPart("sou_photo");
				InputStream in = part.getInputStream();
				sou_photo = new byte[in.available()];
				
				in.read(sou_photo);
				in.close();
				



				SouvenirPhotoVO souphVO = new SouvenirPhotoVO();
				souphVO.setSou_id(soupVO.getSou_id());

				souphVO.setSou_photo(sou_photo);
				// Send the use back to the form, if there were errors
				
				SouvenirPhotoService souphSvc = new SouvenirPhotoService();
				souphVO = souphSvc.addSouPhoto(soupVO.getSou_id(),sou_photo);
				
				
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/souvenir/listAllSou.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir/addSou.jsp");
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
				String sou_id = req.getParameter("sou_id");

				/*************************** 2.開始刪除資料 ***************************************/
				SouvenirProductService souSvc = new SouvenirProductService();
				souSvc.deleteSou(sou_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/souvenir/listAllSou.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView;
				failureView = req.getRequestDispatcher("/back-end/souvenir/listAllSou.jsp");
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
				String sou_id = req.getParameter("sou_id");

				/*************************** 2.開始刪除資料 ***************************************/
				SouvenirProductService souSvc = new SouvenirProductService();
				souSvc.deleteSou(sou_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/souvenir/listAllSou.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView;
				failureView = req.getRequestDispatcher("/back-end/souvenir/listAllSou.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
		
