package com.example.naresh.paseo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class FeedActivity extends AppCompatActivity implements AsyncResponse {
    get getc=new get();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        getc.delegate=this;
        getc.execute();
    }

    public void processFinish(String output){
        TextView sample= (TextView) findViewById(R.id.textView_opsample);
        //sample.setText(output);
        String finalop="From To Date Time"+"\n\n";
        try {
            JSONArray reader = new JSONArray(output);
            for (int i = 0; i < reader.length(); i++) {
                JSONObject jsonobject = reader.getJSONObject(i);
                String flocation = jsonobject.getString("flocation");
                String tlocation = jsonobject.getString("tlocation");
                String date=jsonobject.getString("date");
                String time=jsonobject.getString("time");
                finalop=finalop+flocation+" "+tlocation+" "+date+" "+time+"\n";

            }
            sample.setText(finalop);
        }catch (Exception e){


        }

        /*try {
            JSONArray reader = new JSONArray(output);
            String fl=reader.getString(1);
            Log.d("h",fl);
            Log.d("h", output);
            sample.setText(fl);

            //JSONArray convertedTextArray = reader.getJSONArray("");
            //Log.d("okHttp", jsonResult.toString());
            //final String convertedText = convertedTextArray.get(0).toString();


        }catch (Exception e){

            Log.d("after catch",""+e.toString());
        }*/
        //Here you will receive the result fired from async class
        //of onPostExecute(result) method.
    }

}

class get extends AsyncTask<String, String,String> {

    /*String email;
    String password;
    boolean isAccountExisted=false;
    Context c;
    get(String email,String password, Context c){

        this.email=email;
        this.password=password;
        this.c=c;


    }*/

    public AsyncResponse delegate = null;


    @Override
    protected void onPostExecute(String s) {
        delegate.processFinish(s);
        Log.d("get thread", "Success");

        /*if(isAccountExisted)
        {
            Intent intent= new Intent(c.getApplicationContext(), MapsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            c.startActivity(intent);}
        else
        {
            Toast.makeText(c.getApplicationContext(), "Wrong Credentials !!", Toast.LENGTH_SHORT).show();

        }*/

    }

    @Override
    protected String doInBackground(String... params) {
        try {
            URL url= new URL("https://api.mongolab.com/api/1/databases/asecarpool/collections/tripdetails?&apiKey=ztCS-x7D_40BrPn_vDa4LXIqW_VXO5qk") ;
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String out=br.readLine();
            Log.d("feed : ",out);
            return out;
            /*int res=Integer.parseInt(out);
            if(res==1){
                isAccountExisted=true;
                Log.d("test","account existed");
            }
            else
            {
                Log.d("test","account doesnt exist");
            }
            Log.d("get thread output",""+br.readLine());
            */

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
