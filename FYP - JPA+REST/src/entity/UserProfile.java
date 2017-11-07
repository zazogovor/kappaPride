package entity;


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


@Entity
@XmlRootElement
public class UserProfile {
	
	//every entity requires an id, and we can make it auto generated
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String profileDescription;
	private String carDriven;
	private String location;
	
	private int averageRating;
	private int numberOfReviews;
	
	
	
	
	public UserProfile(){
		
	}
	

	public UserProfile(String profileDescription, String carDriven, String location) {
		super();
		this.profileDescription = profileDescription;
		this.carDriven = carDriven;
		this.location = location;
		this.averageRating = 0;
		this.numberOfReviews = 0;
	}
	
	public void incrementNumberOfReviews(){
		this.numberOfReviews++;
	}
	
	public void incrementRating(int rating){
		int currentTotal = this.averageRating * this.numberOfReviews;
		currentTotal += rating;
		incrementNumberOfReviews();
		this.averageRating = currentTotal / this.numberOfReviews;
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
	public String getProfileDescription() {
		return profileDescription;
	}

	public void setProfileDescription(String profileDescription) {
		this.profileDescription = profileDescription;
	}

	@XmlElement
	public String getCarDriven() {
		return this.carDriven;
	}

	public void setCarDriven(String carDriven) {
		this.carDriven = carDriven;
	}

	@XmlElement
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@XmlElement
	public int getAverageRating(){
		return this.averageRating;
	}
	
	public void setAverageRating(int rating){
		this.averageRating = rating;
	}

	@XmlElement
	public int getNumberOfReviews(){
		return this.numberOfReviews;
	}
	
	public void setNumberOfReviews(int total){
		this.numberOfReviews = total;
	}

}
