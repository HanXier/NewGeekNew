package com.baidu.geeknews.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.baidu.geeknews.R;
import com.baidu.geeknews.base.BaseApp;
import com.baidu.geeknews.bean.MeinvBean;
import com.baidu.geeknews.util.SystemUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * @author xts
 *         Created by asus on 2019/4/23.
 */

public class RlvMeinvAdatper extends RecyclerView.Adapter {
    private Context mContext;
    public ArrayList<MeinvBean.ResultsBean> mData;

    public RlvMeinvAdatper(Context context, ArrayList<MeinvBean.ResultsBean> data) {

        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_meinv, null);
        return new VH(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH) holder;
        MeinvBean.ResultsBean bean = mData.get(position);
        //设置图片之前需要重新设置ImageView的宽高
        int imageWidth = BaseApp.mWidthPixels / 2 - SystemUtil.dp2px(8);
        RelativeLayout.LayoutParams layoutParams =
                (RelativeLayout.LayoutParams) vh.mIv.getLayoutParams();
        layoutParams.width = imageWidth;
        if (bean.getScale() != 0){
            layoutParams.height = (int) (imageWidth/bean.getScale());
        }
        layoutParams.leftMargin = layoutParams.topMargin = SystemUtil.dp2px(4);
        vh.mIv.setLayoutParams(layoutParams);


        RequestOptions options = new RequestOptions()
                .transform(new RoundedCornersTransformation(
                        SystemUtil.dp2px(6), 0));

        Glide.with(mContext).load(bean.getUrl()).apply(options).into(vh.mIv);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addData(List<MeinvBean.ResultsBean> results) {
        mData.addAll(results);
        setImageScale();
    }

    //计算图片的宽高比
    private void setImageScale() {
        for (final MeinvBean.ResultsBean bean : mData) {
            if (bean.getScale() == 0) {
                Glide.with(mContext).load(bean.getUrl()).into(
                        new SimpleTarget<Drawable>() {
                            @Override
                            public void onResourceReady(@NonNull Drawable resource,
                                                        @Nullable Transition<? super Drawable> transition) {
                                //宽高比
                                float scale = resource.getIntrinsicWidth() * 1.0f / resource.getIntrinsicHeight();
                                bean.setScale(scale);
                                notifyDataSetChanged();
                            }
                        });
            } else {
                notifyDataSetChanged();
            }
        }
    }

    class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_img)
        ImageView mIv;

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
