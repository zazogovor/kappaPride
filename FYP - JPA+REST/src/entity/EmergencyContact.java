package entity;


import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@NamedQueries( {
	@NamedQuery(name = "EmergencyContact.findAll", query = "select o from EmergencyContact o"),
	@NamedQuery(name = "EmergencyContact.findById", query = "select o from EmergencyContact o where o.id=:id")
})

@Entity
@XmlRootElement
public class EmergencyContact {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String facebookID;
	
	
	
	public EmergencyContact(){
		
	}
	
	public EmergencyContact(String facebookID){
		super();
		this.facebookID = facebookID;
	}
	
	
	/*
	* 	GETers and SETers beyond this point
	*/
	@XmlElement
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}

	@XmlElement
	public String getFacebookId(){
		return this.facebookID;
	}
	
	public void setFacebookId(String facebookID){
		this.facebookID = facebookID;
	}
}
