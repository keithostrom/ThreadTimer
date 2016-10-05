package com.example.keith.threadtimer;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Handler myHandler;
    boolean gameOn;
    long startTime;
    Button myToggleButtonObject;
    TextView myTextViewObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myHandler = new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if( gameOn){
                    long seconds = ((System.currentTimeMillis()-startTime))/100;
                    myTextViewObject.setText("Seconds: "+seconds/10.0);
                    myHandler.sendEmptyMessageDelayed(0,100); //Restart timer again
                } // End if( gameOn )
            } // End handleMessage
        }; // End myHandler Handler object

        gameOn = false; // Init to not running
        startTime = System.currentTimeMillis();
       // myHandler.sendEmptyMessage(0); // Call the handler the first time to start

        myToggleButtonObject = (Button) findViewById(R.id.toggleButton);
        myToggleButtonObject.setOnClickListener(this);
        myTextViewObject = (TextView) findViewById(R.id.textView);
        myTextViewObject.setText("Seconds: "+0.0);
    } // End onCreate

    @Override
    public void onClick(View view) {
        if( gameOn ){
            gameOn = false;
        }
        else{
            gameOn = true;
            startTime = System.currentTimeMillis();
            myTextViewObject.setText("Seconds: "+0.0);
            myHandler.sendEmptyMessageDelayed(0,100); //Start timer running
        }
    }
} // End MainActivity
