package com.kunal52.pairing;

interface SecretProvider {

    void requestSecret(PairingSession pairingSession);

}
