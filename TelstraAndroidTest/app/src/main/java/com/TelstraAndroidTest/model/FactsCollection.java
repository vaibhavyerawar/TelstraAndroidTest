package com.TelstraAndroidTest.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is for maintaining the collection of facts.
 */

public class FactsCollection {

    /**
     * Title of facts.
     */
    @SerializedName("title")
    private String mFactTitle;

    /**
     * Collection of facts.
     */
    @SerializedName("rows")
    private List<Fact> mFactsList = new ArrayList<>();


    public String getFactTitle() {
        return mFactTitle;
    }

    public List<Fact> getFactsList() {
        return mFactsList;
    }
}