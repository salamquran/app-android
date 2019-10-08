package com.ermile.salamquran.Function.Utility;

import android.content.Context;
import android.util.Log;

import com.ermile.salamquran.Static.format;
import com.ermile.salamquran.Static.tag;
import com.ermile.salamquran.Static.url;
import com.ermile.salamquran.Static.value;

public class carateURL {

    public static String audio(Context context, String vers, String aya){

        String  repl_vers,
                repl_aya;

        String qari = SaveManager.get(context).getstring_appINFO().get(SaveManager.qari);

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

        Log.d(tag.carateURL, url.dl_salamquran + qari + value.slash + repl_vers + repl_aya + format.mp3);
        return url.dl_salamquran + qari + value.slash + repl_vers + repl_aya + format.mp3;
    }

    public static String besmellah(Context context){
        String qari = SaveManager.get(context).getstring_appINFO().get(SaveManager.qari);

        return url.dl_salamquran + qari + value.slash + "001" + "001" + format.mp3;
    }

}
