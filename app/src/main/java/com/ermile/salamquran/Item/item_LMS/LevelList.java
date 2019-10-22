package com.ermile.salamquran.Item.item_LMS;

public class LevelList {

    /*        {
            "id": "3",
            "lm_group_id": "4",
            "title": "سوره ناس",
            "desc": null,
            "type": "quran",
            "type_title": "قرآن",
            "quranfrom": "88221",
            "quran_start_sura": "114",
            "quran_sura": "ناس",
            "quran_start_aya": "1",
            "quranto": "88246",
            "quran_end_sura": "114",
            "quran_end_aya": "6",
            "besmellah": "1",
            "file": null,
            "setting": null,
            "sort": "10",
            "ratio": null,
            "unlockscore": null,
            "status": "enable",
            "datecreated": "2019-07-30 20:28:34",
            "questionrandcount": null,
            "filepic": null,
            "badge": null,
            "userstar": null,
            "xtype": "quran",
            "iframe_link": "https://salamquran.com/fa/s114/1-6?fixframe=1&mode=pagedesign"
        }*/


    private String id,lm_group_id,title,desc,type,type_title,quranfrom,quranto,besmellah,file,setting,sort,ratio,unlockscore,status,datecreated,questionrandcount,filepic,userstar,iframe_link;


    public LevelList(String id, String lm_group_id, String title, String desc, String type, String type_title, String quranfrom, String quranto, String besmellah, String file, String setting, String sort, String ratio, String unlockscore, String status, String datecreated, String questionrandcount, String filepic, String userstar, String iframe_link) {
        this.id = id;
        this.lm_group_id = lm_group_id;
        this.title = title;
        this.desc = desc;
        this.type = type;
        this.type_title = type_title;
        this.quranfrom = quranfrom;
        this.quranto = quranto;
        this.besmellah = besmellah;
        this.file = file;
        this.setting = setting;
        this.sort = sort;
        this.ratio = ratio;
        this.unlockscore = unlockscore;
        this.status = status;
        this.datecreated = datecreated;
        this.questionrandcount = questionrandcount;
        this.filepic = filepic;
        this.userstar = userstar;
        this.iframe_link = iframe_link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLm_group_id() {
        return lm_group_id;
    }

    public void setLm_group_id(String lm_group_id) {
        this.lm_group_id = lm_group_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType_title() {
        return type_title;
    }

    public void setType_title(String type_title) {
        this.type_title = type_title;
    }

    public String getQuranfrom() {
        return quranfrom;
    }

    public void setQuranfrom(String quranfrom) {
        this.quranfrom = quranfrom;
    }

    public String getQuranto() {
        return quranto;
    }

    public void setQuranto(String quranto) {
        this.quranto = quranto;
    }

    public String getBesmellah() {
        return besmellah;
    }

    public void setBesmellah(String besmellah) {
        this.besmellah = besmellah;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String getUnlockscore() {
        return unlockscore;
    }

    public void setUnlockscore(String unlockscore) {
        this.unlockscore = unlockscore;
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

    public String getQuestionrandcount() {
        return questionrandcount;
    }

    public void setQuestionrandcount(String questionrandcount) {
        this.questionrandcount = questionrandcount;
    }

    public String getFilepic() {
        return filepic;
    }

    public void setFilepic(String filepic) {
        this.filepic = filepic;
    }

    public String getUserstar() {
        return userstar;
    }

    public void setUserstar(String userstar) {
        this.userstar = userstar;
    }

    public String getIframe_link() {
        return iframe_link;
    }

    public void setIframe_link(String iframe_link) {
        this.iframe_link = iframe_link;
    }
}
