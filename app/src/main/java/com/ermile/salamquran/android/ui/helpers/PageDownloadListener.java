package com.ermile.salamquran.android.ui.helpers;

import android.graphics.drawable.BitmapDrawable;

import com.ermile.salamquran.android.common.Response;

public interface PageDownloadListener {
  void onLoadImageResponse(BitmapDrawable drawable, Response response);
}
