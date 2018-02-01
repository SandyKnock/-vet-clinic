package su.vistar.vetclinic.service;

import su.vistar.vetclinic.entities.UserProfile;

import java.util.List;


public interface UserProfileService {

	UserProfile findById(int id);

	UserProfile findByType(String type);
	
	List<UserProfile> findAll();
	
}
