package com.orogersilva.bora.data.contracts;

import android.provider.BaseColumns;

/**
 * Created by RogerSilva on 7/26/2015.
 */
public final class EventContract {

    public EventContract() {}

    public static abstract class EventEntry implements BaseColumns {

        public static final String TABLE_NAME = "event";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";

        private static final String INTEGER_TYPE = "INTEGER";
        private static final String TEXT_TYPE = "TEXT";
        private static final String COMMA_SEPARATOR = ",";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_NAME_ID + " " + INTEGER_TYPE + " PRIMARY KEY" + COMMA_SEPARATOR + " " +
                        COLUMN_NAME_NAME + " " + TEXT_TYPE + " " + "NOT NULL" + COMMA_SEPARATOR + " " +
                        COLUMN_NAME_DESCRIPTION + " " + TEXT_TYPE + COMMA_SEPARATOR + " " +
                        ")";

        public static final String SQL_DROP_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
