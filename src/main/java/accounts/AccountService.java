package accounts;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UserDataSet;

/**
 * The class contains information about users which logged
 * 
 * @author Alexey Kopylov
 *
 */
public class AccountService {
	DBService dbService;
	
	public AccountService() {
		this.dbService = new DBService();
	}
	
	public void addNewUser(String login, String password) {
		try {
			dbService.addUser(login, password);
		} catch (DBException e) {
			System.out.println("Cann't add user");
			e.printStackTrace();
		}
	}
	
	public UserDataSet getUserByLogin(String login) {
		try {
			return dbService.getUser(login);
		} catch (DBException e) {
			System.out.println("Cann't get user by Login");
			e.printStackTrace();
			return null;
		}
	}
}
