package com.example.naresh.paseo;

/**
 * Created by Naresh on 13-04-2016.
 */
public class Userdetails {

    public String user_firstname="";
    public String user_lastname="";
    public String user_oid="";

    public void Userdetails(String oid,String fname,String lname){
        this.user_oid=oid;
        this.user_firstname=fname;
        this.user_lastname=lname;

    }
    public String getoid(){
        return user_oid;
    }
    public String getfname(){
        return user_firstname;
    }
    public String getlname(){
        return user_lastname;
    }
    public void setoid(String oid){
        this.user_oid=oid;
    }
    public void setfname(String fname){
        this.user_firstname=fname;
    }
    public void setlname(String lname){
        this.user_lastname=lname;
    }
}
