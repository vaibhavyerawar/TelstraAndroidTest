package com.TelstraAndroidTest.view;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.TelstraAndroidTest.R;
import com.TelstraAndroidTest.adapter.FactsListAdapter;
import com.TelstraAndroidTest.controller.FactController;
import com.TelstraAndroidTest.handler.IControllerCallbackHandler;
import com.TelstraAndroidTest.model.FactsCollection;

public class FactListActivity extends BaseActivity implements
                                IControllerCallbackHandler<FactsCollection> {

    private ListView lstViewFacts;
    private FactsListAdapter mFactsListAdapterInstance;
    private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts_layout);
        mContext = this;
        init();
    }

    private void init(){
        lstViewFacts = (ListView) findViewById(R.id.lstViewFacts);
        mFactsListAdapterInstance = new FactsListAdapter(mContext);
        lstViewFacts.setAdapter(mFactsListAdapterInstance);
        mFactControllerInstance.getFacts(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                menuItem = item;
                menuItem.setActionView(R.layout.progress_bar_layout);
              //  menuItem.expandActionView();
                mFactControllerInstance.getFacts(this);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onSuccess(FactsCollection response) {
        setActionBarTitle(response.getFactTitle());
        mFactsListAdapterInstance.setListDataSource(response);
        restMenuState();
    }

    @Override
    public void onFailure(String errorMsg) {

        restMenuState();
        showAlert(errorMsg);
    }

    /**
     * Method to reset menu states.
     */
    private void restMenuState(){

        if(menuItem != null) {
            menuItem.collapseActionView();
            menuItem.setActionView(null);
        }
    }
}