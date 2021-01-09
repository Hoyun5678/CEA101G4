package com.activity_photo.controller;

import java.io.IOException;

import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.eclipse.jdt.internal.compiler.env.IModule.IService;

import com.activity_photo.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ActivityPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");

		if ("getOneActPho".equals(action)) {
			res.setContentType("img/jpg");
			String act_photo_id = req.getParameter("act_photo_id").trim();
			ActivityPhotoService actphoSvc = new ActivityPhotoService();
			ActivityPhotoVO actphoVO = actphoSvc.getOneActPho(act_photo_id);
			byte[] actpho = actphoVO.getAct_photo();
			res.getOutputStream().write(actpho);
			res.getOutputStream().flush();
			res.getOutputStream().close();
			return;
		}
		
		if ("getListActPhoByActId".equals(action)) {
			res.setContentType("img/jpg");
			String act_id = req.getParameter("act_id").trim();
			ActivityPhotoService actphoSvc = new ActivityPhotoService();
			List<ActivityPhotoVO> list = actphoSvc.getActPhoByActId(act_id);
			if(list.size()!=0) {
				byte[] actpho = list.get(0).getAct_photo();
				res.getOutputStream().write(actpho);
				
			}else {
				InputStream in = getServletContext().getResourceAsStream("/image/nophoto/none2.jpg");
				byte[] b= new byte[in.available()];
				in.read(b);
				res.getOutputStream().write(b);;
				in.close();
			}
			res.getOutputStream().flush();
			res.getOutputStream().close();
			return;
		}


		if ("upDate_By_Act_Photo_id".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
//				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String act_photo_id = req.getParameter("act_photo_id").trim();

				Part part = req.getPart("upfile1");

				InputStream in = part.getInputStream();
				byte[] act_photo = new byte[in.available()];
				in.read(act_photo);
				in.close();

				ActivityPhotoVO actphoVO = new ActivityPhotoVO();
				actphoVO.setAct_photo_id(act_photo_id);
				actphoVO.setAct_photo(act_photo);
				/*************************** 2.開始修改資料 *****************************************/
				ActivityPhotoService actphSvc = new ActivityPhotoService();
				actphSvc.upDatePhotoByActid(actphoVO);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("actphoVO", actphoVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-sell-end/activity_photo/listAllActPho.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

//				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-sell-end/activity_photo/Update_Photo_input.jsp");
				failureView.forward(req, res);
			}
		}


		if ("upDate_By_Act_id".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			ActivityPhotoVO actphoVO=null;
			ActivityPhotoService actphSvc = new ActivityPhotoService();
			try {
//				接多張圖片========================================================================
				String act_id = req.getParameter("act_id").trim();
				Collection<Part> col = req.getParts();
				for (Part part : col) {

					if (part.getContentType() != null && part.getContentType().indexOf("image") >= 0) {

						InputStream in = part.getInputStream();
						byte[] act_photo = new byte[in.available()];
						in.read(act_photo);
						in.close();

						actphoVO = new ActivityPhotoVO();
						actphoVO.setAct_photo_content("");
						actphoVO.setAct_id(act_id);
						actphoVO.setAct_photo(act_photo);
						// *************************** 2.開始修改資料 *****************************************
						actphSvc.insert(actphoVO);
					}

				}
						List<ActivityPhotoVO>list=actphSvc.getActPhoByActId(act_id);
//
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				String url = "/front-sell-end/activity_photo/listAllActPho.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				req.setAttribute("act_id", act_id);
				req.setAttribute("list", list);
				successView.forward(req, res);

//				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/front-sell-end/activity_photo/listAllActPho.jsp");
				failureView.forward(req, res);
			}
		}

		if ("get_Photo_By_Act_photo_id".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String act_photo_id = req.getParameter("act_photo_id");
				if (act_photo_id == null || (act_photo_id).length() == 0) {
					errorMsgs.add("請輸入活動照片編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-sell-end/activity_photo/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				act_photo_id = act_photo_id.trim();
				ActivityPhotoService actphoSvc = new ActivityPhotoService();
				ActivityPhotoVO actphoVO = actphoSvc.getOneActPho(act_photo_id);
				if (actphoVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-sell-end/activity_photo/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("actphoVO", actphoVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-sell-end/activity_photo/listOneActPho.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-sell-end/activity_photo/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("get_Photo_By_Act_id".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String act_id = req.getParameter("act_id");
				if (act_id == null || (act_id.trim()).length() == 0) {
					errorMsgs.add("請輸入活動編號");
				}else {
					act_id = act_id.trim();
					
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ActivityPhotoService actphoSvc = new ActivityPhotoService();
				List<ActivityPhotoVO> list = actphoSvc.getActPhoByActId(act_id);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-sell-end/activity_product/listOneActivityProduct.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
				req.setAttribute("act_id", act_id);
				String url = "/front-sell-end/activity_photo/listAllActPho.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-sell-end/activity_product/listOneActivityProduct.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String act_photo_id = req.getParameter("act_photo_id").trim();

				/*************************** 2.開始查詢資料 ****************************************/
				ActivityPhotoService actphoSvc = new ActivityPhotoService();
				ActivityPhotoVO actphoVO = actphoSvc.getOneActPho(act_photo_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("actphoVO", actphoVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-sell-end/activity_photo/update_ActPho_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-sell-end/activity_photo/listAllActPho.jsp");
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
				String act_photo_id = req.getParameter("act_photo_id");
				String act_id=req.getParameter("act_id");
				/*************************** 2.開始刪除資料 ***************************************/
				ActivityPhotoService actphoSvc = new ActivityPhotoService();
				actphoSvc.deleteActPho(act_photo_id);
				List<ActivityPhotoVO> list=actphoSvc.getActPhoByActId(act_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front-sell-end/activity_photo/listAllActPho.jsp";
				req.setAttribute("list", list);
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView;
				failureView = req.getRequestDispatcher("/front-sell-end/activity_photo/listAllActPho.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//			res.setContentType("img/jpg");

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				ActivityPhotoVO actphoVO=null;
				String act_photo_id = req.getParameter("act_photo_id").trim();
				String act_id = req.getParameter("act_id").trim();
				String act_photo_content="";
				byte[] act_photo=null;
				ActivityPhotoService actphoSvc = new ActivityPhotoService();
				Part part = req.getPart("upfile1");
				InputStream in = part.getInputStream();
				if (in.available() == 0) {
					
					actphoVO = actphoSvc.getOneActPho(act_photo_id);
					act_photo = actphoVO.getAct_photo();
					
				} else {
					actphoVO = actphoSvc.getOneActPho(act_photo_id);
					act_photo = new byte[in.available()];
					in.read(act_photo);
					in.close();
				}

				act_photo_content = req.getParameter("act_photo_content");
				if(!act_photo_content.isEmpty()) {
					act_photo_content=act_photo_content.trim();
				}
				actphoVO.setAct_id(act_id);
				actphoVO.setAct_photo_id(act_photo_id);
				actphoVO.setAct_photo(act_photo);
				actphoVO.setAct_photo_content(act_photo_content);

				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actphoVO", actphoVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-sell-end/activity_photo/update_ActPho_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				actphoSvc = new ActivityPhotoService();
				actphoSvc.update(actphoVO);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("actphoVO", actphoVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-sell-end/activity_photo/listAllActPho.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-sell-end/activity_photo/update_ActPho_input.jsp");
				failureView.forward(req, res);
			}
		}
	}
}