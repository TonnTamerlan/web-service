package accounts;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The class contains information about users which logged and about their sessinId
 * 
 * @author Alexey Kopylov
 *
 */
public class AccountService {
	private Map<String, UserProfile> loginToProfile;
	private Map<String, UserProfile> sessionIdToProfile;
	
	public AccountService() {
		this.loginToProfile = new ConcurrentHashMap<>();
		this.sessionIdToProfile = new ConcurrentHashMap<>();
	}
	
	public void addNewUser(UserProfile user) {
		loginToProfile.put(user.getName(), user);
	}
	
	public void addSession(String sessionId, UserProfile user) {
		sessionIdToProfile.put(sessionId, user);
	}
	
	public UserProfile getUserByLogin(String login) {
		return loginToProfile.get(login);
	}
	
	public UserProfile getUserBySession(String sessionId) {
		return sessionIdToProfile.get(sessionId);
	}
	
	public void delUser(String login) {
		loginToProfile.remove(login);
	}
	
	public void delSesion(String sessionId) {
		sessionIdToProfile.remove(sessionId);
	}
}
