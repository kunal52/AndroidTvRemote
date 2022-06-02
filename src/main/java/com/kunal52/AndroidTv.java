package com.kunal52;

import com.kunal52.exception.PairingException;
import com.kunal52.pairing.PairingListener;
import com.kunal52.pairing.PairingSession;
import com.kunal52.remote.RemoteSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * Hello world!
 */
public class AndroidTv {

    private final Logger logger = LoggerFactory.getLogger(AndroidTv.class);

    private PairingSession mPairingSession;

    private RemoteSession mRemoteSession;

    public void connect(String host, AndroidTvListener androidTvListener) throws GeneralSecurityException, IOException, InterruptedException, PairingException {
        mPairingSession = new PairingSession();

        mRemoteSession = new RemoteSession(host, 6466, new RemoteSession.RemoteSessionListener() {
            @Override
            public void onConnected() {
                androidTvListener.onConnected();
            }

            @Override
            public void onSslError() throws GeneralSecurityException, IOException, InterruptedException, PairingException {
//                mPairingSession.pair(host, 6467, new PairingListener() {
//                    @Override
//                    public void onSessionCreated() {
//                        logger.info("Pairing Session created");
//                    }
//
//                    @Override
//                    public void onPerformInputDeviceRole() throws PairingException {
//                        logger.info("Pairing Session created");
//                    }
//
//                    @Override
//                    public void onPerformOutputDeviceRole(byte[] gamma) throws PairingException {
//
//                    }
//
//                    @Override
//                    public void onSecretRequested() {
//                        logger.info("Secret Requested");
//                        androidTvListener.onSecretRequested();
//                    }
//
//                    @Override
//                    public void onSessionEnded() {
//
//                    }
//
//                    @Override
//                    public void onError(String message) {
//
//                    }
//
//                    @Override
//                    public void onPaired() {
//                    //    mRemoteSession.attemptToReconnect();
//                    }
//
//                    @Override
//                    public void onLog(String message) {
//
//                    }
//                });
            }

            @Override
            public void onDisconnected() {

            }

            @Override
            public void onError(String message) {

            }
        });


//        mPairingSession.pair("192.168.1.8", 6467, new PairingListener() {
//            @Override
//            public void onSessionCreated() {
//
//            }
//
//            @Override
//            public void onPerformInputDeviceRole() throws PairingException {
//
//            }
//
//            @Override
//            public void onPerformOutputDeviceRole(byte[] gamma) throws PairingException {
//
//            }
//
//            @Override
//            public void onSecretRequested() {
//                androidTvListener.onSecretRequested();
//            }
//
//            @Override
//            public void onSessionEnded() {
//
//            }
//
//            @Override
//            public void onError(String message) {
//
//            }
//
//            @Override
//            public void onPaired() {
//
//            }
//
//            @Override
//            public void onLog(String message) {
//
//            }
//        });

        mRemoteSession.connect();

    }

    public void setSecret(String code) {
        mPairingSession.provideSecret(code);
    }


    interface AndroidTvListener {
        void onSessionCreated();

        void onSecretRequested();

        void onPaired();

        void onConnectingToRemote();

        void onConnected();

        void onDisconnect();

        void onError(String error);
    }
}
