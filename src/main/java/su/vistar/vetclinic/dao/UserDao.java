package su.vistar.vetclinic.dao;

import su.vistar.vetclinic.entities.User;

import java.util.List;


public interface UserDao {

	User findById(int id);
	
	User findBySSO(String sso);
	
	void save(User user);
	
	void deleteBySSO(String sso);
	
	List<User> findAllUsers();

	String checkingEmail(String email);

	String checkingSSO(String sso);

}

