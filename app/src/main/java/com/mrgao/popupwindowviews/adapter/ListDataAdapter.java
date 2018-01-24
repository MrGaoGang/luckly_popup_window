package com.mrgao.popupwindowviews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrgao.popupwindowviews.LucklyPopopWindow;
import com.mrgao.popupwindowviews.R;
import com.mrgao.popupwindowviews.beans.DataBeans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mr.gao on 2018/1/24.
 * Package:    com.mrgao.popupwindowviews.adapter
 * Create Date:2018/1/24
 * Project Name:PopupWindowViews
 * Description:
 */

public class ListDataAdapter extends RecyclerView.Adapter<ListDataAdapter.ListDataHolder> {

    private List<DataBeans> mList;
    private Context mContext;
    LucklyPopopWindow.OnItemClickListener mOnItemClickListener;
    private int mTextColor;
    public ListDataAdapter(Context context) {
        mList = new ArrayList<>();
        mContext = context;
    }

    @Override
    public ListDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_layout, parent, false);
        return new ListDataHolder(view);
    }

    @Override
    public void onBindViewHolder(ListDataHolder holder, final int position) {
        DataBeans dataBeans=mList.get(position);
        String data = dataBeans.getData();
        holder.mTextView.setText(data);
        holder.mTextView.setTextColor(mTextColor);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(position);
                }
            }
        });

        if (dataBeans.getBitmap() != null) {
            holder.mImageView.setImageBitmap(dataBeans.getBitmap());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public void setTextColor(int textColor) {
        mTextColor = textColor;
    }

    public void setData(List<DataBeans> list) {
        mList.clear();
        mList = list;
        notifyDataSetChanged();
    }

    public void setData(DataBeans[] data) {
        mList.clear();
        for (int i = 0; i < data.length; i++) {
            mList.add(data[i]);
        }
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(LucklyPopopWindow.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    class ListDataHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        ImageView mImageView;
        public ListDataHolder(View itemView) {
            super(itemView);
            mImageView=(ImageView)itemView.findViewById(R.id.image);
            mTextView = (TextView) itemView.findViewById(R.id.item);
        }
    }
}
