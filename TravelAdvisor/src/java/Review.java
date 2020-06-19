/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vineeth Reddy
 */
public class Review {
    
    private String id;
    private String att_name;
    private String user;
    private String comments;
    private float score;
    private String dateandtime;
    
    public Review(String i, String n, String u,  float s, String c, String d)
    {
        id=i;
        att_name = n;
        user=u;
        score=s;
        comments=c;
        dateandtime=d;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAtt_name() {
        return att_name;
    }

    public void setAtt_name(String att_name) {
        this.att_name = att_name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getDateandtime() {
        return dateandtime;
    }

    public void setDateandtime(String dateandtime) {
        this.dateandtime = dateandtime;
    }
    
    
}
