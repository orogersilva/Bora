package rogersilva.bora.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import rogersilva.bora.R;
import rogersilva.bora.fragments.HomeFragment;
import rogersilva.bora.fragments.HomeFragment.OnEventCreationDispatchListener;

public class MainActivity extends Activity
    implements OnEventCreationDispatchListener{

    // region IMPLEMENTED INTERFACE METHODS

    @Override
    public void onEventCreationDispatched() {
    }

    // endregion

    // region ACTIVITY LIFECYCLE METHODS

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HomeFragment homeFragment = new HomeFragment();

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, homeFragment);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
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
