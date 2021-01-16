package com.souvenir_type.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.souvenir_type.model.SouvenirTypeService;
import com.souvenir_type.model.SouvenirTypeVO;



public class SouvenirTypeServlet extends HttpServlet {
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
				String str = req.getParameter("sou_type_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入特產種類編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_type/select_soutype_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String sou_type_id = null;
				try {
					sou_type_id = str;
				} catch (Exception e) {
					errorMsgs.add("特產種類編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_type/select_soutype_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				SouvenirTypeService soutSvc = new SouvenirTypeService();
				SouvenirTypeVO soutVO = soutSvc.getOneSouType(sou_type_id);
				if (soutVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_type/select_soutype_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("soutVO", soutVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/souvenir_type/listOneSouType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_type/select_soutype_page.jsp");
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
				String sou_type_id = req.getParameter("sou_type_id");

				/*************************** 2.開始查詢資料 ****************************************/
				SouvenirTypeService soutSvc = new SouvenirTypeService();
				SouvenirTypeVO soutVO = soutSvc.getOneSouType(sou_type_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("soutVO", soutVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/souvenir_type/update_soutype_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_type/listAllSouType.jsp");
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
				String sou_type_id = req.getParameter("sou_type_id").trim();

				String sou_type_name = req.getParameter("sou_type_name").trim();


				
				
				SouvenirTypeVO soutVO = new SouvenirTypeVO();
				soutVO.setSou_type_id(sou_type_id);
				soutVO.setSou_type_name(sou_type_name);

				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("soutVO", soutVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_type/update_soutype_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
			
				/*************************** 2.開始修改資料 *****************************************/
				SouvenirTypeService soutSvc = new SouvenirTypeService();
				soutVO = soutSvc.updateSouType( sou_type_id, sou_type_name);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("soutVO", soutVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/souvenir_type/listAllSouType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_type/update_soutype_input.jsp");
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
				String sou_type_name = req.getParameter("sou_type_name").trim();


				
				
				SouvenirTypeVO soutVO = new SouvenirTypeVO();
				soutVO.setSou_type_name(sou_type_name);

				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("soutVO", soutVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_type/addSouType.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				SouvenirTypeService soutSvc = new SouvenirTypeService();
				soutVO = soutSvc.addSouType( sou_type_name);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/souvenir_type/listAllSouType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/souvenir_type/addSouType.jsp");
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
				String sou_type_id = req.getParameter("sou_type_id");

				/*************************** 2.開始刪除資料 ***************************************/
				SouvenirTypeService soutSvc = new SouvenirTypeService();
				soutSvc.deleteSouType(sou_type_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/souvenir_type/listAllSouType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView;
				failureView = req.getRequestDispatcher("/back-end/souvenir_type/listAllSouType.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
		
