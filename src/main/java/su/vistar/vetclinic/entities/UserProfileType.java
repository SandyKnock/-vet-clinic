package su.vistar.vetclinic.entities;

import java.io.Serializable;

public enum UserProfileType implements Serializable{
	USER("USER"),
	EMPLOYEE("EMPLOYEE"), //TODO убрать (почить джава enum)
	ADMIN("ADMIN");
	
	String userProfileType;
	
	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
	
}
