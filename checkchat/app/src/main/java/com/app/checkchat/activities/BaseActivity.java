package com.app.checkchat.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.app.checkchat.CustomApplication;
import com.app.checkchat.R;
import com.app.checkchat.widgets.RotateLoading;
import com.beesightsoft.core.permissions.ActivityManagePermission;
import com.beesightsoft.core.services.application.ApplicationServiceInterface;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EActivity;

import java.util.List;

import javax.inject.Inject;


import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by MyPC on 14/8/2016.
 */
@EActivity
public abstract class BaseActivity extends ActivityManagePermission {
    protected Handler uiHandler = new Handler();

    @App
    CustomApplication application;

    @Inject
    ApplicationServiceInterface applicationService;

    @AfterViews
    public abstract void init();


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private Dialog progressDialogLoading;

    public void showHUD(String... text) {
        if (progressDialogLoading != null)
            progressDialogLoading.dismiss();
        View view = getLayoutInflater().inflate(R.layout.layout_loading, null);
        RotateLoading rotateloading = (RotateLoading) view.findViewById(R.id.layout_loading_rotate);
        rotateloading.start();
        TextView tvContent = (TextView) view.findViewById(R.id.layout_loading_tv_content);
        tvContent.setText((text != null && text.length > 0) ? text[0] : getString(R.string.dialog_loading_text));
        progressDialogLoading = new Dialog(this);
        progressDialogLoading.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialogLoading.setContentView(view);
        progressDialogLoading.setCancelable(false);
        progressDialogLoading.setCanceledOnTouchOutside(false);
        progressDialogLoading.getWindow().setBackgroundDrawableResource(R.drawable.bg_layout_loading);
        progressDialogLoading.show();
    }

    public void hideHUD() {
        if (progressDialogLoading != null && progressDialogLoading.isShowing())
            progressDialogLoading.dismiss();
    }



    public static void hideSoftKeyboard(Activity mActivity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) mActivity
                    .getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(mActivity
                    .getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
        }
    }

    public void setupUI(View view) {

        if (!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(BaseActivity.this);
                    return false;
                }

            });
        }

        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupUI(innerView);
            }
        }
    }

    public int convertToPx(float dp) {
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        float width = size.x;
        final float scale = (float) width / 360;
        return (int) (dp * width / 360);

    }

    public void showSnackbar(String string)
    {
        Snackbar snack = Snackbar.make(getWindow().getDecorView().getRootView(), string, Snackbar.LENGTH_LONG);
        snack.show();
        View snackView = snack.getView();
        snackView.setBackgroundResource(R.drawable.bg_snackbar);
        TextView tv = (TextView) snackView.findViewById(android.support.design.R.id.snackbar_text);
        FrameLayout.LayoutParams params =new FrameLayout.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if(tv!=null) {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
        }
        params.gravity = Gravity.TOP;
        snackView.setLayoutParams(params);
    }
}
