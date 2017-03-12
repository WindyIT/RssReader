package com.example.windy.rssreaderv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by windy on 2017/3/6.
 */

public class ShowDescriptionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_description);
        String content = null;

        Intent intent = getIntent();
        if (intent != null){
            Bundle bundle = intent.getBundleExtra("android.intent.extra.rssItem");
            if(bundle==null){
                content = "不好意思程序出错啦";
            }else{
                content = bundle.getString("title")
                        + bundle.getString("description") + "\n"
                        + "发布时间: " + bundle.getString("pubDate") + "\n"
                        + "\n详细信息请访问以下网址:\n"
                        + bundle.getString("link");
            }
        }else{
            content = "不好意思程序出错啦";
        }

        TextView contentText = (TextView) this.findViewById(R.id.content);
        contentText.setText(content);

        Button backButton = (Button) this.findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }//func onCreate()

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
