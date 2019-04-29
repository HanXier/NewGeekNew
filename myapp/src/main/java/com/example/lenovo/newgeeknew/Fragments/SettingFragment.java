package com.example.lenovo.newgeeknew.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.lenovo.newgeeknew.MainActivity;
import com.example.lenovo.newgeeknew.R;
import com.example.lenovo.newgeeknew.Utils.Constants;
import com.example.lenovo.newgeeknew.Utils.SpUtil;
import com.example.lenovo.newgeeknew.Utils.UIModeUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment{


    private View view;
    private SwitchCompat mSc;

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        //if (sp里面的模式是否为夜间){
        int mode = (int) SpUtil.getParam(Constants.MODE, AppCompatDelegate.MODE_NIGHT_NO);
        if (mode == AppCompatDelegate.MODE_NIGHT_YES){
            mSc.setChecked(true);
        }else {
            mSc.setChecked(false);
        }

        mSc = (SwitchCompat) view.findViewById(R.id.sc);
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
