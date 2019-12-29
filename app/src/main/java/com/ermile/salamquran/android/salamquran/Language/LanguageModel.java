package com.ermile.salamquran.android.salamquran.Language;

public class LanguageModel {
  private String title,tag,local_URL;
  private boolean chBoxVisibel;

  public LanguageModel(String title, String tag, boolean chBoxVisibel, String local_URL) {
    this.title = title;
    this.tag = tag;
    this.local_URL = local_URL;
    this.chBoxVisibel = chBoxVisibel;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public String getLocal_URL() {
    return local_URL;
  }

  public void setLocal_URL(String local_URL) {
    this.local_URL = local_URL;
  }

  public boolean isChBoxVisibel() {
    return chBoxVisibel;
  }

  public void setChBoxVisibel(boolean chBoxVisibel) {
    this.chBoxVisibel = chBoxVisibel;
  }
}
