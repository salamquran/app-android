package com.salamquran.android.salamquran.Lms;

public class LmsModel_levelList {

  private String
      id ,
      lm_group_id ,
      title ,
      desc ,
      type ,
      type_title ,
      file ,
      sort ,
      ratio ,
      unlockscore ,
      badge ,
      userstar ,
      xtype ;

  public LmsModel_levelList(String id, String lm_group_id, String title, String desc,
                            String type, String type_title, String file, String sort,
                            String ratio, String unlockscore, String badge, String userstar,
                            String xtype) {
    this.id = id;
    this.lm_group_id = lm_group_id;
    this.title = title;
    this.desc = desc;
    this.type = type;
    this.type_title = type_title;
    this.file = file;
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
