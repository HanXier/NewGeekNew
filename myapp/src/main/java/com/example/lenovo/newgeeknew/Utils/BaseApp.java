package com.example.lenovo.newgeeknew.Utils;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.tencent.smtt.sdk.QbSdk;


/**
 * Created by asus on 2019/3/5.
 */

public class BaseApp extends Application {
    private static BaseApp sBaseApp;
    //默认不是夜间模式
    public static int mMode = AppCompatDelegate.MODE_NIGHT_NO;

    public static int mWidthPixels;
    public static int mHeightPixels;
    @Override
    public void onCreate() {
        super.onCreate();
        sBaseApp = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
                    @Override
                    public void onCoreInitFinished() {

                    }
                    @Override
                    public void onViewInitFinished(boolean b) {

                    }
                };
                QbSdk.initX5Environment(getApplicationContext(),cb);

            }
        }).start();
setDayNightMode();

getScreenWH();
    }

    private void setDayNightMode() {
        //设置以前用户的模式选项
        mMode = (int) SpUtil.getParam(Constants.MODE,AppCompatDelegate.MODE_NIGHT_NO);
        UIModeUtil.setAppMode(mMode);
    }

    //计算屏幕宽高
    private void getScreenWH() {
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display defaultDisplay = manager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        defaultDisplay.getMetrics(metrics);
        mWidthPixels = metrics.widthPixels;
        mHeightPixels = metrics.heightPixels;
    }
    public static BaseApp getInstance(){
        return sBaseApp;
    }

}
