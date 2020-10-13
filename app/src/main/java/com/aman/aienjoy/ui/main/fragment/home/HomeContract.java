package com.aman.aienjoy.ui.main.fragment.home;


import com.aman.aienjoy.ui.main.base.BaseView;

public interface HomeContract {

    interface IView extends BaseView {

        /**
         *
         */
        void getHeaderInfo(String name);

        /*
        */
        void getLessonList(String name);

        /*
         */
        void getLessonProgress(String name);
    }
}
