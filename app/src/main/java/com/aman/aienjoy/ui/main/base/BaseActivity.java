package com.aman.aienjoy.ui.main.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aman.aienjoy.R;
import com.aman.aienjoy.ui.main.utils.ActivityUtil;
import com.aman.aienjoy.ui.main.utils.StatusBarUtil;
import com.example.zhouwei.library.CustomPopWindow;

import org.jetbrains.annotations.Nullable;


/**
 * Created by kiros on 2019/7/14.
 */

public class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    private View titleView;
    public ImageView back, more;
    private TextView tv_title;
    private FrameLayout ly_content;
    // 内容区域的布局
    private View contentView;
    public Button btnCourse,btnTeacher,btnAppraise,btnMore;
    CustomPopWindow mCustomPopWindow;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ActivityUtil.getInstance().addActivity(this);
        StatusBarUtil.transparencyBar(this); //设置状态栏全透明
        StatusBarUtil.StatusBarLightMode(this); //设置白底黑字
        initView();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public CustomPopWindow.PopupWindowBuilder popWindow;
    private void initView() {
        titleView = findViewById(R.id.top_title);
        back = findViewById(R.id.back);
        tv_title = findViewById(R.id.title);

        more = findViewById(R.id.more);
//        popWindow = new CustomPopWindow.PopupWindowBuilder(this)
//                .setView(R.layout.layout_dropdown);
//
//        btnCourse = findViewById(R.id.btn_menu_course);
//        btnTeacher = findViewById(R.id.btn_menu_teacher);
//        btnAppraise = findViewById(R.id.btn_menu_appraise);

        ly_content = findViewById(R.id.content);

        more.setOnClickListener(this);
    }

    /**
     * 处理弹出显示内容、点击事件等逻辑
     * @param contentView
     */
    private void handleLogic(View contentView){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCustomPopWindow!=null){
                    mCustomPopWindow.dissmiss();
                }
                String showContent = "";
                switch (v.getId()){
//                    case R.id.btn_menu_course:
//                        startActivity(new Intent(BaseActivity.this, CourseIntroduceActivity.class));
//                        break;
//                    case R.id.btn_menu_teacher:
//                        startActivity(new Intent(BaseActivity.this, TeacherInfoActivity.class));
//                        break;
//                    case R.id.btn_menu_appraise:
//                        startActivity(new Intent(BaseActivity.this, EvaluateActivity.class));
//                        break;
                }
               //Toast.makeText(BaseActivity.this,showContent,Toast.LENGTH_SHORT).show();
            }
        };
//        contentView.findViewById(R.id.btn_menu_course).setOnClickListener(listener);
//        contentView.findViewById(R.id.btn_menu_teacher).setOnClickListener(listener);
//        contentView.findViewById(R.id.btn_menu_appraise).setOnClickListener(listener);
    }

    /***
     * 设置内容区域
     *
     * @param resId
     * 资源文件ID
     */
    public void setContentLayout(int resId) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(resId, null);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.FILL_PARENT);
        contentView.setLayoutParams(layoutParams);
        contentView.setBackgroundDrawable(null);
        if (null != ly_content) {
            ly_content.addView(contentView);
        }

    }



    /**
     * 隐藏上方的标题栏
     */
    public void hideTitleView() {

        if (null != titleView) {
            titleView.setVisibility(View.GONE);
        }
    }

    /**
     * 隐藏上方的标题栏
     */
    public void hideLeftIcon() {

        if (null != back) {
            back.setVisibility(View.GONE);
        }
    }


    //初始化跳转
    public void startActivity(Class clz) {
        Intent intent = new Intent(this, clz);
        startActivity(intent);
    }

    //跳转传值
    public void startActivity(Bundle bundle, Class clz) {
        Intent intent = new Intent(this, clz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        ActivityUtil.getInstance().removeActivity(this);
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 点击手机上的返回键，返回上一层
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.more:
//                View contentView = LayoutInflater.from(this).inflate(R.layout.layout_dropdown,null);
//                //处理popWindow 显示内容
//                handleLogic(contentView);
//                //创建并显示popWindow
//                mCustomPopWindow= new CustomPopWindow.PopupWindowBuilder(this)
//                        .setView(contentView)
//                        .create()
//                        .showAsDropDown(this.more,0,20);
                break;
        }
    }
}
