package su.vistar.vetclinic.tests.servicetest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import su.vistar.vetclinic.tests.data.UserFunctions;
import su.vistar.vetclinic.configuration.HibernateConfiguration;
import su.vistar.vetclinic.entities.User;
import su.vistar.vetclinic.security.CustomUserDetailsService;
import su.vistar.vetclinic.security.SecurityConfiguration;
import su.vistar.vetclinic.service.UserService;

import java.util.*;

/**
 * Created by Владислав on 22.03.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfiguration.class, SecurityConfiguration.class, CustomUserDetailsService.class})
public class UserServiceTest{

    static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SessionFactory sessionFactory;

    private UserFunctions userFunctions = new UserFunctions();

    @Test
    @Transactional
    public void save() {
        User user = userFunctions.createUser();
        userService.saveUser(user);
        checkAsserEqualUser(user);
    }

    @Test
    @Transactional
    public void delete() {
        User user = userFunctions.createUser();
        userService.saveUser(user);
        Assert.assertNotNull(user.getId());
        int userId = user.getId();
        userService.deleteUserBySSO(user.getSsoId());
        Assert.assertEquals(getUserById(userId),null);

    }
    @Test
    @Transactional
    public void findAllUsers() {
        createUsers(100);
        List<User> users = userService.findAllUsers();
        for (int i = 0; i < users.size() ; i++) {
            logger.info(users.get(i).getSsoId());
        }
        Assert.assertNotNull(users);
    }

    private void checkAsserEqualUser(User user){
        Assert.assertNotNull(user.getId());
        User reloaded = getUserById(user.getId());
        Assert.assertEquals(user.getId(), reloaded.getId());
        Assert.assertEquals(user.getFirstName(), reloaded.getFirstName());
    }

    private void createUsers(int numberOfUsers) {
        for (int i = 0; i < numberOfUsers; i++) {
            User user = userFunctions.createUser();
            userService.saveUser(user);
            checkAsserEqualUser(user);
        }
    }


    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }


    private User getUserById(int id) {
        getSession().flush();
        return getSession().find(User.class, id);
    }


}
