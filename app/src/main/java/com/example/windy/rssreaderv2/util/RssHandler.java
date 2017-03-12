package com.example.windy.rssreaderv2.util;

import android.util.Log;

import com.example.windy.rssreaderv2.domain.RssFeed;
import com.example.windy.rssreaderv2.domain.RssItem;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by windy on 2017/3/5.
 */

public class RssHandler extends DefaultHandler {
    private RssFeed rssFeed;
    private RssItem rssItem;

    private StringBuilder stringBuilder;
    private String text;

    final int RSS_TITLE = 1;
    final int RSS_LINK = 2;
    final int RSS_AUTHOR = 3;
    final int RSS_PUBDATE = 4;
    final int RSS_DESCRIPTION = 5;

    int currentFlag = 0;

    public RssHandler(){}

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        rssFeed = new RssFeed();
        rssItem = new RssItem();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        stringBuilder = new StringBuilder();

        if ("channel".equals(localName)){
            currentFlag = 0;
            return ;
        }
        if ("item".equals(localName)){
            rssItem = new RssItem();
            return ;
        }
        if ("title".equals(localName)){
            currentFlag = RSS_TITLE;
            return ;
        }
        if ("link".equals(localName)){
            currentFlag = RSS_LINK;
            return ;
        }
        if ("author".equals(localName)){
            currentFlag = RSS_AUTHOR;
            return ;
        }
        if ("pubDate".equals(localName)){
            currentFlag = RSS_PUBDATE;
            return ;
        }
        if ("description".equals(localName)){
            currentFlag = RSS_DESCRIPTION;
            return ;
        }
    }//func startElement()

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);

        //Get the string
        stringBuilder.append(new String(ch, start, length));

    } //func characters()

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        //add rssItem into rssFeed when entry close
        if ("item".equals(localName)){
            rssFeed.addItem(rssItem);
            return ;
        }

        text = stringBuilder.toString();

        switch (currentFlag){
            case RSS_TITLE:
                rssItem.setTitle(text);
                currentFlag = 0;
                break;
            case RSS_LINK:
                rssItem.setLink(text);
                currentFlag = 0;
                break;
            case RSS_AUTHOR:
                rssItem.setAuthor(text);
                currentFlag = 0;
            case RSS_PUBDATE:
                rssItem.setPubDate(text);
                currentFlag = 0;
                break;
            case RSS_DESCRIPTION:
                rssItem.setDescription(text);
                currentFlag = 0;
                break;
            default:
                break;
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    public RssFeed getRssFeed(){
        return rssFeed;
    }

}//class RssHandler
