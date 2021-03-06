package com.aman.aienjoy.ui.main;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.aman.aienjoy.R;
import com.aman.aienjoy.ui.main.http.OnResultListener;
import com.aman.aienjoy.ui.main.utils.AppConstant;
import com.aman.aienjoy.ui.main.utils.LogUtil;
import com.aman.aienjoy.ui.main.utils.OkHttpUtils;
import com.aman.aienjoy.ui.main.utils.ToastUtil;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mBtnLogin,tv_register;

    private View progress;

    private View mInputLayout;

    private float mWidth, mHeight;

    private LinearLayout mName, mPsw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        initView();
    }
    private  EditText et_name_login,et_psd_login;
    private void initView() {
        mBtnLogin = (TextView) findViewById(R.id.main_btn_login);
        tv_register = (TextView) findViewById(R.id.tv_register);
        progress = findViewById(R.id.layout_progress);
        mInputLayout = findViewById(R.id.input_layout);

        mName = (LinearLayout) findViewById(R.id.input_layout_name);
        mPsw = (LinearLayout) findViewById(R.id.input_layout_psw);

        et_name_login =  (EditText) findViewById(R.id.et_name_login);
        et_psd_login =  (EditText) findViewById(R.id.et_psd_login);

        mBtnLogin.setOnClickListener(this);
        tv_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            case R.id.main_btn_login:
                // 计算出控件的高与宽
                mWidth = mBtnLogin.getMeasuredWidth();
                mHeight = mBtnLogin.getMeasuredHeight();
                // 隐藏输入框
                mName.setVisibility(View.INVISIBLE);
                mPsw.setVisibility(View.INVISIBLE);

                inputAnimator(mInputLayout, mWidth, mHeight);

                String usernameStr = et_name_login.getText().toString().trim();
                String passwordStr = et_psd_login.getText().toString().trim();
                if(TextUtils.isEmpty(usernameStr) || TextUtils.isEmpty(passwordStr)){
                    ToastUtil.showShortToast("不能出现空字符");
                    return;
                }

                final String finalUsernameStr ="admin";
                final String finalPasswordStr = "admin1";
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        goLogin(LoginActivity.this, finalUsernameStr, finalPasswordStr, new OnResultListener() {
                            @Override
                            public void onSuccess(String result) {

                            }

                            @Override
                            public void onError(String error) {

                            }
                        });
//                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
//                        finish();
                    }
                },1500);
                break;
        }

    }

    /**
     * 输入框的动画效果
     *
     * @param view
     *            控件
     * @param w
     *            宽
     * @param h
     *            高
     */
    private void inputAnimator(final View view, float w, float h) {

        AnimatorSet set = new AnimatorSet();

        ValueAnimator animator = ValueAnimator.ofFloat(0, w);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view
                        .getLayoutParams();
                params.leftMargin = (int) value;
                params.rightMargin = (int) value;
                view.setLayoutParams(params);
            }
        });

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mInputLayout,
                "scaleX", 1f, 0.5f);
        set.setDuration(1000);
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        set.playTogether(animator, animator2);
        set.start();
        set.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                /**
                 * 动画结束后，先显示加载的动画，然后再隐藏输入框
                 */
                progress.setVisibility(View.VISIBLE);
                progressAnimator(progress);
                mInputLayout.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }
        });

    }

    /**
     * 出现进度动画
     *
     * @param view
     */
    private void progressAnimator(final View view) {
        PropertyValuesHolder animator = PropertyValuesHolder.ofFloat("scaleX",
                0.5f, 1f);
        PropertyValuesHolder animator2 = PropertyValuesHolder.ofFloat("scaleY",
                0.5f, 1f);
        ObjectAnimator animator3 = ObjectAnimator.ofPropertyValuesHolder(view,
                animator, animator2);
        animator3.setDuration(1000);
        animator3.setInterpolator(new JellyInterpolator());
        animator3.start();

    }

    public void goLogin(Context context, String name, String pwd, final OnResultListener resultListener) {
        OkHttpUtils.getInstance(context).getJsonForString(OkHttpUtils.BASE_URL + "/user/userLogin",
                new String[]{"logincode", "password"}, new String[]{name, pwd}, new OkHttpUtils.HttpCallBack() {
                    @Override
                    public void onSuccess(String obj) {
                        try {
                            JSONObject jObject = new JSONObject(obj);
                            if (0 == jObject.getInt("code")) {
                                LogUtil.e("Forget", "成功");
                                resultListener.onSuccess(obj);
                            } else {
                                LogUtil.e("Forget", "失败");
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
                        LogUtil.e("Forget", "失败");
                        resultListener.onError(msg);
                    }
                });
    }
}