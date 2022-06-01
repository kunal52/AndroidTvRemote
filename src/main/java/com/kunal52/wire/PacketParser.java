package com.kunal52.wire;

import com.google.protobuf.InvalidProtocolBufferException;

import java.io.IOException;
import java.io.InputStream;

public abstract class PacketParser extends Thread {
    private final InputStream mInputStream;

    private boolean isAbort = false;


    public PacketParser(InputStream inputStream) {
        mInputStream = inputStream;
    }

    @Override
    public void run() {
        int available;
        int bytesRead = 0;
        while (!isAbort)
            try {
                available = mInputStream.read();
                byte[] buf = new byte[available];
                while (bytesRead < available) {
                    int read = mInputStream.read(buf, bytesRead, available - bytesRead);
                    if (read < 0) {
                        throw new IOException("Stream closed while reading.");
                    }
                    bytesRead += read;
                }

                bytesRead = 0;
                messageBufferReceived(buf);
            } catch (IOException e) {
                isAbort=true;
                throw new RuntimeException(e);
            }
    }


    public void abort() {
        isAbort = true;
    }

    public abstract void messageBufferReceived(byte[] buf);
}
