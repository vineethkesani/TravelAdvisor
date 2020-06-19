/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Vineeth Reddy
 */
@Named(value = "attraction")
@SessionScoped
public class Attraction implements Serializable{

    /**
     * Creates a new instance of Attraction
     */
    private String att_name;
    private String att_desc;
    private String att_city;
    private String att_state;
    private String att_tag;
    private float score;
    private String approve_attraction;

    public Attraction(String n , String c, String s, String d, String t, float sc)
    {
        
        att_name = n;
        att_desc = d;
        att_city = c;
        att_state = s;
        att_tag = t;
        score=sc;  
    }

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

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getApprove_attraction() {
        return approve_attraction;
    }

    public void setApprove_attraction(String approve_attraction) {
        this.approve_attraction = approve_attraction;
    }
    
    
}
