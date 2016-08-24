package com.example.naresh.paseo;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.view.Menu;
import android.view.MenuItem;
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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class HomeActivity extends AppCompatActivity implements OnClickListener{
    Button btnDatePicker,btnTimePicker;
    EditText txtDate,txtTime;
    private int mYear,mMonth,mDay,mHour,mMinute;
    private TextView get_place;
    private TextView get_place2;
    Button direct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final EditText edt1,edt2;
        direct=(Button)findViewById(R.id.button_map);
        edt1=(EditText)findViewById(R.id.editText_FromLocation);
        edt2=(EditText)findViewById(R.id.editText_ToLocation);
        direct.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent myintent = new Intent(HomeActivity.this, MapsActivity.class);
                myintent.putExtra("source", edt1.getText().toString());
                myintent.putExtra("destination", edt2.getText().toString());
                startActivityForResult(myintent, 1);
            }
        });
        get_place2=(TextView)findViewById(R.id.editText_FromLocation);
        get_place2.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                Intent intent1;
                try {
                    intent1 = builder.build(getApplicationContext());
                    startActivityForResult(intent1, 0);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        });
        get_place=(TextView)findViewById(R.id.editText_ToLocation);
        get_place.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder= new PlacePicker.IntentBuilder();
                Intent intent1;
                try {
                    intent1 = builder.build(getApplicationContext());
                    startActivityForResult(intent1,1);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        });

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

        new MongoLab().Post(flocation, tlocation, udate, utime, 1, "", "");
        Toast.makeText(this, "Posted Ride Details !!", Toast.LENGTH_SHORT).show();




    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        if(requestCode==1)
        {
            if (resultCode==RESULT_OK)
            {
                Place place=PlacePicker.getPlace(data,this);
                String address=String.format("%s",place.getAddress());
                get_place.setText(address);

            }
        }

        if(requestCode==0)
        {
            if(resultCode==RESULT_OK)
            {
                Place place=PlacePicker.getPlace(data,this);
                String address=String.format("%s",place.getAddress());
                get_place2.setText(address);
            }

        }


    }
    public  void onViewFeed(View v){

        Intent intent = new Intent(this, FeedActivity.class);
        startActivity(intent);

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
