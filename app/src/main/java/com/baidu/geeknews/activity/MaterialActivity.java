package com.baidu.geeknews.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;

import com.baidu.geeknews.R;

public class MaterialActivity extends AppCompatActivity {

    private static final String TAG = "MaterialActivity";
    private Toolbar mToolBar;
    private NestedScrollView mNsv;
    private AppBarLayout mAppBar;
    private CollapsingToolbarLayout mCtl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);
        initView();
        //1.原生方法:对象是可以直接往textiew里面设置的
        Spanned spanned = Html.fromHtml("");
        //2.腾讯x5浏览器内核
    }

    private void initView() {
        mToolBar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(mToolBar);

        mNsv = (NestedScrollView) findViewById(R.id.nsv);
        mAppBar = (AppBarLayout) findViewById(R.id.appBar);

        //滑动偏移监听事件
        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d(TAG, "onOffsetChanged: " + verticalOffset);
            }
        });
        mCtl = (CollapsingToolbarLayout) findViewById(R.id.ctl);

        //标题必须在CollapsingToolbarLayout设置
        mCtl.setTitle("折叠ToolBar");
        //扩张时候的title颜色
        mCtl.setExpandedTitleColor(getResources().getColor(R.color.colorAccent));
        //收缩后显示title的颜色
        mCtl.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
    }
}
