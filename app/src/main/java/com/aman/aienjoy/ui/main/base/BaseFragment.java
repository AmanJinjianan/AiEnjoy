package com.aman.aienjoy.ui.main.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.Nullable;

/**
 * Created by kiros on 2020/8/25.
 */

public abstract class BaseFragment<V extends BaseView, P extends BasePresenter<V>> extends Fragment {
    private P presenter;
    private V view;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (presenter == null) {
            presenter = createPresenter();
        }
        if (view == null) {
            view = createView();
        }
        if (presenter != null && view != null) {
            presenter.attachView(view);
        }
        return inflater.inflate(getLayoutID(), null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
        initListener();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null && view != null) {
            presenter.detachView();
        }
    }

    protected abstract P createPresenter();

    protected abstract V createView();

    public P getPresenter() {
        return presenter;
    }

    protected abstract int getLayoutID();
    protected abstract void initView(View view);
    protected abstract void initData();
    protected abstract void initListener();
}
