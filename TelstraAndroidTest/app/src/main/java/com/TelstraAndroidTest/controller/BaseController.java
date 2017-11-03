package com.TelstraAndroidTest.controller;

import android.util.Log;

import com.TelstraAndroidTest.api.API;
import com.TelstraAndroidTest.service.NetworkManager;
import com.TelstraAndroidTest.utils.Constants;
import com.TelstraAndroidTest.utils.ErrorType;
import com.google.gson.Gson;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.squareup.okhttp.internal.Util.UTF_8;

/**
 * A parent class for controller classes in application.
 */

public abstract class BaseController<T> {

    private T t;
    private NetworkManager mNetworkManagerInstance;
    protected API mApi;

    public BaseController(){

        mNetworkManagerInstance = NetworkManager.getInstance();
        mApi = mNetworkManagerInstance.getAPI();
    }

    protected Callback makeNetworkCall() {

        Callback callback = new Callback() {

            @Override
            public void success(Object o, Response response) {
                final String processedResponse = readResponse(response);
                if (processedResponse != null) {

                   Object parsedObject = parseJSONResponse(processedResponse);
                   onSuccess(parsedObject);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                verifyErrorCause(error);
            }
        };

        return callback;
    }

    private String readResponse(Response response) {

        try {
            final InputStream inputStream = response.getBody().in();
            final String responseStr = readInputStream(inputStream);
            return responseStr;
        } catch (IOException e) {
            return null;
        }
    }

    private String readInputStream(InputStream responseStream) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(responseStream, UTF_8), 8);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            bufferedReader = new BufferedReader(new InputStreamReader(responseStream));
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            onFailure(Constants.ERROR_FAIL_TO_GET_INFO);
            return null;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                    responseStream.close();
                } catch (IOException e) {
                    onFailure(Constants.ERROR_FAIL_TO_GET_INFO);
                    return null;
                }
            }
        }
    }

    private Object parseJSONResponse(String jsonResponse){

       Object object = new Gson().fromJson(jsonResponse, t.getClass());
       return object;
    }

    private void verifyErrorCause(RetrofitError error) {
        String errorMsg = error.getMessage();
        ErrorType errorType;

        if (error != null) {
            if (error.getKind() == RetrofitError.Kind.NETWORK) {
                errorMsg = Constants.NETWORK_CONNECTION_ERROR_MSG;
            } else if (error.getResponse() != null) {
                String errorCodeStr = error.getResponse().getReason();
                errorCodeStr = StringUtils.deleteWhitespace(errorCodeStr);
                errorType = ErrorType.valueOf(errorCodeStr);

                switch (errorType) {
                    case BadRequest:
                        errorMsg = Constants.BAD_REQUEST_MSG;
                        break;
                    case Unauthorized:
                        errorMsg = Constants.UNAUTHORIZED_MSG;
                        break;
                    case InternalServerError:
                        errorMsg = Constants.INTERNAL_SERVER_ERROR_MSG;
                        break;
                    case ServiceUnavailable:
                        errorMsg = Constants.SERVICE_UNAVAILABLE_ERROR_MSG;
                        break;
                }
            }
        }
        onFailure(errorMsg);
    }

    protected abstract <T> void onSuccess(T response);
    protected abstract void onFailure(String errorMsg);
}