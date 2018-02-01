package su.vistar.vetclinic.dao;

import su.vistar.vetclinic.entities.User;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	public User findById(int id) {
		User user = getByKey(id);
		if(user != null){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	public User findBySSO(String sso) {
		logger.info("SSO : {}", sso);
		Query query = getSession().createQuery("from User where ssoId =:sso").setParameter("sso",sso);
		User user =(User)query.uniqueResult();
		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		Query query = getSession().createQuery("from User ORDER BY firstName ");
		List<User> users = (List<User>)query.list();
		return users;
	}

	@Override
	public String checkingEmail(String email) {
		Query query = getSession().createQuery("select email from User where email =:email").setParameter("email",email);
		String emaill = (String) query.uniqueResult();
		return emaill;
	}

	@Override
	public String checkingSSO(String sso) {
		Query query = getSession().createQuery("select ssoId from User where ssoId =:sso").setParameter("sso",sso);
		String ssoId = (String) query.uniqueResult();
		return ssoId;
	}

	public void save(User user) {
		persist(user);
	}

	public void deleteBySSO(String sso) {
		Query query = getSession().createQuery("from User where ssoId =:sso").setParameter("sso",sso);
		User user = (User)query.uniqueResult();
		delete(user);
	}

}
