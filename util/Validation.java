package com.createfakecontacts.util;

import android.app.Activity;

/**
 * Created by guruchetan on 1/9/16.
 */
public class Validation implements Constants {

    /**
     * This method is used to check email pattern
     *
     * @param email email id
     * @return true, if pattern matches, otherwise false
     */
    public static boolean isValidEmail(String email) {
        if (Util.isStringEmpty(email))
            return false;
        else
            return email.trim().matches(emailPattern);
//            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * This method is used to check mobile number pattern
     *
     * @param number should be a valid mobile number
     * @return true, if pattern matches otherwise false
     */
    public static boolean isValidMobileNumber(String number) {
        if (Util.isStringEmpty(number))
            return false;
        else {
//             return android.util.Patterns.PHONE.matcher(number.trim()).matches();
            return number.trim().matches(mobilePattern);

        }
    }

    /**
     * This method is used to check mobile number pattern
     *
     * @param password should be a valid alpha numeric, min length 6
     * @return true, if pattern matches otherwise false
     */
    private static boolean isValidPassword(String password) {
        if (Util.isStringEmpty(password))
            return false;
        else {
            return password.trim().matches(passwordPattern);

        }
    }

    /**
     * This method is used to check logiID pattern
     *
     * @param logiID should be a valid logiID
     * @return true, if pattern matches otherwise false
     */
    private static boolean isValidLoginID(String logiID) {
        if (Util.isStringEmpty(logiID))
            return false;
        else {
            return logiID.trim().matches(logiIDPattern);

        }
    }

    //// TODO: 27/9/16 add documnetation
    private static boolean isValidLength(String value, int length) {
        if (Util.isStringEmpty(value))
            return false;
        else
            return value.length() >= length;
    }

    public static boolean isValidId(Activity activity, String value, int length) {

        if (Util.isStringEmpty(value)) {
            Util.showToast(activity, activity.getResources().getString(R.string.logiID_empty));
            return false;
        }
        if (!Validation.isValidLength(value, length)) {
            Util.showToast(activity, activity.getResources().getString(R.string.valid_customer_length));
            return false;
        }

        if (!Validation.isValidLoginID(value)) {
            Util.showToast(activity, activity.getResources().getString(R.string.valid_cust_id));
            return false;
        }

        return true;
    }

    public static boolean isValidPassword(Activity activity, String value, int length) {

        if (Util.isStringEmpty(value)) {
            Util.showToast(activity, activity.getResources().getString(R.string.please_enter_password));
            return false;
        }
        if (!Validation.isValidLength(value, length)) {
            Util.showToast(activity, activity.getResources().getString(R.string.password_min_length));
            return false;
        }

        if (!Validation.isValidPassword(value)) {
            Util.showToast(activity, activity.getResources().getString(R.string.password_must_be_alpha_numeric));
            return false;
        }

        return true;
    }

}
