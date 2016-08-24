package com.example.naresh.aselab3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void onSubmit(View v){
        EditText location1= (EditText)findViewById(R.id.editText_FromLocation);
        EditText location2= (EditText)findViewById(R.id.editText_ToLocation);
        EditText date= (EditText)findViewById(R.id.editText_Date);
        EditText time= (EditText)findViewById(R.id.editText_Time);
        String flocation=location1.getText().toString();
        String tlocation=location2.getText().toString();
        String udate=date.getText().toString();
        String utime=time.getText().toString();

        new MongoLab().Post(flocation,tlocation,udate,utime,1,"","");
        Toast.makeText(this, "Posted Ride Details !!", Toast.LENGTH_SHORT).show();




    }
    public  void onViewFeed(View v){

        Intent intent = new Intent(this, FeedActivity.class);
        startActivity(intent);

    }
    public  void  OpenMap(View v){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

}
