package com.ermile.salamquran.ItemModels;

public class item_Ghari {

    private int index;
    private String lang,type,addr,slug,name,image,short_name;

    public item_Ghari(int index, String lang, String type, String addr, String slug, String name, String image, String short_name) {
        this.index = index;
        this.lang = lang;
        this.type = type;
        this.addr = addr;
        this.slug = slug;
        this.name = name;
        this.image = image;
        this.short_name = short_name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }
}
