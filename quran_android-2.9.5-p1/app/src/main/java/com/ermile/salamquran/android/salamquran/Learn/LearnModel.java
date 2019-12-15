package com.ermile.salamquran.android.salamquran.Learn;

public class LearnModel {
  public static final int GROUP= 100;
  public static final int LEVEL_LIST= 200;

  public int type;

  public LearnModel(int type) {
    this.type = type;
  }

  static class group{
    String id,image,title,desc,type,type_title;

    public group(String id, String image, String title,
                 String desc, String type, String type_title) {
      this.id = id;
      this.image = image;
      this.title = title;
      this.desc = desc;
      this.type = type;
      this.type_title = type_title;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getImage() {
      return image;
    }

    public void setImage(String image) {
      this.image = image;
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
  }

  static class level_list {
    private String
        id ,
        lm_group_id ,
        title ,
        desc ,
        type ,
        type_title ,
        file ,
        filepic,
        sort ,
        ratio ,
        unlockscore ,
        badge ,
        userstar ,
        xtype ;

    public level_list(String id, String lm_group_id, String title, String desc, String type,
                      String type_title, String file, String filepic, String sort, String ratio,
                      String unlockscore, String badge, String userstar, String xtype) {
      this.id = id;
      this.lm_group_id = lm_group_id;
      this.title = title;
      this.desc = desc;
      this.type = type;
      this.type_title = type_title;
      this.file = file;
      this.filepic = filepic;
      this.sort = sort;
      this.ratio = ratio;
      this.unlockscore = unlockscore;
      this.badge = badge;
      this.userstar = userstar;
      this.xtype = xtype;
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

    public String getFile() {
      return file;
    }

    public void setFile(String file) {
      this.file = file;
    }

    public String getFilepic() {
      return filepic;
    }

    public void setFilepic(String filepic) {
      this.filepic = filepic;
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

    public String getBadge() {
      return badge;
    }

    public void setBadge(String badge) {
      this.badge = badge;
    }

    public String getUserstar() {
      return userstar;
    }

    public void setUserstar(String userstar) {
      this.userstar = userstar;
    }

    public String getXtype() {
      return xtype;
    }

    public void setXtype(String xtype) {
      this.xtype = xtype;
    }
  }
}


