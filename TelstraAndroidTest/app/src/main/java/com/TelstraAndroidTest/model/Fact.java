package com.TelstraAndroidTest.model;

import com.google.gson.annotations.SerializedName;

/**
 * This is a model class for fact entity.
 */

public class Fact {

    /**
     * Fact title.
     */
    @SerializedName("title")
    private String mTitle;

    /**
     * Fact description.
     */
    @SerializedName("description")
    private String mDescription;

    /**
     * Fact image URL
     */
    @SerializedName("imageHref")
    private String mImageURL;


    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getImageURL() {
        return mImageURL;
    }
}
