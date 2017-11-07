package entity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@NamedQueries( {
	@NamedQuery(name = "User.findAll", query = "select o from User o"),
	@NamedQuery(name = "User.findByUsername", query = "select o from User o where o.username=:username"),
	@NamedQuery(name = "User.edit", query = "select o from User o"),
	@NamedQuery(name = "User.login", query = "SELECT o FROM User o WHERE o.username = :username AND o.password = :password")
})

@Entity
@XmlRootElement
public class User 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(unique=true)
	private String username;
	
	private String password;
	private String email;
	private int routeDeviation;
	
	@OneToOne
	private UserProfile profile;
	
	@OneToOne
	private EmergencyContact contact;
	
	
	//JOIN table containing all carpools associated with User (whether he is a driver or a passenger)
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Carpool> carpools = new ArrayList<Carpool>();
	
	public User(){
		
	}
	
	public User(String username,String email, String password, int routeDeviation,
			EmergencyContact emergencyContact, UserProfile profile) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.routeDeviation = routeDeviation;
		
		if(profile != null){
			this.profile = profile;
		}
		else{
			this.profile = new UserProfile();
		}
				
		this.contact = emergencyContact;
	}



	
	/*
	* 	GETers and SETers beyond this point
	*/
	@XmlElement
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@XmlElement
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@XmlElement
	public String getEmail(){
		return this.email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}

	@XmlElement
	public int getRouteDeviation() {
		return this.routeDeviation;
	}

	public void setRouteDeviation(int routeDeviation) {
		this.routeDeviation = routeDeviation;
	}

	@XmlElement
	public UserProfile getProfile() {
		return this.profile;
	}

	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}

	@XmlElement
	public EmergencyContact getContact() {
		return this.contact;
	}

	public void setContact(EmergencyContact contact) {
		this.contact = contact;
	}

	@XmlElement
	public List<Carpool> getCarpools(){
		return this.carpools;
	}
	
	public void addCarpool(Carpool c){
		this.carpools.add(c);
	}
	
	public void setCarpools(List<Carpool> c){
		this.carpools = c;
	}
	
	
}
