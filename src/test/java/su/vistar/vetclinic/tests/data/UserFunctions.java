package su.vistar.vetclinic.tests.data;

import su.vistar.vetclinic.entities.User;
import su.vistar.vetclinic.entities.UserProfile;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Владислав on 23.03.2017.
 */
public class UserFunctions {

    private static final int substringlength = 26;

    //😊😍😎
    public User createUser(){
        User user = new User();
        Set<UserProfile> userProfileSet = new HashSet<>();
        UserProfile userProfile = getUserProfile();
        userProfileSet.add(userProfile);
        user.setId(null);
        user.setPassword(UUID.randomUUID().toString().substring(substringlength));
        user.setEmail(UUID.randomUUID().toString().substring(substringlength));
        user.setFirstName(UUID.randomUUID().toString().substring(substringlength));
        user.setLastName(UUID.randomUUID().toString().substring(substringlength));
        user.setSsoId(UUID.randomUUID().toString().substring(substringlength));
        user.setUserProfiles(userProfileSet);
        return user;
    }

    public UserProfile getUserProfile(){
        Random random = new Random();
        UserProfile userProfile = new UserProfile();
        switch (random.nextInt(1)+3){
            case 1:{
                userProfile.setId(1);
                userProfile.setType("USER");
                break;
            }
            case 2:{
                userProfile.setId(2);
                userProfile.setType("ADMIN");
                break;
            }
            case 3:{
                userProfile.setId(3);
                userProfile.setType("EMPLOYEE");
                break;
            }
        }
        return userProfile;
    }

}
