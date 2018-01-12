package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import accounts.AccountService;
import dbService.DBService;
import servlets.MirrorRequestServlet;
import servlets.SignUpServlet;
import servlets.SignInServlet;

public class Main {

	public static void main(String[] args) throws Exception {
		AccountService accountService = new AccountService();

		SignUpServlet signUpServlet = new SignUpServlet(accountService);
		SignInServlet signInServlet = new SignInServlet(accountService);

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.addServlet(new ServletHolder(signUpServlet), "/signup");
		context.addServlet(new ServletHolder(signInServlet), "/signin");

		Server server = new Server(8080);
		server.setHandler(context);
		server.start();
		System.out.println("Server started");
		server.join();

	}

}
