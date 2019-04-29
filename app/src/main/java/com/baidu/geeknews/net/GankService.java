package com.baidu.geeknews.net;

import com.baidu.geeknews.bean.MeinvBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author xts
 *         Created by asus on 2019/4/23.
 */

public interface GankService {
    String sBaseUrl = "http://gank.io/api/";

    @GET("data/%E7%A6%8F%E5%88%A9/10/{page}")
    Observable<MeinvBean> getMeiNvData(@Path("page") int page);
}
