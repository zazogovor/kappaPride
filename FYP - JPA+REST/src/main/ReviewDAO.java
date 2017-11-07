package main;

import java.util.List;
import persistence.PersistenceUtil;
import entity.Review;
import entity.User;
import entity.UserProfile;


public class ReviewDAO {

	public void reviewPrinter(Review r){
		System.out.println("Poster: "+r.getPostee().getUsername());
		System.out.println("Rating: "+r.getRating());
		System.out.println("Comment: "+r.getComment());
		System.out.println("");
	}
	
	public void viewReviews(){
		List<Review> reviews = PersistenceUtil.findAllReviews();
		for(Review r:reviews){
			reviewPrinter(r);
		}
	}
	
	public void viewReviewById(int id){
		Review review = PersistenceUtil.findReviewById(id);
		reviewPrinter(review);
	}
	
	public void viewReviewsByPostee(String username){
		List<Review> reviews = PersistenceUtil.findReviewsByPostee(username);
		for(Review r:reviews){
			System.out.println("Review with ID:"+r.getId()+ " was posted by " + username);
		}
	}
	
	public void viewReviewsByProfile(String username){
		List<Review> reviews = PersistenceUtil.findReviewsByReceiver(username);
		System.out.println("The following reviews are on " + username + "'s profile");
		for(Review r:reviews){
			reviewPrinter(r);
		}
	}
	
	public void updateReview(int id, int rating, String comment){
		Review review = PersistenceUtil.findReviewById(id);
		UserProfile up = review.getReceiver();
		int oldReviewRating = review.getRating();
		int oldAverageRating = up.getAverageRating();
		int numberOfReviews = up.getNumberOfReviews();
		
		
		if(rating != 0){
			review.setRating(rating);
			int oldTotal = oldAverageRating * numberOfReviews; 
			int newTotal = (oldTotal - oldReviewRating + rating) * numberOfReviews;
			up.setAverageRating((newTotal / numberOfReviews));
			
			PersistenceUtil.merge(up);
		}
		if(comment != null){
			review.setComment(comment);		
		}
		
		PersistenceUtil.merge(review);
		System.out.println("Review has been updated.");
	}
	
	
	
	public void deleteReview(int id){
		Review review = PersistenceUtil.findReviewById(id);
		PersistenceUtil.remove(review);
		System.out.println("Review with ID:" + id + " sucessfully removed from database.");
	}
	
	public void createReview(int rating, String comment, String posteeUsername, String receiverUsernam){
		User postee = PersistenceUtil.findUserByUsername(posteeUsername);
		User receiver = PersistenceUtil.findUserByUsername(posteeUsername);
		UserProfile receiverUserProfile = receiver.getProfile();
		
		Review review = new Review(rating, comment, postee, receiverUserProfile);
		PersistenceUtil.persist(review);
		System.out.println("Rating: " + rating + "stars.");
		System.out.println("Comment: " + comment + ".");
		System.out.println("Review successfully created.");
		System.out.println("");
	}
			

}
