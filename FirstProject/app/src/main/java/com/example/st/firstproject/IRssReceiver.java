package com.example.st.firstproject;

import com.example.st.firstproject.model.RssFeed;

import java.io.Serializable;

public interface IRssReceiver extends Serializable {
    void receiveRss(RssFeed feed);
}
