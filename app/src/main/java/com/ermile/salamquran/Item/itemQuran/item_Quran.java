package com.ermile.salamquran.Item.itemQuran;

public class item_Quran {
    String page,aya;

    public item_Quran(String page, String aya) {
        this.page = page;
        this.aya = aya;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getAya() {
        return aya;
    }

    public void setAya(String aya) {
        this.aya = aya;
    }
}
