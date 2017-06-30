package np.info.dpshkhnl.newsapp.api;

import com.info.dpshkhnl.newsapp.R;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import np.info.dpshkhnl.newsapp.support.Utils;

/**
 * Created by Dpshkhnl on 2017-06-29.
 */

public class InterviewApi{
  private static String [] newsUrl = null;
  private static String [] newsTitle = null;
  private static Document document = null;
  /**
   * Static Method to get news api address.
   * <p> Get news api address from file raw/news_api.txt , news api are writen
   * in XML format . <br>
   * @param void
   * @return String [] news api address
   */
  public static String [] getNewsUrl(){
    // Check if it has already got the address before
    // if true ,return it directly. Otherwise try to get it from file
    if(newsUrl == null){
      if(document == null) {
        // convert file to InputStream
        InputStream is = Utils.readFileFromRaw(R.raw.interview_api_np);
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
   * @param void
   * @return String []
   */
  public static String [] getNewsTitle(){
    if(newsTitle == null){
      if(document == null) {
        InputStream is = Utils.readFileFromRaw(R.raw.interview_api_np);
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
