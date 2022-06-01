package com.kunal52.pairing;

import com.google.protobuf.InvalidProtocolBufferException;
import com.kunal52.wire.PacketParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

public class PairingPacketParser extends PacketParser {

    private BlockingQueue<Pairingmessage.PairingMessage> mMessagesQueue;

    public PairingPacketParser(InputStream inputStream, BlockingQueue<Pairingmessage.PairingMessage> messagesQueue) {
        super(inputStream);
        mMessagesQueue = messagesQueue;
    }

    @Override
    public void messageBufferReceived(byte[] buf) {
        try {
            Pairingmessage.PairingMessage pairingMessage = Pairingmessage.PairingMessage.parseFrom(buf);
            if (pairingMessage.getStatus() == Pairingmessage.PairingMessage.Status.STATUS_OK) {
                mMessagesQueue.put(pairingMessage);
            }
        } catch (InvalidProtocolBufferException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
