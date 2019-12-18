package com.ermile.salamquran.android;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;

//import com.crashlytics.android.Crashlytics; //1L
//import com.crashlytics.android.core.CrashlyticsCore; //1L
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.ermile.salamquran.android.component.application.ApplicationComponent;
import com.ermile.salamquran.android.component.application.DaggerApplicationComponent;
import com.ermile.salamquran.android.module.application.ApplicationModule;
import com.ermile.salamquran.android.salamquran.Utility.UserInfo;
import com.ermile.salamquran.android.util.QuranSettings;

import java.util.Locale;

import androidx.annotation.NonNull;
//import io.fabric.sdk.android.Fabric; //1L


public class QuranApplication extends Application {
  private ApplicationComponent applicationComponent;

  //  salamquran
  public static final String TAG = QuranApplication.class.getSimpleName();
  private RequestQueue mRequestQueue;
  private static QuranApplication mInstance;
//------------------


  @Override
  public void onCreate() {
    super.onCreate();

//  salamquran
    mInstance = this;
//------------------

    //1L
    /*Fabric.with(this, new Crashlytics.Builder()
        .core(new CrashlyticsCore.Builder()
            .disabled(BuildConfig.DEBUG)
            .build())
        .build());
    Timber.plant(new RecordingLogTree());*/
    //1L
    this.applicationComponent = initializeInjector();
  }

  protected ApplicationComponent initializeInjector() {
    return DaggerApplicationComponent.builder()
        .applicationModule(new ApplicationModule(this))
        .build();
  }

  public ApplicationComponent getApplicationComponent() {
    return this.applicationComponent;
  }

  public void refreshLocale(@NonNull Context context, boolean force) {
    final String language = UserInfo.getAppLanguage(this);

    final Locale locale;
    if (language != null) {
      locale = new Locale(language);
//      locale = new Locale("ar");
    } else {
      // nothing to do...
      return;
    }

    updateLocale(context, locale);
    final Context appContext = context.getApplicationContext();
    if (context != appContext) {
      updateLocale(appContext, locale);
    }
  }

  private void updateLocale(@NonNull Context context, @NonNull Locale locale) {
    final Resources resources = context.getResources();
    Configuration config = resources.getConfiguration();
    config.locale = locale;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      config.setLayoutDirection(config.locale);
    }
    resources.updateConfiguration(config, resources.getDisplayMetrics());
  }




  //  salamquran
  public static synchronized QuranApplication getInstance() {
    return mInstance;
  }

  public RequestQueue getRequestQueue() {
    if (mRequestQueue == null) {
      mRequestQueue = Volley.newRequestQueue(getApplicationContext());
    }
    return mRequestQueue;
  }
  public <T> void addToRequestQueue(Request<T> req, String tag) {
    // set the default tag if tag is empty
    req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
    getRequestQueue().add(req);
  }

  public <T> void addToRequestQueue(Request<T> req) {
    req.setTag(TAG);
    getRequestQueue().add(req);
  }

  public void cancelPendingRequests(Object tag) {
    if (mRequestQueue != null) {
      mRequestQueue.cancelAll(tag);
    }
  }
//------------------

}
