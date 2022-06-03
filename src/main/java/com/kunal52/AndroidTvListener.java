package com.kunal52;

public interface AndroidTvListener {
    void onSessionCreated();

    void onSecretRequested();

    void onPaired();

    void onConnectingToRemote();

    void onConnected();

    void onDisconnect();

    void onError(String error);
}