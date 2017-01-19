package com.createfakecontacts.hitapis;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by guruchetan on 14/9/16.
 */
public class VolleyUtil {
    private RequestQueue volleyRequestQueue;
    private static VolleyUtil volleyUtil;

    private VolleyUtil(Context context) {
        volleyRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static VolleyUtil getInstance(Context context) {
        if (volleyUtil == null) {
            synchronized (VolleyUtil.class) {
                if (volleyUtil == null) {
                    volleyUtil = new VolleyUtil(context);
                }
            }
        }
        return volleyUtil;
    }

    public void addToQueue(Request request, String tag) {
        request.setTag(tag);
        volleyRequestQueue.add(request);
    }

    public void cancelAll(String tag) {
        volleyRequestQueue.cancelAll(tag);
    }
}
