package com.ermile.salamquran.Functions;

import android.content.Context;
import android.util.Log;

import com.ermile.salamquran.Value.format;
import com.ermile.salamquran.Value.tag;

public class utility {

    public static String crateUrlAudio(Context context, String vers, String aya){

        String  repl_vers,
                repl_aya;

        String qari_Link = SaveManager.get(context).getstring_appINFO().get(SaveManager.qari_Link);

        switch (vers.length()){
            default:
                repl_vers = "00"+vers;
                break;
            case 2:
                repl_vers = "0"+vers;
                break;
            case 3:
                repl_vers = vers;
                break;
        }
        switch (aya.length()){
            default:
                repl_aya = "00"+aya;
                break;
            case 2:
                repl_aya = "0"+aya;
                break;
            case 3:
                repl_aya = aya;
                break;
        }

        Log.d(tag.carateURL, qari_Link+ repl_vers + repl_aya + format.mp3);
        return qari_Link+ repl_vers + repl_aya + format.mp3;
    }

    public static String besmellah(Context context){
        String qari_Link = SaveManager.get(context).getstring_appINFO().get(SaveManager.qari_Link);
        Log.e(tag.important, "besmellah: "+qari_Link + "001" + "001" + format.mp3 );
        return qari_Link + "001" + "001" + format.mp3;
    }


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
