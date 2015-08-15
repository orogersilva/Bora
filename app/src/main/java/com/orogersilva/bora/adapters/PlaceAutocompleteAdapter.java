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

package com.orogersilva.bora.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by RogerSilva on 8/15/2015.
 */
public class PlaceAutocompleteAdapter extends ArrayAdapter<PlaceAutocompleteAdapter.PlaceAutocomplete>
    implements Filterable {

    private static final String TAG = "PlaceAutocompleteAdapter";

    // region INSTANCE VARIABLES

    private List<PlaceAutocomplete> mResultList;
    private GoogleApiClient mGoogleApiClient;
    private LatLngBounds mBounds;
    private AutocompleteFilter mPlaceFilter;

    // endregion

    // region CONSTRUCTORS

    public PlaceAutocompleteAdapter(Context context, int resource, GoogleApiClient googleApiClient,
                                    LatLngBounds bounds, AutocompleteFilter placeFilter) {

        super(context, resource);

        mGoogleApiClient = googleApiClient;
        setBounds(bounds);
        mPlaceFilter = placeFilter;
    }

    // endregion

    // region GETTERS AND SETTERS

    public void setBounds(LatLngBounds bounds) {

        mBounds = bounds;
    }

    // endregion

    // region OVERRIDE METHODS

    @Override public int getCount() {

        return mResultList.size();
    }

    @Override public PlaceAutocomplete getItem(int position) {

        return mResultList.get(position);
    }

    @Override public Filter getFilter() {

        Filter filter = new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();

                // Skip the autocomplete query of no constraints are given
                if (constraint != null) {

                    //Query the autocomplete API for the (constraint) search string
                    mResultList = getAutoComplete(constraint);

                    if (mResultList != null) {

                        results.values = mResultList;
                        results.count = mResultList.size();
                    }
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                if (results != null && results.count > 0) {

                    notifyDataSetChanged();
                } else {

                    notifyDataSetInvalidated();
                }
            }
        };

        return filter;
    };

    // endregion

    // region UTILITY METHODS

    /**
     * Submits an autocomplete query to the Places Geo Data Autocomplete API.
     * objects to store the Place ID and description that the API returns.
     * Returns an empty list if no results were found.
     * Returns null if the API client is not available or the query did not complete
     * successfully.
     * This method MUST be called off the main UI thread, as it will block until data is returned
     * from the API, which may include a network request.
     *
     * @param constraint Autocomplete query string
     * @return Results from the autocomplete API or null if the query was not successful.
     */
    private ArrayList<PlaceAutocomplete> getAutoComplete(CharSequence constraint) {

        final int AWAIT_TIME_API_RESULT = 60;

        if (mGoogleApiClient.isConnected()) {

            // Submit the query to the autocomplete API and retrieve a PendingResult that will
            // contain the results when the query completes.
            PendingResult<AutocompletePredictionBuffer> results =
                    Places.GeoDataApi
                        .getAutocompletePredictions(mGoogleApiClient, constraint.toString(),
                                mBounds, mPlaceFilter);

            // This method should have been called off the main UI thread. Block and wait for at most 60s
            // for a result from the API.
            AutocompletePredictionBuffer autocompletePredictions = results
                    .await(AWAIT_TIME_API_RESULT, TimeUnit.SECONDS);

            final Status status = autocompletePredictions.getStatus();

            if (!status.isSuccess()) {

                Toast.makeText(getContext(), "Error contacting API: " + status.toString(),
                        Toast.LENGTH_SHORT).show();

                autocompletePredictions.release();

                return null;
            }

            Iterator<AutocompletePrediction> iterator = autocompletePredictions.iterator();
            ArrayList resultList = new ArrayList<>(autocompletePredictions.getCount());

            while (iterator.hasNext()) {

                AutocompletePrediction prediction = iterator.next();

                resultList.add(new PlaceAutocomplete(prediction.getPlaceId(),
                        prediction.getDescription()));

                autocompletePredictions.release();

                return resultList;
            }
        }

        return null;
    }

    // endregion

    /**
     * Holder for Places Geo Data Autocomplete API results.
     */
    public class PlaceAutocomplete {

        public CharSequence placeId;
        public CharSequence description;

        PlaceAutocomplete(CharSequence placeId, CharSequence description) {

            this.placeId = placeId;
            this.description = description;
        }

        @Override
        public String toString() {

            return description.toString();
        }
    }
}
