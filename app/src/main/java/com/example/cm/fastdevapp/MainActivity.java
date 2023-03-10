package com.example.cm.fastdevapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cm.fastdevapp.test.TestFile;
import com.example.cm.fastdevapp.ui.AlignTextViewActivity;
import com.example.cm.fastdevapp.ui.CoordinatorTestActivity;
import com.example.cm.fastdevapp.ui.FadeInTextViewActivity;
import com.example.cm.fastdevapp.ui.XfermodeTestActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private ListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TestFile.test(this);

        mListView = (ListView) findViewById(R.id.list);

        mListAdapter = new ListAdapter(this);
        final List<Data> dataList = new ArrayList<>();
        dataList.add(new Data("XfermodeTest", XfermodeTestActivity.class));
        dataList.add(new Data("AlignTextViewTest", AlignTextViewActivity.class));
        dataList.add(new Data("CoordinatorTest", CoordinatorTestActivity.class));
        dataList.add(new Data("XfermodeTest", FadeInTextViewActivity.class));
        mListAdapter.setData(dataList);
        mListView.setAdapter(mListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Data data = dataList.get(i);
                Intent intent = new Intent(MainActivity.this, data.intentClass);
                startActivity(intent);
            }
        });
    }

    private static class ListAdapter extends BaseAdapter {
        private List<Data> mDatas;
        private WeakReference<Context> mContextRef;

        public ListAdapter(Context context) {
            mContextRef = new WeakReference<>(context);
        }

        public void setData(@Nullable List<Data> datas) {
            mDatas = datas;
        }

        @Override
        public int getCount() {
            return mDatas == null ? 0 : mDatas.size();
        }

        @Override
        public Data getItem(int i) {
            return mDatas == null ? null : mDatas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(mContextRef.get()).inflate(R.layout.view_item, null);
                viewHolder = new ViewHolder();
                viewHolder.mItem = (TextView) view.findViewById(R.id.item);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.bindData(getItem(i));
            return view;
        }
    }

    private static class ViewHolder {
        private TextView mItem;

        private void bindData(Data data) {
            mItem.setText(data.textStr);
        }
    }

    private static class Data {
        private String textStr;
        private Class intentClass;

        Data(String textStr, Class intentClass) {
            this.textStr = textStr;
            this.intentClass = intentClass;
        }
    }
}
