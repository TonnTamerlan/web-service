package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import accounts.AccountService;
import accounts.UserProfile;

public class SingInServlet extends HttpServlet {
	private final AccountService accountService;

	public SingInServlet(AccountService accountService) {
		this.accountService = accountService;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		UserProfile user = accountService.getUserByLogin(login);
		if (user != null && user.getPassword().equals(password)) {
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.getWriter().println("Authorized: " + login);
		} else {
			resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			resp.getWriter().println("Unauthorized");
		}

	}
	
}
