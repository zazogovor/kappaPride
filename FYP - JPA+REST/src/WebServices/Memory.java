package WebServices;

import entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Memory {

    public static List<User> users = new ArrayList<User>();
    public static List<UserProfile> profiles = new ArrayList<UserProfile>();
    public static List<Review> reviews = new ArrayList<Review>();
    public static List<EmergencyContact> emergencyContacts = new ArrayList<EmergencyContact>();
    public static List<Carpool> carpools = new ArrayList<Carpool>();
    public static List<CarpoolMap> carpoolMaps = new ArrayList<CarpoolMap>();

    static{
        /*
        UserProfile up1 = new UserProfile("My name is Bob", "Fiat Punto", "Gorey");
        EmergencyContact ec1 = new EmergencyContact("FacebookID:1234");
        User u1 = new User("bob", "bob@gmail.com", "1234", 5, ec1, up1);
        profiles.add(up1);
        emergencyContacts.add(ec1);
        users.add(u1);
        System.out.println(users.toString());


        UserProfile up2 = new UserProfile("My name is Mary", "Nissan Micra", "Arklow");
        EmergencyContact ec2 = new EmergencyContact("FacebookID:4567");
        User u2 = new User("mary", "mary@gmail.com", "4567", 7, ec2, up2);
        profiles.add(up2);
        emergencyContacts.add(ec2);
        users.add(u2);
        System.out.println(users.toString());*/
    }
}
