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

package np.info.dpshkhnl.newsapp.ui.daily;

import android.support.v7.widget.RecyclerView;

import np.info.dpshkhnl.newsapp.database.cache.cache.DailyCache;
import np.info.dpshkhnl.newsapp.support.adapter.DailyAdapter;
import np.info.dpshkhnl.newsapp.ui.support.BaseListFragment;

/**
 * Created by mummyding on 15-11-21.
 */
public class DailyFragment extends BaseListFragment{



    @Override
    protected boolean setHeaderTab() {
        return false;
    }

    @Override
    protected void onCreateCache() {
        cache = new DailyCache(handler);
    }

    @Override
    protected RecyclerView.Adapter bindAdapter() {
        return new DailyAdapter(getContext(),cache);
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

    }


}
