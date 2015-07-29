package rogersilva.bora.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import rogersilva.bora.R;
import rogersilva.bora.fragments.HomeFragment;
import rogersilva.bora.interfaces.OnFragmentTransactionListener;

public class MainActivity extends Activity
    implements OnFragmentTransactionListener {

    // region INSTANCE VARIABLES

    // endregion

    // region IMPLEMENTED INTERFACE METHODS

    @Override
    public void goTo(Fragment targetFragment) {

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, targetFragment)
                .addToBackStack(null)
                .commit();
    }

    // endregion

    // region ACTIVITY LIFECYCLE METHODS

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goTo(new HomeFragment());
    }

    // endregion

    // region MENU HANDLING METHODS

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

    // endregion
}
