package com.baidu.geeknews.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.baidu.geeknews.R;
import com.baidu.geeknews.util.ToastUtil;
import com.baidu.geeknews.widget.FlowLayout;

import java.util.ArrayList;

public class FlowActivity extends AppCompatActivity {

    private FlowLayout mFl;
    private ArrayList<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);
        initData();
        initView();
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mData.add("流式布局:"+i);
        }
    }

    private void initView() {
        mFl = (FlowLayout) findViewById(R.id.fl);

        for (int i = 0; i < mData.size(); i++) {
            //获取视图,视图可以自定义,可以添加自己想要的效果
            TextView label = (TextView) View.inflate(this, R.layout.item_label, null);
            //获取数据
            final String data = mData.get(i);
            //绑定数据
            label.setText(data);

            label.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showToast(data);
                }
            });

            //加到容器中,parent是FlowLayout
            mFl.addView(label);
        }
    }

    private void showToast(String data) {
        ToastUtil.showShort(data);
    }
}
