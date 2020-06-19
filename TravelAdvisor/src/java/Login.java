/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Vineeth Reddy
 */
@Named(value = "login")
@SessionScoped
public class Login implements Serializable {

    private String userid;
    private String password;
    private String searchtag;
    
    private OnlineAccount theLoginAccount;
    private AdminAccount theAdmin;
    private String viewatt;
    private float review_score;
    private String review_comments;
 
    
    
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public OnlineAccount getTheLoginAccount() {
        return theLoginAccount;
    }

    public void setTheLoginAccount(OnlineAccount theLoginAccount) {
        this.theLoginAccount = theLoginAccount;
    }

    public String getSearchtag() {
        return searchtag;
    }

    public void setSearchtag(String searchtag) {
        this.searchtag = searchtag;
    }

    public AdminAccount getTheAdmin() {
        return theAdmin;
    }

    public void setTheAdmin(AdminAccount theAdmin) {
        this.theAdmin = theAdmin;
    }

    public String getViewatt() {
        return viewatt;
    }

    public void setViewatt(String viewatt) {
        this.viewatt = viewatt;
    }

    public float getReview_score() {
        return review_score;
    }

    public void setReview_score(float review_score) {
        this.review_score = review_score;
    }

    public String getReview_comments() {
        return review_comments;
    }

    public void setReview_comments(String review_comments) {
        this.review_comments = review_comments;
    }

    
    
    public String Login() {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //return to internalError.xhtml
            return ("internalError");
        }
        
        DataStorage data = new SQL_database();
        if(userid.equals("admin")){
            if(password.equals("admin")){
                theAdmin = new AdminAccount(userid,password);
                theAdmin.dispusers();
                theAdmin.dispapproved();
                theAdmin.disppending();
                return ("adminpage");
            }
            else
                return("loginNotOk");
        }
        
        else{
            String fileName = data.login(userid, password);
            if(fileName.equals("welcome"))
            {
                theLoginAccount = new OnlineAccount(userid,password);
                theLoginAccount.setData(data);
                theLoginAccount.youMayLike();
                theLoginAccount.favorite();
                return "welcome";
            }
            else
            {
                return fileName;
            }
        }  
    }
    
    public String Search(){
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //return to internalError.xhtml
            return ("internalError");
        }
        theLoginAccount.searchMethod(searchtag);
        
        return ("SearchResult");    
    }
    
    public String DisplayAttraction(){
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //return to internalError.xhtml
            return ("internalError");
        }
        // theLoginAccount.searchMethod(searchtag);
        theLoginAccount.setViewatt(viewatt);
        theLoginAccount.getAttraction();
        theLoginAccount.getReview();
        return ("DisplayAttraction");
    }
    
    public String DisplayAfterSearch(String name){
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //return to internalError.xhtml
            return ("internalError");
        }
        // public String current_attr = name;
        theLoginAccount.setViewatt(name);
        theLoginAccount.getAttraction();
        theLoginAccount.getReview();
        return ("DisplayAttraction");
    
    }
    
    DataStorage data = new SQL_database();
   public String clickReview(String name){
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //return to internalError.xhtml
            return ("internalError");
        }
        
        // theLoginAccount.setReview_att_name(name);
        theLoginAccount.setReview_Att_Name(name);
        return("Review");
   
    }
   
   public String submitReview(){
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //return to internalError.xhtml
            return ("internalError");
        }
        Date date = new Date();
        String d = date.toString();
        return theLoginAccount.Review(userid,review_score,review_comments,d);
   
    }
   
   public String clickFav(String name){
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //return to internalError.xhtml
            return ("internalError");
        }
        
        // theLoginAccount.setReview_att_name(name);
        theLoginAccount.setFav_Att_Name(name);
        return("Favorite_added");
   
    }
   
   public String submitFav(){
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //return to internalError.xhtml
            return ("internalError");
        }
        
        return theLoginAccount.Favorite(userid);
    }
   
   
    
}
