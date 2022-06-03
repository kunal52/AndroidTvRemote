
# AndroidTvRemote

_Note: This is a prereleased version._

AndroidTvRemote is an open-source java library to control android tv.

## Usage

```java
 AndroidRemoteTv androidRemoteTv = new AndroidRemoteTv();
        androidRemoteTv.connect("192.168.1.8", new AndroidRemoteTv.AndroidTvListener() {
            @Override
            public void onSessionCreated() {

            }

            @Override
            public void onSecretRequested() {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(System.in));

                try {
                    String name = reader.readLine();
                    androidRemoteTv.sendSecret(name);
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
                
                androidRemoteTv.sendCommand(Remotemessage.RemoteKeyCode.KEYCODE_POWER, Remotemessage.RemoteDirection.SHORT);

            }

            @Override
            public void onDisconnect() {

            }

            @Override
            public void onError(String error) {

            }
        });
```

Refer this Android Page for more commands -  https://developer.android.com/reference/android/view/KeyEvent