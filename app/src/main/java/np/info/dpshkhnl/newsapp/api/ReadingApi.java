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

package np.info.dpshkhnl.newsapp.api;

import np.info.dpshkhnl.newsapp.LeisureApplication;
import com.info.dpshkhnl.newsapp.R;
import np.info.dpshkhnl.newsapp.model.reading.BookBean;
import np.info.dpshkhnl.newsapp.support.Utils;

/**
 * Created by mummyding on 2015-11-15.<br>
 * DouBan(www.douban.com) Books Api
 * @author MummyDing
 * @version 1.0
 */
public class ReadingApi {
    public static final int TAG_LEN = 3;
    public static String searchByTag="https://api.douban.com/v2/book/search?tag=";
    public static String searchByText="https://api.douban.com/v2/book/search?q=";
    public static String searchByID="https://api.douban.com/v2/book/";
    public static String readEBook="https://read.douban.com/reader/ebook/";
    public static String Tag_Titles []={"चलचित्र", "गीत संगीत", "सिने समाचार"};
  /*  public static String HomeTag[] ={"लोकप्रिय विज्ञान", "इन्टरनेट", "विज्ञान", "विज्ञान र प्रविधिको", "विज्ञान", "प्रयोगकर्ता अनुभव", "संचार", "अन्तरक्रियात्मक", "यात्रा", "वांग Xiaobo,", "जीवन" , "प्रेरणादायक", " वृद्धि "," अनिश्चय "," वू Xia "," हान हान "," कल्पना "," युवा साहित्य "};
    public static String LiterTag []={"Fiction", "Chinese Literature", "Murakami Haruki", "Wang Xiaobo", "Yuhua", "Lu Xun", "Milan Kundera", "Foreign Literature", "Classic", "Fairy", " Poems", "poems", "poems", "poems", "poems", "poems", "poems", "poems", "poems", "Essays", "Japanese literature", "essays", "classical literature", "contemporary literature", "Zweig", "Milan Kundera", "Duras", "Hong Kong and Taiwan"};
    public static String CoderTag[] ={"Programming", "interaction", "design", "algorithm", "web", "UE", "internet", "user experience", "communication", "interaction", "UCD"};
    public static String PopularTag[] ={" Comic "," painting "," reason "," youth "," romance "," science fiction "," Higashino Guiyu "," suspense "," martial arts "," Han "," fantasy ", "Jin Mi", "Yushu", "San Mao", "Anne baby", "network novel", "Guo Jingming", "reasoning novel", "through", "Jin Yong", "light novel", "Agatha Christie", "Zhang Xiaoxian", "a few meters", "magic", "youth literature", "JK Rowling", "science fiction", "Takagi Naoki", "Cologne" ,"Cai Kangyong", "lodging", "Zhang Yue Ran"};
    public static String CultureTag[] ={"History", "psychology", "philosophy", "biography", "culture", "sociology", "art", "design", "politics", "society", "architecture", "Film", "Mathematics", "Politics", "Memoirs", "Thought", "Guoxue", "Chinese History", "Music", "Humanities", "Drama", "Biography", "Painting" , "Art History", "Buddhism", "Military", "Western Philosophy", "Modern History", "World War II", "Liberalism", "Archeology", "Art"};
    public static String LifeTag[]={"Love", "travel", "life", "inspirational", "growth", "psychology", "photography", "female", "workplace", "food", "education", "travel" ,"Health", "health", "hand", "health", "gender", "interpersonal", "home"};
    public static String FinancialTag[] ={"Economics", "management", "economy", "finance", "business", "investment", "marketing", "business", "financial management", "advertising", "stock" ,"plan"};
    public static String bookTab_Titles[] ={"Content brief introduction", "directory", "author"};*/
    /*public static String[] getApiTag(int pos){
        switch (pos){
            case 0:
                return HomeTag;
            case 1:
                return LiterTag;
            case 2:
                return CoderTag;
            case 3:
                return PopularTag;
            case 4:
                return CultureTag;
            case 5:
                return LifeTag;
            case 6:
                return FinancialTag;
        }
        return null;
    }*/

    /**
     * Get book tags randomly
     * @param tag
     * @return tags
     */
    public static String [] getTags(String [] tag){
        int len = tag.length;
        String [] res = new String[TAG_LEN];
        for(int i =0 ;i< TAG_LEN;i++){
            boolean flag = true;
            int tmp = 0;
            while (flag) {
                flag = false;
                 tmp = (int) (Math.random() * len);
                for(int j = 0; j<i;j++)
                    if(res[j].equals(tag[tmp])) {
                        flag = true;
                        break;
                    }
            }
            res[i] = tag[tmp];
        }
        return res;
    }

    /**
     * Get details of book ,it contains Book Summary, Book Contents and Author Introduction<br>
     * if api does provide any info ,it will return a "" string
     * @param position position of tab.
     * @param book
     * @return
     */
    public static String getBookInfo(int position,BookBean book){
        switch (position){
            case 0:
                if(Utils.hasString(book.getSummary()) == false) break;
                return book.getSummary();
            case 1:
                if(Utils.hasString(book.getCatalog()) == false)break;
                return  book.getCatalog();
            case 2:
                if(Utils.hasString(book.getAuthor_intro()) == false)break;
                return book.getAuthor_intro();
        }
        return LeisureApplication.AppContext.getString(R.string.text_noinfo);
    }
}
