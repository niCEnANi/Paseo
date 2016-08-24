package com.example.naresh.paseo;


        import android.os.Bundle;
        import android.app.Activity;
        import android.telephony.SmsManager;
        import android.util.Log;
        import android.view.Menu;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;
        import android.widget.ImageButton;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.content.ActivityNotFoundException;
import java.util.ArrayList;

public class sms extends Activity {
    Button sendBtn;
    EditText txtphoneNo;
    EditText txtMessage;
    TextView txtMsg;
    protected static final int RESULT_SPEECH = 1;
Userdetails ud=new Userdetails();
    String emr1=ud.getEmergency_1();
    String emr2=ud.getEmergency_2();

    private ImageButton btnSpeak;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        sendBtn = (Button) findViewById(R.id.btnSendSMS);
        txtphoneNo = (EditText) findViewById(R.id.editText);
        txtMsg=(TextView)findViewById(R.id.editText2);
//        txtMessage = (EditText) findViewById(R.id.editText2);
        Log.d(emr1,emr2);
        composeSms(emr1, "In Danger...Need help!!");
        composeSms(emr2,"In Danger...Need help!!");
//        sendBtn.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                sendSMSMessage();
//            }
//        });
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);

        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(
                        RecognizerIntent.ACTION_RECOGNIZE_SPEECH);


                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

                try {
                    Log.d("speeeaaakkkkk", "dfdsdsd");
                    startActivityForResult(intent, RESULT_SPEECH);
                    txtMsg.setText("");
//                    String vmsg=text.get(0);
//                    Log.d(txtMsg.toString(),txtMsg.toString());
//                    composeSms(emr1, txtMsg.toString());
//                    composeSms(emr2, txtMsg.toString());
//                    txtMessage.setText("");
                } catch (ActivityNotFoundException a) {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Opps! Your device doesn't support Speech to Text",
                            Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });

    }
    protected void sendSMSMessage() {
        Log.i("Send SMS", "");
        String phoneNo = txtphoneNo.getText().toString();
        String message = txtMessage.getText().toString();

//        try {
//            SmsManager smsManager = SmsManager.getDefault();
//            smsManager.sendTextMessage(phoneNo, null, message, null, null);
//            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
//        }
//
//        catch (Exception e) {
//            Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
//            e.printStackTrace();
//        }
    }
    protected void composeSms(String num,String msg){
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(num, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
        }

        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_SPEECH: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> text = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                        txtMsg.setText(text.get(0));
                    Log.d(txtMsg.toString(), txtMsg.toString());
                    composeSms(emr1, txtMsg.getText().toString());
                    composeSms(emr2, txtMsg.getText().toString());
                }
                break;
            }

        }
    }
}
