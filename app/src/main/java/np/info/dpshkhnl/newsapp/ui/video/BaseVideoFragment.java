package np.info.dpshkhnl.newsapp.ui.video;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.info.dpshkhnl.newsapp.R;

import np.info.dpshkhnl.newsapp.api.LiteratureApi;
import np.info.dpshkhnl.newsapp.api.VideoApi;
import np.info.dpshkhnl.newsapp.support.adapter.PagerAdapter;
import np.info.dpshkhnl.newsapp.ui.Literature.LiteratureFragment;
import np.info.dpshkhnl.newsapp.ui.support.AbsTopNavigationFragment;

/**
 * Created by Dpshkhnl on 2017-06-28.
 */

public class BaseVideoFragment  extends AbsTopNavigationFragment {
  private PagerAdapter pagerAdapter;
  private String [] name ;
  private String [] url ;
  @Override
  protected PagerAdapter initPagerAdapter() {
    name = VideoApi.getNewsTitle();
    url = VideoApi.getNewsUrl();
    pagerAdapter = new PagerAdapter(getChildFragmentManager(),name) {
      @Override
      public Fragment getItem(int position) {
        VideoFragment fragment = new VideoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(getString(R.string.id_url),url[position]);
        bundle.putString(getString(R.string.id_category),name[position]);
        fragment.setArguments(bundle);
        return fragment;
      }
    };
    return pagerAdapter;
  }
  @Override
  public void onDetach() {
    super.onDetach();
    if(getChildFragmentManager().getFragments()!=null){
      getChildFragmentManager().getFragments().clear();
    }
  }
}
