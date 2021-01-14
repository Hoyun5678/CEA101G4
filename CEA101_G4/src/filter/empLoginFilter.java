package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emp.model.EmpVO;

public class empLoginFilter implements Filter {
	private FilterConfig config;
	
	public void init(FilterConfig fConfig) throws ServletException {
		this.config = config;
	}
	
	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		
		//判斷是否登入
		EmpVO empVO = (EmpVO) session.getAttribute("empVO");
		if (empVO == null) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/back-end/empLogin.jsp");
			return;
		}else {
			req.setAttribute("empVO", empVO);
			chain.doFilter(req, res);
			} 
	}

	

}
