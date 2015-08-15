package com.orogersilva.bora.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.crashlytics.android.Crashlytics;
import com.orogersilva.bora.R;
import com.orogersilva.bora.fragments.HomeFragment;
import com.orogersilva.bora.interfaces.OnFragmentTransactionListener;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends ActionBarActivity
    implements OnFragmentTransactionListener {

    public static final String TAG = "MainActivity";

    // region INSTANCE VARIABLES

    // endregion

    // region IMPLEMENTED INTERFACE METHODS

    @Override public void goTo(Fragment targetFragment, String tag) {

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, targetFragment)
                .addToBackStack(tag)
                .commit();
    }

    // endregion

    // region ACTIVITY LIFECYCLE METHODS

    @Override protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        goTo(new HomeFragment(), HomeFragment.TAG);
    }

    @Override public void onResume() {

        super.onResume();

        Log.d(TAG, TAG + " onResumed.");
    }

    // endregion

    // region OVERRIDE METHODS

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override public void onBackPressed() {

        getFragmentManager().popBackStack();

        if (getFragmentManager().getBackStackEntryCount() == 1)
            finish();
    }

    // endregion
}
