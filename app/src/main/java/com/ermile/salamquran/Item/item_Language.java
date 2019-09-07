package com.ermile.salamquran.Item;

public class item_Language {

    private String title;
    private String tag;
    private int chBoxVisibel;

    public item_Language(String title, String tag, int chBoxVisibel) {
        this.title = title;
        this.tag = tag;
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

    public int getChBoxVisibel() {
        return chBoxVisibel;
    }

    public void setChBoxVisibel(int chBoxVisibel) {
        this.chBoxVisibel = chBoxVisibel;
    }
}
