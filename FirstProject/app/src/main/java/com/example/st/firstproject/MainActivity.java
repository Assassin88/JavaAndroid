package com.example.st.firstproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("Activity", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("Activity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("Activity", "onDestroy");

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.first_container , MainFragment.CreateNewInstance(50))
                                                        .add(R.id.second_container, new SecondFragment()).commit();

        Log.d("Activity", "onCreate");

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("Activity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("Activity", "onPause");
    }


}
