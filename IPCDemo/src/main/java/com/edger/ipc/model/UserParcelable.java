package com.edger.ipc.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class UserParcelable implements Serializable, Parcelable {

    private static final long serialVersionUID = -2487223250819528234L;

    public int userId;
    public String userName;
    public boolean isMale;

    public UserParcelable(int userId, String userName, boolean isMale) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.userId);
        dest.writeString(this.userName);
        dest.writeByte(this.isMale ? (byte) 1 : (byte) 0);
    }

    protected UserParcelable(Parcel in) {
        this.userId = in.readInt();
        this.userName = in.readString();
        this.isMale = in.readByte() != 0;
    }

    public static final Parcelable.Creator<UserParcelable> CREATOR =
            new Parcelable.Creator<UserParcelable>() {
        @Override
        public UserParcelable createFromParcel(Parcel source) {
            return new UserParcelable(source);
        }

        @Override
        public UserParcelable[] newArray(int size) {
            return new UserParcelable[size];
        }
    };
}
