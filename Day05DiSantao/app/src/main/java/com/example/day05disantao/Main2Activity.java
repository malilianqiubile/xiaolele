package com.example.day05disantao;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.day05disantao.adapter.AdapterRx;
import com.example.day05disantao.bean.BannBean;
import com.example.day05disantao.bean.FzBean;
import com.example.day05disantao.bean.Lei;
import com.example.day05disantao.molder.Imolder;
import com.example.day05disantao.mview.Mview;
import com.example.day05disantao.prenter.Imprenter;
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

public class Main2Activity extends AppCompatActivity implements Mview, View.OnClickListener {

    private RecyclerView mRv;
    private ArrayList<FzBean.DataBean> mList;
    private AdapterRx mAdapterRx;
    private Imprenter mImprenter;
    private Banner mBann;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        mRv = (RecyclerView) findViewById(R.id.rv);

        mBann = (Banner) findViewById(R.id.bann);
        mRv.setOnClickListener(this);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mList = new ArrayList<>();
        mAdapterRx = new AdapterRx(mList, Main2Activity.this);
        mRv.setAdapter(mAdapterRx);
        initdata();
        initbanner();
        mBann.setImageLoader(new Myolder());

    }

    private void initbanner() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Lei.urlBanner)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Lei lei = retrofit.create(Lei.class);
        lei.getDatabann().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BannBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BannBean bannBean) {
                        List<BannBean.DataBean> data = bannBean.getData();
                        ArrayList<String> strings = new ArrayList<>();
                        for (BannBean.DataBean a : data) {
                            strings.add(a.getImagePath());
                        }

                        mBann.setImages(strings);
                        mBann.start();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("tag", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initdata() {
        mImprenter = new Imprenter(new Imolder(), this);
        mImprenter.getData();

    }

    @Override
    public void Scuess(FzBean fzBean) {
        mList.addAll(fzBean.getData());
        mAdapterRx.notifyDataSetChanged();
    }

    @Override
    public void Error(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.rv:
                break;
        }
    }

    class Myolder extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            String path1 = (String) path;
            Glide.with(context).load(path1).into(imageView);
        }
    }
}