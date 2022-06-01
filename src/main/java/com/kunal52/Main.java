package com.kunal52;

import com.kunal52.exception.PairingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;

public class Main {
    public static void main(String[] args) throws GeneralSecurityException, IOException, InterruptedException, PairingException {
        AndroidTv androidTv = new AndroidTv();
        androidTv.connect("192.168.1.8", new AndroidTv.AndroidTvListener() {
            @Override
            public void onSessionCreated() {

            }

            @Override
            public void onSecretRequested() {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(System.in));

                // Reading data using readLine
                try {
                    String name = reader.readLine();
                    androidTv.setSecret(name);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onPaired() {

            }

            @Override
            public void onConnectingToRemote() {

            }

            @Override
            public void onConnected() {
                System.out.println("Connected");
            }

            @Override
            public void onDisconnect() {

            }

            @Override
            public void onError(String error) {

            }
        });
    }
}