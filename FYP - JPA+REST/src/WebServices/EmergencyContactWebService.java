package WebServices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.EmergencyContact;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.ArrayList;

@Path("/emergencyContact")
public class EmergencyContactWebService {

    @GET
    @Path("/emergencyContacts")
    @Produces("application/json")
    public String getEmergencyContacts(){
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(Memory.emergencyContacts);
        return json;
    }

    @GET
    @Path("/emergencyContact/{id}")
    @Produces("application/json")
    public String getEmergencyContact(@PathParam("id")int id){
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(Memory.emergencyContacts.get(id-1));
        return json;
    }
}
