package com.TelstraAndroidTest.handler;

/**
 * Interface for passing network calls onSuccess to controller.
 * @param <T> : Class reference for onSuccess type.
 */
public interface INetworkCallbackHandler<T> {

    /*
      Method to get onSuccess callback.
     */
    void response(T response);

    /*
      Method to get error callback.
     */
    void isError(String errorMsg);
}