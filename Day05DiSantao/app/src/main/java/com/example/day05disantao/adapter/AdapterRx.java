package com.example.day05disantao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.day05disantao.R;
import com.example.day05disantao.bean.BannBean;
import com.example.day05disantao.bean.FzBean;
import com.example.day05disantao.bean.Lei;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 小乐乐 on 2019/5/31.
 */

public class AdapterRx extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<FzBean.DataBean> mList;
    private Context context;

    public AdapterRx(ArrayList<FzBean.DataBean> list, Context context) {
        mList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==1){
            View inflate2 = LayoutInflater.from(context).inflate(R.layout.erxml, parent, false);
            return new holder2(inflate2);
        }
        else if (viewType==2){

            View inflate3 = LayoutInflater.from(context).inflate(R.layout.samxml, parent, false);
            return new holder3(inflate3);
        }
        else {
            View inflate4 = LayoutInflater.from(context).inflate(R.layout.sixml, parent, false);
            return new holder4(inflate4);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof holder2){
            holder2 holder2= (holder2) holder;
            Glide.with(context).load(mList.get(position).getPic()).into(holder2.mIvPic1);
            holder2.mTvFoodStr1.setText(mList.get(position).getFood_str());
            holder2.mTvTitle1.setText(mList.get(position).getTitle());
        }
        else if (holder instanceof holder3){
            holder3 holder3= (holder3) holder;
            Glide.with(context).load(mList.get(position).getPic()).into(holder3.mIvPic2);
            Glide.with(context).load(mList.get(position).getPic()).into(holder3.mIvPic3);
            holder3.mTvFoodStr2.setText(mList.get(position).getFood_str());
        }
        else{
            holder4 holder4= (holder4) holder;
            Glide.with(context).load(mList.get(position).getPic()).into(holder4.mIvPic4);
            Glide.with(context).load(mList.get(position).getPic()).into(holder4.mIvPic5);
            Glide.with(context).load(mList.get(position).getPic()).into(holder4.mIvPic6);
        holder4.mTvFoodStr3.setText(mList.get(position).getFood_str());
        holder4.mTvTitle2.setText(mList.get(position).getTitle());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position%3== 0) {
            return 1;
        } else if (position % 3 == 1) {
            return 2;
        }
        else {
            return 3;
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

  /*  class holder1 extends RecyclerView.ViewHolder {
        View view;
        Banner mBann;

        public holder1(View itemView) {
            super(itemView);
            view = itemView;
            this.mBann = (Banner) itemView.findViewById(R.id.bann);
        }
    }*/

    class holder2 extends RecyclerView.ViewHolder {
        View view;
        TextView mTvFoodStr1;
        ImageView mIvPic1;
        TextView mTvTitle1;

        public holder2(View itemView) {
            super(itemView);
            view = itemView;
            this.mTvFoodStr1 = (TextView) itemView.findViewById(R.id.tv_food_str1);
            this.mIvPic1 = (ImageView) itemView.findViewById(R.id.iv_pic1);
            this.mTvTitle1 = (TextView) itemView.findViewById(R.id.tv_title1);
        }
    }

    class holder3 extends RecyclerView.ViewHolder {
        View view;
        TextView mTvFoodStr2;
        ImageView mIvPic2;
        ImageView mIvPic3;

        public holder3(View itemView) {
            super(itemView);
            view = itemView;
            this.mTvFoodStr2 = (TextView) itemView.findViewById(R.id.tv_food_str2);
            this.mIvPic2 = (ImageView) itemView.findViewById(R.id.iv_pic2);
            this.mIvPic3 = (ImageView) itemView.findViewById(R.id.iv_pic3);
        }
    }

    class holder4 extends RecyclerView.ViewHolder {
        View view;
        TextView mTvFoodStr3;
        ImageView mIvPic4;
        ImageView mIvPic5;
        ImageView mIvPic6;
        TextView mTvTitle2;

        public holder4(View itemView) {
            super(itemView);
            view = itemView;
            this.mTvFoodStr3 = (TextView) itemView.findViewById(R.id.tv_food_str3);
            this.mIvPic4 = (ImageView) itemView.findViewById(R.id.iv_pic4);
            this.mIvPic5 = (ImageView) itemView.findViewById(R.id.iv_pic5);
            this.mIvPic6 = (ImageView) itemView.findViewById(R.id.iv_pic6);
            this.mTvTitle2 = (TextView) itemView.findViewById(R.id.tv_title2);
        }
    }



    }

