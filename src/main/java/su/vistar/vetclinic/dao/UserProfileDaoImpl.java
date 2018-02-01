package su.vistar.vetclinic.dao;


import su.vistar.vetclinic.entities.UserProfile;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDao<Integer, UserProfile>implements UserProfileDao{

	public UserProfile findById(int id) {
		return getByKey(id);
	}

	public UserProfile findByType(String type) {
		Query query = getSession().createQuery("from UserProfile where type =:type").setParameter("type",type);
		return (UserProfile) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<UserProfile> findAll(){
		Query query = getSession().createQuery("from UserProfile ORDER BY type ");
		return (List<UserProfile>)query.list();
	}

}
