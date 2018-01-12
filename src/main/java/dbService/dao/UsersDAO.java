package dbService.dao;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import dbService.dataSets.UserDataSet;
import dbService.dataSets.UserDataSet_;

/**
 * The class presents DAO for UserDataSet
 * 
 * @author Alexey Kopylov
 *
 */
public class UsersDAO {

	private Session session;

	public UsersDAO(Session session) {
		this.session = session;
	}

	public UserDataSet get(long id) {
		return session.get(UserDataSet.class, id);
	}

	public long insertUser(String login, String password) {
		return (Long) session.save(new UserDataSet(login, password));
	}

	public long getUserId(String login) {
		try {
			CriteriaQuery<UserDataSet> criteria = buildCriteria(login);
			return session.getEntityManagerFactory().createEntityManager().createQuery(criteria).getSingleResult()
					.getId();
		} catch (NoResultException e) {
			return -1;
		}
	}

	private CriteriaQuery<UserDataSet> buildCriteria(String login) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<UserDataSet> criteria = builder.createQuery(UserDataSet.class);
		Root<UserDataSet> root = criteria.from(UserDataSet.class);
		criteria.select(root);
		criteria.where(builder.equal(root.get(UserDataSet_.login), login));
		return criteria;
	}
}
