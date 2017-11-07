package com.TelstraAndroidTest.model;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.StringUtils;

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


    /**
     * Method to get fact title.
     * @return - Fact title.
     */
    public String getTitle() {
        return nullCheckValidator(mTitle);
    }

    /**
     * Method to get description about fact.
     * @return - Fact description.
     */
    public String getDescription() {
        return nullCheckValidator(mDescription);
    }

    /**
     * Method to get URL of image in fact details.
     * @return - Fact image URL.
     */
    public String getImageURL() {
        return nullCheckValidator(mImageURL);
    }

    /**
     * Method to validate nullable values.
     * @param value to validate
     * @return - value if not null else returns empty string as value.
     */
    private String nullCheckValidator(String value){

        final String defaultText = "Facts";
        return StringUtils.isNotBlank(value) ? value : defaultText;
    }
}