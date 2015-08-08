package rogersilva.bora.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rogersilva.bora.R;
import rogersilva.bora.interfaces.OnFragmentTransactionListener;

/**
 * Created by RogerSilva on 7/28/2015.
 */
public class EventCreationFragment extends Fragment {

    public static final String TAG = "EventCreationFragment";

    // region INSTANCE VARIABLES

    private OnFragmentTransactionListener mTransactionListener;

    // endregion

    // region FRAGMENT LIFECYCLE METHODS

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);

        try {

            mTransactionListener = (OnFragmentTransactionListener) activity;
        }
        catch (ClassCastException e) {

            throw new ClassCastException(activity.toString() + " must implement OnFragmentTransactionListener.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_event_creation, container, false);
    }

    @Override
    public void onResume() {

        super.onResume();

        Log.d(TAG, TAG + " onResumed.");
    }

    // endregion
}
