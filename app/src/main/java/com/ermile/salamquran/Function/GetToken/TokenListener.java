package com.ermile.salamquran.Function.GetToken;

public interface TokenListener {
    void onTokenRecieved(String token);

    void onTokenFailed(String error);
}
