package WebServices;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.UserProfile;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/userProfile")
public class UserProfileWebService {


    @GET
    @Path("/userProfiles")
    @Produces("application/json")
    public String getUserProfiles() {
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(Memory.profiles);
        return json;
    }

    @GET
    @Path("/userProfile/{id}")
    @Produces("application/json")
    public String getUserProfile(@PathParam("id")int id){
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(Memory.profiles.get(id-1));
        return json;
    }

    @PUT
    @Path("/updateProfile")
    @Produces(MediaType.TEXT_HTML)
    @Consumes("multipart/form-data")
    public void updateProfile( @FormParam("id") int id,
                               @FormParam("description") String description,
                               @FormParam("carDriven") String carDriven,
                               @FormParam("location") String location,
                               @Context HttpServletResponse response){

        UserProfile up = Memory.profiles.get(id-1);

        if(description != "")
            up.setProfileDescription(description);
        if(carDriven != "")
            up.setCarDriven(carDriven);
        if(location != "")
            up.setLocation(location);

        Memory.profiles.set(id-1, up);

        try {
            response.sendRedirect("/index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
