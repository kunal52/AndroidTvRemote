package com.kunal52.remote;

import com.google.protobuf.ByteString;
import com.kunal52.pairing.Pairingmessage;
import com.kunal52.wire.MessageManager;

import java.nio.ByteBuffer;

public class RemoteMessageManager extends MessageManager {

    public byte[] createRemoteConfigure(int code, String model, String vendor, int unknown1, String unknown2) {
        Remotemessage.RemoteConfigure remoteConfigure = Remotemessage.RemoteConfigure.newBuilder()
                .setCode1(code)
                .setDeviceInfo(Remotemessage.RemoteDeviceInfo.newBuilder()
                        .setModel(model)
                        .setVendor(vendor)
                        .setUnknown1(unknown1)
                        .setUnknown2(unknown2)
                        .setPackageName("androidtv-remote")
                        .setAppVersion("1.0.0")
                        .build())
                .build();

        return createRemoteConfigure(remoteConfigure);
    }

    public byte[] createRemoteConfigure(Remotemessage.RemoteConfigure remoteConfigure) {

        Remotemessage.RemoteMessage remoteMessage = Remotemessage.RemoteMessage.newBuilder()
                .setRemoteConfigure(remoteConfigure)
                .build();
        byte[] pairingMessageByteArray = remoteMessage.toByteArray();
        return addLengthAndCreate(pairingMessageByteArray);
    }

    public byte[] createRemoteActive(int code) {

        Remotemessage.RemoteMessage remoteMessage = Remotemessage.RemoteMessage.newBuilder()
                .setRemoteSetActive(Remotemessage.RemoteSetActive.newBuilder().setActive(code).build())
                .build();
        byte[] pairingMessageByteArray = remoteMessage.toByteArray();
        return addLengthAndCreate(pairingMessageByteArray);
    }


    public byte[] createPingResponse(int val1) {
        Remotemessage.RemotePingResponse remotePingResponse = Remotemessage.RemotePingResponse.newBuilder().setVal1(val1).build();
        Remotemessage.RemoteMessage remoteMessage = Remotemessage.RemoteMessage.newBuilder().setRemotePingResponse(remotePingResponse).build();
        byte[] pairingMessageByteArray = remoteMessage.toByteArray();
        return addLengthAndCreate(pairingMessageByteArray);
    }

    public byte[] createPower() {
        Remotemessage.RemoteKeyInject remoteKeyInject = Remotemessage.RemoteKeyInject.newBuilder().setDirection(Remotemessage.RemoteDirection.SHORT).setKeyCode(Remotemessage.RemoteKeyCode.KEYCODE_POWER).build();
        Remotemessage.RemoteMessage remoteMessage = Remotemessage.RemoteMessage.newBuilder().setRemoteKeyInject(remoteKeyInject).build();
        byte[] pairingMessageByteArray = remoteMessage.toByteArray();
        int length = pairingMessageByteArray.length;
        mPacketBuffer.put((byte) length).put(pairingMessageByteArray);
        byte[] bArr = new byte[mPacketBuffer.position()];
        System.arraycopy(mPacketBuffer.array(), mPacketBuffer.arrayOffset(), bArr, 0, mPacketBuffer.position());
        mPacketBuffer.clear();
        return bArr;
    }

    public byte[] createVolumeLevel(int volume) {
        //    Remotemessage.RemoteKeyInject remoteKeyInject = Remotemessage.RemoteKeyInject.newBuilder().setDirection(Remotemessage.RemoteDirection.SHORT).setKeyCode(Remotemessage.RemoteKeyCode.KEYCODE_POWER).build();
        Remotemessage.RemoteMessage remoteMessage = Remotemessage.RemoteMessage.newBuilder().setRemoteAdjustVolumeLevel(Remotemessage.RemoteAdjustVolumeLevel.newBuilder().build()).build();
        byte[] pairingMessageByteArray = remoteMessage.toByteArray();
        int length = pairingMessageByteArray.length;
        mPacketBuffer.put((byte) length).put(pairingMessageByteArray);
        byte[] bArr = new byte[mPacketBuffer.position()];
        System.arraycopy(mPacketBuffer.array(), mPacketBuffer.arrayOffset(), bArr, 0, mPacketBuffer.position());
        mPacketBuffer.clear();
        return bArr;
    }

    public byte[] createKeyCommand(Remotemessage.RemoteKeyCode keyCode, Remotemessage.RemoteDirection remoteDirection) {
        Remotemessage.RemoteMessage remoteMessage = Remotemessage.RemoteMessage.newBuilder()
                .setRemoteKeyInject(Remotemessage.RemoteKeyInject.newBuilder()
                        .setKeyCode(keyCode)
                        .setDirection(remoteDirection)
                        .build())
                .build();

        return addLengthAndCreate(remoteMessage.toByteArray());
    }

}
