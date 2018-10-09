package com.marisa.guillaume.instagramunittestjava.listener;

public interface InstaAuthListener {
    void onCodeReceived(String auth_token);
}
