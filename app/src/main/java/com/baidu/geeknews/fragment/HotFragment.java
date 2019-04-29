package com.baidu.geeknews.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.geeknews.R;
import com.baidu.geeknews.adapter.RlvMeinvAdatper;
import com.baidu.geeknews.base.BaseFragment;
import com.baidu.geeknews.bean.MeinvBean;
import com.baidu.geeknews.presenter.HotP;
import com.baidu.geeknews.util.ToastUtil;
import com.baidu.geeknews.view.HotV;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public class HotFragment extends BaseFragment<HotV, HotP>
        implements HotV {
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    @BindView(R.id.srl)
    SmartRefreshLayout mSrl;
    private int mPage = 1;
    private RlvMeinvAdatper mAdapter;

    @Override
    protected HotP initPresenter() {
        return new HotP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView() {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL);
        ////防止图片在上下滑动的时候移动.
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRlv.setLayoutManager(manager);

        ArrayList<MeinvBean.ResultsBean> data = new ArrayList<>();
        mAdapter = new RlvMeinvAdatper(getContext(), data);
        mRlv.setAdapter(mAdapter);

        mSrl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //加载更多
                mPage++;
                initData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //下拉刷新
                mAdapter.mData.clear();
                mPage = 1;
                initData();
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getData(mPage);
    }

    @Override
    public void setData(MeinvBean bean) {
        if (bean.getResults() != null && bean.getResults().size()>0) {
            mAdapter.addData(bean.getResults());
        }

        finishLoadMore();
        finishRefresh();
    }

    private void finishRefresh() {
        mSrl.finishRefresh();
    }

    private void finishLoadMore() {
        mSrl.finishLoadMore();
    }

    @Override
    public void onFail(String msg) {
        ToastUtil.showShort(msg);

        finishLoadMore();
        finishRefresh();
    }
}
