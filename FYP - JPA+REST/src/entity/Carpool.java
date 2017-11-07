package entity;


import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@NamedQueries( {
	@NamedQuery(name = "Carpool.findAll", query = "select o from Carpool o"),
	@NamedQuery(name = "Carpool.findById", query = "select o from Carpool o where o.id=:id")
})

@Entity
@XmlRootElement
public class Carpool {

	//every entity requires an id, and we can make it auto generated
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private Date date;
	private Time time;

	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(
            name="Passengers",
            joinColumns=@JoinColumn(
                    name="Carpool_ID", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(
                    name="User_ID", referencedColumnName="id")
            )
	private List<User> passengers = new ArrayList<User>();

	@ManyToOne
	private User driver;


	@OneToOne
	private	CarpoolMap map;

	public Carpool(){

	}

	public Carpool(Date date, Time time, User driver){
		super();
		this.date = date;
		this.time = time;
		this.driver = driver;
	}

	public void addPassenger(User passenger){
		this.passengers.add(passenger);
	}

	public List<User> getPassengers(){
		return (ArrayList<User>) this.passengers;
	}

	public void setPassengers(List<User> passengers){
		this.passengers = passengers;
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
	public Date getDate(){
		return this.date;
	}

	public void setDate(Date date){
		this.date = date;
	}

	@XmlElement
	public Time getTime(){
		return this.time;
	}

	public void setTime(Time time){
		this.time = time;
	}

	@XmlElement
	public CarpoolMap getMap(){
		return this.map;
	}

	public void setMap(CarpoolMap map){
		this.map = map;
	}

	@XmlElement
	public User getDriver(){
		return this.driver;
	}

	public void setDriver(int id){
		this.id = id;
	}

}
