/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import static jdk.nashorn.internal.objects.NativeString.substr;
/**
 *
 * @author Vineeth Reddy
 */
public class SQL_database implements DataStorage{
    
    static final String DATABASE_URL = 
                "jdbc:mysql://mis-sql.uhcl.edu/kesaniv1536?useSSL=false";
     static final String db_id = "kesaniv1536";
     static final String db_psw = "1847246";
     Connection connection = null;
     Statement statement = null;
     static Connection connect = null;
     static Statement stm = null;
    @Override
    public String createAccount(String name, String userid, String pwd, String tag1, String tag2){
        try
        {
            
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                    db_id, db_psw);
            connection.setAutoCommit(false);
            //crate the statement
            statement = connection.createStatement();
            int r = statement.executeUpdate("insert into user values"
                        + "('" + name + "', '" + userid + "', '" + pwd + "', '" + tag1 + "', '"
                        + tag2 +"')");
                
                // System.out.println();
            connection.commit();
            connection.setAutoCommit(true);
            return ("Account creation successful!");
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return("Something wrong during the creation process!");
        }
        finally
        {
             //close the database
             try
             {
                 // resultSet.close();
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
        }
    }
    
    @Override
    public String login(String userid, String password){
    
        ResultSet rs = null;
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                    db_id, db_psw);
            connection.setAutoCommit(false);
            //crate the statement
            statement = connection.createStatement();
            rs= statement.executeQuery("Select * from user where User_ID='"+userid+"'");
            
            if(rs.next())
            {
                //the id is found, check the password
                if(password.equals(rs.getString("pwd")))
                {
                    //password is good
                    return ("welcome" );
                    // displaying the user menu
                    // User.MainMenu(id);
                }
                else
                {
                    //password is not correct
                    // return "The password is not correct!";
                    return("loginNotOk");
                     
                }
            }
            else
            {
                // return "The login ID is not found!";
                System.out.println("User ID doesnot exists. Please enter correct ID or register if you are new.");
                return("loginNotOk");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Something wrong during the creation process!");
            return("loginNotOk");
            
        
        }
        finally
        {
        //close the database
        try
        {
            rs.close();
            statement.close();
            connection.close();
        }
         catch(Exception e)
         {
            e.printStackTrace();
            System.out.println("Something went wrong");
         }
        }    
    }
    
    @Override
    public ArrayList youmaylike(String user){
	ArrayList<String> yml = new ArrayList<String>();
        Statement statement2=null;
        ResultSet fav_tags = null;
        ResultSet youmaylike = null;
        String fav_tag1="";
        String fav_tag2="";
        String fav_tag01="";
        String fav_tag02="";
        
        try
        {
            
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                    db_id, db_psw);
            connection.setAutoCommit(false);
            //crate the statement
            statement = connection.createStatement();
            statement2 = connection.createStatement();
            // search_attr=statement.executeQuery("select * from attractions where Attraction_Name like'%" +searchtag + "%' or Tag like '%"+searchtag+"%' or State like '%"+searchtag+"%' or City like '%"+searchtag+"%' and Status = approved' order by Score");
            
            fav_tags = statement.executeQuery("Select Tag1, Tag2 from user where User_ID='"+user+"'");
            
            while(fav_tags.next()){
                fav_tag1= fav_tags.getString("Tag1");
                fav_tag2 = fav_tags.getString("Tag2");
                // String fav_tag2 = fav_tags.getString("Tag2");
            }
            fav_tag01 = substr(fav_tag1,0,fav_tag1.indexOf(' '));
            fav_tag02 = substr(fav_tag2,0,fav_tag2.indexOf(' '));
            
            
            youmaylike = statement2.executeQuery("select Attraction_Name from attractions where tag ='"+fav_tag01+"' or tag='"+fav_tag02+"' order by score desc");
            int j=1;
            while (youmaylike.next() && j<=3) {    
                yml.add(youmaylike.getString(1));
                j++;
            }
			
            System.out.println();
            connection.commit();
            connection.setAutoCommit(true);
            
        }
        catch (SQLException e)
        {
            System.out.println("Something wrong during the creation process!");
            e.printStackTrace();
        }
        finally
        {
             //close the database
             try
             {
                 youmaylike.close();
                 fav_tags.close();
                 statement.close();
                 statement2.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
        }
	return yml;
    }
    
    @Override
    public ArrayList search(String searchtag){
        ResultSet search_attr = null;
        // ArrayList<String> search_result = new ArrayList<String>();
    
        try
        {
            
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                db_id, db_psw);
            connection.setAutoCommit(false);
            //crate the statement
            statement = connection.createStatement();
           
            // searching the attraction using tag, city, state, or name.
            search_attr=statement.executeQuery("select * from attractions where (Status = 'approved') and (Attraction_Name like '%" + searchtag+ " %' or tag like '%" +searchtag+ "%' or City like '%" + searchtag+ "%' or State like '%" +searchtag+ "%' or tag like '%" +searchtag+ "%')  order by Score");
            
            ArrayList<Attraction> search_result = new ArrayList<Attraction>();
            while(search_attr.next())
            {
                Attraction anAttraction = new Attraction(search_attr.getString(1),
		search_attr.getString(2), search_attr.getString(3),
		search_attr.getString(4), search_attr.getString(5),
		search_attr.getFloat(6));
                        
		search_result.add(anAttraction);
            }
            return search_result;
        }
        catch (SQLException e)
        {
            // System.out.println("Something wrong during the creation process!");
            e.printStackTrace();
            return null;
        }
        finally
        {
             //close the database
             try
             {
                 search_attr.close();
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
        }
        // return search_result;
    }
    
    @Override
    public String createAttraction(String user, String name, String desc, String city, String state, String tag){
     
        try
        {
            
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                    db_id, db_psw);
            connection.setAutoCommit(false);
            //crate the statement
            statement = connection.createStatement();
            
            int r = statement.executeUpdate("insert into attractions values" + "('" +name+ "', '" + city+ "', '" + state+ 
						"', '" +desc+ "', '" +tag+ "', 0, 'pending', '" + user+ "')");
           
            System.out.println();
            connection.commit();
            connection.setAutoCommit(true);
             return ("Attraction created successfully!");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return ("Something wrong during the creation process!");
        }
        finally
        {
             //close the database
             try
             {
                 // resultSet.close();
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
        }   
    }
    
    @Override
    public ArrayList displayUsers(){
        
        ArrayList<String> allusers = new ArrayList<String>();
        ResultSet dispusers = null;
        
        try {
            connection = DriverManager.getConnection(DATABASE_URL,db_id, db_psw);
            statement= connection.createStatement();
            dispusers = statement.executeQuery("select User_ID from user");
            while (dispusers.next()) {    
                allusers.add(dispusers.getString(1));
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        finally
        {
            //close the database
            try{
                dispusers.close();
                connection.close();
                statement.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return allusers;
    }
    
    @Override
    public ArrayList pendingAttractions(){
        ArrayList<String> allpending = new ArrayList<String>();
        ResultSet pend_attr = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL,db_id, db_psw);
            statement= connection.createStatement();
            pend_attr = statement.executeQuery("select Attraction_Name from attractions where Status = 'pending'");
            while (pend_attr.next()) {    
                allpending.add(pend_attr.getString(1));
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        finally
        {
            //close the database
            try{
                pend_attr.close();
                connection.close();
                statement.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return allpending;
    }
    
    @Override
    public ArrayList approvedAttractions(){
        ArrayList<String> allapproved = new ArrayList<String>();
        ResultSet approved_attr = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL,db_id, db_psw);
            statement= connection.createStatement();
            approved_attr = statement.executeQuery("select Attraction_Name from attractions where Status = 'approved'");
            while (approved_attr.next()) {    
                allapproved.add(approved_attr.getString(1));
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        finally
        {
            //close the database
            try{
                approved_attr.close();
                connection.close();
                statement.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return allapproved;
    }
    
    @Override
    public String approve(String name){
        
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                    db_id, db_psw);
            connection.setAutoCommit(false);
            //crate the statement
            statement = connection.createStatement();
            // updating the status to approved in attractions table
            int r = statement.executeUpdate("update attractions set Status = 'approved' where Attraction_Name = '" + name + "'"); 
            System.out.println();
            connection.commit();
            connection.setAutoCommit(true);
            return ("Successful");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return ("Something wrong during the creation process!");
            
        }
        finally
        {
             //close the database
             try
             {
                 // resultSet.close();
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
        }
    }
    
    
    @Override
    public ArrayList<Attraction> getAttraction(String att_name){
        Statement statement2=null;
        ResultSet att_details = null;
        // ResultSet att_reviews = null;
        // storing all approved attractions into arraylist
        ArrayList<String> allAttr = allAttractions();
        
        for(String var:allAttr)
        {
            if(var.equals(att_name))
            {
                // Display Attraction
                try
                {
            
                    //connect to the databse
                    connection = DriverManager.getConnection(DATABASE_URL, 
                                        db_id, db_psw);
                    connection.setAutoCommit(false);
                    //crate the statement
                    statement = connection.createStatement();
                    att_details=statement.executeQuery("select * from attractions where Attraction_Name='" + att_name+ "'");
		    ArrayList<Attraction> aList = new ArrayList<Attraction>();
            
		    while(att_details.next())
		    {
                        Attraction anAttraction = new Attraction(att_details.getString(1),
			att_details.getString(2), att_details.getString(3),
			att_details.getString(4), att_details.getString(5),
			att_details.getFloat(6));
                        
			aList.add(anAttraction);
		    }
		    return aList;
		}
                        
                catch (SQLException e)
                {
                    
                    e.printStackTrace();
                    return null;
                }
                finally
                {
                    //close the database
                    try
                    {
                        att_details.close();
                        // att_reviews.close();
                        statement.close();
                        connection.close();    
                    }
                     catch(Exception e)
                    {
                         e.printStackTrace();
                    }
                }
                      
            }          
        }
        return null;
    }
    
    @Override
    public ArrayList<Review> getReviews(String att_name){
	ResultSet att_reviews = null;
        // storing all approved attractions into arraylist        
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
            db_id, db_psw);
            connection.setAutoCommit(false);
            //crate the statement
            statement = connection.createStatement();
            att_reviews=statement.executeQuery("select * from review where Attraction_Name='" + att_name+ "'");
			
	    ArrayList<Review> reviewList = new ArrayList<Review>();
            while(att_reviews.next())
            {
		Review aReview = new Review(att_reviews.getString(1),
		att_reviews.getString(2), att_reviews.getString(3),
		att_reviews.getFloat(4), att_reviews.getString(5),
		att_reviews.getString(6));
                
                reviewList.add(aReview);
            }
            return reviewList;
        }   
        catch (SQLException e)
        {        
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                att_reviews.close();
                // att_reviews.close();
                statement.close();
                connection.close();    
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }           
    }
        
    @Override
    public ArrayList<String> displayfavattr(String user){
        ResultSet fav_attr = null;
        ArrayList<String> favorite = new ArrayList<String>();
        try {
            connection = DriverManager.getConnection(DATABASE_URL,db_id, db_psw);
            statement= connection.createStatement();
            fav_attr = statement.executeQuery("select * from fav_destination where User_ID='"+user+"'");
            System.out.println("Your favorite attractions are:");
            
			
			while (fav_attr.next()) {
                
                favorite.add(fav_attr.getString(2));
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        finally
        {
            //close the database
            try{
                fav_attr.close();
                connection.close();
                statement.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return favorite;
    }
    
    @Override
    public String addfavattr(String att_name, String user){
        Statement statement_addfav = null;
        Connection connection_addfav = null;
        ResultSet fav_attr_unique = null;
        try
        {
            //connect to the databse
            connection_addfav = DriverManager.getConnection(DATABASE_URL, 
                    db_id, db_psw);
            connection_addfav.setAutoCommit(false);
            //crate the statement
            statement_addfav = connection_addfav.createStatement();
            
            fav_attr_unique = statement_addfav.executeQuery("select * from fav_destination");
            
            // validating if this destination is already added to favorites for this user
            boolean update = fav_unique(att_name, user, fav_attr_unique);
            if(update == true){
                int r = statement_addfav.executeUpdate("insert into fav_destination values" + "('" +user+ "', '" + att_name+"')");
                connection_addfav.commit();
                connection_addfav.setAutoCommit(true);
                return ("Attraction added successfully to your favorite destinations"); 
            }
            else{
               return("Attraction already added to your favorites");
            }
            

             
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return ("Something wrong while adding to your favorite places");
            
        }
        finally
        {
             //close the database
             try
             {
                 // resultSet.close();
                 statement_addfav.close();
                 connection_addfav.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
        }
    }
    
    public boolean fav_unique(String att_name, String user, ResultSet fav){
        
        boolean ans = true;
        try{
            while(fav.next() && ans){
                if(((fav.getString("User_ID")).equals(user)) && ((fav.getString("Attraction_Name")).equals(att_name))){
                    ans = false;  
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println("Something wrong while adding to your favorite places");
            e.printStackTrace();
        }
        return ans;
    }
    
    @Override
    public String postreview(String att_name, String user, float score, String comment, String date){
        Statement statement2 =null;
        Statement statement3 =null;
        Statement statement4=null;
        ResultSet review_num = null;
        ResultSet all_attr = null;
        ResultSet review_unique = null;
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                    db_id, db_psw);
            connection.setAutoCommit(false);
            //crate the statement
            statement = connection.createStatement();
            statement2 = connection.createStatement();
            statement3 = connection.createStatement();
            statement4 = connection.createStatement();
            
            all_attr = statement2.executeQuery("select * from attractions where Attraction_Name ='"+att_name+ "'");            
            
            review_unique = statement4.executeQuery("Select Attraction_Name, User_ID from review");
            
            // validating if the attraction is already reviewed by user
            boolean update = review_unique(att_name, user, review_unique);
            if(all_attr.next()){
                review_num=statement.executeQuery("select * from review");
                int nextNum = 1;
                String reviewNum = "";
                while(review_num.next())
                {
                    reviewNum = "" + review_num.getInt(1);
                    nextNum = review_num.getInt(1) + 1;
                 
                }
                if(update == true){
                    int r = statement.executeUpdate("insert into review values" + "('" +nextNum+ "', '" + att_name+ "', '" + user+ 
						"', '" +score+ "', '" +comment+ "', '" + date+ "')");
                    
                    
                    connection.commit();
                    connection.setAutoCommit(true);
                    updatescore(score,att_name);
                    return("Review added successfuly");
                }
                else{
                    return ("You have already reviewed this attraction!! You cannot review an attraction twice ");
                    
                }
   
            }
            else
            {
                // return "The login ID is not found!";
                return("This attraction doesn't exists. Please create attraction or enter correct name");
            }
 
//            System.out.println();
//            connection.commit();
//            connection.setAutoCommit(true);
            
        }
        catch (SQLException e)
        {
            
            e.printStackTrace();
            return ("Something wrong during the creation process!");
        }
        finally
        {
             //close the database
             try
             {
                 review_num.close();
                 all_attr.close();
                 statement.close();
                 statement2.close();
                 statement3.close();
                 statement4.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
        }     
    }
    
    public void updatescore(float new_score, String att_name){
        
        ResultSet update_score = null;
        // float update_score;
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, 
                    db_id, db_psw);
            connection.setAutoCommit(false);
            //crate the statement
            statement = connection.createStatement();
            
            update_score = statement.executeQuery("select score from attractions where Attraction_Name='" +att_name+"'");
            
            
            float last_score =0;
            while(update_score.next())
            {
                last_score = update_score.getFloat(1);     
            }
            float updated_score = (new_score+last_score)/2;
            int s = statement.executeUpdate("update attractions set score ='"+updated_score+"'where Attraction_Name='" +att_name+"'");
            
            System.out.println();
            connection.commit();
            connection.setAutoCommit(true);
            
        }
        catch (SQLException e)
        {
            System.out.println("Something wrong during the updation of score!");
            e.printStackTrace();
        }
        finally
        {
             //close the database
             try
             {
                 update_score.close();
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
        }
    }
    
    public boolean review_unique(String att_name, String user, ResultSet review){
        
        boolean ans = true;
        try{
            while(review.next() && ans){
                if(((review.getString("User_ID")).equals(user)) && ((review.getString("Attraction_Name")).equals(att_name))){
                    ans = false;  
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println("Something wrong while adding to your favorite places");
            e.printStackTrace();
        }
        return ans;
    }
    
    
    public static ArrayList allUsers() {
        ArrayList<String> allUsers = new ArrayList<String>();
        ResultSet resultSet = null;
        ResultSet res = null;
        
        try {
            connect = DriverManager.getConnection(DATABASE_URL,db_id, db_psw);
            stm= connect.createStatement();
            resultSet = stm.executeQuery("select User_ID from user");
            while (resultSet.next()) {
                
                allUsers.add(resultSet.getString(1));
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        finally
        {
            //close the database
            try{
                connect.close();
                stm.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return allUsers;
    }
    
    public static ArrayList allAttractions() {
        ArrayList<String> allattractions = new ArrayList<String>();

        ResultSet resultSet = null;
        ResultSet res = null;
        
        try {
            connect = DriverManager.getConnection(DATABASE_URL,db_id, db_psw);
            stm= connect.createStatement();
            resultSet = stm.executeQuery("select Attraction_Name from attractions where status='approved'");
            while (resultSet.next()) {
                
                allattractions.add(resultSet.getString(1));
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        finally
        {
            //close the database
            try{
                connect.close();
                stm.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return allattractions;
    }
}
