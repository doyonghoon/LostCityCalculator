package com.spencerdo.lostcity.fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Bind;
import com.spencerdo.lostcity.App;
import com.spencerdo.lostcity.R;
import com.spencerdo.lostcity.model.NumberCardColor;
import com.spencerdo.lostcity.model.Score;
import com.spencerdo.lostcity.util.ActivityManager;
import com.spencerdo.lostcity.util.BundleSet;
import com.spencerdo.lostcity.util.FragmentType;
import com.spencerdo.lostcity.view.NumberView;
import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends BaseFragment {

  @Bind(R.id.layout_top) LinearLayout layoutTop;
  @Bind(R.id.layout_mid) LinearLayout layoutMid;
  @Bind(R.id.layout_bottom) LinearLayout layoutBottom;

  private List<NumberView> numberViews = new ArrayList<>();
  private NumberCardColor cardColor = null;

  @Override protected int getLayoutId() {
    return R.layout.frag_card_chooser;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
    cardColor = getCardColor();
    getActivity().setTitle(cardColor.name());
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    menu.clear();
    inflater.inflate(R.menu.menu_card_chooser, menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.menu_next:
        final Score s = new Score(getTotal(this.numberViews), this.cardColor);
        App.getInstance().setScore(s);
        final NumberCardColor nextColor = App.getInstance().getNextCardColor();
        if (nextColor != null) {
          final Bundle b = new BundleSet.Builder().putColor(nextColor).build().getBundle();
          ActivityManager.with(getActivity(), FragmentType.MAIN).addBundle(b).go();
        } else {
          ActivityManager.with(getActivity(), FragmentType.RESULT).go();
        }
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    List<NumberView> n1 = getNumberViews(layoutTop);
    List<NumberView> n2 = getNumberViews(layoutMid);
    List<NumberView> n3 = getNumberViews(layoutBottom);
    numberViews.addAll(n1);
    numberViews.addAll(n2);
    numberViews.addAll(n3);
    for (int i = 0; i < numberViews.size(); i++) {
      numberViews.get(i).setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          NumberView nv = (NumberView) v;
          nv.setSelected(!nv.isSelected());
          nv.setCardColor(cardColor);
        }
      });
    }
  }

  private NumberCardColor getCardColor() {
    if (getArguments() != null && getArguments().getSerializable(BundleSet.CARD_COLOR) != null) {
      return (NumberCardColor) getArguments().getSerializable(BundleSet.CARD_COLOR);
    }
    return NumberCardColor.BLUE;
  }

  private List<NumberView> getNumberViews(LinearLayout l) {
    List<NumberView> views = new ArrayList<>();
    for (int i = 0; i < l.getChildCount(); i++) {
      if (l.getChildAt(i) instanceof NumberView) {
        views.add((NumberView) l.getChildAt(i));
      }
    }
    return views;
  }

  private int getTotal(List<NumberView> views) {
    int sum = 0;
    int multiplier = 1;
    for (int i = 0; i < 3; i++) {
      if (views.get(i).isSelected()) {
        multiplier++;
      }
    }
    for (int i = 3; i < views.size(); i++) {
      if (views.get(i).isSelected()) {
        sum += views.get(i).getNumber();
      }
    }
    int handicap = 20;
    if (sum == 0 && multiplier == 1) {
      handicap = 0;
    }

    return (sum - handicap + getExtraPoints(views)) * multiplier;
  }

  private int getExtraPoints(List<NumberView> views) {
    int count = 0;
    for (NumberView v : views) {
      if (v.isSelected()) {
        count++;
      }
    }
    return count >= 8 ? 20 : 0;
  }

  @Override public void onDestroy() {
    super.onDestroy();
    App.getInstance().removeScore(cardColor);
  }
}
