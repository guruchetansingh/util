package com.createfakecontacts.hitapis;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.constants.Constants;
import com.hitapis.response.ResponseOfAPI;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import de.greenrobot.event.EventBus;

import static com.createfakecontacts.util.Constants.BASE_URL;

/**
 * Created by guruchetan on 14/9/16.
 * <p/>
 * All API hits are controlled by this class
 */
public class HitAPI implements Constants {


    private EventBus eventBus;
    private Context context;

    /** to identify response */
    private String tag;

    public HitAPI(Context context, EventBus eventBus, String tag) {
        this.context = context;
        this.tag = tag;
        this.eventBus = eventBus;
    }

    /**
     * This is a common method to hit any API
     */
    public void hitAPI(String action, Map<String, Object> params) {
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, BASE_URL + action, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: " + response.toString());
                        eventBus.post(new ResponseOfAPI(tag, response));
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error.toString());
                eventBus.post(error);
            }

        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getHeader();
            }

            @Override
            public String getBodyContentType() {
                return CONTENT_TYPE_VALUE;
            }
        };
        try {
            Log.d(TAG, "requestParameters : " + new String(req.getPostBody(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        VolleyUtil.getInstance(context).addToQueue(req, tag);
    }


    private HashMap<String, String> getHeader() {
        HashMap<String, String> params = new HashMap<>();
        params.put(HEADER_API_KEY, API_KEY_VALUE);
        params.put(HEADER_CONTENT_TYPE, CONTENT_TYPE_VALUE);
        return params;
    }
}
