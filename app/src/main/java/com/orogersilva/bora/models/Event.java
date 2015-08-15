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

package com.orogersilva.bora.models;

/**
 * Created by RogerSilva on 7/26/2015.
 */
public class Event {

    // region INSTANCE VARIABLES

    private long mId;
    private String mName;
    private String mDescription;

    // endregion

    // region CONSTRUCTORS

    public Event(long id, String name, String description) {

        setId(id);
        setName(name);
        setDescription(description);
    }

    // endregion

    // region GETTERS AND SETTERS

    public long getId() {

        return mId;
    }

    public void setId(long id) {

        mId = id;
    }

    public String getName() {

        return mName;
    }

    public void setName(String name) {

        mName = name;
    }

    public String getDescription() {

        return mDescription;
    }

    public void setDescription(String description) {

        mDescription = description;
    }

    // endregion

    @Override
    public boolean equals(Object obj) {

        if (obj == null)
            return false;

        if (obj == this)
            return true;

        if (obj instanceof Event) {

            Event otherEvent = (Event) obj;

            return (otherEvent.getId() == this.getId() &&
                    otherEvent.getName().equals(this.getName()) &&
                    otherEvent.getDescription().equals(this.getDescription()));
        }

        return false;
    }
}
