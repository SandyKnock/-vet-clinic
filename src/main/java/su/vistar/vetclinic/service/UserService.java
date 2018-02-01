package su.vistar.vetclinic.service;


import su.vistar.vetclinic.entities.User;

import java.util.List;


public interface UserService {
	
	User findById(int id);
	
	User findBySSO(String sso);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserBySSO(String sso);

	List<User> findAllUsers(); 
	
	boolean isUserSSOUnique(Integer id, String sso);

	String checkingEmail(String email);

	String checkingSSO(String sso);

}