package com.aman.aienjoy.ui.main;

import android.os.Bundle;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.aman.aienjoy.R;
import com.aman.aienjoy.ui.main.fragment.home.HomeFragment;
import com.aman.aienjoy.widget.BottomBar;


/**
 */

public class HomeActivity extends AppCompatActivity {

    private BottomBar bottomBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
    }

    protected void initView() {
        bottomBar = findViewById(R.id.bottom_bar);
        bottomBar.setContainer(R.id.fl_container)
                .setTitleBeforeAndAfterColor("#666666", "#3399ff")
                .addItem(HomeFragment.class,
                        getString(R.string.movie),
                        R.drawable.group1,
                        R.drawable.group)
                .addItem(HomeFragment.class,
                        getString(R.string.tv),
                        R.drawable.group1,
                        R.drawable.group)
                .addItem(HomeFragment.class,
                        getString(R.string.game),
                        R.drawable.group1,
                        R.drawable.group)
                .build();
    }
}
