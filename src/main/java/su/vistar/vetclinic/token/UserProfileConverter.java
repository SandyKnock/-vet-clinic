package su.vistar.vetclinic.token;

import su.vistar.vetclinic.entities.User;
import su.vistar.vetclinic.entities.UserProfile;

import java.util.Set;

/**
 * Created by Владислав on 17.02.2017.
 */
public class UserProfileConverter {

    public String[] converterUserTypesArray(User user){
        Set<UserProfile> userProfileSet = user.getUserProfiles();
        UserProfile[] userProfileArray = userProfileSet.toArray(new UserProfile[userProfileSet.size()]);
        String[] userType = new String[userProfileArray.length];
        for (int i = 0; i < userProfileArray.length; i++){
            userType[i] = userProfileArray[i].getType();
        }
        return userType;
    }

    public String converterUserTypes(String[] userType){
        String type = "";
        for (int i = 0; i < userType.length; i++){
            if (i < 0) {
                type += userType[i];
            }else {
                if (i+1 == userType.length){
                    type += userType[i];
                }else {
                    type += userType[i] + " ";
                }
            }
        }
        return type;
    }
}
