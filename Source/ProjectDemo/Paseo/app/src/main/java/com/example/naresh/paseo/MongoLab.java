package com.example.naresh.paseo;


import android.app.Application;
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
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Naresh on 16-02-2016.
 */
public class MongoLab extends Application {

    public String firstname="";
    public String lastname="";
    public String oid="";
    public String mobile="";
    public String emergency1="";
    public String emergency2="";
    public String getGlobalVarValue() {
        return firstname;
    }
    public void Post(String fname,String lname,String email, String password,int check,String mobile,String dlicence) {
        post p=new post(fname,lname,email,password,check,mobile,dlicence);
        p.execute("");

    }
    public void Get(String email, String password, Context c) {
        get g=new get(email,password,c);
        g.execute("");

    }

    public void Put(JSONObject replace,String url){

        Put p=new Put(replace,url);
        p.execute("");
    }

    class Put extends AsyncTask{
        JSONObject j;
        String url;
        Put(JSONObject j,String url){
            this.j=j;
            this.url=url;

        }
        @Override
        protected Object doInBackground(Object[] params) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPut put=new org.apache.http.client.methods.HttpPut(url);
            try {
                JSONObject rep=new JSONObject();
                rep.put("$set", j);
                put.setEntity(new ByteArrayEntity(rep.toString().getBytes("UTF-8")));
                put.setHeader("Content-Type", "application/json");
                httpClient.execute(put);

            }
            catch (Exception e){}
            return null;
        }
    }

    class post extends AsyncTask<String, String,String> {

        String fname,lname,email;
        String password;
        String flocation,tlocation,date,time,mobile,dlicence;
        int check;
        /*post(String fname,String lname,String email, String password){

            this.fname=fname;
            this.lname=lname;
            this.email=email;
            this.password=password;

        }*/
        post(String flocation,String tlocation,String date, String time,int check,String mobile,String dlicence){
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
                this.mobile=mobile;
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
                j.put("mobile",mobile);
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
                Userdetails ud=new Userdetails();
                j.put("flocation", flocation);
                j.put("tlocation", tlocation);
                j.put("date", date);
                j.put("time", time);
                String pmobile=ud.getmobile();
                //Log.d("pmobile: ",pmobile);
                j.put("pmobile",pmobile);
                String puid=ud.getoid();
                String puname=ud.getfname();
                j.put("puname",puname);
                //Log.d("puid: ",puid);
                j.put("puid",puid);
                j.put("purating","");
                j.put("ruid","");
                j.put("runame","");
                j.put("rstatus","");
                j.put("rmobile","");
                /*
                j.put("ruid2","");
                j.put("runame2","");
                j.put("rstatus2","");
                j.put("rmobile2","");
                */
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
                Intent intent= new Intent(c.getApplicationContext(), Main2Activity.class);
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
                Log.d("login found count: ",out);
                int res=Integer.parseInt(out);
                if(res==1){
                    isAccountExisted=true;
                    Log.d("test","account existed");
                    URL url1= new URL("https://api.mongolab.com/api/1/databases/asecarpool/collections/userdetails?q={\"email\":\""+email+"\",\"password\":\""+password+"\"}&apiKey=ztCS-x7D_40BrPn_vDa4LXIqW_VXO5qk") ;
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(url1.openStream()));
                    String out1=br1.readLine();
                    Log.d("string out1",out1);
                    JSONArray reader = new JSONArray(out1);
                    Log.d("ddd",out1);
                    try {
                        for (int i = 0; i < reader.length(); i++) {
                            JSONObject jsonobject = reader.getJSONObject(i);
                            JSONObject oidobj=jsonobject.getJSONObject("_id");
                            oid=oidobj.getString("$oid");
                            firstname = jsonobject.getString("fname");
                            lastname = jsonobject.getString("lname");
                            mobile=jsonobject.getString("mobile");
                            emergency1=jsonobject.getString("Emergency1");
                            emergency2=jsonobject.getString("Emergency2");
                            //String date = jsonobject.getString("$oid");
                            //String time = jsonobject.getString("email");
                            Log.d("ttessst", firstname + " " + lastname +" "+ oid +" ");
                            Userdetails ud=new Userdetails();
                            ud.Userdetails(oid,firstname,lastname,mobile,emergency1,emergency2);
                            //ud.setoid(oid);

                            //finalop=finalop+flocation+" "+tlocation+" "+date+" "+time+"\n";

                        }
                    }
                    catch (Exception e){

                        e.printStackTrace();
                    }
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
