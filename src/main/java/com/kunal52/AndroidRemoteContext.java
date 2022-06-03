package com.kunal52;

public class AndroidRemoteContext {

    private String serviceName = "com.github.kunal52/androidTvRemote";

    private String clientName = "androidTvRemoteJava";

    private static volatile AndroidRemoteContext instance;


    private AndroidRemoteContext() {

    }

    public static AndroidRemoteContext getInstance() {
        AndroidRemoteContext result = instance;
        if (result != null) {
            return result;
        }
        synchronized (AndroidRemoteContext.class) {
            if (instance == null) {
                instance = new AndroidRemoteContext();
            }
            return instance;
        }
    }

    public String getServiceName() {
        return serviceName;
    }

    public AndroidRemoteContext setServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    public String getClientName() {
        return clientName;
    }

    public AndroidRemoteContext setClientName(String clientName) {
        this.clientName = clientName;
        return this;
    }

}
