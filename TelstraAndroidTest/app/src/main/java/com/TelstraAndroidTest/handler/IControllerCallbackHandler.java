package com.TelstraAndroidTest.handler;

/**
 * Interface for passing controller onSuccess as a callback to activity.
 * @param <T> : Class reference for onSuccess type.
 */
public interface IControllerCallbackHandler<T> {

    /*
      Method to get onSuccess callback.
     */
    void onSuccess(T response);

    /*
      Method to get error callback.
     */
    void onFailure(String errorMsg);
}
