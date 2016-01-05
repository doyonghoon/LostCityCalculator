package com.spencerdo.lostcity.util;

import com.spencerdo.lostcity.fragment.BaseFragment;
import com.spencerdo.lostcity.fragment.MainFragment;
import com.spencerdo.lostcity.fragment.ResultFragment;

/**
 * Created by Spencer Do on 2015. 11. 20..
 */
public enum FragmentType {

  INIT(null),
  MAIN(MainFragment.class),
  CARD_SELECT(MainFragment.class),
  RESULT(ResultFragment.class);

  private Class<? extends BaseFragment> mFragmentClass;
  private String mTitle;

  FragmentType(Class<? extends BaseFragment> frag) {
    mFragmentClass = frag;
  }

  public Class<? extends BaseFragment> getFragmentClass() {
    return mFragmentClass;
  }

  public int getValue() {
    return ordinal();
  }
}

