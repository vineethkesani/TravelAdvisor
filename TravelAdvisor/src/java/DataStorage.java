
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vineeth Reddy
 */
public interface DataStorage {
    String createAccount(String name, String userid, String pwd, String tag1, String tag2);
    String login(String userid,String password);
    ArrayList youmaylike(String userid);
    ArrayList search(String searchtag);
    String createAttraction(String user, String name, String desc, String city, String state, String tag);
    ArrayList displayUsers();
    ArrayList pendingAttractions();
    ArrayList approvedAttractions();
    String approve(String name);
    ArrayList<Attraction> getAttraction(String att_name);
    ArrayList<Review> getReviews(String att_name);
    ArrayList<String> displayfavattr(String user);
    String postreview(String att_name, String user, float score, String comment, String date);
    String addfavattr(String att_name, String user);
}
