package com.omnify.hackernews.hackernews;

import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;

public class ProgressDialog extends android.app.ProgressDialog {

    public ProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    private static ProgressDialog progressDialog;

    private static ProgressDialog getSingleton(Context context) {
        if (progressDialog != null) {
            return progressDialog;
        } else {
            progressDialog = new ProgressDialog(context, R.style.ProgressDialog);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(true);
            WindowManager.LayoutParams wlmp = progressDialog.getWindow().getAttributes();
            wlmp.gravity = Gravity.CENTER_HORIZONTAL;
            progressDialog.getWindow().setAttributes(wlmp);
            progressDialog.setInverseBackgroundForced(true);
            return progressDialog;
        }
    }

    public static void setProgressVisible(Context context, boolean visible) {
        try {

            progressDialog = getSingleton(context);
            if (visible) {
                progressDialog.show();
            } else {
                progressDialog.hide();
                progressDialog.dismiss();
                progressDialog = null;
            }
        } catch (Exception e) {

        }
    }

    public static void show(Context context) {
        setProgressVisible(context, true);
    }

    public static void hide(Context context) {
        setProgressVisible(context, false);
    }
}
