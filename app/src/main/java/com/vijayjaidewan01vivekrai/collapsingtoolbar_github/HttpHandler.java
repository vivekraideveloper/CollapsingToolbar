package com.vijayjaidewan01vivekrai.collapsingtoolbar_github;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by MR VIVEK RAI on 11-06-2018.
 */

public class HttpHandler {
    public HttpHandler() {
    }

    public String makeServiceCall(String requrl){

        StringBuilder response = new StringBuilder();
        try{

            URL url = new URL(requrl);
            HttpURLConnection connection =(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            Log.d("Responnse Code:", String.valueOf(connection.getResponseCode()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            int charsRead;
            char[] inputRead = new char[1000];

            while (true){
                charsRead = reader.read(inputRead);
                if (charsRead<0){
                    break;
                }

                if (charsRead>0){
                    response.append(String.copyValueOf(inputRead, 0, charsRead));
                }
            }

            reader.close();
            return response.toString();


        }catch (Exception e){
            e.printStackTrace();
        }


        return null;
    }

}
