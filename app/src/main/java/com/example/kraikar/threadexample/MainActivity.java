package com.example.kraikar.threadexample;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            textViewMessage = (TextView) findViewById(R.id.textViewMessage);
            textViewMessage.setText("Nice Job Kina!");
        }
    };

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
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                long futureTime = System.currentTimeMillis() + 10000;
                while (System.currentTimeMillis() < futureTime) {
                    synchronized (this) {
                        try {
                            Log.i(TAG, "Wait for 10Sec...");
                            wait(futureTime - System.currentTimeMillis());
                        } catch (Exception e) {
                            Log.i(TAG, Log.getStackTraceString(e));
                            // Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                    handler.sendEmptyMessage(0);
                }
                Log.i(TAG, "Execution of thread completed.");
            }
        };

        Thread kinasThread = new Thread(runnable);
        kinasThread.start();
    }
}
