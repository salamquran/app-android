package com.ermile.salamquran.Function.Utility;

public class even_odd {
   public static boolean isOdd( int val ) {
        return (val & 0x01) != 0;
    }

    public static boolean isEven( int val ) {
        return (val & 0x01) == 0;
    }
}
