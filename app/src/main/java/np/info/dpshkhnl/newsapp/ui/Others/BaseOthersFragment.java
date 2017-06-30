package np.info.dpshkhnl.newsapp.ui.Others;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.info.dpshkhnl.newsapp.R;

import np.info.dpshkhnl.newsapp.api.NewsApi;
import np.info.dpshkhnl.newsapp.api.OthersApi;
import np.info.dpshkhnl.newsapp.support.adapter.PagerAdapter;
import np.info.dpshkhnl.newsapp.ui.news.NewsFragment;
import np.info.dpshkhnl.newsapp.ui.support.AbsTopNavigationFragment;

/**
 * Created by Dpshkhnl on 2017-06-29.
 */

public class BaseOthersFragment extends AbsTopNavigationFragment {
  private PagerAdapter pagerAdapter;
  private String [] name ;
  private String [] url ;
  @Override
  protected PagerAdapter initPagerAdapter() {
    name = OthersApi.getNewsTitle();
    url = OthersApi.getNewsUrl();
    pagerAdapter = new PagerAdapter(getChildFragmentManager(),name) {
      @Override
      public Fragment getItem(int position) {
        NewsFragment fragment = new NewsFragment();
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
