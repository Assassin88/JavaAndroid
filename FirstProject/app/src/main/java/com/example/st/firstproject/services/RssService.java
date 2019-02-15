package com.example.st.firstproject.services;
import com.example.st.firstproject.model.RssFeed;
import com.example.st.firstproject.services.api.TechCrunchApi;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


public class RssService {

    private TechCrunchApi api;

    public RssService(TechCrunchApi api) {
        this.api = api;
    }

    public Observable<RssFeed> getItems() {
        return api.getItems().subscribeOn(Schedulers.io());
    }
}
