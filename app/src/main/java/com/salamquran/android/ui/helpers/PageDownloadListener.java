package com.salamquran.android.ui.helpers;

import android.graphics.drawable.BitmapDrawable;

import com.salamquran.android.common.Response;

public interface PageDownloadListener {
  void onLoadImageResponse(BitmapDrawable drawable, Response response);
}
