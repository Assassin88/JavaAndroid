package com.example.st.firstproject;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.st.firstproject.model.RssFeed;
import com.example.st.firstproject.services.RssService;
import com.example.st.firstproject.services.api.TechCrunchApi;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class AutoUpdateService extends Service {

    public static void startService(Context context, IRssReceiver receiver){
        Intent intent = new Intent(context, AutoUpdateService.class);
        intent.putExtra(RECEIVER_KEY, receiver);
        context.startService(intent);
    }

    private static final String RSS_LINK = "http://feeds.feedburner.com/";
    private static final String RECEIVER_KEY = AutoUpdateService.class.getSimpleName() +
            ".RECEIVER_KEY";
    private static final int DEFAULT_INTERVAL_SEC = 5;

    private RssService networkService;
    private IRssReceiver receiver;
    private BehaviorSubject<RssFeed> subject = BehaviorSubject.create();
    private Disposable disposable;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        receiver = (IRssReceiver) intent.getSerializableExtra(RECEIVER_KEY);
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        disposable = subject.subscribe(new Consumer<RssFeed>() {
            @Override
            public void accept(RssFeed rssFeed) throws Exception {
                if(receiver != null){
                    receiver.receiveRss(rssFeed);
                }
            }
        });
        networkService = new RssService(createNetworkApi());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

    private void scheduleUpdate(int second){
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.set(
                AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + second * 1000,
                PendingIntent.getService(
                        getApplicationContext(),
                        0,
                        new Intent(this, AutoUpdateService.class),
                        0
                )
        );
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        receiver = (IRssReceiver) intent.getSerializableExtra(RECEIVER_KEY);
        updateRss();
        scheduleUpdate(DEFAULT_INTERVAL_SEC);
        return Service.START_STICKY;
    }

    private void updateRss() {
        networkService.getItems().subscribe(subject);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        receiver = null;
        return super.onUnbind(intent);
    }

    private TechCrunchApi createNetworkApi(){
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