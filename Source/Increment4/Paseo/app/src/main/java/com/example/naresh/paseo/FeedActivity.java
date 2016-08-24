package com.example.naresh.paseo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Exchanger;

public class FeedActivity extends AppCompatActivity implements AsyncResponse {
    get getc=new get();
    final Context context = this;
    private Button button;
    ListView lv;
    ArrayList prgmName;
    //public static int [] prgmImages={R.drawable.images,R.drawable.images1,R.drawable.images2,R.drawable.images3,R.drawable.images4,R.drawable.images5,R.drawable.images6,R.drawable.images7,R.drawable.images8};
    //public static String [] prgmNameList={"Let Us C","c++","JAVA","Jsp","Microsoft .Net","Android","PHP","Jquery","JavaScript"};
    //public static String [] FromList=new String[100];
    //public static String [] ToList=new String[100];
    //public static String [] DateList=new String[100];
    //public static String [] TimeList=new String[100];
    ArrayList<String> FromList = new ArrayList<String>();
    ArrayList<String> ToList = new ArrayList<String>();
    ArrayList<String> DateList = new ArrayList<String>();
    ArrayList<String> TimeList = new ArrayList<String>();
    ArrayList<String> MobileList = new ArrayList<String>();
    ArrayList<String> OidList = new ArrayList<String>();
    ArrayList<String> PuidList = new ArrayList<String>();
    ArrayList<String> RuidList = new ArrayList<String>();
    ArrayList<String> RstatusList = new ArrayList<String>();
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Intent intent1 = new Intent(this, LoginActivity.class);
            startActivity(intent1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get getc=new get();
        getc.delegate=this;
        getc.execute();
        try{
        Thread.sleep(2000);}
        catch (Exception e)
        {
            Log.d("thread feed",e.toString());
        }

        Log.d("Valuese",FromList.toString());
        lv=(ListView) findViewById(R.id.listView);

        lv.setAdapter(new CustomAdapter(this,FromList,ToList,DateList,TimeList,MobileList,OidList,PuidList,RuidList,RstatusList));

        /*
        button = (Button) findViewById(R.id.buttonCall);

        // add PhoneStateListener
        PhoneCallListener phoneListener = new PhoneCallListener();
        TelephonyManager telephonyManager = (TelephonyManager) this
                .getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);

        // add button listener
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:0000000000"));
                startActivity(callIntent);

            }

        });
        */
    }
    public void callto(){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:0000000000"));
        startActivity(callIntent);


    }
    //monitor phone call activities
    private class PhoneCallListener extends PhoneStateListener {

        private boolean isPhoneCalling = false;

        String LOG_TAG = "LOGGING 123";

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            if (TelephonyManager.CALL_STATE_RINGING == state) {
                // phone ringing
                Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
            }

            if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
                // active
                Log.i(LOG_TAG, "OFFHOOK");

                isPhoneCalling = true;
            }

            if (TelephonyManager.CALL_STATE_IDLE == state) {
                // run when class initial and phone call ended,
                // need detect flag from CALL_STATE_OFFHOOK
                Log.i(LOG_TAG, "IDLE");

                if (isPhoneCalling) {

                    Log.i(LOG_TAG, "restart app");

                    // restart app
                    /*
                    Intent i = getBaseContext().getPackageManager()
                            .getLaunchIntentForPackage(
                                    getBaseContext().getPackageName());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    */
                    isPhoneCalling = false;
                }

            }
        }
    }

    public void processFinish(String output){
        //TextView sample= (TextView) findViewById(R.id.textView_opsample);
        //sample.setText(output);
        //String finalop="From To Date Time"+"\n\n";
        try {
            JSONArray reader = new JSONArray(output);
            for (int i = 0; i < reader.length(); i++) {
                JSONObject jsonobject = reader.getJSONObject(i);
                JSONObject oidobj=jsonobject.getJSONObject("_id");
                String oid=oidobj.getString("$oid");
                String flocation = jsonobject.getString("flocation");
                String tlocation = jsonobject.getString("tlocation");
                String date=jsonobject.getString("date");
                String time=jsonobject.getString("time");
                if(!jsonobject.getString("pmobile").equals(null)) {
                    String pmobile = jsonobject.getString("pmobile");
                    MobileList.add(pmobile);
                    Log.d("pmobile",pmobile);
                }
                else{

                    MobileList.add("0000000000");
                }
                String puid=jsonobject.getString("puid");
                PuidList.add(puid);
                String ruid=jsonobject.getString("ruid");
                RuidList.add(ruid);
                String rstatus=jsonobject.getString("rstatus");
                RstatusList.add(rstatus);

                OidList.add(oid);
                FromList.add(flocation);
                ToList.add(tlocation);
                DateList.add(date);
                TimeList.add(time);
                //finalop=finalop+flocation+" "+tlocation+" "+date+" "+time+"\n";
            }
            Log.d("Values",FromList.toString());
            //sample.setText(finalop);
        }catch (Exception e){

        }
    }

}

class get extends AsyncTask<String, String,String> {
    public AsyncResponse delegate = null;
    @Override
    protected void onPostExecute(String s) {
        delegate.processFinish(s);
        Log.d("get thread", "Success");
    }
    @Override
    protected String doInBackground(String... params) {
        try {
            URL url= new URL("https://api.mongolab.com/api/1/databases/asecarpool/collections/tripdetails?&apiKey=ztCS-x7D_40BrPn_vDa4LXIqW_VXO5qk") ;
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String out=br.readLine();
            Log.d("feed : ",out);
            return out;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
class CustomAdapter extends BaseAdapter {
    public ArrayList<String>  Fromresult;
    public ArrayList<String>  Toresult;
    public ArrayList<String>  Dateresult;
    public ArrayList<String>  Timeresult;
    public ArrayList<String>  Mobileresult;
    public ArrayList<String>  Oidresult;
    public ArrayList<String>  Puidresult;
    public ArrayList<String>  Ruidresult;
    public ArrayList<String>  Rstatusresult;
    Context context;
    //int [] imageId;
    private static LayoutInflater inflater=null;
    public CustomAdapter(FeedActivity feedActivity, ArrayList<String>FromList,ArrayList<String>  ToList,ArrayList<String>  DateList,ArrayList<String> TimeList,ArrayList<String> MobileList,ArrayList<String>OidList,ArrayList<String>PuidList,ArrayList<String>RuidList,ArrayList<String>RstatusList) {
        // TODO Auto-generated constructor stub
        this.Fromresult=FromList;
        this.Toresult=ToList;
        this.Dateresult=DateList;
        this.Timeresult=TimeList;
        this.Mobileresult=MobileList;
        this.Oidresult=OidList;
        this.Puidresult=PuidList;
        this.Ruidresult=RuidList;
        this.Rstatusresult=RstatusList;
        this.context=feedActivity;
        //imageId=prgmImages;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return Fromresult.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return Fromresult.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        TextView fv;
        TextView dv;
        TextView ttv;
        //ImageView img;
        ImageButton callbutton;
        ImageButton Reqbutton;

        TextView info;
        ImageButton accept;
        ImageButton decline;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Holder holder=new Holder();
        View rowView;
        final Userdetails ud=new Userdetails();
        rowView = inflater.inflate(R.layout.custom_row, null);
        //Log.d("view",Fromresult.get(1));
        holder.tv=(TextView) rowView.findViewById(R.id.ToLV_textView);
        //holder.img=(ImageView) rowView.findViewById(R.id.imageView1);
        holder.tv.setText(Toresult.get(position));
        holder.fv=(TextView) rowView.findViewById(R.id.FromLV_textView);
        holder.fv.setText(Fromresult.get(position));
        holder.dv=(TextView) rowView.findViewById(R.id.DateLV_textView);
        holder.dv.setText(Dateresult.get(position));
        holder.ttv=(TextView) rowView.findViewById(R.id.TimeLV_textView);
        holder.ttv.setText(Timeresult.get(position));
        holder.callbutton=(ImageButton) rowView.findViewById(R.id.call_imageButton);
        holder.Reqbutton=(ImageButton) rowView.findViewById(R.id.Req_imageButton);
        holder.info=(TextView) rowView.findViewById(R.id.Info_textView);
        holder.accept=(ImageButton) rowView.findViewById(R.id.Accept_imageButton);
        holder.decline=(ImageButton) rowView.findViewById(R.id.Decline_imageButton);

        if(Puidresult.get(position).equals(ud.getoid())){
            holder.Reqbutton.setVisibility(View.INVISIBLE);
            holder.callbutton.setVisibility(View.INVISIBLE);
            holder.info.setText("You posted this Ride !!");
            holder.info.setVisibility(View.VISIBLE);
            if(Rstatusresult.get(position).equals("Requsted")){
                holder.accept.setVisibility(View.VISIBLE);
                holder.decline.setVisibility(View.VISIBLE);
                holder.info.setText("1 person Req your ride ");
                holder.info.setVisibility(View.VISIBLE);

            }

        }
        if(Ruidresult.get(position).equals(ud.getoid())){
            holder.Reqbutton.setVisibility(View.INVISIBLE);
            if(Rstatusresult.get(position).equals("Requsted")){
                holder.info.setText("Your Request is in pending !!");
                holder.info.setVisibility(View.VISIBLE);
            }
            else if(Rstatusresult.get(position).equals("Accepted")){
                holder.info.setText("Your Request is approved !!");
                holder.info.setVisibility(View.VISIBLE);
            }
            else{
                holder.info.setText("Your Request is declined !!");
                holder.info.setVisibility(View.VISIBLE);
            }
        }


        //holder.img.setImageResource(imageId[position]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Toast.makeText(context, "You Clicked "+Fromresult.get(position)+" To "+Toresult.get(position), Toast.LENGTH_LONG).show();
            }
        });
        holder.callbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"+Mobileresult.get(position)));
                    context.startActivity(callIntent);
            }
        });
        holder.Reqbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://api.mlab.com/api/1/databases/asecarpool/collections/tripdetails/"+Oidresult.get(position)+"?apiKey=ztCS-x7D_40BrPn_vDa4LXIqW_VXO5qk";
                JSONObject j = new JSONObject();

                try{
                    j.put("ruid",ud.getoid());
                    j.put("rstatus","Requsted");
                    new MongoLab().Put(j, url);
                    Log.d("Request Submitted", "success");
                    Context context1 = context.getApplicationContext();
                    CharSequence text = "Request Submitted !!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context1, text, duration);
                    toast.show();
                    holder.Reqbutton.setVisibility(View.INVISIBLE);
                }
                catch (Exception e){


                }
            }
        });
        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://api.mlab.com/api/1/databases/asecarpool/collections/tripdetails/"+Oidresult.get(position)+"?apiKey=ztCS-x7D_40BrPn_vDa4LXIqW_VXO5qk";
                JSONObject j = new JSONObject();

                try{
                    j.put("rstatus","Accepted");
                    new MongoLab().Put(j, url);
                    Log.d("Accept Submitted", "success");
                    Context context1 = context.getApplicationContext();
                    CharSequence text = "Accepted the Request Successfully !!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context1, text, duration);
                    toast.show();
                    holder.accept.setVisibility(View.INVISIBLE);
                    holder.decline.setVisibility(View.INVISIBLE);
                    holder.info.setVisibility(View.INVISIBLE);
                }
                catch (Exception e){


                }
            }
        });
        holder.decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://api.mlab.com/api/1/databases/asecarpool/collections/tripdetails/"+Oidresult.get(position)+"?apiKey=ztCS-x7D_40BrPn_vDa4LXIqW_VXO5qk";
                JSONObject j = new JSONObject();

                try{
                    j.put("rstatus","Declined");
                    new MongoLab().Put(j, url);
                    Log.d("Decline Submitted", "success");
                    Context context1 = context.getApplicationContext();
                    CharSequence text = "Request is declined successfully !!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context1, text, duration);
                    toast.show();
                    holder.accept.setVisibility(View.INVISIBLE);
                    holder.decline.setVisibility(View.INVISIBLE);
                    holder.info.setVisibility(View.INVISIBLE);
                }
                catch (Exception e){


                }
            }
        });
        return rowView;
    }

}
