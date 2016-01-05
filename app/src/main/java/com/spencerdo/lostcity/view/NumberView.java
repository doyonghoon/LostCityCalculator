package com.spencerdo.lostcity.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.spencerdo.lostcity.R;
import com.spencerdo.lostcity.model.NumberCardColor;

/**
 * Created by Spencer Do on 2016. 1. 1..
 */
public class NumberView extends LinearLayout {

  private NumberCardColor numberCardColor = null;
  private boolean selected = false;
  private int number = 0;
  @Bind(R.id.number) TextView numberView;

  public NumberView(Context context) {
    this(context, null, 0);
  }

  public NumberView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public NumberView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    inflate(getContext(), R.layout.view_number, this);
    ButterKnife.bind(this);
    init(attrs);
  }

  private void init(AttributeSet attrs) {
    if (attrs != null && getContext() != null) {
      TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.NumberView);
      if (a.hasValue(R.styleable.NumberView_card_number)) {
        setNumber(a.getInt(R.styleable.NumberView_card_number, 0));
      }
      a.recycle();
    }
  }

  public void setNumber(int n) {
    this.number = n;
    numberView.setText(String.valueOf(n));
  }

  public int getNumber() {
    return this.number;
  }

  public NumberCardColor getNumberColor() {
    return this.numberCardColor;
  }

  public void setCardColor(NumberCardColor c) {
    numberCardColor = c;
    numberView.setTextColor(getResources().getColor(isSelected() ? c.getTextColor() : R.color.gray));
    setBackgroundColor(isSelected() ? getResources().getColor(c.getBackgroundColor()) : Color.TRANSPARENT);
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
    numberView.setTypeface(null, this.selected ? Typeface.BOLD : Typeface.NORMAL);
  }

  public boolean isSelected() {
    return selected;
  }
}
