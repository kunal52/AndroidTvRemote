package com.kunal52;

abstract class BaseAndroidRemoteTv {

    private final AndroidRemoteContext androidRemoteContext;

    BaseAndroidRemoteTv() {
        androidRemoteContext = AndroidRemoteContext.getInstance();
    }


    public String getServiceName() {
        return androidRemoteContext.getServiceName();
    }

    public void setServiceName(String serviceName) {
        androidRemoteContext.setServiceName(serviceName);
    }

    public String getClientName() {
        return androidRemoteContext.getClientName();
    }

    public void setClientName(String clientName) {
        androidRemoteContext.setClientName(clientName);
    }
}
