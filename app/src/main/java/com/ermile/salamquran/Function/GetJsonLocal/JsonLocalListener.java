package com.ermile.salamquran.Function.GetJsonLocal;

public interface JsonLocalListener {

    void onGetJson_Online(String ResponeOnline);

    void onGetJson_Offline(String ResponeOffline);

    void OnGetJson_OfflineNoNULL();
}
