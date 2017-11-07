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
	@NamedQuery(name = "CarpoolMap.findAll", query = "select o from CarpoolMap o"),
	@NamedQuery(name = "CarpoolMap.findById", query = "select o from CarpoolMap o where o.id=:id"),
})

@Entity
@XmlRootElement
public class CarpoolMap {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String route;
	private String origination;
	private String destination;
	
	public CarpoolMap(){
		
	}
	
	public CarpoolMap(String route, String origination, String destination){
		super();
		this.route = route;
		this.origination = origination;
		this.destination = destination;
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
	public String getRoute(){
		return this.route;
	}
	
	public void setRoute(String route){
		this.route = route;
	}

	@XmlElement
	public String getDestination(){
		return this.destination;
	}
	
	public void setDestination(String destination){
		this.destination = destination;
	}

	@XmlElement
	public String getOrigination(){
		return this.origination;
	}
	
	public void setOrigination(String origination){
		this.origination = origination;
	}
	
}
