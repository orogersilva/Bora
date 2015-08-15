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
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.orogersilva.bora.R;
import com.orogersilva.bora.interfaces.OnFragmentTransactionListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by RogerSilva on 7/26/2015.
 */
public class HomeFragment extends Fragment {

    public static final String TAG = "HomeFragment";

    // region INSTANCE VARIABLES

    @Bind(R.id.create_event_button) Button mCreateEventButton;
    private OnFragmentTransactionListener mTransactionListener;

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

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, fragmentView);

        return fragmentView;
    }

    @Override public void onResume() {

        super.onResume();

        Log.d(TAG, TAG + " onResumed.");
    }

    @Override public void onDestroyView() {

        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    // endregion

    // region BUTTERKNIFE METHODS

    @OnClick(R.id.create_event_button)
    public void onCreateEvent() {

        mTransactionListener.goTo(new EventCreationFragment(), EventCreationFragment.TAG);
    }

    // endregion
}
