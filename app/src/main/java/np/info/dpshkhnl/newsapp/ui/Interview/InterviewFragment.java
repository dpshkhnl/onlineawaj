package np.info.dpshkhnl.newsapp.ui.Interview;

import android.support.v7.widget.RecyclerView;

import com.info.dpshkhnl.newsapp.R;

import np.info.dpshkhnl.newsapp.database.cache.cache.NewsCache;
import np.info.dpshkhnl.newsapp.support.adapter.NewsAdapter;
import np.info.dpshkhnl.newsapp.ui.support.BaseListFragment;

/**
 * Created by Dpshkhnl on 2017-06-29.
 */

public class InterviewFragment extends BaseListFragment {

  private String mCategory;
  private String mUrl;

  @Override
  protected void onCreateCache() {
    cache = new NewsCache(handler,mCategory,mUrl);
  }

  @Override
  protected RecyclerView.Adapter bindAdapter() {
    return new NewsAdapter(getContext(),cache);
  }

  @Override
  protected void loadFromNet() {
    cache.load();
  }

  @Override
  protected void loadFromCache() {
    cache.loadFromCache();
  }

  @Override
  protected boolean hasData() {
    return cache.hasData();
  }

  @Override
  protected void getArgs() {
    mUrl = getArguments().getString(getString(R.string.id_url));
    mCategory = getArguments().getString(getString(R.string.id_category));
  }
}
