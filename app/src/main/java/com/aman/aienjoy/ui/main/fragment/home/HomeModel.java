package com.aman.aienjoy.ui.main.fragment.home;

import android.content.Context;
import android.text.TextUtils;


import com.aman.aienjoy.ui.main.base.BaseModel;
import com.aman.aienjoy.ui.main.http.OnResultListener;
import com.aman.aienjoy.ui.main.utils.OkHttpUtils;

import org.json.JSONObject;

/**
 * Created by kiros on 2020/9/3.
 */

public class HomeModel extends BaseModel {

    public void getDataList(Context context, final OnResultListener resultListener){

        OkHttpUtils.getInstance(context).getJsonForString(OkHttpUtils.BASE_URL + "/appCourse/queryMyCurTermCourseList",
                new String[]{"userCode"},
                new String[]{null}, new OkHttpUtils.HttpCallBack() {
                    @Override
                    public void onSuccess(String obj) {
                        try {
                            JSONObject jObject = new JSONObject(obj);
                            if (0 == jObject.getInt("code")) {
                                resultListener.onSuccess(obj);
                            } else {
                                resultListener.onError(jObject.get("message").toString());
                            }
                        } catch (Exception e) {
                            e.toString();
                        }
                    }
                    @Override
                    public void onSuccess(JSONObject obj) {
                        //resultListener.onSuccess(obj.toString());
                    }
                    @Override
                    public void onError(String msg) {
                        resultListener.onError(msg);
                    }
                });
    }
}
