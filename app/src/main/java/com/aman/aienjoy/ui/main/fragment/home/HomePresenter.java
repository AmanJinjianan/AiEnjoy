package com.aman.aienjoy.ui.main.fragment.home;

import android.content.Context;

import com.aman.aienjoy.ui.main.base.BasePresenter;
import com.aman.aienjoy.ui.main.http.OnResultListener;

/**
 * Created by kiros on 2020/9/3.
 */

public class HomePresenter extends BasePresenter<HomeContract.IView> {

    private HomeModel nModel;
    public HomePresenter(){
        this.nModel = new HomeModel();
    }
    public void getDataList(Context context){
        nModel.getDataList(context,new OnResultListener() {
            @Override
            public void onSuccess(String result) {
                getView().getLessonList(result);
            }

            @Override
            public void onError(String error) {
                getView().showError(error);
            }
        });
    }

}
