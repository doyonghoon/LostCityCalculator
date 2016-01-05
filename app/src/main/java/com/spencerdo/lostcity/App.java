package com.spencerdo.lostcity;

import android.app.Application;
import com.spencerdo.lostcity.model.NumberCardColor;
import com.spencerdo.lostcity.model.Score;
import com.spencerdo.lostcity.util.WLog;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Spencer Do on 2016. 1. 1..
 */
public class App extends Application {

  private static App singleton;
  private List<Score> scores = new ArrayList<>();

  public static App getInstance() {
    return singleton;
  }

  @Override public void onCreate() {
    super.onCreate();
    singleton = this;
  }

  public List<Score> getScores() {
    return scores;
  }

  public void removeScore(NumberCardColor c) {
    for (Score s : scores) {
      if (s.getColor() == c) {
        scores.remove(s);
        WLog.i("removed " + s.getColor().name());
        return;
      }
    }
  }

  public void setScore(Score s) {
    int p = -1;
    for (int i = 0; i < scores.size(); i++) {
      Score tmp = scores.get(i);
      if (s.getColor() == tmp.getColor()) {
        p = i;
        break;
      }
    }
    if (p > -1) {
      scores.get(p).setTotal(s.getTotal());
    } else {
      scores.add(s);
    }
  }

  public NumberCardColor getNextCardColor() {
    switch (scores.size()) {
      case 0:
        return NumberCardColor.BLUE;
      case 1:
        return NumberCardColor.RED;
      case 2:
        return NumberCardColor.WHITE;
      case 3:
        return NumberCardColor.GREEN;
      case 4:
        return NumberCardColor.YELLOW;
      default:
        return null;
    }
  }
}
