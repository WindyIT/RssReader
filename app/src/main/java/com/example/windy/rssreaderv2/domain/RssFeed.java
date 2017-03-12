package com.example.windy.rssreaderv2.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by windy on 2017/3/5.
 */

public class RssFeed {
    private String title;
    private String pubDate;
    private int itemCount;
    private List<RssItem> rssItems;

    public RssFeed() {
        itemCount = 0;
        rssItems = new ArrayList<RssItem>();
    }

    /**
     * Add item and return the number of current items
     */
    public int addItem(RssItem rssItem) {
        rssItems.add(rssItem);
        itemCount++;
        return itemCount;
    }

    /**
     * Get the item based on the subscript
     */
    public RssItem getItem(int i) {
        return rssItems.get(i);
    }

    /**
     * Get all items and return the list
     */
    public List<HashMap<String, Object>> getAllItems() {
        List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i != rssItems.size(); i++) {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put(RssItem.TITLE, rssItems.get(i).getTitle());
            item.put(RssItem.PUBDATE, rssItems.get(i).getPubDate());
            data.add(item);
        }
        return data;
    }

    public String getTitle() {
        return title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public int getItemCount() {
        return itemCount;
    }

    public List<RssItem> getRssItems() {
        return rssItems;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public void setRssItems(List<RssItem> rssItems) {
        this.rssItems = rssItems;
    }
}
