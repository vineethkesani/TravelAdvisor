
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
public class AdminAccount {
    private String userid;
    private String password;
    private ArrayList<String> users = new ArrayList<String>();
    private ArrayList<String> approved_attractions = new ArrayList<String>();
    private ArrayList<String> pending_attractions = new ArrayList<String>();
    DataStorage data = new SQL_database();

    public void dispusers(){
        users = data.displayUsers();
    }
    
    public void dispapproved(){
        approved_attractions = data.approvedAttractions();
    }
    
    public void disppending(){
        pending_attractions = data.pendingAttractions();
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

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }

    public ArrayList<String> getApproved_attractions() {
        return approved_attractions;
    }

    public void setApproved_attractions(ArrayList<String> approved_attractions) {
        this.approved_attractions = approved_attractions;
    }

    public ArrayList<String> getPending_attractions() {
        return pending_attractions;
    }

    public void setPending_attractions(ArrayList<String> pending_attractions) {
        this.pending_attractions = pending_attractions;
    }
    
    public AdminAccount(String i, String p)
    {
        userid = i;
        password = p;
    }
    
}
