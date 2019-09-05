package com.ermile.salamquran.Function.Splash_function;

import android.content.Context;
import android.util.Log;

import com.ermile.salamquran.Static.SaveManager;

public class userIsAdded {
    Context context;

    public Boolean userIsAdded(Context context) {
        this.context = context;
        String usercode = SaveManager.get(context).getstring_appINFO().get(SaveManager.userCode);
        String zoneid = SaveManager.get(context).getstring_appINFO().get(SaveManager.zoneID);
        String apikey = SaveManager.get(context).getstring_appINFO().get(SaveManager.apiKey);
        if (usercode == null)
        {
            return false;
        }
        else if (zoneid == null)
        {
            return false;
        }
        else if (apikey == null)
        {
            return false;
        }
        return true;
    }
}
