package com.example.naresh.paseo;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.widget.Button;
import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements OnClickListener{
    Button btnDatePicker,btnTimePicker;
    EditText txtDate,txtTime;
    private int mYear,mMonth,mDay,mHour,mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);


    }
    public void onSubmit(View v){
        EditText location1= (EditText)findViewById(R.id.editText_FromLocation);
        EditText location2= (EditText)findViewById(R.id.editText_ToLocation);
        EditText date= (EditText)findViewById(R.id.in_date);
        EditText time= (EditText)findViewById(R.id.in_time);
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
        Intent intent = new Intent(this, MapsActivity2.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {


        if(v==btnDatePicker){


            final Calendar c=Calendar.getInstance();
            mYear=c.get(Calendar.YEAR);
            mMonth=c.get(Calendar.MONTH);
            mDay=c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    txtDate.setText(dayOfMonth + "-" +(monthOfYear+1) + "-" + year);

                }
            },mYear,mMonth,mDay);

            datePickerDialog.show();
        }

        if(v==btnTimePicker)
        {
            final Calendar c = Calendar.getInstance();
            mHour=c.get(Calendar.HOUR_OF_DAY);
            mMinute=c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog=new TimePickerDialog(this, new OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    txtTime.setText(hourOfDay + ":" + minute);

                }
            },mHour,mMinute,false);


            timePickerDialog.show();
        }

    }
}
