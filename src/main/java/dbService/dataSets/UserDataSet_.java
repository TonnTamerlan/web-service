package dbService.dataSets;


import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Metamodel of the class UserDataSet
 * 
 * @author Alexey Kopylov
 *
 */

@StaticMetamodel(UserDataSet.class)
public class UserDataSet_ {

	public static volatile SingularAttribute<UserDataSet, Long> id;
	public static volatile SingularAttribute<UserDataSet, String> login;
	public static volatile SingularAttribute<UserDataSet, String> password;

}
