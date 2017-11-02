package com.TelstraAndroidTest.service;

import com.TelstraAndroidTest.api.API;
import com.TelstraAndroidTest.handler.INetworkCallbackHandler;
import com.TelstraAndroidTest.utils.Constants;
import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Class for creating and managing network connection instance.
 */
public class NetworkManager {
    private RestAdapter adapter;
    public API api;

    private static NetworkManager ourInstance = null;

    public static NetworkManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new NetworkManager();
        }
        return ourInstance;
    }

    private NetworkManager() {
        final OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        client.setReadTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS);

        adapter = new RestAdapter.Builder()
                .setEndpoint(Constants.BASE_URL)
                .setClient(new OkClient(client))
                .build();
        api = (API) adapter.create(API.class);
    }

    public API getAPI() {
        return api;
    }

    /*
      Method to download resource from URL.
     */
    public void getResourceFromURL(String strUrl, INetworkCallbackHandler<InputStream> callbackHandler) {

        InputStream ios = null;

        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(strUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            ios = urlConnection.getInputStream();
            callbackHandler.response(ios);
        } catch (IOException e) {
            callbackHandler.isError(e.getMessage());
        } catch (Exception e) {
            callbackHandler.isError(e.getMessage());
        }
    }
}