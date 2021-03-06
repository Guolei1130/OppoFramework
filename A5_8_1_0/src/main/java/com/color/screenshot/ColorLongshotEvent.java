package com.color.screenshot;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ColorLongshotEvent implements Parcelable {
    public static final Creator<ColorLongshotEvent> CREATOR = new Creator<ColorLongshotEvent>() {
        public ColorLongshotEvent createFromParcel(Parcel in) {
            return new ColorLongshotEvent(in);
        }

        public ColorLongshotEvent[] newArray(int size) {
            return new ColorLongshotEvent[size];
        }
    };
    private static final String TAG = "ColorLongshotEvent";
    private boolean mIsOverScroll = false;
    private int mOffset = 0;
    private String mSource = null;

    public ColorLongshotEvent(String source, int offset, boolean isOverScroll) {
        this.mSource = source;
        this.mOffset = offset;
        this.mIsOverScroll = isOverScroll;
    }

    public ColorLongshotEvent(Parcel in) {
        readFromParcel(in);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.mSource);
        out.writeInt(this.mOffset);
        out.writeInt(this.mIsOverScroll ? 1 : 0);
    }

    public void readFromParcel(Parcel in) {
        boolean z = true;
        this.mSource = in.readString();
        this.mOffset = in.readInt();
        if (in.readInt() != 1) {
            z = false;
        }
        this.mIsOverScroll = z;
    }

    public String getSource() {
        return this.mSource;
    }

    public int getOffset() {
        return this.mOffset;
    }

    public boolean isOverScroll() {
        return this.mIsOverScroll;
    }
}
