package com.shopping.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.souvenir_product.model.SouvenirProductService;
import com.souvenir_product.model.SouvenirProductVO;


@MultipartConfig
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		HttpSession session = req.getSession();
		Vector<SouvenirProductVO> buylist = (Vector<SouvenirProductVO>) session.getAttribute("soupVO");


		if (!action.equals("CHECKOUT")) {
			
			if ("DELETECART".equals(action)) {
				String del = req.getParameter("del");
				int d = Integer.parseInt(del);
				buylist.removeElementAt(d);
			}
			else if ("ADD".equals(action)) {
				boolean match = false;

				SouvenirProductVO soupVO = getSouvenirProduct(req);

				if (buylist == null) {
					buylist = new Vector<SouvenirProductVO>();
					buylist.add(soupVO);
				} else {
					for (int i = 0; i < buylist.size(); i++) {
						SouvenirProductVO sp = buylist.get(i);

						// ���Y�s�W�����y�M�ʪ��������y�@�ˮ�
						if (sp.getSou_name().equals(soupVO.getSou_name())) {
							sp.setQuantity(sp.getQuantity()
									+ soupVO.getQuantity());
							buylist.setElementAt(sp, i);
							match = true;
						} // end of if name matches
					} // end of for

					// ���Y�s�W�����y�M�ʪ��������y���@�ˮ�
					if (!match)
						buylist.add(soupVO);
				}
			}

			session.setAttribute("soupVO", buylist);
			String url = "/front-mem-end/souvenir/souvenir.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
		// ���b�A�p���ʪ������y�����`��
		else if ("CHECKOUT".equals(action)) {
			Integer total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				SouvenirProductVO order = buylist.get(i);
				Integer price = order.getSou_price();
				int quantity = order.getQuantity();
				total += (price * quantity);
			}
			req.setAttribute("amount", total);
			String url = "/front-mem-end/souvenir/souvenir_checkout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
			}
	}
	private SouvenirProductVO getSouvenirProduct(HttpServletRequest req) {

		String quantity = req.getParameter("quantity");
		String sou_name = req.getParameter("sou_name");
		String sou_id = req.getParameter("sou_id");
		Integer sou_price = new Integer(req.getParameter("sou_price"));			
		
		SouvenirProductVO soupVO = new SouvenirProductVO();
		soupVO.setSou_name(sou_name);
		soupVO.setSou_id(sou_id);
		soupVO.setSou_price(sou_price);
		soupVO.setQuantity((new Integer(quantity)).intValue());
		return soupVO;
	}
}
		
		
