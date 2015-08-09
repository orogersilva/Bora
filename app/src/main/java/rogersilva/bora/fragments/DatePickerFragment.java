package rogersilva.bora.fragments;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

import rogersilva.bora.interfaces.OnDatePickerFragmentListener;

/**
 * Created by RogerSilva on 8/8/2015.
 */
public class DatePickerFragment extends DialogFragment
    implements OnDateSetListener {

    public static final String TAG = "DatePickerFragment";

    // region INSTANCE VARIABLES

    private OnDatePickerFragmentListener mDatePickerFragmentListener;

    // endregion

    // region FACTORY METHOD

    public static DatePickerFragment newInstance(OnDatePickerFragmentListener listener) {

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setDatePickerListener(listener);

        return fragment;
    }

    // endregion

    // region OVERRIDES METHODS

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {

        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        Date date = c.getTime();

        notifyDatePickerListener(date);
    }

    // endregion

    // region GETTERS AND SETTERS

    public void setDatePickerListener(OnDatePickerFragmentListener listener) {

        this.mDatePickerFragmentListener = listener;
    }

    // endregion

    // region NOTIFY METHODS

    public void notifyDatePickerListener(Date date) {

        if (mDatePickerFragmentListener != null)
            mDatePickerFragmentListener.onDateSet(date);
    }

    // endregion
}
