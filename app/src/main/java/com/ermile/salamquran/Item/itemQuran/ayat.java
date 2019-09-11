package com.ermile.salamquran.Item.itemQuran;

public class ayat {

    Integer page,sure,aya;

    public ayat(Integer page, Integer sure, Integer aya) {
        this.page = page;
        this.sure = sure;
        this.aya = aya;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSure() {
        return sure;
    }

    public void setSure(Integer sure) {
        this.sure = sure;
    }

    public Integer getAya() {
        return aya;
    }

    public void setAya(Integer aya) {
        this.aya = aya;
    }
}
