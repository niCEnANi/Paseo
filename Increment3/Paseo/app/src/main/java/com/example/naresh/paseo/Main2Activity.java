package com.example.naresh.paseo;

import android.os.Bundle;
import android.util.Log;
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
import android.widget.ImageButton;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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

        //MongoLab mApp = ((MongoLab)getApplicationContext());
        //String globalVarValue = mApp.getGlobalVarValue();
        //MongoLab mlab=new MongoLab();
        //TextView fl=(TextView) findViewById(R.id.flname);
        //fl.setText(globalVarValue);
        //fl.setText(mlab.firstname+" "+ mlab.lastname);

    }
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
    }
    public  void GoToRide(View v){

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);

    } public  void Emergency(View v){

        Intent intent = new Intent(this, sms.class);
        startActivity(intent);

    }
    @Override
    public void onBackPressed() {
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
        if (id == R.id.action_settings) {
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
            android.content.Intent intent=new Intent(this,Main2Activity.class);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {
            android.content.Intent intent=new Intent(this,Settings.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {
            android.content.Intent intent=new Intent(this,AboutUs.class);
            startActivity(intent);

        } else if (id == R.id.nav_send) {
            android.content.Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
