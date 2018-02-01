package su.vistar.vetclinic.service;

import su.vistar.vetclinic.dao.UserDao;
import su.vistar.vetclinic.entities.User;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dao;

	@Autowired
    private PasswordEncoder passwordEncoder;

	public User findById(int id) {
		return dao.findById(id);
	}

	public User findBySSO(String sso) {
		User user = dao.findBySSO(sso);
		return user;
	}

			//TODO преобразование пароля
	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		dao.save(user);
	}

//	Поскольку метод работает с транзакцией, нет необходимости вызывать спящий режим обновления в явном виде.
//* Просто извлечь объект из БД и обновлять его с соответствующими значениями в пределах транзакции.
//			* Он будет обновлен в БД один раз транзакция завершается.
	public void updateUser(User user) {
		User entity = dao.findById(user.getId());
		if(entity != null){
			entity.setSsoId(user.getSsoId());
			if(!user.getPassword().equals(entity.getPassword())){
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());
		}
	}

	
	public void deleteUserBySSO(String sso) {
		dao.deleteBySSO(sso);
	}

	public List<User> findAllUsers() {
		List<User> users = dao.findAllUsers();
		users.forEach(u -> Hibernate.initialize(u.getUserProfiles()));
		return users;
	}
	public boolean isUserSSOUnique(Integer id, String sso) {
		User user = findBySSO(sso);
		return ( user == null || ((id != null) && (user.getId() == id)));
	}
	
	public String checkingEmail(String email) {
		return dao.checkingEmail(email);
	}

	public String checkingSSO(String sso) {
		return dao.checkingSSO(sso);
	}

}
