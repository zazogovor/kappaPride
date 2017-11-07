package WebServices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.EmergencyContact;
import entity.Review;
import entity.User;
import entity.UserProfile;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;

import static WebServices.Memory.profiles;

@Path("/review")
public class ReviewWebService {

    @GET
    @Path("/reviews")
    @Produces("application/json")
    public String getReviews(){
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(Memory.reviews);
        return json;
    }

    @GET
    @Path("/review/{id}")
    @Produces("application/json")
    public String getReview(@PathParam("id")int id){
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(Memory.reviews.get(id-1));
        return json;
    }

    @DELETE
    @Path("/remove/{id}")
    public String removeReview(@PathParam("id")int id){
        Memory.reviews.set(id-1, null);
        return ("Review with id:" + id + " has been removed successfully");
    }

    @POST
    @Path("/add")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newUser(@FormParam("reviewer") int reviewer,
                        @FormParam("reviewee") int reviewee,
                        @FormParam("rating") int rating,
                        @FormParam("comment") String comment,
                        @Context HttpServletResponse response){

        User u1 = Memory.users.get(reviewer-1);
        User u2 = Memory.users.get(reviewee-1);

        Review r = new Review(rating, comment, u1, u2.getProfile());
        Memory.reviews.add(r);

        try {
            response.sendRedirect("/index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
