package com.spencerdo.lostcity.model;

import android.support.annotation.ColorRes;
import com.spencerdo.lostcity.R;

/**
 * Created by Spencer Do on 2016. 1. 3..
 */
public enum NumberCardColor {
  BLUE(R.color.blue, android.R.color.white),
  RED(R.color.red, android.R.color.white),
  WHITE(android.R.color.white, android.R.color.black),
  GREEN(R.color.green, android.R.color.white),
  YELLOW(R.color.yellow, android.R.color.white);

  private @ColorRes int background;
  private @ColorRes int text;

  NumberCardColor(@ColorRes int background, @ColorRes int text) {
    this.background = background;
    this.text = text;
  }

  public int getTextColor() {
    return text;
  }

  public int getBackgroundColor() {
    return background;
  }
}
