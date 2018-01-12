package dbService;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import dbService.dao.UsersDAO;
import dbService.dataSets.UserDataSet;

/**
 * The class presents a service for database
 * 
 * @author Alexey Kopylov
 *
 */

public class DBService {

	private final SessionFactory sessionFactory;

	public DBService() {
		Configuration config = getMySQLConfiguration();
		sessionFactory = createSessionFactory(config);
	}

	public long addUser(String login, String password) throws DBException {
		try {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			UsersDAO dao = new UsersDAO(session);
			long id = dao.insertUser(login, password);
			transaction.commit();
			session.close();
			return id;
		} catch (HibernateException e) {
			throw new DBException(e);
		}
	}
	
	public UserDataSet getUser(long id) throws DBException {
		try {
			Session session = sessionFactory.openSession();
			Transaction transanction = session.beginTransaction();
			UsersDAO dao = new UsersDAO(session);
			UserDataSet userDataSet = dao.get(id);
			transanction.commit();
			session.close();
			return userDataSet;
		} catch (HibernateException e) {
			throw new DBException(e);
		}
	}

	public UserDataSet getUser(String login) throws DBException {
		try {
			Session session = sessionFactory.openSession();
			Transaction transanction = session.beginTransaction();
			UsersDAO dao = new UsersDAO(session);
			long userId = dao.getUserId(login);
			if(userId == -1) {
				return null;
			}
			UserDataSet userDataSet = getUser(userId);
			transanction.commit();
			session.close();
			return userDataSet;
		} catch (HibernateException e) {
			throw new DBException(e);
		}

	}


	private Configuration getMySQLConfiguration() {
		Configuration config = new Configuration();
		config.addAnnotatedClass(UserDataSet.class);

		// config.setProperty("hibernate.dialect",
		// "org.hibernate.dialect.MySQLMyISAMDialect");
		// System.out.println("hibernate.dialect: " +
		// config.getProperty("hibernate.dialect"));

		config.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
		config.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/web_service?useSSL=false&"
				+ "useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
		config.setProperty("hibernate.connection.username", "root");
		config.setProperty("hibernate.connection.password", "root");
		config.setProperty("hibernate.show_sql", "false");
		config.setProperty("hibernate.hbm2ddl.auto", "update");
		return config;
	}

	private SessionFactory createSessionFactory(Configuration config) {
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
		builder.applySettings(config.getProperties());
		ServiceRegistry serviceRegistry = builder.build();
		return config.buildSessionFactory(serviceRegistry);
	}

}
