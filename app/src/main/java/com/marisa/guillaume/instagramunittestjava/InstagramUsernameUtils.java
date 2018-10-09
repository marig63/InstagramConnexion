package com.marisa.guillaume.instagramunittestjava;

import android.os.AsyncTask;
import android.util.Log;

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

public class InstagramUsernameUtils {

    private String token;
    private InstaUsernameListener listener;

    public InstagramUsernameUtils(String token, InstaUsernameListener listener){
        this.token = token;
        this.listener = listener;
    }

    public void execute(){
        new RequestInstagramAPI().execute();
    }

    private class RequestInstagramAPI extends AsyncTask<Void,String,String> {

        @Override
        protected String doInBackground(Void... voids) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(Contants.GET_USER_INFO_URL+token);

            try{

                HttpResponse response = httpClient.execute(httpGet);
                HttpEntity httpEntity = response.getEntity();
                String json = EntityUtils.toString(httpEntity);
                return json;

            }catch (ClientProtocolException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPostExecute(String response){
            super.onPostExecute(response);
            if(response != null){
                try{
                    JSONObject json = new JSONObject(response);
                    Log.e("response",json.toString());
                    JSONObject jsonData = json.getJSONObject("data");
                    if(jsonData.has("id")){
                        String username = jsonData.getString("username");
                        Log.e("username",username);
                        listener.onUsernameReceived(username);
                        // send request to server : saving username
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }
    }

}
