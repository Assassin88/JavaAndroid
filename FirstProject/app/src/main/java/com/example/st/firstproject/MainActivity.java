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

public class MainActivity extends AppCompatActivity implements ListFragment.PizzaSelectedListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            initFragments();
        }
    }


    private  void initFragments(){
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.first_container, new ListFragment())
                .commit();
    }

    @Override
    public void onPizzaSelected(int index) {
        Fragment detailsFragment =
                DetailsFragment.newInstance(Constants.PIZZA_DESCRIPTION[index]);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.second_container,detailsFragment)
                .commit();


    }
}
