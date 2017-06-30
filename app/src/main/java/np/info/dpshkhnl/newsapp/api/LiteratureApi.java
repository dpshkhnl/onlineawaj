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


import com.info.dpshkhnl.newsapp.R;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import np.info.dpshkhnl.newsapp.support.Utils;

public class LiteratureApi {
  private static String [] newsUrl = null;
  private static String [] newsTitle = null;
  private static Document document = null;
  /**
   * Static Method to get news api address.
   * <p> Get news api address from file raw/news_api.txt , news api are writen
   * in XML format . <br>
   *
   * @return String [] news api address
   */
  public static String [] getNewsUrl(){
    // Check if it has already got the address before
    // if true ,return it directly. Otherwise try to get it from file
    if(newsUrl == null){
      if(document == null) {
        // convert file to InputStream
        InputStream is = Utils.readFileFromRaw(R.raw.literature_api_np);
        // convert document format from InputStream format
        document = Utils.getDocmentByIS(is);
      }
      // Parsing required data from document.
      NodeList urlList = document.getElementsByTagName("url");
      newsUrl = new String[urlList.getLength()];
      for(int i = 0 ; i < urlList.getLength();i++){
        newsUrl[i] = urlList.item(i).getTextContent();
      }
    }
    return newsUrl;
  }

  /**
   * Static Method get news titles <br>
   * <p>Get news titles from file raw/news_api.txt. titles are written in Chinese,don't
   * support local. <br>

   * @return String []
   */
  public static String [] getNewsTitle(){
    if(newsTitle == null){
      if(document == null) {
        InputStream is = Utils.readFileFromRaw(R.raw.literature_api_np);
        document = Utils.getDocmentByIS(is);
      }
      NodeList titleList = document.getElementsByTagName("name");
      newsTitle = new String[titleList.getLength()];
      for(int i = 0 ; i < titleList.getLength();i++){
        newsTitle[i] = titleList.item(i).getTextContent();
      }
    }
    return newsTitle;
  }
}
