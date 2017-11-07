package com.TelstraAndroidTest;

import android.support.test.espresso.core.deps.guava.base.Verify;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.view.menu.ActionMenuItem;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.TelstraAndroidTest.view.FactListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by Vaibhav Yerawar on 11/5/2017.
 */

@MediumTest
@RunWith(AndroidJUnit4.class)
public class FactListActivityTest {

    /**
     * Reference to activity to test.
     */
    @Rule
    public ActivityTestRule<FactListActivity> rule  = new ActivityTestRule<>(FactListActivity.class);

    /**
     * Method to test listview initialization functionality.
     * @throws Exception - Exception raised during execution.
     */
    @Test
    public void testListViewInitialized() throws Exception {
        FactListActivity activity = rule.getActivity();
        View viewById = activity.findViewById(R.id.lstViewFacts);
        assertThat(viewById,notNullValue());
        assertThat(viewById, instanceOf(ListView.class));
        ListView lstViewFacts = (ListView) viewById;
        ListAdapter adapter = lstViewFacts.getAdapter();
        assertThat(adapter, instanceOf(BaseAdapter.class));
        assertThat(adapter.getCount(), greaterThanOrEqualTo(0));
    }


    /**
     * Method to test list refresh functionality.
     * @throws Exception - Exception raised during execution.
     */
    @Test
    public void testRefreshList() throws Exception {
        FactListActivity activity = rule.getActivity();
        View viewById = activity.findViewById(R.id.action_refresh);
        assertThat(viewById,notNullValue());
        assertThat(viewById, instanceOf(ActionMenuItem.class));
        onView(withId(R.id.action_refresh)).perform(click());

        viewById = activity.findViewById(R.id.lstViewFacts);
        assertThat(viewById,notNullValue());
        assertThat(viewById, instanceOf(ListView.class));
        ListView lstViewFacts = (ListView) viewById;
        ListAdapter adapter = lstViewFacts.getAdapter();
        assertThat(adapter, instanceOf(BaseAdapter.class));
        assertThat(adapter.getCount(), greaterThan(0));
    }
}
