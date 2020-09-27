package com.edger.ipc.binderpool;

import android.os.RemoteException;

public class SecurityImpl extends ISecurityCenter.Stub {

    public static final char SECRETE_CODE = '^';

    @Override
    public String encrypt(String content) throws RemoteException {
        char[] chars = content.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] ^= SECRETE_CODE;
        }
        return new String(chars);
    }

    @Override
    public String decrypt(String password) throws RemoteException {
        return encrypt(password);
    }
}
