package com.spencerdo.lostcity.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.spencerdo.lostcity.R;
import com.spencerdo.lostcity.fragment.BaseFragment;
import com.spencerdo.lostcity.util.ActivityManager;
import com.spencerdo.lostcity.util.FragmentType;

/**
 * Created by Spencer Do on 2016. 1. 1..
 */
public class BaseActivity extends AppCompatActivity {

  @Bind(R.id.base_drawer) DrawerLayout drawerLayout;
  @Bind(R.id.toolbar) Toolbar toolbar;

  private Bundle bundle;
  protected BaseFragment mFragment;
  private FragmentType fragmentType;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.act_base);
    ButterKnife.bind(this);
    setSupportActionBar(toolbar);

    if (getIntent() != null) {
      if (getIntent().hasExtra(ActivityManager.TASK)) {
        fragmentType = (FragmentType) getIntent().getSerializableExtra(ActivityManager.TASK);
      }

      if (getIntent().hasExtra(ActivityManager.BUNDLE)) {
        bundle = getIntent().getBundleExtra(ActivityManager.BUNDLE);
        //mAnimType = (ActivityStarter.AnimationType) bundle.getSerializable(
        //    BundleSet.TRANSITION_ANIMATION_TYPE);
      }
    }

    if (fragmentType == null) {
      fragmentType = FragmentType.MAIN;
    }

    if (savedInstanceState == null) {
      try {
        mFragment = fragmentType.getFragmentClass().newInstance();
        if (bundle != null) {
          mFragment.setArguments(bundle);
        }

        //setOnBackKeyListener(mFragment.getOnBackKeyListener());
      } catch (Exception localException) {
        localException.printStackTrace();
      }

      try {
        getFragmentManager().beginTransaction()
            .add(R.id.activity_layout, mFragment)
            .commit();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    fragmentType = null;
    ButterKnife.unbind(this);
  }

  public FragmentType getFragmentType() {
    return fragmentType;
  }
}

