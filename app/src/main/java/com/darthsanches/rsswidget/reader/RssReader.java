/**
 *
 */
package com.darthsanches.rsswidget.reader;

import android.content.Context;
import android.util.Log;

import com.darthsanches.rsswidget.util.Article;
import com.darthsanches.rsswidget.util.CommonStringsHelper;
import com.darthsanches.rsswidget.util.RSSHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alexandroid
 */
public class RssReader {
    public final static String BOLD_OPEN = "<B>";
    public final static String BOLD_CLOSE = "</B>";
    public final static String PARAGRAPH = "<P/>";
    public final static String ITALIC_OPEN = "<I>";
    public final static String ITALIC_CLOSE = "</I>";
    public final static String SMALL_OPEN = "<SMALL>";
    public final static String SMALL_CLOSE = "</SMALL>";

    /**
     * This method defines a feed URL and then calles our SAX Handler to read the article list
     * from the stream
     *
     * @return List<JSONObject> - suitable for the List View activity
     */
    public static List<JSONObject> getLatestRssFeed(Context context, CommonStringsHelper res) {
        String feed = context.getSharedPreferences("rsswidget", Context.MODE_PRIVATE).getString("url", null);

        if (feed == null) {
            return null;
        }

        Log.d("RssReader", feed);
        RSSHandler rh = new RSSHandler();
        List<Article> articles = rh.getLatestArticles(feed);

        return fillData(articles);
    }


    /**
     * This method takes a list of Article objects and converts them in to the
     * correct JSON format so the info can be processed by our list view
     *
     * @param articles - list<Article>
     * @return List<JSONObject> - suitable for the List View activity
     */
    private static List<JSONObject> fillData(List<Article> articles) {

        List<JSONObject> items = new ArrayList<JSONObject>();
        for (Article article : articles) {
            JSONObject current = new JSONObject();
            try {
                buildJsonObject(article, current);
            } catch (JSONException e) {
                Log.e("RSS ERROR", "Error creating JSON Object from RSS feed");
            }
            items.add(current);
        }

        return items;
    }


    /**
     * This method takes a single Article Object and converts it in to a single JSON object
     * including some additional HTML formating so they can be displayed nicely
     *
     * @param article
     * @param current
     * @throws JSONException
     */
    private static void buildJsonObject(Article article, JSONObject current) throws JSONException {
        String title = article.getTitle();
        String description = article.getDescription();

        int tagPos = description.indexOf("<");

        if (tagPos > 0) {
            description = description.substring(0, tagPos) + '.';
        }

        current.put("text", description);
        current.put("title", title);

    }
}
