package com.example.keith.threadtimer;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
                    long seconds = ((System.currentTimeMillis()-startTime))/1000;
                    //Log.i("info","seconds= "+seconds);
                    myTextViewObject.setText("Seconds: "+seconds);
                } // End if( gameOn )
                else{
                    // Reset the start time
                    startTime = System.currentTimeMillis();
                    myTextViewObject.setText("Seconds: "+0);
                } // End else( gameOn )

                myHandler.sendEmptyMessageDelayed(0,1000);
            } // End handleMessage
        }; // End myHandler Handler object
        gameOn = false;
        startTime = System.currentTimeMillis();
        myHandler.sendEmptyMessage(0); // Call the handler the first time to start

        myToggleButtonObject = (Button) findViewById(R.id.toggleButton);
        myToggleButtonObject.setOnClickListener(this);

        myTextViewObject = (TextView) findViewById(R.id.textView);
    } // End onCreate

    @Override
    public void onClick(View view) {
        if( gameOn ){
            gameOn = false;
        }
        else{
            gameOn = true;
        }
    }
} // End MainActivity
