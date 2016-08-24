package com.example.naresh.paseo;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Main2Activity extends AppCompatActivity implements AsyncResponse,NavigationView.OnNavigationItemSelectedListener {
        gethm getc=new gethm();
        final Context context = this;
        private Button button;
        ListView lv;
        //ArrayList prgmName;
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
        ArrayList<String> PunameList = new ArrayList<String>();
        ArrayList<String> RuidList = new ArrayList<String>();
        ArrayList<String> RunameList = new ArrayList<String>();
        ArrayList<String> RmobileList = new ArrayList<String>();
        ArrayList<String> RstatusList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Hello 'user first name'
        Userdetails ud=new Userdetails();
        TextView fname=(TextView) findViewById(R.id.textView4);
        fname.setText("Hello "+ud.getfname());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View hView =  navigationView.inflateHeaderView(R.layout.nav_header_main2);
        ImageView imgvw = (ImageView)hView.findViewById(R.id.imageView);
        TextView tv = (TextView)hView.findViewById(R.id.textView);
        String lwrname=ud.getfname().toLowerCase();
        int id=getResources().getIdentifier(lwrname, "drawable", getPackageName());
        imgvw.setImageResource(id);

        tv.setText(ud.getfname() +" "+ ud.getlname());





        getc.delegate=this;
        getc.execute();
        try{
            Thread.sleep(3000);}
        catch (Exception e)
        {
            Log.d("thread feed",e.toString());
        }

        Log.d("Valuese",FromList.toString());
        lv=(ListView) findViewById(R.id.listView3);
        lv.setAdapter(new CustomAdapterhm(this,FromList,ToList,DateList,TimeList,MobileList,OidList,PuidList,PunameList,RuidList,RunameList,RmobileList,RstatusList));


        //MongoLab mApp = ((MongoLab)getApplicationContext());
        //String globalVarValue = mApp.getGlobalVarValue();
        //MongoLab mlab=new MongoLab();
        //TextView fl=(TextView) findViewById(R.id.flname);
        //fl.setText(globalVarValue);
        //fl.setText(mlab.firstname+" "+ mlab.lastname);

    }/*
    public void clickFeed(View view)
    {
        String s=view.getTag().toString();
        int aNums[] = { R.id.i1,R.id.i2,R.id.i3,R.id.i4,R.id.i5 };

        for(int j=0;j<aNums.length;j++) {
            ImageButton imgb = (ImageButton)findViewById(aNums[j]);
            imgb.setImageResource(R.drawable.night);
        }
        int i=Integer.parseInt(s);
        for(int j=0;j<i;j++) {
            ImageButton imgb = (ImageButton)findViewById(aNums[j]);
            imgb.setImageResource(R.drawable.favorite);
        }
        Log.d("Value of clicked..!!",s);
    }
    */
     public  void Emergency(View v){

        Intent intent = new Intent(this, sms.class);
        startActivity(intent);

    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            android.content.Intent intent=new Intent(this,HomeActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            android.content.Intent intent=new Intent(this,FeedActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {
            android.content.Intent intent=new Intent(this,MainHomeActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {
            android.content.Intent intent=new Intent(this,Settings.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {
            android.content.Intent intent=new Intent(this,AboutUs.class);
            startActivity(intent);

        }
//        else if (id == R.id.nav_send) {
//            android.content.Intent intent=new Intent(this,LoginActivity.class);
//            startActivity(intent);
//        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void processFinish(String output){
        //TextView sample= (TextView) findViewById(R.id.textView_opsample);
        //sample.setText(output);
        //String finalop="From To Date Time"+"\n\n";
        try {
            Userdetails ud=new Userdetails();
            String user_id= ud.getoid();
            Log.d("inner for : ",user_id);
            JSONArray reader = new JSONArray(output);
            for (int i = 0; i < reader.length(); i++) {
                JSONObject jsonobject = reader.getJSONObject(i);
                JSONObject oidobj=jsonobject.getJSONObject("_id");
                String oid=oidobj.getString("$oid");
                String flocation = jsonobject.getString("flocation");
                String tlocation = jsonobject.getString("tlocation");
                String date=jsonobject.getString("date");
                String time=jsonobject.getString("time");
                String pmobile = jsonobject.getString("pmobile");
                String puid=jsonobject.getString("puid");
                String puname=jsonobject.getString("puname");
                String runame=jsonobject.getString("runame");
                String rmobile=jsonobject.getString("rmobile");
                String ruid=jsonobject.getString("ruid");
                String rstatus=jsonobject.getString("rstatus");
                if(puid.equals(user_id)||ruid.equals(user_id)) {
                    Log.d("inner for : ",user_id);
                    MobileList.add(pmobile);
                    PuidList.add(puid);
                    RuidList.add(ruid);
                    RunameList.add(runame);
                    RmobileList.add(rmobile);
                    RstatusList.add(rstatus);
                    OidList.add(oid);
                    FromList.add(flocation);
                    ToList.add(tlocation);
                    DateList.add(date);
                    TimeList.add(time);
                }
                //finalop=finalop+flocation+" "+tlocation+" "+date+" "+time+"\n";
            }
            Log.d("Values",FromList.toString());
            //sample.setText(finalop);
        }catch (Exception e){

        }
    }
}
class gethm extends AsyncTask<String, String,String> {
    public AsyncResponse delegate = null;
    Userdetails ud=new Userdetails();
    String user_id= ud.getoid();
    Calendar c = Calendar.getInstance();
    //System.out.println("Current time => " + c.getTime());
    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    String formattedDate = df.format(c.getTime());
    @Override
    protected void onPostExecute(String s) {
        delegate.processFinish(s);
        Log.d("get thread", "Success");
    }
    @Override
    protected String doInBackground(String... params) {
        try {
            Log.d("current Date: ",formattedDate);
            URL url= new URL("https://api.mongolab.com/api/1/databases/asecarpool/collections/tripdetails?&q={$or:[{\"puid\":\""+user_id+"\"},{\"ruid\":\""+user_id+"\"}],\"date\":{$gt:\""+formattedDate+"\"},\"rstatus\":\"Accepted\"}&l=2&apiKey=ztCS-x7D_40BrPn_vDa4LXIqW_VXO5qk") ;
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
class CustomAdapterhm extends BaseAdapter {
    public ArrayList<String> Fromresult;
    public ArrayList<String>  Toresult;
    public ArrayList<String>  Dateresult;
    public ArrayList<String>  Timeresult;
    public ArrayList<String>  Mobileresult;
    public ArrayList<String>  Oidresult;
    public ArrayList<String>  Puidresult;
    public ArrayList<String>  Punameresult;
    //public ArrayList<String>  Pmobileresult;
    public ArrayList<String>  Ruidresult;
    public ArrayList<String>  Runameresult;
    public ArrayList<String>  Rmobileresult;
    public ArrayList<String>  Rstatusresult;
    Context context;
    //int [] imageId;
    private static LayoutInflater inflater=null;
    public CustomAdapterhm(Main2Activity mhActivity, ArrayList<String>FromList,ArrayList<String>  ToList,ArrayList<String>  DateList,ArrayList<String> TimeList,ArrayList<String> MobileList,ArrayList<String>OidList,ArrayList<String>PuidList,ArrayList<String>PunameList,ArrayList<String>RuidList,ArrayList<String>RunameList,ArrayList<String>RmobileList,ArrayList<String>RstatusList) {
        // TODO Auto-generated constructor stub
        this.Fromresult=FromList;
        this.Toresult=ToList;
        this.Dateresult=DateList;
        this.Timeresult=TimeList;
        this.Mobileresult=MobileList;
        this.Oidresult=OidList;
        this.Puidresult=PuidList;
        this.Punameresult=PunameList;
        //this.Pmobileresult=PmobileList;
        this.Ruidresult=RuidList;
        this.Runameresult=RunameList;
        this.Rmobileresult=RmobileList;
        this.Rstatusresult=RstatusList;
        this.context=mhActivity;
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
        ImageButton rcallbutton;
        // ImageButton Reqbutton;

        TextView info;
        Button accept;
        Button decline;
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
        holder.rcallbutton=(ImageButton) rowView.findViewById(R.id.rcall_imageButton);
        holder.info=(TextView) rowView.findViewById(R.id.Info_textView);
        holder.accept=(Button) rowView.findViewById(R.id.button_Accept);
        holder.decline=(Button) rowView.findViewById(R.id.button_Reject);

        if(Puidresult.get(position).equals(ud.getoid())){
            //  holder.Reqbutton.setVisibility(View.INVISIBLE);
            holder.callbutton.setVisibility(View.INVISIBLE);
            holder.info.setText("You posted this Ride !!");
            holder.info.setTextColor(Color.GRAY);
            holder.info.setVisibility(View.VISIBLE);
            if(Rstatusresult.get(position).equals("Requsted")){
                holder.accept.setVisibility(View.VISIBLE);
                holder.decline.setVisibility(View.VISIBLE);
                holder.info.setText(Runameresult.get(position)+" requested your ride");
                holder.info.setTextColor(Color.CYAN);
                //holder.info.setVisibility(View.VISIBLE);

            }
            else if(Rstatusresult.get(position).equals("Accepted")){
                //holder.accept.setVisibility(View.VISIBLE);
                //holder.decline.setVisibility(View.VISIBLE);
                holder.info.setText("Accepted: "+Runameresult.get(position));
                holder.info.setTextColor(Color.GREEN);
                holder.rcallbutton.setVisibility(View.VISIBLE);
                //holder.info.setVisibility(View.VISIBLE);

            }

        }
        if(Ruidresult.get(position).equals(ud.getoid())){
            //holder.Reqbutton.setVisibility(View.INVISIBLE);
            if(Rstatusresult.get(position).equals("Requsted")){
                holder.info.setText("Your Request is in pending !!");
                holder.info.setVisibility(View.VISIBLE);
                holder.info.setTextColor(Color.BLUE);
            }
            else if(Rstatusresult.get(position).equals("Accepted")){
                holder.info.setText("Your Request is approved !!");
                holder.info.setVisibility(View.VISIBLE);
                holder.info.setTextColor(Color.GREEN);
            }
            else{
                holder.info.setText("Your Request is declined !!");
                holder.info.setVisibility(View.VISIBLE);
                holder.info.setTextColor(Color.RED);
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
        holder.rcallbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+Rmobileresult.get(position)));
                context.startActivity(callIntent);
            }
        });
        /*
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
        */
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
                    holder.accept.setVisibility(View.GONE);
                    holder.decline.setVisibility(View.GONE);
                    holder.info.setText("Accepted");
                    holder.info.setTextColor(Color.GREEN);
                    //holder.info.setVisibility(View.INVISIBLE);
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
                    holder.accept.setVisibility(View.GONE);
                    holder.decline.setVisibility(View.GONE);
                    holder.info.setText("Declined");
                    holder.info.setTextColor(Color.RED);
                    //holder.info.setVisibility(View.INVISIBLE);
                }
                catch (Exception e){


                }
            }
        });
        return rowView;
    }

}
