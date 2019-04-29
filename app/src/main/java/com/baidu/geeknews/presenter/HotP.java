package com.baidu.geeknews.presenter;

import android.util.Log;

import com.baidu.geeknews.base.BasePresenter;
import com.baidu.geeknews.bean.MeinvBean;
import com.baidu.geeknews.model.HotM;
import com.baidu.geeknews.net.ResultCallBack;
import com.baidu.geeknews.view.EmptyV;
import com.baidu.geeknews.view.HotV;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public class HotP extends BasePresenter<HotV> {

    private static final String TAG = "HotP";
    private HotM mHotM;

    @Override
    protected void initModel() {
        mHotM = new HotM();
        mModels.add(mHotM);
    }

    public void getData(int page) {
        mHotM.getData(page, new ResultCallBack<MeinvBean>() {
            @Override
            public void onSuccess(MeinvBean bean) {
                Log.d(TAG, "onSuccess: "+bean.toString());
                if (bean != null && !bean.isError()){
                    if (mMvpView != null){
                        mMvpView.setData(bean);
                    }
                }else {
                    if (mMvpView != null){
                        mMvpView.onFail("加载失败");
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
