package com.ermile.salamquran.Item;

public class item_LMS_GroupList {
    int id;
    String title,type,desc,sort,status,datecreated,file;

    public item_LMS_GroupList(int id, String title, String type, String desc, String sort, String status, String datecreated, String file) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.desc = desc;
        this.sort = sort;
        this.status = status;
        this.datecreated = datecreated;
        this.file = file;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(String datecreated) {
        this.datecreated = datecreated;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
