package rogersilva.bora.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rogersilva.bora.R;

/**
 * Created by RogerSilva on 7/26/2015.
 */
public class HomeFragment extends Fragment {

    // region INSTANCE VARIABLES

    private OnEventCreationDispatchListener mListener;

    // endregion

    // region INTERFACES

    public interface OnEventCreationDispatchListener {

        public void onEventCreationDispatched();
    }

    // endregion

    // region FRAGMENT LIFECYCLE METHODS

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);

        try {

            mListener = (OnEventCreationDispatchListener) activity;
        }
        catch (ClassCastException e) {

            throw new ClassCastException(activity.toString() + " must implement");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    // endregion
}
