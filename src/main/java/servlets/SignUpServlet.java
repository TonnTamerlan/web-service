package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import accounts.AccountService;
import accounts.UserProfile;

/**
 * 
 * 
 * @author Alexey Kopylov
 *
 */

public class SignUpServlet extends HttpServlet{
	private final AccountService accountService;
	
	public SignUpServlet(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		accountService.addNewUser(new UserProfile(req.getParameter("login"), req.getParameter("password"), ""));
		resp.setStatus(HttpServletResponse.SC_OK);
	}
}	
