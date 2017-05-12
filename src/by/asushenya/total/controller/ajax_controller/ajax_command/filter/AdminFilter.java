package by.asushenya.total.controller.ajax_controller.ajax_command.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import by.asushenya.total.bean.User;
import by.asushenya.total.bean.util.UserRole;
import by.asushenya.total.controller.JspPageName;
import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.controller.ResponseParameterName;

/**
 * Filter requests to admin pages and allow access only admins.
 * 
 * @author Artyom Suschenya
 *
 */
public class AdminFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest rq = (HttpServletRequest) request;
		User user = (User) rq.getSession(true).getAttribute(RequestParameterName.USER);
		if (user == null) {
			request.setAttribute(ResponseParameterName.ERROR_MSSAGE, "You are not authorized");
			request.getRequestDispatcher(JspPageName.FORBIDDEN_PAGE).forward(request, response);
		} else if (user.getRole() != UserRole.ADMIN) {
			request.setAttribute("errorMessage", "Not enough privileges. You are " + user.getRole());
			request.getRequestDispatcher(JspPageName.FORBIDDEN_PAGE).forward(request, response);
		}

		chain.doFilter(request, response);
	}

}
