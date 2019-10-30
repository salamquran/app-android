package com.ermile.salamquran.Functions.Interface;

public class api_interface {
    public interface token_Listener {
        void onRecive(String result);
        void onFailed(String result);
    }

    public interface singUp_Listener {
        void onRecive(Boolean result);
        void onFailed(Boolean result);
    }

    public interface appDetail_Listener {
        void onRecive_Online(String ResponeOnline);
        void onRecive_Offline(String ResponeOffline);
        void onRecive_OfflineNoNULL();
    }
}
