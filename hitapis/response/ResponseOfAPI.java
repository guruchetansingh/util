package com.createfakecontacts.hitapis.response;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by guruchetan on 19/9/16.
 * <p/>
 * Handling response
 */
public class ResponseOfAPI {

    /**
     * This helps to diffrenciate response
     * */
    public  int errorCode;
    /**
     * This helps to identify response category, like either login/sign up/others
     */
    public String tag;

    /**
     * Represent API response in JSON format
     */
    public JSONObject response;

    /*
    * Represent API response is successful or not;
    * true in case of success, false otherwise.
    * */
    public boolean isSuccess = false;

    /*
    * Represent status of server response,for both success and failure
    * */
    public String message;

    public ResponseOfAPI(String tag, JSONObject response) {
        this.tag = tag;
        this.response = response;

        try {
            this.message = response.getString("errorMessage");
            this.errorCode=response.getInt("errorCode");
            if (errorCode == 0) {
                this.isSuccess = true;
            } else {
                this.isSuccess = false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
