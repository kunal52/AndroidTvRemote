package com.kunal52.remote;

import com.google.protobuf.InvalidProtocolBufferException;
import com.kunal52.wire.PacketParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

public class RemotePacketParser extends PacketParser {

    BlockingQueue<Remotemessage.RemoteMessage> mMessageQueue;
    private final OutputStream mOutputStream;
    private final RemoteMessageManager remoteMessageManager;

    private final RemoteListener mRemoteListener;

    private boolean isConnected = false;

    public RemotePacketParser(InputStream inputStream, OutputStream outputStream, BlockingQueue<Remotemessage.RemoteMessage> messageQueue, RemoteListener remoteListener) {
        super(inputStream);
        mOutputStream = outputStream;
        remoteMessageManager = new RemoteMessageManager();
        mRemoteListener = remoteListener;
        mMessageQueue = messageQueue;
    }

    @Override
    public void messageBufferReceived(byte[] buf) {
        System.out.println(Arrays.toString(buf));
        Remotemessage.RemoteMessage remoteMessage;
        try {
            remoteMessage = Remotemessage.RemoteMessage.parseFrom(buf);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
        //Send Ping Response
        if (remoteMessage.hasRemotePingRequest()) {
            try {
                mOutputStream.write(remoteMessageManager.createPingResponse(remoteMessage.getRemotePingRequest().getVal1()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (remoteMessage.hasRemoteStart()) {
            if (!isConnected)
                mRemoteListener.onConnected();
            isConnected = true;
        } else {
            try {
                mMessageQueue.put(remoteMessage);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        System.out.println(remoteMessage);

    }


    public interface RemotePacketParserListener {

        void onConnected();

    }

}
