package com.baidu.geeknews.view;

import com.baidu.geeknews.base.BaseMvpView;
import com.baidu.geeknews.bean.MeinvBean;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public interface HotV extends BaseMvpView {
    void setData(MeinvBean bean);

    void onFail(String msg);
}
