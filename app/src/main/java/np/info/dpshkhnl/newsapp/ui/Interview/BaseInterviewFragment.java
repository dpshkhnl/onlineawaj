package np.info.dpshkhnl.newsapp.ui.Interview;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.info.dpshkhnl.newsapp.R;

import np.info.dpshkhnl.newsapp.api.InterviewApi;
import np.info.dpshkhnl.newsapp.api.NewsApi;
import np.info.dpshkhnl.newsapp.support.adapter.PagerAdapter;
import np.info.dpshkhnl.newsapp.ui.news.NewsFragment;
import np.info.dpshkhnl.newsapp.ui.support.AbsTopNavigationFragment;

/**
 * Created by Dpshkhnl on 2017-06-29.
 */

public class BaseInterviewFragment extends AbsTopNavigationFragment {
  private PagerAdapter pagerAdapter;
  private String [] name ;
  private String [] url ;
  @Override
  protected PagerAdapter initPagerAdapter() {
    name = InterviewApi.getNewsTitle();
    url = InterviewApi.getNewsUrl();
    pagerAdapter = new PagerAdapter(getChildFragmentManager(),name) {
      @Override
      public Fragment getItem(int position) {
        InterviewFragment fragment = new InterviewFragment();
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


  /* // isRemove = true;
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }*/

