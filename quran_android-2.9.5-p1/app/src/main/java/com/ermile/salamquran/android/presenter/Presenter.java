package com.ermile.salamquran.android.presenter;

public interface Presenter<T> {
  void bind(T what);
  void unbind(T what);
}
