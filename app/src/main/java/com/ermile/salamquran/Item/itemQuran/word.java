package com.ermile.salamquran.Item.itemQuran;

public class word {
    Integer line,position;
    String char_type,code,text,audio;

    public word(Integer line, Integer position, String char_type, String code, String text, String audio) {
        this.line = line;
        this.position = position;
        this.char_type = char_type;
        this.code = code;
        this.text = text;
        this.audio = audio;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getChar_type() {
        return char_type;
    }

    public void setChar_type(String char_type) {
        this.char_type = char_type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }
}
