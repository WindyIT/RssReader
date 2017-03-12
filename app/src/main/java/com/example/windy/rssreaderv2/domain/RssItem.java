package com.example.windy.rssreaderv2.domain;

/**
 * Created by windy on 2017/3/5.
 */

public class RssItem {
    private String title;
    private String link;
    private String pubDate;
    private String author;
    private String description;

    public static final String TITLE = "title";
    public static final String PUBDATE = "pubDate";

    public RssItem() {}

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getAuthor() { return author; }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public void setAuthor(String anthor) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RssItem{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", pubDate='" + pubDate + '\'' +
                '}';
    }
}
