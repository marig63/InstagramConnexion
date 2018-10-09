package com.marisa.guillaume.instagramunittestjava;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.marisa.guillaume.instagramunittestjava.customView.InstaAuthDialog;
import com.marisa.guillaume.instagramunittestjava.listener.InstaAuthListener;
import com.marisa.guillaume.instagramunittestjava.listener.InstaUsernameListener;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements InstaAuthListener,InstaUsernameListener{

    private InstaAuthDialog insta_auth_dialog;
    //private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //tv = (TextView)findViewById(R.id.textView);
    }

    @Override
    public void onCodeReceived(String auth_token) {
        if(auth_token != null){
            //launch get user info from logged user
            new InstagramUsernameUtils(auth_token,this).execute();
        }
    }

    @Override
    public void onUsernameReceived(String username) {
        //tv.setText(username);
        // send save request
        // ->
    }

    public void onClick(View view){
        // launch user log into insta
        insta_auth_dialog = new InstaAuthDialog(this,this);
        insta_auth_dialog.setCancelable(true);
        insta_auth_dialog.show();
    }

}
