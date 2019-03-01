package com.example.st.firstproject;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

import com.example.st.firstproject.model.RssFeed;

public abstract class RSSReceiver extends ResultReceiver implements IRssReceiver {

    private static final String RSS_KEY = RSSReceiver.class.getSimpleName()
            + ".RSS_KEY";
    private static final int OK_RESULT_CODE = 1;

    public RSSReceiver(){
        super(new Handler());
    }

    abstract void onRssReceived(RssFeed feed);

    @Override
    public void receiveRss(RssFeed feed) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(RSS_KEY, feed);
        send(OK_RESULT_CODE, bundle);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);
        onRssReceived((RssFeed) resultData.getSerializable(RSS_KEY));
    }
}