package com.ermile.salamquran.Item;

public class item_Qari {
    String name,url,image,code;

    public item_Qari(String name, String url, String image, String code) {
        this.name = name;
        this.url = url;
        this.image = image;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
