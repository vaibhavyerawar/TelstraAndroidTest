package com.TelstraAndroidTest.controller;

import com.TelstraAndroidTest.model.FactsCollection;

/**
 * Controller class for managing operations for getting facts from network and processed for UI.
 */

public class FactController extends BaseController<FactsCollection> {

    public FactController(){
        super();
    }

    @Override
    protected <FactsCollection> void onSuccess(FactsCollection response) {

    }

    @Override
    protected void onFailure(String errorMsg) {

    }
}
