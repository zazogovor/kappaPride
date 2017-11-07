package WebServices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.org.apache.xpath.internal.operations.Bool;
import entity.EmergencyContact;
import entity.User;
import entity.UserProfile;
import main.UserDAO;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

import static WebServices.Memory.profiles;

@Path("/user")
public class UserWebService {

    @GET
    @Path("/users")
    @Produces({MediaType.APPLICATION_JSON})
    public String getUsers(){
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(Memory.users);
        return json;
    }

    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser(@PathParam("id")int id){
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(Memory.users.get(id-1));
        return json;
    }

    //Create USER
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String newUser(@FormParam("username") String username,
                        @FormParam("email") String email,
                        @FormParam("password") String password,
                        @FormParam("deviation") String deviation,
                        @FormParam("emergencyContact") String emergencyContact,
                        @FormParam("description") String description,
                        @FormParam("carDriven") String carDriven,
                        @FormParam("location") String location){

        int d = Integer.parseInt(deviation);

        UserDAO.createUser(username, email, password, d, emergencyContact, description,
                carDriven, location);

        return "1";
    }

    //Login USER
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String newUser(@FormParam("username") String username,
                          @FormParam("password") String password){
        System.out.println("User LOGIN request...");
        Boolean success = UserDAO.loginUser(username, password);

        if(success == true){
            System.out.println("User LOGIN successfull...");
            return "1";
        }
        else{
            System.out.println("User LOGIN failed...");
            return "0";
        }
    }

    //Update USER
    @PUT
    @Path("/updateUserDetails/{id}/{email:.*}/{password:.*}/{deviation:.*}/{emergencyContact:.*}")
    public Response updateUserDetails(@PathParam("id")int id,
                                      @PathParam("email")String email,
                                      @PathParam("password")String password,
                                      @PathParam("deviation")int deviation,
                                      @PathParam("emergencyContact")String emergencyContact,
                                      @Context HttpServletResponse response){

        User u = Memory.users.get(id-1);

        if(!email.equals(""))
            u.setEmail(email);
        if(!password.equals(""))
            u.setPassword(password);
        if(deviation != 0)
            u.setRouteDeviation(deviation);
        if(!emergencyContact.equals("")) {
            EmergencyContact ec = new EmergencyContact(emergencyContact);
            u.setContact(ec);
        }
        Memory.users.set(id-1, u);

        return Response.noContent().build();
    }

    //Delete USER
    @DELETE
    @Path("/delete/{id}")
    public Response removeUser(@PathParam("id")int id,
                               @Context HttpServletResponse response){
        /*
        User u = Memory.users.get(id-1);
        UserProfile up = u.getProfile();
        EmergencyContact ec = u.getContact();
        */

        Memory.profiles.set(id-1, null);
        Memory.emergencyContacts.set(id-1, null);
        Memory.users.set(id-1, null);

        return Response.noContent().build();
    }


}
