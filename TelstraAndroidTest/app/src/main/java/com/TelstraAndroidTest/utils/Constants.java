package com.TelstraAndroidTest.utils;

/**
 * Class for holding constant value used in application.
 */
public class Constants {

    /* Base URL for network request.*/
    public static final String BASE_URL = "https://dl.dropboxusercontent.com";

    /* Network timeout.*/
    public static final int CONNECTION_TIMEOUT = 30;

    /* Network read timeout.*/
    public static final int READ_TIMEOUT = 30;

    /* Progress dialog message. */
    public final static String DIALOG_DEFAULT_MSG = "Loading...";

    /*  Error Messages */
    public final static String ERROR_FAIL_TO_GET_INFO = "Failed to get information.";

    /* Network error messages */
    public final static String NETWORK_CONNECTION_ERROR_MSG = "Check Network Connectivity.";
    public static final String BAD_REQUEST_MSG = "Unable to process your request.";
    public static final String UNAUTHORIZED_MSG = "User is not authorized to access resources.";
    public static final String INTERNAL_SERVER_ERROR_MSG = "Unable to process your request.";
    public static final String SERVICE_UNAVAILABLE_ERROR_MSG = "Service Unavailable. Please try again.";
}
