package com.example.day05disantao.bean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by 小乐乐 on 2019/5/31.
 */

public interface Lei {
    //http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=20&page=1
    String url="http://www.qubaobei.com/ios/";
    @GET("cf/dish_list.php?stage_id=1&limit=20&page=1")
    Observable<FzBean> getData();
    //https://www.wanandroid.com/banner/json
    String urlBanner="https://www.wanandroid.com/";
    @GET("banner/json")
    Observable<BannBean> getDatabann();
}
