package com.example.st.firstproject.services.api;
import com.example.st.firstproject.model.RssFeed;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface TechCrunchApi {

    @GET("/TechCrunch/social")
    Observable<RssFeed> getItems();
}
