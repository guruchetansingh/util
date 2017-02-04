package com.createfakecontacts.util;
/**
 * Created by guru on 03-02-2017.
 */

import android.text.TextUtils;
import android.util.Log;

import java.math.BigDecimal;

/**
 * Created by guru
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
//        Log.i("AmountValidation", "valid 9.782 = " + new AmountValidation().isValidAmount("9.782"));
//        Log.i("AmountValidation", "valid 9..782 = " + new AmountValidation().isValidAmount("9..782"));
//        Log.i("AmountValidation", "valid .. = " + new AmountValidation().isValidAmount(".."));
//    Log.i("AmountValidation", "new Double(\"..\") = " + new AmountValidation().isValidAmount(new Double("..")));
public class AmountValidation {

    private final String TAG = "AmountValidation";

    /**
     * Maximum amount range eg. 0-2000 or 10-10000 or 5-400 etc
     */
    private double MAX_RANG;

    /**
     * Minimum amount range
     */
    private double MIN_RANG;

    public AmountValidation() {
        this.MIN_RANG = 0;
        this.MAX_RANG = 100000;
    }

    public AmountValidation(double MIN_RANG, double MAX_RANG) {
        this.MIN_RANG = MIN_RANG;
        this.MAX_RANG = MAX_RANG;

    }


    /**/
    public boolean isValidAmount(String amount) {

        if (hasThreeDigitAfterDecimal(amount))
            return false;


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
//            Log.i(TAG, "decimal.doubleValue() = " + decimal.toString());
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

    private boolean hasThreeDigitAfterDecimal(String number) {
        if (TextUtils.isEmpty(number))
            return true;

        int counter = 0;
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == '.') {
                ++counter;
                if (counter > 2)
                    return true;
            }

        }

        int index = number.indexOf('.');
        try {
            if (index == (number.length() - 1))
                return true;

            if (index == -1)
                return false;

        } catch (Exception e) {
            return true;
        }


        String sub = number.substring(number.indexOf('.') + 1);
        if (sub.length() > 2) {
            return true;
        }
        return false;
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
//            Log.i(TAG, "decimal.doubleValue() = " + decimal.toString());
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

    /**
     * This method is used to get amount formatted string<br>
     * eg. 1. to 1.00 or .0 to 0.00 or 12.123456 to 12.12
     */
    public String convertNumberToAmountFormat(Double amount) {
        if (amount != null)
            return convertNumberToAmountFormat(String.valueOf(amount));
        else
            return convertNumberToAmountFormat("");
    }
}
