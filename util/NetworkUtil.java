package com.hathway.util;

import android.content.Context;
import android.net.ConnectivityManager;

import com.hathway.constants.Constants;

import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * This is a single tone class to handles network things.
 */
public class NetworkUtil implements Constants {

    private static NetworkUtil networkUtil;
    private Context context;

    private NetworkUtil(Context context) {
        this.context = context;
    }

    public static NetworkUtil getInstance(Context context) {
        if (networkUtil == null)
            return networkUtil = new NetworkUtil(context);
        else
            return networkUtil;
    }

    ///// TODO: 7/9/16 add documentation
    private boolean checkConnection() {

        boolean connected = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm != null) {
//            NetworkInfo[] netInfo = cm.getAllNetworkInfo();
//
//            for (NetworkInfo ni : netInfo) {
//                if ((ni.getTypeName().equalsIgnoreCase("WIFI")
//                        || ni.getTypeName().equalsIgnoreCase("MOBILE"))
//                        & ni.isConnected() & ni.isAvailable()) {
//                    connected = true;
//                }
//            }

            try {
                connected = cm.getActiveNetworkInfo().isConnected() & cm.getActiveNetworkInfo().isConnected();
            } catch (Exception e) {
                e.printStackTrace();
                connected = false;
            }
        }
        return connected;
    }

    ///// TODO: 7/9/16 add documentation 
    private boolean inetAddr() {

        boolean flag = false;


        try {
            Socket s = new Socket("utcnist.colorado.edu", 37);

            InputStream i = s.getInputStream();

            Scanner scan = new Scanner(i);

            while (scan.hasNextLine()) {
                System.out.println(scan.nextLine());
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }

        return flag;

    }


    /**
     * To check if internet is available.
     *
     * @return true if available, false otherwise.
     */
    public boolean isNetworkAvailable() {
        return checkConnection() && inetAddr();
    }
}
