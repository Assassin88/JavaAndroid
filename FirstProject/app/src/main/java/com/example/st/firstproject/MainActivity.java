package com.example.st.firstproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.st.firstproject.model.RssFeed;
import com.example.st.firstproject.services.RssService;
import com.example.st.firstproject.services.api.TechCrunchApi;

import java.io.Console;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity implements ListFragment.PizzaSelectedListener, IServiceProvider {

    private static final String RSS_LINK = "http://feeds.feedburner.com/";
    private RssService service;

    @Override
    public RssService GetRssServiceProvider() {
        return service;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service = new RssService(buildApi());
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

    private TechCrunchApi buildApi() {
        return provideRetrofit().create(TechCrunchApi.class);
    }

    private Retrofit provideRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        return new Retrofit.Builder()
                .baseUrl(RSS_LINK)
                .client(client)
                .addConverterFactory(SimpleXmlConverterFactory.createNonStrict())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


}
