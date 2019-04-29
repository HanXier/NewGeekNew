package com.baidu.geeknews.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.geeknews.R;
import com.baidu.geeknews.base.BaseFragment;
import com.baidu.geeknews.presenter.GankP;
import com.baidu.geeknews.util.SystemUtil;
import com.baidu.geeknews.view.GankV;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public class GankFragment extends BaseFragment<GankV, GankP>
        implements GankV {
    @BindView(R.id.iv_tech_blur)
    ImageView mIvTechBlur;
    @BindView(R.id.iv_tech_origin)
    ImageView mIvTechOrigin;
    @BindView(R.id.tv_tech_copyright)
    TextView mTvTechCopyright;
    @BindView(R.id.tech_appbar)
    AppBarLayout mTechAppbar;
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.nsv)
    NestedScrollView mNsv;
    String mUrl = "https://ws1.sinaimg.cn/large/610dc034ly1fiednrydq8j20u011itfz.jpg";


    @Override
    protected GankP initPresenter() {
        return new GankP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank;
    }

    @Override
    protected void initView() {
        RequestOptions options = new RequestOptions()
                .transform(new BlurTransformation());
        Glide.with(getContext()).load(R.drawable.test2).apply(options).into(mIvTechBlur);
        Glide.with(getContext()).load(R.drawable.test2).into(mIvTechOrigin);
        mTechAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //图片随着滑动变模糊,
                //有两张图片,后面一张是高斯模糊过的,前面一张是原图,
                // 随着滑动原图改变透明度实现上面的效果
                //透明度 0-1,0完全透明,1,完全不透明
                //verticalOffset *1.0f / SystemUtil.dp2px(256) -1到0
                //以两倍的速度减小透明度,可以更快的看到模糊效果
                float rate = 1 + verticalOffset * 2.0f / SystemUtil.dp2px(256);
                if (rate >= 0)
                    mIvTechOrigin.setAlpha(rate);
            }
        });
    }
}
