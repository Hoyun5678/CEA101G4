package com.souvenir_photo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.souvenir_photo.model.SouvenirPhotoService;
import com.souvenir_photo.model.SouvenirPhotoVO;

@MultipartConfig
public class SouvenirPhotoServlet extends HttpServlet {
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
				String str = req.getParameter("sou_photo_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入特產照片編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_photo/select_souphoto_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String sou_photo_id = null;
				try {
					sou_photo_id = str;
				} catch (Exception e) {
					errorMsgs.add("特產照片編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_photo/select_souphoto_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				SouvenirPhotoService souphSvc = new SouvenirPhotoService();
				SouvenirPhotoVO souphVO = souphSvc.getOneSouPhoto(sou_photo_id);
				if (souphVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_photo/select_souphoto_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("souphVO", souphVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/souvenir_photo/listOneSouPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_photo/select_souphoto_page.jsp");
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
				String sou_photo_id = req.getParameter("sou_photo_id");

				/*************************** 2.開始查詢資料 ****************************************/
				SouvenirPhotoService souphSvc = new SouvenirPhotoService();
				SouvenirPhotoVO souphVO = souphSvc.getOneSouPhoto(sou_photo_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("souphVO", souphVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/souvenir_photo/update_souphoto_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_photo/listAllSouPhoto.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("getSouPhoto".equals(action)) {
			String sou_photo_id = req.getParameter("sou_photo_id");
			res.setContentType("images/*");
			SouvenirPhotoService souphSvc = new SouvenirPhotoService();
			SouvenirPhotoVO souphVO = souphSvc.getOneSouPhoto(sou_photo_id);
			InputStream  in= null;
			ServletOutputStream out = null ;
			byte[] sou_photo=souphVO.getSou_photo();
			if (!(sou_photo == null)) {
				sou_photo=souphVO.getSou_photo();
				res.getOutputStream().write(sou_photo);
			}
			if (sou_photo == null || sou_photo.length <=1) {
				sou_photo=souphVO.getSou_photo();
				in = getServletContext().getResourceAsStream("/back-end/souvenir_photo/images/No_image.png");
				byte[] buf = new byte[in.available()];
				in.read(buf);
				out = res.getOutputStream();
				out.write(buf);
				out.close();
				in.close();		
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			SouvenirPhotoVO souphVO = new SouvenirPhotoVO();


//			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String sou_photo_id = req.getParameter("sou_photo_id").trim();

				String sou_id = req.getParameter("sou_id").trim();
				
				byte[] sou_photo = null;
				Part part = req.getPart("sou_photo");
				InputStream in = part.getInputStream();
				
				if (in.available() == 0) {
					SouvenirPhotoService souphSvc = new SouvenirPhotoService();
					souphVO = souphSvc.getOneSouPhoto(sou_photo_id);
					sou_photo = souphVO.getSou_photo();
				
				}else{
					sou_photo=new byte[in.available()];
					in.read(sou_photo);
					in.close();
				}

				String sou_photo_content = req.getParameter("sou_photo_content");

				String sou_photo_content_Reg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,300}$";
				if (sou_photo_content != null || sou_photo_content.trim().length() != 0) { // 以下練習正則(規)表示式(regular-expression)
					 if(!sou_photo_content.trim().matches(sou_photo_content_Reg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("照片敘述只能是中、英文字母、數字和_ , 且長度必需在2到300之間");
					 }
				}
				
				
				souphVO.setSou_photo_id(sou_photo_id);
				souphVO.setSou_id(sou_id);
				souphVO.setSou_photo(sou_photo);
				souphVO.setSou_photo_content(sou_photo_content);
			

				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("souphVO", souphVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_photo/update_souphoto_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
			
				/*************************** 2.開始修改資料 *****************************************/
				SouvenirPhotoService souphSvc = new SouvenirPhotoService();
				souphVO = souphSvc.updateSouPhoto(sou_photo_id, sou_id, sou_photo, sou_photo_content);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("souphVO", souphVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/souvenir_photo/listOneSouPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_photo/update_souphoto_input.jsp");
//				failureView.forward(req, res);
//			}
		}
		

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String sou_id = req.getParameter("sou_id").trim();
				
				byte[] sou_photo = null;
				Part part = req.getPart("sou_photo");
				InputStream in = part.getInputStream();
				sou_photo = new byte[in.available()];
				
				in.read(sou_photo);
				in.close();


				
				SouvenirPhotoVO souphVO = new SouvenirPhotoVO();
				souphVO.setSou_id(sou_id);
				souphVO.setSou_photo(sou_photo);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("souphVO", souphVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_photo/addSouPhoto.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				SouvenirPhotoService souphSvc = new SouvenirPhotoService();
				souphVO = souphSvc.addSouPhoto( sou_id, sou_photo);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/souvenir_photo/listAllSouPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_photo/addSouPhoto.jsp");
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
				String sou_photo_id = req.getParameter("sou_photo_id");

				/*************************** 2.開始刪除資料 ***************************************/
				SouvenirPhotoService souphSvc = new SouvenirPhotoService();
				souphSvc.deleteSouPhoto(sou_photo_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/souvenir_photo/listAllSouPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView;
				failureView = req.getRequestDispatcher("/back-end/souvenir_photo/listAllSouPhoto.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
		
