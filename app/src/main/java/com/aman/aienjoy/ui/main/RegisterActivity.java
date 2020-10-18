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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.aman.aienjoy.R;
import com.aman.aienjoy.ui.main.utils.AppConstant;
import com.aman.aienjoy.ui.main.utils.LogUtil;
import com.aman.aienjoy.ui.main.utils.OkHttpUtils;
import com.aman.aienjoy.ui.main.utils.ToastUtil;

import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mBtnRegiser;

    private View progress;

    private View mInputLayout;

    private float mWidth, mHeight;

    private LinearLayout ll_layout_register,ll_layout_psw,ll_layout_psw2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        initView();
    }
    ImageButton ib_register_back;
    private EditText et_register_name,et_register_psd,et_register_psd2;
    private void initView() {
        ib_register_back = (ImageButton) findViewById(R.id.ib_register_back);
        mBtnRegiser = (TextView) findViewById(R.id.main_btn_register);
        progress = findViewById(R.id.layout_progress_register);
        mInputLayout = findViewById(R.id.input_layout_regisrer);
        et_register_name = (EditText) findViewById(R.id.et_register_name);
        et_register_psd = (EditText) findViewById(R.id.et_register_psd);
        et_register_psd2 = (EditText) findViewById(R.id.et_register_psd2);

        mBtnRegiser.setOnClickListener(this);
        ib_register_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_register_back:
                finish();
                break;
            case R.id.main_btn_register:
                if(TextUtils.isEmpty(et_register_name.getText().toString().trim()) || TextUtils.isEmpty(et_register_psd.getText().toString().trim()) || TextUtils.isEmpty(et_register_psd2.getText().toString().trim()))
                {
                    ToastUtil.showShortToast("不能出现空字符");
                    return;
                }
                if(!(et_register_psd.getText().toString().trim().equals((et_register_psd2.getText().toString().trim()))))
                {
                    ToastUtil.showShortToast("两次密码输入不一样");
                    return;
                }
                register();
                // 计算出控件的高与宽
//                mWidth = mBtnRegiser.getMeasuredWidth();
//                mHeight = mBtnRegiser.getMeasuredHeight();
                // 隐藏输入框
//                ll_layout_register.setVisibility(View.INVISIBLE);
//                ll_layout_psw.setVisibility(View.INVISIBLE);
//                ll_layout_psw2.setVisibility(View.INVISIBLE);
//                inputAnimator(mInputLayout, mWidth, mHeight);
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
        set.setDuration(1);
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

    public void register(Context context, String oldPwd, String pwd, final OnResultListener resultListener) {

        OkHttpUtils.getInstance(context).getJsonForString(OkHttpUtils.BASE_URL + "/appAccount/changePassword",
                new String[]{"oldPwd", "pwd","userCode"}, new String[]{oldPwd, pwd, AppConstant.userCode}, new OkHttpUtils.HttpCallBack() {
                    @Override
                    public void onSuccess(String obj) {
                        try {
                            JSONObject jObject = new JSONObject(obj);
                            if (0 == jObject.getInt("code")) {
                                LogUtil.e("Forget", "注册成功");
                                resultListener.onSuccess(obj);
                            } else {
                                LogUtil.e("Forget", "注册失败");
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
                        LogUtil.e("Forget", "密码修改失败");
                        resultListener.onError(msg);
                    }
                });
    }
}