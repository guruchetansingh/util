package com.hathway.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.hathway.constants.Constants;

/**
 * This is a common class for shared preference.
 * <p>
 * Created by guruchetan on 29/8/16.
 */
public class SharedPreferenceUtils implements Constants {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static SharedPreferenceUtils sharedPreferenceUtils;

    private SharedPreferenceUtils(Context context) {
        this.sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    public static SharedPreferenceUtils getInstance(Context context) {
        if (sharedPreferenceUtils == null)
            return sharedPreferenceUtils = new SharedPreferenceUtils(context);
        else
            return sharedPreferenceUtils;
    }

    /**
     * This method is used to store a string value in the shared preference
     *
     * @param key   the key on which the string value will be stored
     * @param value the string value to store
     */
    public void setString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * This method returns the value corresponding to the key, or the default value if the key value pair does not exist
     *
     * @param key        the key corresponding which the string value is to be retrieved
     * @param defaultVal the default value to return if the key does not exist
     * @return the string value corresponding to the key if the key exists, else returns the default value
     */
    public String getString(String key, String defaultVal) {
        return sharedPreferences.getString(key, defaultVal);
    }
}