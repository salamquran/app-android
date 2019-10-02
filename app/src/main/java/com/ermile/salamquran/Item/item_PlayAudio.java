package com.ermile.salamquran.Item;

public class item_PlayAudio {
    int page,vers,aya,index;
    String url;

    public item_PlayAudio(int page, int vers, int aya, int index, String url) {
        this.page = page;
        this.vers = vers;
        this.aya = aya;
        this.index = index;
        this.url = url;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getVers() {
        return vers;
    }

    public void setVers(int vers) {
        this.vers = vers;
    }

    public int getAya() {
        return aya;
    }

    public void setAya(int aya) {
        this.aya = aya;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
