package com.baidu.geeknews.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.baidu.geeknews.R;
import com.baidu.geeknews.activity.MainActivity;
import com.baidu.geeknews.base.BaseFragment;
import com.baidu.geeknews.base.Constants;
import com.baidu.geeknews.presenter.EmptyP;
import com.baidu.geeknews.util.SpUtil;
import com.baidu.geeknews.util.UIModeUtil;
import com.baidu.geeknews.view.EmptyV;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public class SettingFragment extends BaseFragment<EmptyV, EmptyP>
        implements EmptyV {
    @BindView(R.id.sc)
    SwitchCompat mSc;
    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initView() {
        //if (sp里面的模式是否为夜间){
        int mode = (int) SpUtil.getParam(Constants.MODE, AppCompatDelegate.MODE_NIGHT_NO);
        if (mode == AppCompatDelegate.MODE_NIGHT_YES){
            mSc.setChecked(true);
        }else {
            mSc.setChecked(false);
        }
    }

    @Override
    protected void initListener() {
        mSc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               //切换模式,
                //切换夜间模式的时候,Activiyt会重新创建,新建的switchCompat默认是false,会
                //调用这个回调,去掉非人为的回调
                if (buttonView.isPressed()){
                    UIModeUtil.changeModeUI((MainActivity)getActivity());

                    //保存设置碎片的位置,再次进来之后直接显示设置Fragmnet
                    SpUtil.setParam(Constants.NIGHT_CURRENT_FRAG_POS,MainActivity.TYPE_SETTING);
                }

            }
        });
    }
}
