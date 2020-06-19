/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Vineeth Reddy
 */
@Named(value = "register")
@RequestScoped
public class Register {

    /**
     * Creates a new instance of Register
     */
    private String name;
    private String userid;
    private String password;
    private String tag1;
    private String tag2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }
    
    DataStorage data = new SQL_database();
    public String register() {  
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception e)
        {
            return ("Internal Error! Please try again later.");
        }
        // checking if ID is unique
        if(!UniqueID(userid)){
            return ("ID already taken. Please go back to main page and try another ID");
        }
        else{
            return data.createAccount(name, userid, password, tag1, tag2);
        } 
    }
    
    
    public static boolean UniqueID(String userid){
              ArrayList<String> allUserID = SQL_database.allUsers();            
              for(String var:allUserID)
              {
                  if(var.equals(userid))
                  {
                      
                      return false;
                  }
              }
              return true;           
    }
}
