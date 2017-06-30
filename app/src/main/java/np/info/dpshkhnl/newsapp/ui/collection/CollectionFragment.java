/*
 *  Copyright (C) 2015 MummyDing
 *
 *  This file is part of Leisure( <https://github.com/MummyDing/Leisure> )
 *
 *  Leisure is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *                             ｀
 *  Leisure is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Leisure.  If not, see <http://www.gnu.org/licenses/>.
 */

package np.info.dpshkhnl.newsapp.ui.collection;

import android.support.v7.widget.RecyclerView;

import com.info.dpshkhnl.newsapp.R;
import np.info.dpshkhnl.newsapp.database.cache.collection.CollectionDailyCache;
import np.info.dpshkhnl.newsapp.database.cache.collection.CollectionNewsCache;
import np.info.dpshkhnl.newsapp.database.cache.collection.CollectionReadingCache;
import np.info.dpshkhnl.newsapp.database.cache.collection.CollectionScienceCache;
import np.info.dpshkhnl.newsapp.support.CONSTANT;
import np.info.dpshkhnl.newsapp.support.adapter.DailyAdapter;
import np.info.dpshkhnl.newsapp.support.adapter.NewsAdapter;
import np.info.dpshkhnl.newsapp.support.adapter.ReadingAdapter;
import np.info.dpshkhnl.newsapp.ui.support.BaseListFragment;

/**
 * Created by mummyding on 15-12-4.
 */
public class CollectionFragment extends BaseListFragment {

    private int pos;

    @Override
    protected void setLayout() {
        mLayout = R.layout.layout_collection_list;
    }

    @Override
    protected boolean setRefreshView() {
        return false;
    }

    @Override
    protected void onCreateCache() {
        switch (pos){
            case 0:
              cache = new CollectionNewsCache(handler);
              break;

            case 1:
                cache = new CollectionReadingCache(handler);
                break;
            case 2:
              cache = new CollectionDailyCache(handler);
              break;
            case 3:
                cache = new CollectionScienceCache(handler);
                break;
        }
    }

    @Override
    protected RecyclerView.Adapter bindAdapter() {
        switch (pos){
            case 0:
              return new NewsAdapter(getContext(),cache);

            case 1:
                return new ReadingAdapter(getContext(),cache);
            case 2:
                return new NewsAdapter(getContext(),cache);
        }
        return null;
    }

    @Override
    protected void loadFromNet() {
        handler.sendEmptyMessage(CONSTANT.ID_FAILURE);
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
        pos = getArguments().getInt(getString(R.string.id_pos));
    }
}
