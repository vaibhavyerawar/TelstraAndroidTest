package com.TelstraAndroidTest.handler;

/**
 * Interface for handing result callback from various executions.
 * @param <T> : Class reference for response type.
 */
public interface INetworkCallbackHandler<T> {

    /*
      Method to get response callback.
     */
    void response(T response);

    /*
      Method to get error callback.
     */
    void isError(String errorMsg);
}