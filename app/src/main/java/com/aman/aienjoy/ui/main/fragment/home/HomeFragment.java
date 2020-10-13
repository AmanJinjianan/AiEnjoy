package com.aman.aienjoy.ui.main.fragment.home;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.aman.aienjoy.R;
import com.aman.aienjoy.ui.main.base.BaseFragment;
import com.aman.aienjoy.ui.main.utils.LogUtil;
import com.aman.aienjoy.ui.main.utils.ToastUtil;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by kiros on 2020/8/25.
 */

public class HomeFragment extends BaseFragment<HomeContract.IView, HomePresenter> implements HomeContract.IView, View.OnClickListener, OnBannerListener {

    public Banner banner;
    private RecyclerView.LayoutManager layoutManager;
    private List lessons = new ArrayList<>();
    int requestCount = 0;

    private List imgs = new ArrayList<>();
    private List titles = new ArrayList<>();
    private List img_tolink = new ArrayList<>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected HomeContract.IView createView() {
        return this;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_movie;
    }

    @Override
    protected void initView(View view) {
        banner = view.findViewById(R.id.banner1);
    }

    @Override
    protected void initData() {
        titles.add("title1");
        titles.add("title2");
        titles.add("title3");
        imgs.add(R.drawable.game_1);
        imgs.add(R.drawable.game_2);
        imgs.add(R.drawable.game_3);
        //getPresenter().getDataHeader(getContext());
        setBanner();
    }

    @Override
    protected void initListener() { }

    @Override
    public void getData(String data) {
    }

    @Override
    public void showError(String error) {
        if (error != null) {
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void getHeaderInfo(String data) {
        try {
            JSONObject jObject = new JSONObject(data);
            JSONArray jArray = jObject.getJSONArray("data");

            if (!(jArray.length() > 0)) {
                ToastUtil.showShortToast("header无数据");
                return;
            }

        } catch (Exception e) {
            e.toString();
        }
    }

    @Override
    public void getLessonList(String data) {

    }

    Map<String, Object> progressMap = new HashMap<>();

    @Override
    public void getLessonProgress(String data) {

        try {
            JSONObject jObject = new JSONObject(data);
            JSONArray jArray = jObject.getJSONArray("data");

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jsonObject = jArray.getJSONObject(i);
                JSONArray names = jsonObject.names();
                progressMap = jsonToMap(jsonObject.toString());

            }

            if (!(jArray.length() > 0)) {
                ToastUtil.showShortToast("header无数据");
            }
        } catch (Exception e) {
            e.toString();
        }

    }

    private void setBanner() {
        //imgs.add(getStringFromDrawableRes(getContext(), R.drawable.zu1));
        //imgs.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1346142700,2282998096&fm=26&gp=0.jpg");
        //titles.add("教育图片1");
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        banner.setImages(imgs);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
        banner.setBannerTitles(titles);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();
    }
    private Map<String, Object> jsonToMap(String content) {
        content = content.trim();
        Map<String, Object> result = new HashMap<>();
        try {
            if (content.charAt(0) == '[') {
                JSONArray jsonArray = new JSONArray(content);
                for (int i = 0; i < jsonArray.length(); i++) {
                    Object value = jsonArray.get(i);
                    if (value instanceof JSONArray || value instanceof JSONObject) {
                        result.put(i + "", jsonToMap(value.toString().trim()));
                    } else {
                        result.put(i + "", jsonArray.getString(i));
                    }
                }
            } else if (content.charAt(0) == '{') {
                JSONObject jsonObject = new JSONObject(content);
                Iterator<String> iterator = jsonObject.keys();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    Object value = jsonObject.get(key);
                    if (value instanceof JSONArray || value instanceof JSONObject) {
                        result.put(key, jsonToMap(value.toString().trim()));
                    } else {
                        result.put(key, value.toString().trim());
                    }
                }
            } else {

            }
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    @Override
    public void OnBannerClick(int position) {
        LogUtil.e("notice","你点了第"+position+"张轮播图");
    }


    //自定义的图片加载器
    private class MyLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);
        }
    }

}
