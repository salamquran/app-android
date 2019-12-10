package com.salamquran.android.ui.util;

import android.view.MotionEvent;

import com.salamquran.android.ui.helpers.AyahSelectedListener;

public interface PageController {
  boolean handleTouchEvent(MotionEvent event, AyahSelectedListener.EventType eventType, int page);
  void handleRetryClicked();
  void onScrollChanged(int x, int y, int oldx, int oldy);
}
