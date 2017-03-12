package com.muxistudio.jobs.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ybao on 17/1/2.
 */

public class PostData implements Parcelable{

    public int pid;
    public String title;
    public String name;
    public String content;
    public long time;
    public int click;
    public int reply;
    public String avator;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.pid);
        dest.writeString(this.title);
        dest.writeString(this.name);
        dest.writeString(this.content);
        dest.writeLong(this.time);
        dest.writeInt(this.click);
        dest.writeInt(this.reply);
        dest.writeString(this.avator);
    }

    public PostData() {
    }

    protected PostData(Parcel in) {
        this.pid = in.readInt();
        this.title = in.readString();
        this.name = in.readString();
        this.content = in.readString();
        this.time = in.readLong();
        this.click = in.readInt();
        this.reply = in.readInt();
        this.avator = in.readString();
    }

    public static final Creator<PostData> CREATOR = new Creator<PostData>() {
        @Override
        public PostData createFromParcel(Parcel source) {
            return new PostData(source);
        }

        @Override
        public PostData[] newArray(int size) {
            return new PostData[size];
        }
    };
}
