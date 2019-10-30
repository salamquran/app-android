package com.ermile.salamquran.ItemModels;

public class item_ListQuran {
    public static final int SURAH_TYPE=0;
    public static final int JUZ_TYPE=1;
    public static final int HEZB_TYPE=2;


    public int type;
    public int data;
    public String numbers,titles,desc,page,hezb ;
    public int bg_hezb;


    public item_ListQuran(int type, int data, String numbers, String titles, String desc, String page, String hezb, int bg_hezb) {
        this.type = type;
        this.data = data;
        this.numbers = numbers;
        this.titles = titles;
        this.desc = desc;
        this.page = page;
        this.hezb = hezb;
        this.bg_hezb = bg_hezb;
    }
}