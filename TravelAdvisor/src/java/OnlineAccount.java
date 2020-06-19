import java.util.ArrayList;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vineeth Reddy
 */
public class OnlineAccount {
    
    private String userid;
    private String password;
    private DataStorage data;
    
    private ArrayList<String> yml = new ArrayList<String>();
    private ArrayList<String> fav_attr= new ArrayList<String>();
    // private ArrayList<String> searchres = new ArrayList<String>();
    private ArrayList<Attraction> searchres = new ArrayList<Attraction>();
    String viewatt;
    private ArrayList<Attraction> att_details = new ArrayList<Attraction>();
    private ArrayList<Review> reviews = new ArrayList<Review>();
    
    private String Review_Att_Name;
    private String Fav_Att_Name;
    

    public String getFav_Att_Name() {
        return Fav_Att_Name;
    }

    public void setFav_Att_Name(String Fav_Att_Name) {
        this.Fav_Att_Name = Fav_Att_Name;
    }

    
    
    public String getReview_Att_Name() {
        return Review_Att_Name;
    }

    public void setReview_Att_Name(String Review_Att_Name) {
        this.Review_Att_Name = Review_Att_Name;
    }

    public void favorite(){
        fav_attr=data.displayfavattr(userid);
        // return fav_attr;
    }
    
    public void youMayLike(){
        yml = data.youmaylike(userid);
    }
    
    public void searchMethod(String searchtag){
        searchres = data.search(searchtag);
    }
    
    public ArrayList<Attraction> returnSearch(){
        return searchres;
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
    
    public DataStorage getData() {
        return data;
    }

    public void setData(DataStorage data) {
        this.data = data;
    }
    
    public ArrayList<String> getYml() {
        return yml;
    }

    public void setYml(ArrayList<String> yml) {
        this.yml = yml;
    }

    public ArrayList<Attraction> getSearchres() {
        return searchres;
    }

    public void setSearchres(ArrayList<Attraction> searchres) {
        this.searchres = searchres;
    }

    

    public ArrayList<Attraction> getAtt_details() {
        return att_details;
    }

    public void setAtt_details(ArrayList<Attraction> att_details) {
        this.att_details = att_details;
    }

    public String getViewatt() {
        return viewatt;
    }

    public void setViewatt(String viewatt) {
        this.viewatt = viewatt;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }
    
    public void getAttraction(){
        att_details = data.getAttraction(viewatt);
    }
    
    public ArrayList<Attraction> showAttraction()
    {
        return att_details;
    }
    
    public void getReview(){
        reviews = data.getReviews(viewatt);
    }
    
    public ArrayList<Review> showReviews()
    {
        return reviews;
    }

    public ArrayList<String> getFav_attr() {
        return fav_attr;
    }

    public void setFav_attr(ArrayList<String> fav_attr) {
        this.fav_attr = fav_attr;
    }


    
    public String Review(String user, float score,String comments,String d){
        return data.postreview(Review_Att_Name, user, score, comments, d);
    }
    
    public String Favorite(String user){
        return data.addfavattr(Fav_Att_Name, user);
    }
    
    
    public OnlineAccount(String i, String p)
    {
        userid = i;
        password = p;
    }
    
    
    
    
    
}
