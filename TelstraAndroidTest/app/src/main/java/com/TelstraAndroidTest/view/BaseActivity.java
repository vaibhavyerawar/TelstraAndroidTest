package com.TelstraAndroidTest.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.TelstraAndroidTest.R;
import com.TelstraAndroidTest.controller.FactController;
import com.TelstraAndroidTest.utils.Constants;

public class BaseActivity extends AppCompatActivity {

    private AlertDialog mAlertDialog;

    protected Context mContext;
    protected FactController mFactControllerInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    /**
     * Method for initialization.
     */
    private void init() {
        mFactControllerInstance = FactController.getInstance();
    }

    /**
     * Method to initialize alert dialog and display.
     * @param message - Message for alert dialog.
     */
    public void showAlert(String message) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
        dialogBuilder.setTitle(getString(R.string.app_name));
        dialogBuilder.setMessage(message);
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        mAlertDialog = dialogBuilder.create();
        mAlertDialog.show();
    }

    /**
     * Method to hide action bar.
     */
    protected void hideActionBar() {
        getSupportActionBar().hide();
    }

    /**
     * Method to update action bar title string.
     * @param title - Text to set as title on action bar.
     */
    protected void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    /**
     * Method to launch new activity and finish current activity.
     * @param destClassRef - Reference to new activity to start.
     */
    protected void startNewActivity(Class destClassRef) {
        startActivity(new Intent(mContext, destClassRef));
        finish();
    }
}