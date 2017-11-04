package com.TelstraAndroidTest.controller;

import com.TelstraAndroidTest.handler.IControllerCallbackHandler;
import com.TelstraAndroidTest.model.FactsCollection;

/**
 * Controller class for managing operations for getting facts from network and processed for UI.
 */

public class FactController extends BaseController {

    private static FactController mFactControllerInstance = null;

    public static FactController getInstance(){

        if(mFactControllerInstance == null){
            mFactControllerInstance = new FactController();
        }

        return mFactControllerInstance;
    }

    private FactController(){
        super();
    }

    /**
     * Method to send network call and provide parsed onSuccess to UI.
     * @param controllerInstance - Reference to controller callback handler class.
     */
    public void getFacts(IControllerCallbackHandler<FactsCollection> controllerInstance){
        mControllerCallbackHandlerInstance = controllerInstance;
        classRef = FactsCollection.class;
        mApi.getFactsData(callback);
    }
}
