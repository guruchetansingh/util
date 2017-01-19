package com.createfakecontacts.util;

import android.util.Log;

import java.math.BigDecimal;

/**
 * Created by guruchetan on 18-01-2017.
 */

//        .0 , .00 , 0. , 0.0 , 0.00 , 00. , 00.0 , 00.00
//Log.i("AmountValidation", "valid . = " + new AmountValidation().isValidAmount("."));
//        Log.i("AmountValidation", "valid .0 = " + new AmountValidation().isValidAmount(".0"));
//        Log.i("AmountValidation", "valid .00 = " + new AmountValidation().isValidAmount(".00"));
//        Log.i("AmountValidation", "valid 0. = " + new AmountValidation().isValidAmount("0."));
//        Log.i("AmountValidation", "valid 0.0 = " + new AmountValidation().isValidAmount("0.0"));
//        Log.i("AmountValidation", "valid 00. = " + new AmountValidation().isValidAmount("00."));
//        Log.i("AmountValidation", "valid 00.0 = " + new AmountValidation().isValidAmount("00.0"));
//        Log.i("AmountValidation", "valid 00.00 = " + new AmountValidation().isValidAmount("00.00"));
//        Log.i("AmountValidation", "valid 9.. = " + new AmountValidation().isValidAmount("9.."));
//        Log.i("AmountValidation", "valid .99 = " + new AmountValidation().isValidAmount(".99"));
//        Log.i("AmountValidation", "valid 9. = " + new AmountValidation().isValidAmount("9."));
//        Log.i("AmountValidation", "valid 9,78 = " + new AmountValidation().isValidAmount("9,78"));
public class AmountValidation {

    private final String TAG = "AmountValidation";

    /**
     * Maximum amount range eg. 0-2000 or 10-10000 or 5-400 etc
     */
    private final double MAX_RANG = 100000;

    /**
     * Minimum amount range
     */
    private final double MIN_RANG = 0;

    /**/
    public boolean isValidAmount(String amount) {

        if (!isValidNumber(amount))
            return false;

        return true;
    }

    public boolean isValidAmount(double amount) {
        return isValidAmount(amount + "");
    }

    private boolean isValidNumber(String number) {
        try {

            BigDecimal decimal = new BigDecimal(number);
            decimal = decimal.setScale(2, BigDecimal.ROUND_DOWN);
            Log.i(TAG, "decimal.doubleValue() = " + decimal.toString());
            if (!isValidRange(decimal.doubleValue()))
                return false;

            return true;
        } catch (NumberFormatException e) {
            Log.e(TAG, "[NumberFormatException] = " + e.toString());
            return false;
        } catch (Exception e) {
            Log.e(TAG, "[Exception] = " + e.toString());
            return false;
        }
    }

    /**
     * This method validate amount range.Amount should be in between MIN_RANG to MAX_RANG.
     */
    private boolean isValidRange(double amount) {
        return (amount > MIN_RANG && amount <= MAX_RANG);
    }

    /**
     * This method is used to get amount formatted string<br>
     * eg. 1. to 1.00 or .0 to 0.00 or 12.123456 to 12.12
     */
    public String convertNumberToAmountFormat(String amount) {
        try {

            BigDecimal decimal = new BigDecimal(amount);
            decimal = decimal.setScale(2, BigDecimal.ROUND_DOWN);
            Log.i(TAG, "decimal.doubleValue() = " + decimal.toString());
            return decimal.toString();
        } catch (NumberFormatException e) {
            Log.e(TAG, "[NumberFormatException] = " + e.toString());
            return "0.00";
        } catch (Exception e) {
            Log.e(TAG, "[Exception] = " + e.toString());
            return "0.00";
        }
    }

    /**
     * This method is used to get amount formatted string<br>
     * eg. 1. to 1.00 or .0 to 0.00 or 12.123456 to 12.12
     */
    public String convertNumberToAmountFormat(double amount) {
        return convertNumberToAmountFormat(amount + "");
    }

}
