package com.orogersilva.bora.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

import com.orogersilva.bora.interfaces.OnTimePickerFragmentListener;

/**
 * Created by RogerSilva on 8/8/2015.
 */
public class TimePickerFragment extends DialogFragment
    implements OnTimeSetListener {

    public static final String TAG = "TimePickerFragment";

    // region INSTANCE VARIABLES

    private OnTimePickerFragmentListener mTimePickerFragmentListener;

    // endregion

    // region FACTORY METHOD

    public static TimePickerFragment newInstance(OnTimePickerFragmentListener listener) {

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setTimePickerListener(listener);

        return fragment;
    }

    // endregion

    // region OVERRIDES METHODS

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        String time = hourOfDay + ":" + minute;

        notifyTimePickerListener(time);
    }

    // endregion

    // region GETTERS AND SETTERS

    public void setTimePickerListener(OnTimePickerFragmentListener listener) {

        mTimePickerFragmentListener = listener;
    }

    // endregion

    // region NOTIFY METHODS

    public void notifyTimePickerListener(String time) {

        if (mTimePickerFragmentListener != null)
            mTimePickerFragmentListener.onTimeSet(time);
    }

    // endregion
}
