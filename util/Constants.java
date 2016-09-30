package com.hathway.constants;

import com.google.gson.Gson;

/**
 * Created by guruchetan on 29/8/16.
 */
public interface Constants {


    String TAG = "way";

   
    String HEADER_CONTENT_TYPE = "Content-Type";
    String CONTENT_TYPE_VALUE = "application/json; charset=utf-8";

    String BASE_URL = "http:/.....";

    String ACTION_LOGIN = "/login";
    String ACTION_SIGN_UP = "/signup";
    String ACTION_VERIFY_OTP = "/verifyForgotPasswordOTP";
    String ACTION_FORGET_PASSWORD_OTP = "/sendForgotPasswordOTP";
    String ACTION_UPDATE_PASSWORD = "/updateForgotPassword";
    String ACTION_RESEND_OTP = "/resendOTP";
    String ACTION_CUSTOMER_DETAILS="/customerDetails";

 
    
    //Shared Preference
    String SHARED_PREF_NAME = "_pref";

    //for email pattern validation
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    //for mobile pattern validation
    String mobilePattern = "[0-9]{10}";


    //for password pattern
    String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$";//"^[a-zA-Z0-9]+$";//"((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,100})";

    int ID_LENGTH = 10;
    int PASSWORD_MIN_LENGTH = 6;

    //for event bus
    String LOGIN_TAG = "login";
    String SIGN_UP_TAG = "signup";
    String VERIFY_OTP_TAG = "verify";
    String FORGOT_PASSWORD_TAG = "forgetPassword";
    String UPDATE_PASSWORD_TAG = "updatePassword";
    String CUSTOMER_DETAILS_TAG = "customerDetails";
    String RESEND_OTP_TAG = "resendOTP";

    Gson gson = new Gson();
}
