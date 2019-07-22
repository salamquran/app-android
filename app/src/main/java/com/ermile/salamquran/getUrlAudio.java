package com.ermile.salamquran;

public class getUrlAudio {

    public String UrlAudio(int aya,int sura){
        String urlAya;
        String urlSura;
        if (aya < 10){
            urlAya = "00"+aya;
        }else if (aya < 100){
            urlAya = "0"+aya;
        }else {
            urlAya = ""+aya;
        }

        if (sura < 10){
            urlSura = "00"+sura;
        }else if (sura < 100){
            urlSura = "0"+sura;
        }else {
            urlSura = ""+sura;
        }

        return urlSura+urlAya;
    }
}
