package com.spencerdo.lostcity.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import com.spencerdo.lostcity.App;
import com.spencerdo.lostcity.R;
import com.spencerdo.lostcity.model.Score;

/**
 * Created by Spencer Do on 2016. 1. 4..
 */
public class ResultFragment extends BaseFragment {

  @Bind(R.id.result_score) TextView scoreView;

  @Override protected int getLayoutId() {
    return R.layout.frag_result;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getActivity().setTitle("RESULT");
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    scoreView.setText(getTotalSum());
  }

  private String getTotalSum() {
    int sum = 0;
    for (Score s : App.getInstance().getScores()) {
      sum += s.getTotal();
    }
    return sum + "";
  }
}
