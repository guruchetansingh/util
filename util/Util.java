package com.hathway.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hathway.constants.Constants;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by guruchetan on 29/8/16.
 */
public class Util implements Constants {


    /**
     * This method check that string is null/empty
     *
     * @param s string value
     * @return true, if string is null/empty, otherwise false
     */
    public static boolean isStringEmpty(String s) {
        if (s == null) {
            return true;
        } else if (s.trim().isEmpty()) {
            return true;
        } else
            return false;
    }

    /**
     * This method check that string array is null/empty
     *
     * @param s string array value
     * @return true, if string array or its any element is null/empty, otherwise false
     */
    public static boolean isStringEmpty(String... s) {
        boolean flag = false;
        if (s == null)
            return true;

        if (s.length == 0)
            return true;
        else {
            for (int i = 0; i < s.length; i++) {
                if (isStringEmpty(s[i])) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * This method is used to show toast message
     *
     * @param activity activity reference
     * @param message  string message to be shown on toast
     */
    public static void showToast(final Activity activity, final String message) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * This method is used to set blank text to a view.
     *
     * @param view array of view(s)
     */
    public static void setBlankText(View... view) {

        for (int i = 0; i < view.length; i++) {

            if (view[i] instanceof TextView)
                ((TextView) view[i]).setText("");
            if (view[i] instanceof Button)
                ((Button) view[i]).setText("");
        }
    }


    /**
     * This method is used to create bitmap for indicating usage percentage, like 40 of 100 or 160 of 250 ..etc
     *
     * @param width     width for creating bitmap(should be equal to width of imageView/view)
     * @param height    height for creating bitmap(should be equal to height of imageView/view)
     * @param totalData total plan value, like 40 of 100, then 100 would be plan value
     * @param usedData  certain amount of plan which has been used, like 40 of 100, then 40 amount is used out of 100
     * @return bitmap image indicating plan usage in form of analog format
     **/
    public static Bitmap setPixel(int width, int height, float totalData, float usedData) throws Exception {

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int color = Color.GREEN;
        float tickHalf = bitmap.getWidth() / 2.0f;
        float oneAndAHalf = ((bitmap.getWidth() / 2.0f) + ((bitmap.getWidth() / 2.0f) / 2.0f));
        float numberOfPixels = (width / totalData) * usedData;

        for (int x = 0; x < bitmap.getWidth(); x++) {

            if (x <= tickHalf && x <= numberOfPixels)
                color = Color.GREEN;
            else if (x > tickHalf && x <= oneAndAHalf && x <= numberOfPixels)
                color = Color.YELLOW;
            else if (x > oneAndAHalf && x <= numberOfPixels)
                color = Color.parseColor("#f86f39");
            else
                color = Color.TRANSPARENT;

            for (int y = 0; y < bitmap.getHeight(); y++) {
                bitmap.setPixel(x, y, color);
            }
        }

        addDivider(bitmap, bitmap.getWidth() / 2);
        addDivider(bitmap, ((bitmap.getWidth() / 2) + ((bitmap.getWidth() / 2) / 2)));
        return bitmap;
    }

    /**
     * This method is used to add divider in bitmap
     *
     * @param bitmap in which divider is needed
     * @param div    from where divider starts
     */
    private static void addDivider(Bitmap bitmap, int div) {
        for (int x = div; x < (div + 5); x++) {
            for (int y = 0; y < bitmap.getHeight(); y++) {
                bitmap.setPixel(x, y, Color.WHITE);
            }
        }
    }


    //// TODO: 28/9/16  add documentation
    public static String getDeviceType() {
        return "android";
    }

    //// TODO: 28/9/16  add documentation
    public static String getDeviceOS() {
        StringBuilder builder = new StringBuilder();
        builder.append("androidRelease-").append(Build.VERSION.RELEASE);

        Field[] fields = Build.VERSION_CODES.class.getFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            int fieldValue = -1;

            try {
                fieldValue = field.getInt(new Object());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            if (fieldValue == Build.VERSION.SDK_INT) {
                builder.append("--").append(fieldName + "-" + fieldValue);
            }

        }
        return builder.toString();
    }

    public static void hideKeyBoard(Context context) {
        InputMethodManager inputManager = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        // check if no view has focus:
        View v = ((Activity) context).getCurrentFocus();
        if (v == null)
            return;

        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

}
