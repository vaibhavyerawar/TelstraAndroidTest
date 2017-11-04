package com.TelstraAndroidTest.controller;

import com.TelstraAndroidTest.api.API;
import com.TelstraAndroidTest.handler.IControllerCallbackHandler;
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

public abstract class BaseController {

    /**
     * Reference to the class for JSON parser for binding to class object.
     */
    protected Class classRef;

    /**
     * Instance of NetworkManager class for performing network operations.
     */
    private NetworkManager mNetworkManagerInstance;

    /**
     * Reference of Retrofit REST call API interface.
     */
    protected API mApi;

    /**
     * Reference to Controller callback handler interface.
     */
    protected IControllerCallbackHandler mControllerCallbackHandlerInstance;

    /**
     * Retrofit network callback interface reference.
     */
    protected Callback callback;

    public BaseController(){
        mNetworkManagerInstance = NetworkManager.getInstance();
        mApi = mNetworkManagerInstance.getAPI();
        initNetworkCall();
    }

    /**
     * Method to initialise Retrofit network callback for getting onSuccess of network calls.
     */
    private void initNetworkCall() {

        callback = new Callback() {

            @Override
            public void success(Object o, Response response) {
                final String processedResponse = readResponse(response);
                if (processedResponse != null) {

                   Object parsedObject = parseJSONResponse(processedResponse);
                    mControllerCallbackHandlerInstance.onSuccess(parsedObject);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                verifyErrorCause(error);
            }
        };
    }

    /**
     * Method to read Retrofit onSuccess and get the data for parsing.
     * @param response - Network call execution onSuccess from Retrofit.
     * @return - Extracted data from onSuccess in string format for paring.
     */
    private String readResponse(Response response) {

        try {
            final InputStream inputStream = response.getBody().in();
            final String responseStr = readInputStream(inputStream);
            return responseStr;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Method to convert data inputstream to string format.
     * @param responseStream - Data inputstream from network call onSuccess.
     * @return - String read from inputstream.
     */
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
            mControllerCallbackHandlerInstance.onFailure(Constants.ERROR_FAIL_TO_GET_INFO);
            return null;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                    responseStream.close();
                } catch (IOException e) {
                    mControllerCallbackHandlerInstance.onFailure(Constants.ERROR_FAIL_TO_GET_INFO);
                    return null;
                }
            }
        }
    }

    /**
     * Method to parse JSON onSuccess string using GSON parser.
     * @param jsonResponse - JSON onSuccess string.
     * @return - Class object from parsed JSON string.
     */
    private Object parseJSONResponse(String jsonResponse){

       Object object = new Gson().fromJson(jsonResponse, classRef);
       return object;
    }

    /**
     * Method to verify error code of network call on failure.
     * @param error - Error object get from Retrofit on failure of network call.
     */
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
        mControllerCallbackHandlerInstance.onFailure(errorMsg);
    }
}