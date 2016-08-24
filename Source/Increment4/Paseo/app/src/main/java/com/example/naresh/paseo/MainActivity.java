package com.example.naresh.paseo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
    public void onSignup(View v){
        EditText ufname= (EditText)findViewById(R.id.editText_fname);
        EditText ulname= (EditText)findViewById(R.id.editText_lname);
        EditText uemail= (EditText)findViewById(R.id.editText_email);
        EditText upwd= (EditText)findViewById(R.id.editText_pwd);
        EditText ucpwd= (EditText)findViewById(R.id.editText_cpwd);
        EditText umobile= (EditText)findViewById(R.id.editText_mobile);
        EditText udlicence= (EditText)findViewById(R.id.editText_Dlicence);


        String fname=ufname.getText().toString();
        String lname=ulname.getText().toString();
        String email=uemail.getText().toString();
        String pwd=upwd.getText().toString();
        String cpwd=ucpwd.getText().toString();
        String mobile=umobile.getText().toString();
        String dlicence=udlicence.getText().toString();

        if(fname.equals("")||lname.equals("")||email.equals("")||pwd.equals("")||cpwd.equals("")||mobile.equals("")){

            Toast.makeText(this, " Fields are Empty, please enter !!", Toast.LENGTH_SHORT).show();
        }
        else if(!pwd.equals(cpwd)){
            Toast.makeText(this, "Password don't match..Re enter !!", Toast.LENGTH_SHORT).show();

        }
        else{

            new MongoLab().Post(fname, lname, email, pwd, 0,mobile,dlicence);
            Toast.makeText(this, "Account Created !!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

    }
}


