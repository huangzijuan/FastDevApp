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
import com.example.cm.fastdevapp.view.MyTextView;

public class AlignTextViewActivity extends Activity {
    private TextView mTextView;
    private TextView mTextView1;
    private AutoSplitTextView mTextView2;
    private MyTextView mTextView3;
    private TextView mTextView4;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textview);

        mTextView1 = (TextView) findViewById(R.id.textView1);
        mTextView1.setText("本文地址http://www.cnblogs.com/goagent/p/5159125.html本文地址啊本文。地址。啊http://www.cnblogs.com/goagent/p/5159125.html");

        mTextView2 = (AutoSplitTextView) findViewById(R.id.textView2);
        mTextView2.setText("本文地址http://www.cnblogs.com/goagent/p/5159125.html本文地址啊本文。地址。啊http://www.cnblogs.com/goagent/p/5159125.html");

        mTextView3 = (MyTextView) findViewById(R.id.textView3);
        mTextView3.setText("本文地址http://www.cnblogs.com/goagent/p/5159125.html本文地址啊本文。地址。啊http://www.cnblogs.com/goagent/p/5159125.html");

        mTextView4 = (TextView) findViewById(R.id.textView4);
        mTextView4.setText("本文地址http://www.cnblogs.com/goagent/p/5159125.html本文地址啊本文。地址。啊http://www.cnblogs.com/goagent/p/5159125.html");

        mTextView = (TextView) findViewById(R.id.textView);
        mTextView.setText("本文地址http://www.cnblogs.com/goagent/p/5159125.html本文地址啊本文。地址。啊http://www.cnblogs.com/goagent/p/5159125.html");
        mTextView.getViewTreeObserver().addOnGlobalLayoutListener(new OnTvGlobalLayoutListener());
    }

    private class OnTvGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onGlobalLayout() {
            mTextView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            final String newText = autoSplitText(mTextView);
            if (!TextUtils.isEmpty(newText)) {
                mTextView.setText(newText);
            }
        }
    }

    private String autoSplitText(final TextView tv) {
        final String rawText = tv.getText().toString(); //原始文本
        final Paint tvPaint = tv.getPaint(); //paint，包含字体等信息
        final float tvWidth = tv.getWidth() - tv.getPaddingLeft() - tv.getPaddingRight(); //控件可用宽度

        //将原始文本按行拆分
        String [] rawTextLines = rawText.replaceAll("\r", "").split("\n");
        StringBuilder sbNewText = new StringBuilder();
        for (String rawTextLine : rawTextLines) {
            if (tvPaint.measureText(rawTextLine) <= tvWidth) {
                //如果整行宽度在控件可用宽度之内，就不处理了
                sbNewText.append(rawTextLine);
            } else {
                //如果整行宽度超过控件可用宽度，则按字符测量，在超过可用宽度的前一个字符处手动换行
                float lineWidth = 0;
                for (int cnt = 0; cnt != rawTextLine.length(); ++cnt) {
                    char ch = rawTextLine.charAt(cnt);
                    lineWidth += tvPaint.measureText(String.valueOf(ch));
                    if (lineWidth <= tvWidth) {
                        sbNewText.append(ch);
                    } else {
                        sbNewText.append("\n");
                        lineWidth = 0;
                        --cnt;
                    }
                }
            }
            sbNewText.append("\n");
        }

        //把结尾多余的\n去掉
        if (!rawText.endsWith("\n")) {
            sbNewText.deleteCharAt(sbNewText.length() - 1);
        }

        return sbNewText.toString();
    }
}
