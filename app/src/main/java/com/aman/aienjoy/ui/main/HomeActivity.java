package com.aman.aienjoy.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

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
        setContentView(R.layout.activity_main);
        initView();
    }
    TextView  tv_center;

    protected void initView() {
        tv_center = (TextView)findViewById(R.id.bottom_bar);
        tv_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,MyActivity.class));
            }
        });

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
