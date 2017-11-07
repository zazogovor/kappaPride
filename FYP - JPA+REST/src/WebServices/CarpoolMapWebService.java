package WebServices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.CarpoolMap;

import javax.ws.rs.*;
import java.util.ArrayList;

@Path("/carpoolMap")
public class CarpoolMapWebService {

    @GET
    @Path("/carpoolMaps")
    @Produces("application/json")
    public String getCarpoolMaps(){
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(Memory.carpoolMaps);
        return json;
    }

    @GET
    @Path("/carpoolMap/{id}")
    @Produces("application/json")
    public String getCarpoolMap(@PathParam("id")int id){
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(Memory.carpoolMaps.get(id-1));
        return json;
    }

    @DELETE
    @Path("/remove/{id}")
    public String removeCarpoolMap(@PathParam("id")int id){
        Memory.carpoolMaps.set(id-1, null);
        return ("CarpoolMap with id:" + id + " has been removed successfully");
    }
}
