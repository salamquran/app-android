package com.ermile.salamquran.android.salamquran.Mag;

public class MagModel {
  static class list_short {
    String image,title,excerpt,subtitle;

    public list_short(String image, String title, String excerpt, String subtitle) {
      this.image = image;
      this.title = title;
      this.excerpt = excerpt;
      this.subtitle = subtitle;
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

    public String getExcerpt() {
      return excerpt;
    }

    public void setExcerpt(String excerpt) {
      this.excerpt = excerpt;
    }

    public String getSubtitle() {
      return subtitle;
    }

    public void setSubtitle(String subtitle) {
      this.subtitle = subtitle;
    }
  }

  static class list_long{
    String id,title,excerpt,subtitle,content,source_title,source_url,image,special;
    String gallery;

    public list_long(String id, String title, String excerpt, String subtitle,
                     String content, String source_title, String source_url,
                     String image, String special, String gallery) {
      this.id = id;
      this.title = title;
      this.excerpt = excerpt;
      this.subtitle = subtitle;
      this.content = content;
      this.source_title = source_title;
      this.source_url = source_url;
      this.image = image;
      this.special = special;
      this.gallery = gallery;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getExcerpt() {
      return excerpt;
    }

    public void setExcerpt(String excerpt) {
      this.excerpt = excerpt;
    }

    public String getSubtitle() {
      return subtitle;
    }

    public void setSubtitle(String subtitle) {
      this.subtitle = subtitle;
    }

    public String getContent() {
      return content;
    }

    public void setContent(String content) {
      this.content = content;
    }

    public String getSource_title() {
      return source_title;
    }

    public void setSource_title(String source_title) {
      this.source_title = source_title;
    }

    public String getSource_url() {
      return source_url;
    }

    public void setSource_url(String source_url) {
      this.source_url = source_url;
    }

    public String getImage() {
      return image;
    }

    public void setImage(String image) {
      this.image = image;
    }

    public String getSpecial() {
      return special;
    }

    public void setSpecial(String special) {
      this.special = special;
    }

    public String getGallery() {
      return gallery;
    }

    public void setGallery(String gallery) {
      this.gallery = gallery;
    }
  }

}

