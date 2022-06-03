package com.kunal52;

import com.kunal52.exception.PairingException;
import com.kunal52.pairing.PairingListener;
import com.kunal52.pairing.PairingSession;
import com.kunal52.remote.RemoteSession;
import com.kunal52.remote.Remotemessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;


public class AndroidRemoteTv extends BaseAndroidRemoteTv {

    private final Logger logger = LoggerFactory.getLogger(AndroidRemoteTv.class);

    private PairingSession mPairingSession;

    private RemoteSession mRemoteSession;

    public void connect(String host, AndroidTvListener androidTvListener) throws GeneralSecurityException, IOException, InterruptedException, PairingException {
        mRemoteSession = new RemoteSession(host, 6466, new RemoteSession.RemoteSessionListener() {
            @Override
            public void onConnected() {
                androidTvListener.onConnected();
            }

            @Override
            public void onSslError()  {

            }

            @Override
            public void onDisconnected() {

            }

            @Override
            public void onError(String message) {

            }
        });

        if (AndroidRemoteContext.getInstance().getKeyStoreFile().exists())
            mRemoteSession.connect();
        else {
            mPairingSession = new PairingSession();
            mPairingSession.pair("192.168.1.8", 6467, new PairingListener() {
                @Override
                public void onSessionCreated() {

                }

                @Override
                public void onPerformInputDeviceRole() throws PairingException {

                }

                @Override
                public void onPerformOutputDeviceRole(byte[] gamma) throws PairingException {

                }

                @Override
                public void onSecretRequested() {
                    androidTvListener.onSecretRequested();
                }

                @Override
                public void onSessionEnded() {

                }

                @Override
                public void onError(String message) {

                }

                @Override
                public void onPaired() {
                    try {
                        mRemoteSession.connect();
                    } catch (GeneralSecurityException | IOException | InterruptedException | PairingException e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void onLog(String message) {

                }
            });
        }

    }

    public void sendCommand(Remotemessage.RemoteKeyCode remoteKeyCode, Remotemessage.RemoteDirection remoteDirection) {
        mRemoteSession.sendCommand(remoteKeyCode, remoteDirection);
    }

    public void sendSecret(String code) {
        mPairingSession.provideSecret(code);
    }

}
