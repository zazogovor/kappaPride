package main;

import java.util.List;
import persistence.PersistenceUtil;
import entity.EmergencyContact;


public class EmergencyContactDAO {

	public void viewEmergencyContacts(){
		List<EmergencyContact> contacts = PersistenceUtil.findAllEmergencyContacts();
		for(EmergencyContact ec:contacts){
			System.out.println("Emeregency Contact ID: "+ec.getId());
			System.out.println("Facebook ID: "+ec.getFacebookId());
			System.out.println();
		}
	}
	
	public void viewEmergencyContact(int id){
		EmergencyContact ec = PersistenceUtil.findEmergencyContactById(id);
		System.out.println("EmergencyContact ID: "+ec.getId()+ ".");
		System.out.println("EmergencyContact FacebookID: "+ec.getFacebookId()+ ".");
	}
	
	public void updateEmergencyContact(int id, String facebookID){
		EmergencyContact ec = PersistenceUtil.findEmergencyContactById(id);
		
		if(facebookID != null){
			ec.setFacebookId(facebookID);
		}
		
		PersistenceUtil.merge(ec);
		System.out.println("Emergency contact successfully updated.");
	}
}
