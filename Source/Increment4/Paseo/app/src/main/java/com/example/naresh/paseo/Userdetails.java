package com.example.naresh.paseo;

/**
 * Created by Naresh on 13-04-2016.
 */
public class Userdetails {

    public static String user_firstname="";
    public static String user_lastname="";
    public static String user_oid="";
    public static String user_mobile="";
    public static String emergency_1="";
    public static String emergency_2="";

    public void Userdetails(String oid,String fname,String lname,String mobile,String emergency1,String emergency2){
        this.user_oid=oid;
        this.user_firstname=fname;
        this.user_lastname=lname;
        this.user_mobile=mobile;
        this.emergency_1=emergency1;
        this.emergency_2=emergency2;


    }
    public static String getoid(){
        return user_oid;
    }
    public static String getfname(){
        return user_firstname;
    }
    public static String getlname(){
        return user_lastname;
    }
    public static String getmobile(){
        return user_mobile;
    }
    public static String getEmergency_1(){
        return emergency_1;
    }
    public static String getEmergency_2(){
        return emergency_2;
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
    public void setmobile(String mobile){
        this.user_mobile=mobile;
    }
    public void setEmergency_1(String emergency1){
        this.emergency_1=emergency1;
    }
    public void setEmergency_2(String emergency2){
        this.emergency_2=emergency2;
    }
}
