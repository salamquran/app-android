package com.ermile.salamquran.Function.LoginByNumber.step2_VerifyCode;

public interface VerifuCodeListener {

    void VerifyCode(String apikey,String zoneID, String userCode);
    void ErrorGetVerifyCode(String Error);
}
