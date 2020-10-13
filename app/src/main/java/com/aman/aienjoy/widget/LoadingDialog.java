package com.aman.aienjoy.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.aman.aienjoy.R;

/**
 * Created by kiros on 2019/7/24.
 */

public class LoadingDialog extends Dialog {
    String message = null;
    private Dialog dialog;
    private Activity context;

    public LoadingDialog(Context context) {
        super(context);
        this.context = (Activity) context;
        init();
    }

    private void init() {
        dialog = new Dialog(context, R.style.Loading);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_loading, null, false);
        dialog.setContentView(view);
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        if (lp != null) {
            lp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setAttributes(lp);
        }
    }

    public LoadingDialog(Context context, String message) {
        super(context);
        this.context = (Activity) context;
        this.message = message;
        init();
    }

    public void show() {
        if (dialog != null && !context.isFinishing()) {
                dialog.show();
        }
    }

    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
