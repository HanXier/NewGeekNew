package com.baidu.geeknews.fragment;

import android.support.design.widget.Snackbar;
import android.util.Log;

import com.baidu.geeknews.R;
import com.baidu.geeknews.base.BaseFragment;
import com.baidu.geeknews.bean.V2exTabsBean;
import com.baidu.geeknews.presenter.GankP;
import com.baidu.geeknews.presenter.V2exP;
import com.baidu.geeknews.view.GankV;
import com.baidu.geeknews.view.V2exV;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author xts
 * Created by asus on 2019/4/3.
 */

public class V2exFragment extends BaseFragment<V2exV,V2exP>
    implements V2exV{
    private static final String TAG = "V2exFragment";
    private String mUrl = "https://www.v2ex.com/";
    @Override
    protected V2exP initPresenter() {
        return new V2exP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_v2ex;
    }

    @Override
    protected void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(mUrl).get();
                    //tabs
                    ///  //class等于masthead的div标签
                    Element tabs = doc.select("div#Tabs").first();
                    Elements allTabs = tabs.select("a[href]");

                    ArrayList<V2exTabsBean> tabsList = new ArrayList<>();
                    for (Element element :allTabs) {
                        //获取href属性
                        String linkHref = element.attr("href");
                        //获取标签里面文本的
                        String linkText = element.text();
                        Log.d(TAG, "linkHref: "+linkHref+",tab:"+linkText);
                        V2exTabsBean v2exTabsBean = new V2exTabsBean(linkHref, linkText);
                        tabsList.add(v2exTabsBean);
                    }

                    //新闻item数据
                    Elements items = doc.select("div.cell.item_meinv");
                    for (Element item :items) {
                        //图片
                        Element image = item.select("table tr td a > img.avatar").first();
                        String src = image.attr("src");
                        //Log.d(TAG, "src: "+src);

                        //评论数量和评论链接地址
                        Element comment = item.select("table tbody tr td a.count_livid").first();
                        if (comment != null){
                            String href = comment.attr("href");
                            String text = comment.text();
                            //Log.d(TAG, "评论: "+",链接:"+href+",数量:"+text);
                        }
                        //标题
                        Element title = item.select("table tbody tr td span.item_title > a").first();
                        String text = title.text();
                        Log.d(TAG, "标题: "+text);

                        //topic_info
                        Element topic = item.select("table tbody tr td span.topic_info").first();
                        Element secondaryTab = topic.select("a.node").first();
                        String secTab = secondaryTab.text();
                        Log.d(TAG, "secTab: "+secTab);

                        String topicText = topic.text();
                        Log.d(TAG, "topicText: "+topicText);

                        Elements people = topic.select("strong > a");
                        if (people.size()>0){
                            //作者
                            Element element = people.get(0);
                            Log.d(TAG, "作者: "+element.text());
                        }

                        if (people.size()>1){
                            //最后的评论者
                            Element element = people.get(1);
                            Log.d(TAG, "最后的评论者: "+element.text());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
