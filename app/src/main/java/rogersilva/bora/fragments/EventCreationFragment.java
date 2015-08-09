package rogersilva.bora.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import rogersilva.bora.R;
import rogersilva.bora.interfaces.OnDatePickerFragmentListener;
import rogersilva.bora.interfaces.OnFragmentTransactionListener;
import rogersilva.bora.interfaces.OnTimePickerFragmentListener;

/**
 * Created by RogerSilva on 7/28/2015.
 */
public class EventCreationFragment extends Fragment
    implements OnDatePickerFragmentListener, OnTimePickerFragmentListener {

    public static final String TAG = "EventCreationFragment";

    // region INSTANCE VARIABLES

    private OnFragmentTransactionListener mTransactionListener;
    private EditText eventDateEditText;
    private EditText eventTimeEditText;

    // endregion

    // region GETTERS AND SETTERS

    private OnDatePickerFragmentListener getDatePickerFragmentListener() {

        return this;
    }

    private OnTimePickerFragmentListener getTimePickerFragmentListener() {

        return this;
    }

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

        View fragmentView = inflater.inflate(R.layout.fragment_event_creation, container, false);

        eventTimeEditText = (EditText) fragmentView.findViewById(R.id.event_time_edittext);
        eventTimeEditText.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                DialogFragment timePickerFragment = TimePickerFragment.newInstance(
                        getTimePickerFragmentListener());
                timePickerFragment.show(getFragmentManager(), TimePickerFragment.TAG);
            }
        });

        eventDateEditText = (EditText) fragmentView.findViewById(R.id.event_date_edittext);
        eventDateEditText.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                DialogFragment datePickerFragment = DatePickerFragment.newInstance(
                        getDatePickerFragmentListener());
                datePickerFragment.show(getFragmentManager(), DatePickerFragment.TAG);
            }
        });

        return fragmentView;
    }

    @Override
    public void onResume() {

        super.onResume();

        Log.d(TAG, TAG + " onResumed.");
    }

    // endregion

    // region OVERRIDES METHODS

    @Override
    public void onDateSet(Date date) {

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
        String formattedDate = dateFormat.format(date);

        eventDateEditText.setText(formattedDate);
    }

    @Override
    public void onTimeSet(String time) {

        eventTimeEditText.setText(time);
    }

    // endregion
}