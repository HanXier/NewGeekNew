package com.baidu.geeknews.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.geeknews.R;
import com.baidu.geeknews.activity.FlowActivity;
import com.baidu.geeknews.activity.MainActivity;
import com.baidu.geeknews.activity.MaterialActivity;
import com.baidu.geeknews.adapter.RlvAdapter;
import com.baidu.geeknews.base.BaseFragment;
import com.baidu.geeknews.bean.Car;
import com.baidu.geeknews.presenter.EmptyP;
import com.baidu.geeknews.presenter.GankP;
import com.baidu.geeknews.view.EmptyV;
import com.baidu.geeknews.view.GankV;

import java.util.ArrayList;

import butterknife.BindView;
import qdx.stickyheaderdecoration.NormalDecoration;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public class CollectFragment extends BaseFragment<EmptyV,EmptyP>
    implements EmptyV{
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    private ArrayList<Car> mCars;

    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    protected void initData() {
        mCars = new ArrayList<>();
        mCars.add(new Car("奥迪",  "A"));
        mCars.add(new Car("阿尔法罗密欧",  "A"));
        mCars.add(new Car("阿斯顿马丁",  "A"));
        mCars.add(new Car("ALPINA",  "A"));
        mCars.add(new Car("安凯客车",  "A"));


        mCars.add(new Car("本田", "B"));
        mCars.add(new Car("别克", "B"));
        mCars.add(new Car("奔驰",  "B"));
        mCars.add(new Car("宝马", "B"));
        mCars.add(new Car("保时捷",  "B"));
        mCars.add(new Car("比亚迪", "B"));
        mCars.add(new Car("北京", "B"));
        mCars.add(new Car("宾利",  "B"));
        mCars.add(new Car("巴博斯",  "B"));
        mCars.add(new Car("布加迪威龙", "B"));

        mCars.add(new Car("长安", "C"));
        mCars.add(new Car("长城",  "C"));

        mCars.add(new Car("大众", "D"));
        mCars.add(new Car("东南",  "D"));
        mCars.add(new Car("东风", "D"));
        mCars.add(new Car("DS", "D"));
        mCars.add(new Car("道奇", "D"));
        mCars.add(new Car("东风小康", "D"));

        setData();
    }

    private void setData() {
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        RlvAdapter rlvAdapter = new RlvAdapter(mCars);
        //返回头布局的内容
        final NormalDecoration decoration = new NormalDecoration() {
            @Override
            public String getHeaderName(int i) {
                return mCars.get(i).header;
            }
        };
        //自定义头布局,可不设置
        decoration.setOnDecorationHeadDraw(new NormalDecoration.OnDecorationHeadDraw() {
            @Override
            public View getHeaderView(final int i) {
                View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_header, null);
                TextView tv = inflate.findViewById(R.id.tv);
                tv.setText(mCars.get(i).header);

                return inflate;
            }
        });

        mRlv.addItemDecoration(decoration);
        //头布局的点击事件
        decoration.setOnHeaderClickListener(new NormalDecoration.OnHeaderClickListener() {
            @Override
            public void headerClick(int i) {
                Toast.makeText(getContext(), mCars.get(i).header, Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(getContext(), FlowActivity.class));
                startActivity(new Intent(getContext(), MaterialActivity.class));
            }
        });
        mRlv.setAdapter(rlvAdapter);
    }


}
