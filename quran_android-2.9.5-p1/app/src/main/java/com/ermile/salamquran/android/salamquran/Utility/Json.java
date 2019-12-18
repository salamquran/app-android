package com.ermile.salamquran.android.salamquran.Utility;

import android.content.Context;

public class Json {

  public static class defaultValue {
    public static String appLanguage = "{\"ok\": true, \"result\": {\"fa\": {\"name\": \"fa\", \"direction\": \"rtl\", \"iso\": \"fa_IR\", \"localname\": \"فارسی\", \"api_url\": \"https://salamquran.com/fa/api/v6\"}, \"en\": {\"name\": \"en\", \"direction\": \"ltr\", \"iso\": \"en_US\", \"localname\": \"English\", \"api_url\": \"https://salamquran.com/en/api/v6\"}, \"ar\": {\"name\": \"ar\", \"direction\": \"rtl\", \"iso\": \"ar_IQ\", \"localname\": \"العربية\", \"api_url\": \"https://salamquran.com/ar/api/v6\"} } }";
  }

  public static class Get{
    public static String appLnaguage(Context context){
      return SaveManager.get(context).getstring_appINFO().get(SaveManager.jsonAppLanguage);
    }
  }
}
