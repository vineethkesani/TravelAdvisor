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
@Named(value = "createNewAttraction")
@RequestScoped
public class CreateNewAttraction {

    /**
     * Creates a new instance of CreateNewAttraction
     */
    
    private String att_name;
    private String att_desc;
    private String att_city;
    private String att_state;
    private String att_tag;
    // private String approve_attraction;
    
    DataStorage data = new SQL_database();

    public String getAtt_name() {
        return att_name;
    }

    public void setAtt_name(String att_name) {
        this.att_name = att_name;
    }

    public String getAtt_desc() {
        return att_desc;
    }

    public void setAtt_desc(String att_desc) {
        this.att_desc = att_desc;
    }

    public String getAtt_city() {
        return att_city;
    }

    public void setAtt_city(String att_city) {
        this.att_city = att_city;
    }

    public String getAtt_state() {
        return att_state;
    }

    public void setAtt_state(String att_state) {
        this.att_state = att_state;
    }

    public String getAtt_tag() {
        return att_tag;
    }

    public void setAtt_tag(String att_tag) {
        this.att_tag = att_tag;
    }
    
    
    public String CreateAttraction(String userid) {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            return ("internalError");
        }

        if(!(UniqueAttraction(att_name))){
                return ("Attraction name already exists. Please select other name");
        }
        else{
            return data.createAttraction(userid,att_name, att_desc, att_city, att_state, att_tag);
        }
        
    }
    
    public static boolean UniqueAttraction(String attname){
              ArrayList<String> allattractionnames = SQL_database.allAttractions();
              for(String var:allattractionnames)
              {
                  if(var.equals(attname))
                  {
                      return false;
                  }
              }
              return true;           
    }
    
    public String approveAttraction(String approve_attraction){
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            return ("internalError");
        }
        
        String res = data.approve(approve_attraction);
        if(res.equals("Successful")){
            return "AttractionApproved";
        }
        else{
           return ("internalError");   
        }
        
        
    }
    
}
