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

package np.info.dpshkhnl.newsapp.support.sax;

import np.info.dpshkhnl.newsapp.model.news.NewsBean;
import np.info.dpshkhnl.newsapp.support.HttpUtil;
import np.info.dpshkhnl.newsapp.support.Settings;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by mummyding on 15-11-14.
 */
public class SAXNewsParse  {
    public static List<NewsBean> items;
    public static List<NewsBean> parse(InputStream is) throws ParserConfigurationException, SAXException, IOException {
        XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
        SAXNewsHandler saxHandler = new SAXNewsHandler();
        xmlReader.setContentHandler(saxHandler);
        xmlReader.parse(new InputSource(is));
        items = saxHandler.getItems();

      for(NewsBean news : items) {
        String urlString = news.getImageUrl();
        if(HttpUtil.isWIFI == true || Settings.getInstance().getBoolean(Settings.NO_PIC_MODE, false) == false) {

          if (urlString != null && !urlString.equals("")) {
            URL myURL = new URL(urlString);
            byte[] bitmapdata = downloadUrl(myURL);
            news.setImage(bitmapdata);
          }
        }
        news.setPubTime(news.getAuthor()+" "+news.getPubTime());
      }
        return items;
    }

  private static byte[] downloadUrl(URL toDownload) {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    try {
      byte[] chunk = new byte[4096];
      int bytesRead;
      InputStream stream = toDownload.openStream();

      while ((bytesRead = stream.read(chunk)) > 0) {
        outputStream.write(chunk, 0, bytesRead);
      }

    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }

    return outputStream.toByteArray();
  }
}
