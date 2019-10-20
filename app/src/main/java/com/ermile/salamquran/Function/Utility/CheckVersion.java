package com.ermile.salamquran.Function.Utility;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.ermile.salamquran.Static.file;
import com.ermile.salamquran.Static.format;
import com.ermile.salamquran.Static.tag;
import com.ermile.salamquran.Static.value;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class CheckVersion {
    public static Boolean Deprecated(Context context){
        try {
            String settingApp = ReadFile.FromStorage(context, file.setting, format.json);
            JSONObject respone = new JSONObject(settingApp);
            JSONObject result = respone.getJSONObject("result");
            JSONObject url = result.getJSONObject("url");
            JSONObject version = result.getJSONObject("version");
            /*Url For Update*/
            final String urlUpdate = url.getString("update");
            /*Deprecate Value*/
            if (!version.isNull("deprecated")){
                String deprecatedVersion = version.getString("deprecated").replace(".","");
                int Depver = Integer.valueOf(deprecatedVersion);
                String deprecated_title = version.getString("deprecated_title");
                String deprecated_desc = version.getString("deprecated_desc");
                String deprecated_btnTitle = "Update Now!";
                /*Update Value*/
                String lastVersion = version.getString("last").replace(".","");
                int Updver = Integer.valueOf(lastVersion);
                String update_title = version.getString("update_title");
                String update_desc = version.getString("update_desc");
                if (value.versionCode <= Depver){
                    Log.d(tag.function, "deprecated Version "+value.versionCode+" = "+Depver);
                    SaveManager.get(context).change_deprecatedVersion(true);
                    Intent openURL = new Intent ( Intent.ACTION_VIEW );
                    openURL.setData (Uri.parse( urlUpdate ));
                    new Dialog(context,deprecated_title,deprecated_desc,deprecated_btnTitle,false,openURL);
                    return true;

                }else {
                    SaveManager.get(context).change_deprecatedVersion(false);
                    updateVersion(context,Updver);
                    return false;
                }
            }
            else {
                return false;
            }


        } catch (JSONException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*Check Update Version*/
    private static void updateVersion(Context context,int UpdateVersion){
        if (value.versionCode < UpdateVersion){
            Log.d(tag.function, "Update Version "+value.versionCode+" = "+UpdateVersion);
            SaveManager.get(context).change_hasNewVersion(true);
        }
        else {
            SaveManager.get(context).change_hasNewVersion(false);
        }
    }
}
