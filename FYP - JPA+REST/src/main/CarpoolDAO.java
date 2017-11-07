package main;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import persistence.PersistenceUtil;
import entity.Carpool;
import entity.CarpoolMap;
import entity.EmergencyContact;
import entity.User;
import entity.UserProfile;


public class CarpoolDAO {

	public void carpoolPrinter(Carpool c){
		System.out.println("ID: "+c.getId());
		System.out.println("Date: "+c.getDate());
		System.out.println("Time: "+c.getTime());
		System.out.println("Driver: "+ c.getDriver().getUsername());
		System.out.println("\n");
	}

	public void viewCarpools(){
		List<Carpool> carpools = PersistenceUtil.findAllCarpools();
		for(Carpool c:carpools){
			carpoolPrinter(c);
		}
	}
	
	public void viewCarpool(int id){
		Carpool c = PersistenceUtil.findCarpoolById(id);
		carpoolPrinter(c);
	}
	
	public void updateCarpool(int id, Date date, Time time){
		Carpool c = PersistenceUtil.findCarpoolById(id);
		
		if(date != null){
			c.setDate(date);
		}
		if(time != null){
			c.setTime(time);
		}

		PersistenceUtil.merge(c);
		System.out.println("Carpool has been updated.");
	}
	
	public void deleteCarpool(int id){
		Carpool c = PersistenceUtil.findCarpoolById(id);
		if(c.getMap() != null){
			PersistenceUtil.remove(c.getMap());
		}
		PersistenceUtil.remove(c);
		System.out.println("Carpool with ID: "+ id + " has been successfully removed from database.");
	}
	
	public void createCarpool(Date date, Time time, String username){
		User driver = PersistenceUtil.findUserByUsername(username);
		Carpool c = new Carpool(date, time, driver);
		driver.addCarpool(c);
		PersistenceUtil.persist(c);
		PersistenceUtil.merge(driver);
		
		
		System.out.println("Carpool has been created.");
	}
			

}
