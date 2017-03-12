package com.example.windy.rssreaderv2;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.windy.rssreaderv2.domain.*;
import com.example.windy.rssreaderv2.util.*;

import org.xml.sax.SAXException;

import java.io.Console;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity
                          implements AdapterView.OnItemClickListener{
    public final String RSS_URL = "http://rss.sina.com.cn/tech/rollnews.xml";

    public final String tag = "RSS_READER";
    private RssFeed feed = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //谷歌规定网络连接等耗时操作不能放在主UI里
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
                .penaltyLog().penaltyDeath().build());

        try{
            feed = new RssFeed_SAXParser().getFeed(RSS_URL);
        }catch (ParserConfigurationException ex){
            ex.printStackTrace();
        }catch (SAXException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        showListView();
    }

    private void showListView(){
        ListView itemList = (ListView)this.findViewById(R.id.list);
        if (feed == null){
            setTitle("Unavailable RSS address");
            return ;
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                        feed.getAllItems(), android.R.layout.simple_list_item_2,
                        new String[]{RssItem.TITLE, RssItem.PUBDATE},
                        new int[]{android.R.id.text1, android.R.id.text2});
        itemList.setAdapter(simpleAdapter);
        itemList.setOnItemClickListener(this);
        itemList.setSelection(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(this, ShowDescriptionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title", feed.getItem(position).getTitle());
        bundle.putString("description",feed.getItem(position).getDescription().toString());
        bundle.putString("link", feed.getItem(position).getLink());
        bundle.putString("pubDate", feed.getItem(position).getPubDate());
        // 用android.intent.extra.INTENT的名字来传递参数
        intent.putExtra("android.intent.extra.rssItem", bundle);

       // Log.d("sum",feed.getItem(position).toString());
        startActivityForResult(intent, 0);
    }
}
