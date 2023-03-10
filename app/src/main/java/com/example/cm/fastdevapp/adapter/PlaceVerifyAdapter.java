package com.example.cm.fastdevapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.cm.fastdevapp.R;

import java.util.ArrayList;

class PlaceVerifyAdapter extends BaseAdapter {
    private ArrayList<PlaceVerifyItem> mData;
    private Context mContext;

    public PlaceVerifyAdapter(ArrayList<PlaceVerifyItem> list, Context context){
        mContext = context;
        mData = list;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_place_verify_list, null);
        }

        PlaceVerifyViewHolder placeVerifyViewHolder = (PlaceVerifyViewHolder) convertView.getTag();
        if (placeVerifyViewHolder == null) {
            placeVerifyViewHolder = new PlaceVerifyViewHolder(convertView);
            convertView.setTag(placeVerifyViewHolder);
        }

        placeVerifyViewHolder.bindData(mData.get(position));

        return convertView;
    }

    public static class PlaceVerifyViewHolder {
        TextView textView;
        ImageView imageView;

        PlaceVerifyViewHolder(View convertView) {
//            textView = (TextView) convertView.findViewById(R.id.setting_dialog_item);
//            imageView = (ImageView) convertView.findViewById(R.id.setting_dialog_check);
        }

        public void bindData(PlaceVerifyItem itemData) {

        }

    }

    public static class PlaceVerifyItem{
        public String placeName;
        public PlaceVerifyResult placeVerifyResult;

        PlaceVerifyItem(String placeName, PlaceVerifyResult placeVerifyResult){
            this.placeName = placeName;
            this.placeVerifyResult = placeVerifyResult;
        }
    }

    public static enum PlaceVerifyResult {
        UNSTART,
        TESTING,
        FINISH_SUCCESS,
        FINISH_FAILED
    }
}