package com.salamquran.android.salamquran;

import android.content.Context;

public class url {
  public static String site = "https://salamquran.com/fa";
  public static String api = site+"/api/v6";

  public static String getLocal(Context context){
    return SaveManager.get(context).getstring_appINFO().get(SaveManager.local);
  }

  public static String getLmsGroup(Context context){
    String lms_group = "/lms/group";
    return getLocal(context) + lms_group;
  }
  public static String getLmsLevelList(Context context, String id){
    String lms_level_list = "/lms/levellist?id="+id;
    return getLocal(context) + lms_level_list;
  }
  public static String getLmsLevel(Context context,String id){
    String lms_level = "/lms/level?id="+id;
    return getLocal(context) + lms_level;
  }
}
