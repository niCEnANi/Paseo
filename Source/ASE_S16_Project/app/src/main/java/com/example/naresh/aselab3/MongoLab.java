package com.example.naresh.aselab3;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naresh on 16-02-2016.
 */
public class MongoLab  {


    public void Post(String fname,String lname,String email, String password,int check,String passport,String dlicence) {
        post p=new post(fname,lname,email,password,check,passport,dlicence);
        p.execute("");

    }
    public void Get(String email, String password, Context c) {
        get g=new get(email,password,c);
        g.execute("");

    }


    class post extends AsyncTask<String, String,String> {

        String fname,lname,email;
        String password;
        String flocation,tlocation,date,time,passport,dlicence;
        int check;
        /*post(String fname,String lname,String email, String password){

            this.fname=fname;
            this.lname=lname;
            this.email=email;
            this.password=password;

        }*/
        post(String flocation,String tlocation,String date, String time,int check,String passport,String dlicence){
            if(check==1) {
                this.flocation = flocation;
                this.tlocation = tlocation;
                this.date = date;
                this.time = time;
                this.check = check;
            }
            if(check==0){
                //but these are fname,lname,email,password form signup page
                this.fname=flocation;
                this.lname=tlocation;
                this.email=date;
                this.password=time;
                this.passport=passport;
                this.dlicence=dlicence;


            }

        }


        @Override
        protected void onPostExecute(String s) {
            Log.d("thread", "Success");
        }

        @Override
        protected String doInBackground(String... params) {


        if(check==0) {
            HttpClient httpClient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost("https://api.mongolab.com/api/1/databases/asecarpool/collections/userdetails?apiKey=ztCS-x7D_40BrPn_vDa4LXIqW_VXO5qk");


            JSONObject j = new JSONObject();


            try {
                j.put("fname", fname);
                j.put("lname", lname);
                j.put("email", email);
                j.put("password", password);
                j.put("passport",passport);
                j.put("dlicence",dlicence);
                httpPost.setEntity(new ByteArrayEntity(j.toString().getBytes("UTF-8")));
                httpPost.setHeader("Content-Type", "application/json");
                httpClient.execute(httpPost);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(check==1){
            HttpClient httpClient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost("https://api.mongolab.com/api/1/databases/asecarpool/collections/tripdetails?apiKey=ztCS-x7D_40BrPn_vDa4LXIqW_VXO5qk");


            JSONObject j = new JSONObject();


            try {
                j.put("flocation", flocation);
                j.put("tlocation", tlocation);
                j.put("date", date);
                j.put("time", time);
                httpPost.setEntity(new ByteArrayEntity(j.toString().getBytes("UTF-8")));
                httpPost.setHeader("Content-Type", "application/json");
                httpClient.execute(httpPost);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

            return null;


        }
    }
    class get extends AsyncTask<String, String,String> {

        String email;
        String password;
        boolean isAccountExisted=false;
        Context c;
        get(String email,String password, Context c){

            this.email=email;
            this.password=password;
            this.c=c;


        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("get thread", "Success");
            if(isAccountExisted)
            {
                Intent intent= new Intent(c.getApplicationContext(), HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(intent);}
            else
            {
                Toast.makeText(c.getApplicationContext(),"Wrong Credentials !!",Toast.LENGTH_SHORT).show();

            }

        }

        @Override
        protected String doInBackground(String... params) {
            try {
            URL url= new URL("https://api.mongolab.com/api/1/databases/asecarpool/collections/userdetails?q={\"email\":\""+email+"\",\"password\":\""+password+"\"}&c=true&apiKey=ztCS-x7D_40BrPn_vDa4LXIqW_VXO5qk") ;
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                String out=br.readLine();
                Log.d("trail",out);
                int res=Integer.parseInt(out);
                if(res==1){
                    isAccountExisted=true;
                    Log.d("test","account existed");
                }
                else
                {
                     Log.d("test","account doesnt exist");
                }
                Log.d("get thread output",""+br.readLine());

            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
