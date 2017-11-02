package com.TelstraAndroidTest.api;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;

/**
 * Interface for declaring methods for making RESTfull web service calls.
 */
public interface API {

    /*
        Method to make web service call for getting facts data from network.
     */
    @GET("/s/2iodh4vg0eortkl/facts.json")
    public void getFactsData(Callback<Response> response);

}