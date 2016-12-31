package com.example.kraikar.threadexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Thread_App";
    private TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "Thread App Started Successfully");
    }

    public void buttonTouchMe(View view) {
        Log.i(TAG, "You touched the button!");
        long futureTime = System.currentTimeMillis() + 10000;
        while (System.currentTimeMillis() < futureTime) {
            synchronized (this) {
                try {
                    wait(futureTime - System.currentTimeMillis());
                } catch (Exception e) {
                    Log.i(TAG,  Log.getStackTraceString(e));
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
            textViewMessage = (TextView) findViewById(R.id.textViewMessage);
            textViewMessage.setText("Nice Job Kina!");
        }
    }
}
