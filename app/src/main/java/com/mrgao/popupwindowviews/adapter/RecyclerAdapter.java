package com.mrgao.popupwindowviews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrgao.popupwindowviews.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mr.gao on 2018/1/12.
 * Package:    mrgao.com.recyclerviewtext.dividerModule.adapter
 * Create Date:2018/1/12
 * Project Name:RecyclerViewText
 * Description:zhge
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    List<String> mStringList;
    Context mContext;
    OnItemClickListener mOnItemClickListener;
    public RecyclerAdapter(Context context) {
        mContext = context;
        mStringList = new ArrayList<>();

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.mTextView.setText(mStringList.get(position));

        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(holder.mTextView,position);
                }
            }
        });

        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(holder.mImageView,position);
                }
            }
        });
        holder.mLeftTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(holder.mLeftTextView,position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mStringList.size();
    }


    public void addAll(List<String> stringList) {
        if (mStringList != null) {
            mStringList.addAll(stringList);
        }
    }

    public void add(String s, int position) {
        mStringList.add(position, s);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mStringList.remove(position);
        notifyItemRemoved(position);
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        ImageView mImageView;
        TextView mLeftTextView;
        public MyViewHolder(View itemView) {
            super(itemView);
            mLeftTextView = getView(itemView, R.id.left);
            mTextView = getView(itemView, R.id.item);
            mImageView = getView(itemView, R.id.more);
        }
    }

    public <T extends View> T getView(View view, int id) {
        return (T) view.findViewById(id);
    }
}
