package com.ermile.salamquran.item.dialog_language;

import java.util.HashMap;
import java.util.Map;

public class Result {

    private String language;
    private String title;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}