package main;

import persistence.PersistenceUtil;
import entity.User;
import entity.UserProfile;


public class UserProfileDAO {
	public void viewUserProfileByUsername(String username){
		User user = PersistenceUtil.findUserByUsername(username);
		UserProfile userProfile = user.getProfile();
		
		System.out.println("Username: "+user.getUsername() + "\n");
		System.out.println("Profile description: "+userProfile.getProfileDescription() + "\n");
		System.out.println("Lives in: "+userProfile.getLocation() + "\n");
		System.out.println("Rating: "+userProfile.getAverageRating() + "\n");
		System.out.println("Number of reviews: "+userProfile.getNumberOfReviews() + "\n");
	}
	
	public void updateUserProfile(String username, String profileDescription, String carDriven, String location){
		User user = PersistenceUtil.findUserByUsername(username);
		UserProfile up = user.getProfile();
		
		if(profileDescription != null){
			up.setProfileDescription(profileDescription);
		}
		if(carDriven != null){
			up.setCarDriven(carDriven);		
		}
		if(location != null){
			up.setLocation(location);
		}
		
		PersistenceUtil.merge(up);
		System.out.println("UserProfile of " + username + " has been updated");
	}
}
