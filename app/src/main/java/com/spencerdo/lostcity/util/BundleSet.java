package com.spencerdo.lostcity.util;

import android.os.Bundle;
import com.spencerdo.lostcity.model.NumberCardColor;
import com.spencerdo.lostcity.model.Score;

/**
 * Created by Spencer Do on 2015. 11. 20..
 */
public class BundleSet {

  public static final String CARD_COLOR = "CardColor";
  public static final String TRANSITION_ANIMATION_TYPE = "TransitionAnimationType";

  private Bundle mBundle;
  private Builder mBuilder = null;

  private BundleSet(Builder builder) {
    this.mBuilder = builder;
    mBundle = new Bundle();
    if (mBuilder.getColor() != null) {
      mBundle.putSerializable(CARD_COLOR, mBuilder.getColor());
    }
  }

  public Builder getBuilder() {
    return this.mBuilder;
  }

  public Bundle getBundle() {
    return mBundle;
  }

  public static class Builder {

    private Score score = null;
    private NumberCardColor color = null;

    public Builder() {
    }

    public Builder putColor(NumberCardColor c) {
      color = c;
      return this;
    }

    public NumberCardColor getColor() {
      return color;
    }

    public BundleSet build() {
      return new BundleSet(this);
    }
  }
}
