package WebServices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.*;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;

@Path("/carpool")
public class CarpoolWebService {

    @GET
    @Path("/carpools")
    @Produces("application/json")
    public String getCarpools(){
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(Memory.carpools);
        return json;
    }

    @GET
    @Path("/carpool/{id}")
    @Produces("application/json")
    public String getCarpool(@PathParam("id")int id) {
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(Memory.carpools.get(id-1));
        return json;
    }

    @DELETE
    @Path("/remove/{id}")
    public String removeCarpool(@PathParam("id")int id){
        Carpool c

                = Memory.carpools.get(id-1);
        CarpoolMap cm = c.getMap();

        if(cm != null)
        {
            Memory.carpoolMaps.remove(cm.getId());
        }
        Memory.carpools.remove(id-1);
        return ("Carpool with id:" + id + " has been removed successfully");
    }

    @PUT
    @Path("/joinCarpool/{carpoolID}/{userID}/")
    public Response updateUserDetails(@PathParam("carpoolID")int carpoolID,
                                      @PathParam("userID")int userID,
                                      @Context HttpServletResponse response){

        User u = Memory.users.get(userID-1);
        Carpool c = Memory.carpools.get(carpoolID-1);

        //u.addCarpool(c);
        c.addPassenger(u);

        return Response.noContent().build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public void addCarpool(@FormParam("date")Date date,
                           @FormParam("time")Time time,
                           @FormParam("driver")int driver,
                           @Context HttpServletResponse response){
        User u = Memory.users.get(driver-1);
        Carpool c = new Carpool(date, time, u);

        Memory.carpools.add(c);
        //u.addCarpool(c);

        try {
            response.sendRedirect("/index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
