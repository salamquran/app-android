package com.ermile.salamquran.Static;

import android.util.Log;

public class log {
    public static void d(String Class, String Desc , String Values ){

        if (Values == null){
            Log.d(tag.publicsh,Class+"  "+Desc);
        }else {
            Log.d(tag.publicsh,Class+"  "+Desc+" \n ---Value--- \n " + Values);
        }
    }
}
