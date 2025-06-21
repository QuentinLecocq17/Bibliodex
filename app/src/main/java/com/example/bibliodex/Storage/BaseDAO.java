package com.example.bibliodex.Storage;

import android.content.Context;

import com.google.gson.Gson;

public class BaseDAO {
    private Gson gson;
    private Context context;

    /**
     * Base constructor for Data Access Objects (DAO).
     * @param context Android context
     */
    public BaseDAO(Context context) {
        this.context = context;
        this.gson = new Gson();
    }

    /**
     * Returns the Android context associated with this DAO.
     * @return Android context
     */
    protected Context getContext() {
        return context;
    }

    /**
     * Returns the Gson instance used for JSON serialization/deserialization.
     * @return Gson instance
     */
    protected Gson getGson() {
        return gson;
    }
}