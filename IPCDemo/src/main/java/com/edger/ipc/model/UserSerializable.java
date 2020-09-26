package com.edger.ipc.model;

import java.io.Serializable;

public class UserSerializable implements Serializable {

    private static final long serialVersionUID = -2487223250819528234L;

    public int userId;
    public String userName;
    public boolean isMale;

    public UserSerializable(int userId, String userName, boolean isMale) {
        this.userId = userId;
        this.userName = userName;
        this.isMale = isMale;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", isMale=" + isMale +
                '}';
    }
}
