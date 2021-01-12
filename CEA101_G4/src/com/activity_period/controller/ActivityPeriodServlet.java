
package com.activity_period.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.activity_period.model.*;
import com.room.model.RoomService;
import com.room.model.RoomVO;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Activity_Period;

public class ActivityPeriodServlet extends HttpServlet {
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

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String act_period_id = req.getParameter("act_period_id");
				if (act_period_id == null || (act_period_id.trim()).length() == 0) {
					errorMsgs.add("請輸入活動週期編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-sell-end/activity_period/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ActivityPeriodService actperSvc = new ActivityPeriodService();
				ActivityPeriodVO actperVO = actperSvc.getOneActPeriod(act_period_id);
				if (actperVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-sell-end/activity_period/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("actperVO", actperVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-sell-end/activity_period/listOneActivityPeriod.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-sell-end/activity_period/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Insert".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String act_id = req.getParameter("act_id");

				/*************************** 2.開始查詢資料 ****************************************/
				ActivityPeriodVO actperVO = new ActivityPeriodVO();
				actperVO.setAct_id(act_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("actperVO", actperVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-sell-end/activity_period/addActivityPeriod.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-sell-end/activity_product/listOneActivityPorduct.jsp");
				failureView.forward(req, res);
			}
		}

	
		 if("getActPeriodByKeyDate".equals(action)) { // 來自listAllEmp.jsp的請求
			
						List<String> errorMsgs = new LinkedList<String>();
						// Store this set in the request scope, in case we need to
						// send the ErrorPage view.
						req.setAttribute("errorMsgs", errorMsgs);
						ActivityPeriodService actperSvc=new ActivityPeriodService();
						List<ActivityPeriodVO>datefilter1List=null;
						Map<String, String[]> comQueMap=new TreeMap<String,String[]>();
			
						try {
							/*************************** 1.接收請求參數 ****************************************/
							String key_word=req.getParameter("key_word").trim();
							if(key_word.isEmpty()) {
								System.out.println("使用者未輸入key_word");
							}
							
							String checkString = "^[(\u4e00-\u9fa5)]{1,}$";
							String checkNum="^[(0-9)]{1,}$";
							
							if(key_word.matches(checkNum)) {
								comQueMap.put("price", new String[]{key_word});
							}else if(key_word.matches(checkString)) {
								comQueMap.put("word", new String[]{key_word});
							}
							
							
							String datefilter1 = req.getParameter("datefilter1");
							System.out.println(datefilter1);
							if(datefilter1 == null || datefilter1.trim().length() < 1) {
								System.out.println("使用者未輸入時間 送他全部");
							} else {
								comQueMap.put("datefilter1",new String[]{datefilter1});
								
							}
							
							List<ActivityPeriodVO>  queryFinalList=actperSvc.getAll(comQueMap);
							
							
							
							
							
							
//							req.setAttribute(, ); // 資料庫取出的empVO物件,存入req
							String url = "/front-mem-end/activity_period/listActivityPeriod.jsp";
							req.setAttribute("queryFinalList", queryFinalList);
							RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
							successView.forward(req, res);
			
							/*************************** 其他可能的錯誤處理 **********************************/
						} catch (Exception e) {
							errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
							RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/front-index.jsp");
							failureView.forward(req, res);
						}
					}
		
//		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數 ****************************************/
//				String mem_id = req.getParameter("mem_id");
//
//				/*************************** 2.開始查詢資料 ****************************************/
//				MemberService memSvc = new MemberService();
//				MemberVO memVO = memSvc.getOneMem(mem_id);
//
//				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//				req.setAttribute("memVO", memVO); // 資料庫取出的empVO物件,存入req
//				String url = "/front-mem-end/mem/update_mem_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem/listAllMem.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
//		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//				String mem_id = req.getParameter("mem_id").trim();
//
//				String mem_account = req.getParameter("mem_account").trim();
//				String maccountReg = "^[(a-zA-Z0-9)]{2,100}$";
//				if (mem_account == null || mem_account.trim().length() == 0) {
//					errorMsgs.add("帳號請勿空白");
//				} else if (!mem_account.trim().matches(maccountReg)) {
//					errorMsgs.add("會員帳號: 只能是英文字母、數字和 , 且長度必需在2到10之間");
//				}
//
//				String mem_pwd = req.getParameter("mem_pwd").trim();
//				String mpwdReg = "^[(a-zA-Z0-9)]{2,100}$";
//				if (mem_pwd == null || mem_pwd.trim().length() == 0) {
//					errorMsgs.add("帳號請勿空白");
//				} else if (!mem_pwd.trim().matches(mpwdReg)) {
//					errorMsgs.add("會員帳號: 只能是英文字母、數字和 , 且長度必需在2到10之間");
//				}
//				
//				String mem_name = req.getParameter("mem_name");
//				String mnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,100}$";
//				if (mem_name == null || mem_name.trim().length() == 0) {
//					errorMsgs.add("會員姓名: 請勿空白");
//				} else if (!mem_name.trim().matches(mnameReg)) { // 以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//				}
//				
//				java.sql.Date mem_birth=null;
//				try {
//					mem_birth = java.sql.Date.valueOf(req.getParameter("mem_birth").trim());
//				} catch (IllegalArgumentException e) {
//					mem_birth = new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請填入生日");
//				}
//
//				String mem_tel = null;
//				try {
//					mem_tel =req.getParameter("mem_tel").trim();
//				} catch (NullPointerException e) {
//					mem_tel = "";
//					errorMsgs.add("請打入電話.");
//				}
//				String mem_address = null;
//				try {
//					mem_address =req.getParameter("mem_address").trim();
//				} catch (NullPointerException e) {
//					mem_address = "";
//					errorMsgs.add("請打入地址.");
//				}
//
//				String mem_mail = null;
//				try {
//					mem_mail =req.getParameter("mem_mail").trim();
//				} catch (NullPointerException e) {
//					mem_mail = "";
//					errorMsgs.add("請輸入信箱.");
//				}
//				String mem_id_number = null;
//				try {
//					mem_id_number =req.getParameter("mem_id_number").trim();
//				} catch (NullPointerException e) {
//					mem_id_number = "";
//					errorMsgs.add("請輸入身分證字號.");
//				}
//				Integer mem_acc_status = null;
//				try {
//					mem_acc_status =new Integer(req.getParameter("mem_acc_status").trim());
//				} catch (NumberFormatException e) {
//					mem_acc_status = new Integer(0);
//					errorMsgs.add("請輸入信箱.");
//				}
//				Integer mem_gender = null;
//				try {
//					mem_gender =Integer.parseInt(req.getParameter("mem_gender").trim());
//				} catch (NullPointerException e) {
//					mem_gender = new Integer(0);
//					errorMsgs.add("請輸入信箱.");
//				}
//				
//				java.sql.Timestamp mem_jointime=null;
//				
//				try {
//					String mj=req.getParameter("mem_jointime").trim();
//					mem_jointime = java.sql.Timestamp.valueOf(mj);
//				} catch (IllegalArgumentException e) {
//					mem_jointime = new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("請加入時間");
//				}
//
//
//				MemberVO memVO = new MemberVO();
//				memVO.setMem_id(mem_id);
//				memVO.setMem_account(mem_account);
//				memVO.setMem_pwd(mem_pwd);
//				memVO.setMem_name(mem_name);
//				memVO.setMem_birth(mem_birth);
//				memVO.setMem_tel(mem_tel);
//				memVO.setMem_address(mem_address);
//				memVO.setMem_mail(mem_mail);
//				memVO.setMem_id_number(mem_id_number);
//				memVO.setMem_acc_status(mem_acc_status);
//				memVO.setMem_gender(mem_gender);
//				memVO.setMem_jointime(mem_jointime);
//				
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/update_mem_input.jsp");
//					failureView.forward(req, res);
//					return; // 程式中斷
//				}
//
//				/*************************** 2.開始修改資料 *****************************************/
//				MemberService memSvc = new MemberService();
//				memVO = memSvc.updateMem(mem_id, mem_account,mem_pwd,mem_name,mem_birth,mem_tel,mem_address, mem_mail,mem_id_number,mem_acc_status,mem_gender,mem_jointime);
//
//				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//				req.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/front-end/member/listOneMem.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/update_mem_input.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
		if ("upDateActivityPeriodStatus".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				String act_period_id = req.getParameter("act_period_id").trim();
				Integer act_period_status=Integer.parseInt(req.getParameter("act_period_status"));


				/*************************** 2.開始新增資料 ***************************************/
				ActivityPeriodService actperSvc = new ActivityPeriodService();
				actperSvc.upDateActPerStatus(act_period_id, act_period_status);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-sell-end/activity_period/listOneActivityPeriod.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-sell-end/activity_period/listOneActivityPeriod.jsp");
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

				String act_id = req.getParameter("act_id").trim();
				String mpwdReg = "^[(a-zA-Z0-9)]{2,100}$";
				if (act_id == null || act_id.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				} else if (!act_id.trim().matches(mpwdReg)) {
					errorMsgs.add("會員密碼錯誤");
				}
				java.sql.Timestamp act_sign_start = new java.sql.Timestamp(
						java.sql.Date.valueOf(req.getParameter("act_sign_start")).getTime());
				java.sql.Timestamp act_sign_end = new java.sql.Timestamp(
						java.sql.Date.valueOf(req.getParameter("act_sign_end")).getTime());
				java.sql.Timestamp act_period_start = java.sql.Timestamp
						.valueOf(req.getParameter("act_period_start") + ":00.0");
				java.sql.Timestamp act_period_end = java.sql.Timestamp
						.valueOf(req.getParameter("act_period_end") + ":00.0");
				Integer act_up_limit = Integer.parseInt(req.getParameter("act_up_limit"));
				Integer act_low_limit = Integer.parseInt(req.getParameter("act_low_limit"));
				Double act_cur_price = Double.parseDouble(req.getParameter("act_cur_price"));
				Integer act_period_status, act_sign_sum;
				if (req.getParameter("act_period_status") == null) {
					act_period_status = 1;
				} else {

					act_period_status = Integer.parseInt(req.getParameter("act_period_status"));
				}
				if (req.getParameter("act_sign_sum") == null) {
					act_sign_sum = 5;
				} else {

					act_sign_sum = Integer.parseInt(req.getParameter("act_sign_sum"));
				}

				ActivityPeriodVO actperVO = new ActivityPeriodVO();
				actperVO.setAct_id(act_id);
				actperVO.setAct_sign_start(act_sign_start);
				actperVO.setAct_sign_end(act_sign_end);
				actperVO.setAct_period_start(act_period_start);
				actperVO.setAct_period_end(act_period_end);
				actperVO.setAct_up_limit(act_up_limit);
				actperVO.setAct_low_limit(act_low_limit);
				actperVO.setAct_cur_price(act_cur_price);
				actperVO.setAct_period_status(act_period_status);
				actperVO.setAct_sign_sum(act_sign_sum);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actperVO", actperVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-sell-end/activity_period/addActivityPeriod.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ActivityPeriodService actperSvc = new ActivityPeriodService();
				actperSvc.insert(act_id, act_sign_start, act_sign_end, act_period_start, act_period_end, act_up_limit,
						act_low_limit, act_cur_price, act_period_status, act_sign_sum);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-sell-end/activity_product/listOneActivityProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-sell-end/activity_period/addActivityPeriod.jsp");
				failureView.forward(req, res);
			}
		}
//
//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數 ***************************************/
//				String mem_id = req.getParameter("mem_id");
//
//				/*************************** 2.開始刪除資料 ***************************************/
//				MemberService memSvc = new MemberService();
//				memSvc.deleteMem(mem_id);
//
//				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
//				String url = "/front-end/member/listAllMem.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:" + e.getMessage());
//				RequestDispatcher failureView;
//				failureView = req.getRequestDispatcher("/front-end/member/listAllMem.jsp");
//				failureView.forward(req, res);
//			}
//		}
//	}
	}
}
