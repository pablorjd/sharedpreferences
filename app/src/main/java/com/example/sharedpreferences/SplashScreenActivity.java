package com.example.sharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        preferences = getSharedPreferences("login", Context.MODE_PRIVATE);

        Thread background = new Thread() {
            @Override
            public void run() {
                try {
                    // Thread will sleep for 5 seconds
                    Thread.sleep(2000);
                    String correo = preferences.getString("correo","");

                    if (!correo.isEmpty()){

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                        return;

                    }else{

                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                        return;

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        background.start();
        }
}
