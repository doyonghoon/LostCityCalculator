package com.spencerdo.lostcity.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.spencerdo.lostcity.util.WLog;

/**
 * Created by Spencer Do on 2016. 1. 4..
 */
public class Score implements Parcelable {

  private int total;
  private NumberCardColor color;

  public Score(int total, @NonNull NumberCardColor c) {
    this.total = total;
    this.color = c;
  }

  protected Score(Parcel in) {
    total = in.readInt();
    byte c = in.readByte();
    switch (c) {
      case 0:
        color = NumberCardColor.BLUE;
        break;
      case 1:
        color = NumberCardColor.GREEN;
        break;
      case 2:
        color = NumberCardColor.RED;
        break;
      case 3:
        color = NumberCardColor.WHITE;
        break;
      case 4:
        color = NumberCardColor.YELLOW;
        break;
    }
    WLog.i("color: " + color.name());
  }

  public static final Creator<Score> CREATOR = new Creator<Score>() {
    @Override public Score createFromParcel(Parcel in) {
      return new Score(in);
    }

    @Override public Score[] newArray(int size) {
      return new Score[size];
    }
  };

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public NumberCardColor getColor() {
    return color;
  }

  public void setColor(NumberCardColor color) {
    this.color = color;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(total);
    if (color != null) {
      switch (color) {
        case BLUE:
          dest.writeByte((byte) 0);
          break;
        case GREEN:
          dest.writeByte((byte) 1);
          break;
        case RED:
          dest.writeByte((byte) 2);
          break;
        case WHITE:
          dest.writeByte((byte) 3);
          break;
        case YELLOW:
          dest.writeByte((byte) 4);
          break;
      }
      WLog.i("color: " + color.name());
    }
  }
}
