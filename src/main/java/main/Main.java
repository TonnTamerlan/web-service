package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import accounts.AccountService;
import servlets.MirrorRequestServlet;
import servlets.SignUpServlet;
import servlets.SingInServlet;

public class Main {

	public static void main(String[] args) throws Exception {
		MirrorRequestServlet mirrorRequestServlet = new MirrorRequestServlet();
		AccountService accountService = new AccountService();
		
		SignUpServlet singUpServlet = new SignUpServlet(accountService);
		SingInServlet singInServlet = new SingInServlet(accountService);
		
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.addServlet(new ServletHolder(mirrorRequestServlet), "/mirror");
		context.addServlet(new ServletHolder(singUpServlet), "/signup");
		context.addServlet(new ServletHolder(singInServlet), "/signin");
		
		
		Server server = new Server(8080);
		server.setHandler(context);
		server.start();
		System.out.println("Server started");
		server.join();
	}

}
