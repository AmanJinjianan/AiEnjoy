package com.aman.aienjoy.ui.main.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.Nullable;

/**
 * Created by kiros on 2019/6/27.
 */

public abstract class BaseMvpActivity<V extends BaseView, P extends BasePresenter<V>> extends AppCompatActivity {

    private P presenter;
    private V view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());

        if (presenter == null) {
            presenter = createPresenter();
        }
        if (view == null) {
            view = createView();
        }
        if (presenter != null && view != null) {
            presenter.attachView(view);
        }

        initView();
        initData();
        initListener();
    }

    protected abstract int getLayoutID();

    protected abstract P createPresenter();

    protected abstract V createView();

    protected abstract void initView();
    protected abstract void initData();
    protected abstract void initListener();

    public P getPresenter() {
        return presenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null && view != null) {
            presenter.detachView();
        }
    }
}
