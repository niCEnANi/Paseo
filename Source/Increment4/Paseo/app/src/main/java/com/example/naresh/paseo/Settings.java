package com.example.naresh.paseo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class Settings extends AppCompatActivity {
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
        setContentView(R.layout.activity_settings);
    }
    public void updateDetails(View v){
        EditText cpt=(EditText)findViewById(R.id.editText_changepwd);
        EditText cet=(EditText)findViewById(R.id.editText_changeemail);
        EditText cmt=(EditText)findViewById(R.id.editText_changemobile);
        EditText emn1=(EditText)findViewById(R.id.editText_emname1);
        EditText emn2=(EditText)findViewById(R.id.editText_emname2);
        EditText emnumb1=(EditText)findViewById(R.id.editText_emnumb1);
        EditText emnumb2=(EditText)findViewById(R.id.editText_emnumb2);
        String cp=cpt.getText().toString();
        String ce=cet.getText().toString();
        String cm=cmt.getText().toString();
        String emname1=emn1.getText().toString();
        String emname2=emn2.getText().toString();
        String emno1=emnumb1.getText().toString();
        String emno2=emnumb2.getText().toString();
        Userdetails ud=new Userdetails();
        String user_oid=ud.getoid();
        String url="https://api.mlab.com/api/1/databases/asecarpool/collections/userdetails/"+user_oid+"?apiKey=ztCS-x7D_40BrPn_vDa4LXIqW_VXO5qk";
        JSONObject j = new JSONObject();
        try {
            boolean check=false;
            if(!cp.equals("")) {
                j.put("password",cp);
                check=true;
            }
            if(!ce.equals("")){
                j.put("email",ce);
                check=true;
            }
            if(!cm.equals("")){
                j.put("mobile",cm);
                check=true;
            }
            if((!emname1.equals(""))&&(!emno1.equals(""))){
                j.put("Emergency1",emname1+"/"+emno1);
                check=true;
            }
            if((!emname2.equals(""))&&(!emno2.equals(""))){
                j.put("Emergency2",emname2+"/"+emno2);
                check=true;
            }
            if(check) {
                new MongoLab().Put(j, url);
                Log.d("update function", "success");
                Context context = getApplicationContext();
                CharSequence text = "Details are updated !";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            else{
                Context context = getApplicationContext();
                CharSequence text = "No details are updated !!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
        catch (Exception e){
            Log.e("update","json error");
        }
    }
}
