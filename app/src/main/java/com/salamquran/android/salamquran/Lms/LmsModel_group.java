package com.salamquran.android.salamquran.Lms;

public class LmsModel {
  String id,image,title,techerName,type,type_title,countLevel;

  public LmsModel(String id, String image, String title, String techerName, String type, String type_title, String countLevel) {
    this.id = id;
    this.image = image;
    this.title = title;
    this.techerName = techerName;
    this.type = type;
    this.type_title = type_title;
    this.countLevel = countLevel;
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

  public String getTecherName() {
    return techerName;
  }

  public void setTecherName(String techerName) {
    this.techerName = techerName;
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

  public String getCountLevel() {
    return countLevel;
  }

  public void setCountLevel(String countLevel) {
    this.countLevel = countLevel;
  }
}
