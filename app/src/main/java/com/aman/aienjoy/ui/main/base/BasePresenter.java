package com.aman.aienjoy.ui.main.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by kiros on 2019/7/2.
 */

public abstract class BasePresenter<V extends BaseView> {

    protected Reference<V> view;

    public V getView() {
        return view.get();
    }

    public void attachView(V view) {
        this.view = new WeakReference<V>(view);
    }

    public void detachView() {
        if (view != null) {
            view.clear();
            view = null;
        }
    }
}
