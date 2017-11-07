package main;

import java.util.List;
import persistence.PersistenceUtil;
import entity.Carpool;
import entity.EmergencyContact;
import entity.User;
import entity.UserProfile;


public class UserDAO {

	public void userPrinter(User u){
		System.out.println("Username: "+u.getUsername());
		System.out.println("Email: "+u.getEmail());
		System.out.println("Max route deviation: "+u.getRouteDeviation());
		System.out.println("");
	}

	public void viewUsers(){
		List<User> users = PersistenceUtil.findAllUsers();
		for(User u:users){
			userPrinter(u);
		}
	}
	
	public static User viewUser(String username){
		User user = PersistenceUtil.findUserByUsername(username);
		return user;
	}

	public static Boolean loginUser(String username, String password){
		User user = PersistenceUtil.userLogin(username, password);
		if(user != null){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void joinCarpool(String username, int carpoolID){
		User user = PersistenceUtil.findUserByUsername(username);
		Carpool c = PersistenceUtil.findCarpoolById(carpoolID);
		
		user.addCarpool(c);
		c.addPassenger(user);
		
		PersistenceUtil.merge(user);
		PersistenceUtil.merge(c);
		
		System.out.println("Carpool with ID:" + c.getId() + " added to user " + username);
		System.out.println("User " + username + " joined carpool ID:" + c.getId());
		System.out.println("");
		
	}
	
	public void updateUser(String username, String email, String password, int routeDeviation){
		User user = PersistenceUtil.findUserByUsername(username);
		
		if(username != null){
			user.setUsername(username);
		}
		if(email != null){
			user.setEmail(email);		
		}
		if(password != null){
			user.setPassword(password);
		}
		if(routeDeviation != 0){
			user.setRouteDeviation(routeDeviation);
		}
		
		PersistenceUtil.merge(user);
		System.out.println("User details updated");
	}
	
	public void deleteUser(String username){
		User user = PersistenceUtil.findUserByUsername(username);
		UserProfile up = PersistenceUtil.findUserProfileByUsername(username);
		PersistenceUtil.remove(up);
		PersistenceUtil.remove(user.getContact());
		PersistenceUtil.remove(user);
		System.out.println("User "+ username + " successfully removed from database.");
	}
	
	public static void createUser(String username, String email, String password, int routeDeviation,
								  String emergencyContact, String profileDescription, String carDriven, String location){

		UserProfile profile = new UserProfile(profileDescription, carDriven, location);
		PersistenceUtil.persist(profile);
		EmergencyContact ec = new EmergencyContact(emergencyContact);
		PersistenceUtil.persist(ec);
		User user = new User(username, email, password, routeDeviation, ec, profile);
		PersistenceUtil.persist(user);
		System.out.println("User registered");
	}
			

}
