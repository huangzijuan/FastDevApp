package com.example.cm.fastdevapp.ui;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.example.cm.fastdevapp.R;
import com.example.cm.fastdevapp.view.AutoSplitTextView;
import com.example.cm.fastdevapp.view.FadeInTextView;
import com.example.cm.fastdevapp.view.MyTextView;

public class FadeInTextViewActivity extends Activity {
    private FadeInTextView mTextView1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fadein);

        mTextView1 = (FadeInTextView) findViewById(R.id.textView1);
        mTextView1.setTextString("本文地址http://www.cnblogs.com/goagent/p/5159125.html本文地址啊本文。地址。啊http://www.cnblogs.com/goagent/p/5159125.html").startFadeInAnimation();


    }

}
