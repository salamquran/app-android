package com.ermile.salamquran.item.dialog_language;

import java.util.HashMap;
import java.util.Map;

public class MainItem {

    private Boolean ok;
    private Result result;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
