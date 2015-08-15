/*
 * Copyright (C) 2015 Roger Silva
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.orogersilva.bora.fragments;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.orogersilva.bora.R;
import com.orogersilva.bora.adapters.PlaceAutocompleteAdapter;
import com.orogersilva.bora.interfaces.OnDatePickerFragmentListener;
import com.orogersilva.bora.interfaces.OnFragmentTransactionListener;
import com.orogersilva.bora.interfaces.OnTimePickerFragmentListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Created by RogerSilva on 7/28/2015.
 */
public class EventCreationFragment extends Fragment
    implements OnDatePickerFragmentListener, OnTimePickerFragmentListener,
        ConnectionCallbacks, OnConnectionFailedListener {

    public static final String TAG = "EventCreationFragment";

    private static final LatLngBounds BOUNDS_PORTOALEGRE = new LatLngBounds(
            new LatLng(-30.081245, -51.135085), new LatLng(-30.026270, -51.220744));

    private PlaceAutocompleteAdapter mAdapter;

    // region INSTANCE VARIABLES

    @Bind(R.id.event_date_edittext) EditText eventDateEditText;
    @Bind(R.id.event_time_edittext) EditText eventTimeEditText;
    // @Bind(R.id.event_place_autocompletetextview) AutoCompleteTextView eventPlaceAutocompleteTextView;
    private AutoCompleteTextView eventPlaceAutocompleteTextView;

    private GoogleApiClient mGoogleApiClient;
    private OnFragmentTransactionListener mTransactionListener;

    private OnItemClickListener mAutoCompleteClickListener =
            new AdapterView.OnItemClickListener() {

        @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            final PlaceAutocompleteAdapter.PlaceAutocomplete item = mAdapter.getItem(position);
            final String placeId = String.valueOf(item.placeId);

            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(mGoogleApiClient, placeId);
            // placeResult.setResultCallback();

            Toast.makeText(getActivity(), "Clicked: " + item.description,
                    Toast.LENGTH_SHORT).show();
        }
    };

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

    @Override public void onAttach(Activity activity) {

        super.onAttach(activity);

        try {

            mTransactionListener = (OnFragmentTransactionListener) activity;
        }
        catch (ClassCastException e) {

            throw new ClassCastException(activity.toString() + " must implement OnFragmentTransactionListener.");
        }
    }

    @Override public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mGoogleApiClient = new GoogleApiClient
                .Builder(getActivity())
                .addApi(Places.GEO_DATA_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_event_creation, container, false);

        ButterKnife.bind(this, fragmentView);

        eventPlaceAutocompleteTextView = (AutoCompleteTextView) fragmentView.findViewById(R.id.event_place_autocompletetextview);
        eventPlaceAutocompleteTextView.setOnItemClickListener(mAutoCompleteClickListener);

        mAdapter = new PlaceAutocompleteAdapter(getActivity(), android.R.layout.simple_list_item_1,
                mGoogleApiClient, BOUNDS_PORTOALEGRE, null);
        eventPlaceAutocompleteTextView.setAdapter(mAdapter);

        return fragmentView;
    }

    @Override public void onStart() {

        super.onStart();

        mGoogleApiClient.connect();
    }

    @Override public void onResume() {

        super.onResume();
    }

    @Override public void onStop() {

        mGoogleApiClient.disconnect();

        super.onStop();
    }

    @Override public void onDestroyView() {

        super.onDestroyView();

        ButterKnife.unbind(this);
    }

    // endregion

    // region BUTTERKNIFE METHODS

    @OnClick(R.id.event_date_edittext)
    public void showEventDateDialog() {

        DialogFragment datePickerFragment = DatePickerFragment.newInstance(
                getDatePickerFragmentListener());
        datePickerFragment.show(getFragmentManager(), DatePickerFragment.TAG);
    }

    @OnClick(R.id.event_time_edittext)
    public void showEventTimeDialog() {

        DialogFragment timePickerFragment = TimePickerFragment.newInstance(
                getTimePickerFragmentListener());
        timePickerFragment.show(getFragmentManager(), TimePickerFragment.TAG);
    }

    /*@OnItemClick(R.id.event_place_autocompletetextview)
    public void selectPlaceSuggestion(int position) {

        final PlaceAutocompleteAdapter.PlaceAutocomplete item = mAdapter.getItem(position);
        final String placeId = String.valueOf(item.placeId);

        PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                .getPlaceById(mGoogleApiClient, placeId);
        // placeResult.setResultCallback();

        Toast.makeText(getActivity(), "Clicked: " + item.description,
                Toast.LENGTH_SHORT).show();
    }*/

    // endregion

    // region OVERRIDES METHODS

    @Override public void onDateSet(Date date) {

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
        String formattedDate = dateFormat.format(date);

        eventDateEditText.setText(formattedDate);
    }

    @Override public void onTimeSet(String time) {

        eventTimeEditText.setText(time);
    }

    @Override public void onConnected(Bundle bundle) {
    }

    @Override public void onConnectionSuspended(int i) {
    }

    @Override public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    // endregion
}