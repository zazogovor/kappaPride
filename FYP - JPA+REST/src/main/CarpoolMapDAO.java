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


public class CarpoolMapDAO {

	public void carpoolMapPrinter(CarpoolMap cm){
		System.out.println("ID: "+cm.getId());
		System.out.println("Origination: "+cm.getOrigination());
		System.out.println("Destination: "+cm.getDestination());
		System.out.println("Route link: "+cm.getRoute());
		System.out.println("\n");
	}

	public void viewCarpoolMaps(){
		List<CarpoolMap> carpoolMaps = PersistenceUtil.findAllCarpoolMaps();
		for(CarpoolMap cm:carpoolMaps){
			carpoolMapPrinter(cm);
		}
	}
	
	public void viewCarpool(int id){
		CarpoolMap cm = PersistenceUtil.findCarpoolMapById(id);
		carpoolMapPrinter(cm);
	}
	
	public void updateCarpoolMap(int id, String route, String origination, String destination){
		CarpoolMap cm = PersistenceUtil.findCarpoolMapById(id);
		
		if(route != null){
			cm.setRoute(route);
		}
		if(origination != null){
			cm.setOrigination(origination);
		}
		if(destination != null){
			cm.setDestination(destination);
		}

		PersistenceUtil.merge(cm);
		System.out.println("CarpoolMap has been updated.");
	}
	
	public void deleteCarpoolMap(int id){
		CarpoolMap cm = PersistenceUtil.findCarpoolMapById(id);
		PersistenceUtil.remove(cm);
		System.out.println("CarpoolMap with ID: "+ id + " has been successfully removed from database.");
	}
	
	public void createCarpoolMap(String route, String origination, String destination, int carpoolID){
		Carpool cp = PersistenceUtil.findCarpoolById(carpoolID);
		
		CarpoolMap cm = new CarpoolMap(route, origination, destination);
		PersistenceUtil.persist(cm);
		
		cp.setMap(cm);
		PersistenceUtil.merge(cp);
		System.out.println("CarpoolMap has been created.");
	}
			

}
