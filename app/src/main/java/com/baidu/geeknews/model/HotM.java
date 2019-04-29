package com.baidu.geeknews.model;

import com.baidu.geeknews.base.BaseModel;
import com.baidu.geeknews.bean.MeinvBean;
import com.baidu.geeknews.net.BaseObserver;
import com.baidu.geeknews.net.GankService;
import com.baidu.geeknews.net.HttpUtils;
import com.baidu.geeknews.net.ResultCallBack;
import com.baidu.geeknews.net.RxUtils;

import io.reactivex.disposables.Disposable;

/**
 * @author xts
 *         Created by asus on 2019/4/23.
 */

public class HotM extends BaseModel {
    public void getData(int page, final ResultCallBack<MeinvBean> callBack) {
        GankService apiserver = HttpUtils.getInstance().getApiserver(GankService.sBaseUrl, GankService.class);
        apiserver.getMeiNvData(page)
                .compose(RxUtils.<MeinvBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MeinvBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(MeinvBean meinvBean) {
                        callBack.onSuccess(meinvBean);
                    }
                });
    }
}
