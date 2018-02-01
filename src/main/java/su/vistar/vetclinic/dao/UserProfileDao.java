package su.vistar.vetclinic.dao;


import su.vistar.vetclinic.entities.UserProfile;

import java.util.List;


public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);

}
