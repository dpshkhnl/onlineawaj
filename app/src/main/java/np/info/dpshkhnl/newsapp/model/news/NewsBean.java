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

package np.info.dpshkhnl.newsapp.model.news;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import np.info.dpshkhnl.newsapp.support.CONSTANT;
import np.info.dpshkhnl.newsapp.support.Utils;

/**
 * Created by mummyding on 15-11-14.
 * @author MummyDing
 * @version Leisure 1.0
 */
public class NewsBean {
    private String title;
    private String link;
    private String description;
    private String pubTime;
    private String author;
    private String imageUrl;

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public byte[] getImage() {
    return image;
  }

  public void setImage(byte[] image) {
    this.image = image;
  }

  private byte[] image;

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  /*
          self define
       */
    private int is_collected = 0;

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }
    public void setPubTimeWithFormat(String pubTime) {
        this.pubTime = formatTime(pubTime);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = formatClearHtmlLabel(title);
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public int getIs_collected() {
        return is_collected;
    }

    public void setIs_collected(int is_collected) {
        this.is_collected = is_collected;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setDescriptionWithFormat(String description) {
        this.description = formatClearHtmlLabel(description);
    }
    private String formatTime(String pubTime) {

     /* SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      Date date = null;
      try {
        date = sdf.parse(pubTime);
      } catch (ParseException e) {
        e.printStackTrace();
      }
*/
      return pubTime;
    }
    private String formatClearHtmlLabel(String string){

      String ImageUrl="";
      String[] anArray = string.split("=");
      for (int i=0; i < anArray.length; i++){
        if (anArray[i].contains("alt")){
          ImageUrl = anArray[i].replace("alt","").replace("\"","");
          ImageUrl = ImageUrl.trim();
        }
    }
    setImageUrl(ImageUrl);
      return  this.description = Utils.RegexReplace("<[^>\n]*>",string,"");
    }

    private int formatMonth(String month){
        for(int i = 1 ; i < CONSTANT.MONTH.length;i++)
            if(month.equalsIgnoreCase(CONSTANT.MONTH[i]))
            return i;
        return -1;
    }
}

