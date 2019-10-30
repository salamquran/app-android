package com.ermile.salamquran.Functions;

public class utility {

    public static String numberToFarsi(int Number) {
        String faNumbers = String.valueOf(Number);
        String[][] mChars = new String[][]{
                {"0", "۰"},
                {"1", "۱"},
                {"2", "۲"},
                {"3", "۳"},
                {"4", "۴"},
                {"5", "۵"},
                {"6", "۶"},
                {"7", "۷"},
                {"8", "۸"},
                {"9", "۹"}
        };

        for (String[] num : mChars) {

            faNumbers = faNumbers.replace(num[0], num[1]);

        }

        return faNumbers;
    }

    /**
     * Odd & Even
     */
    public static boolean isOdd( int val ) {
        return (val & 0x01) != 0;
    }

    public static boolean isEven( int val ) {
        return (val & 0x01) == 0;
    }
}
