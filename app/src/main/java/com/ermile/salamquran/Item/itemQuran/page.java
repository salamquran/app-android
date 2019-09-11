package com.ermile.salamquran.Item.itemQuran;

import java.util.List;

public class page {
    List<ayat> ayatList;

    public page(List<ayat> ayatList) {
        this.ayatList = ayatList;
    }

    public List<ayat> getAyatList() {
        return ayatList;
    }

    public void setAyatList(List<ayat> ayatList) {
        this.ayatList = ayatList;
    }
}

