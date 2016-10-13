package com.app.checkchat.fragments;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
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
import com.app.checkchat.models.User;
import com.app.checkchat.widgets.RotateLoading;
import com.beesightsoft.core.permissions.FragmentManagePermission;
import com.beesightsoft.core.services.application.ApplicationServiceInterface;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EFragment;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by MyPC on 14/8/2016.
 */
@EFragment
public abstract class BaseFragment extends FragmentManagePermission  {

    @App
    protected CustomApplication application;

    @Inject
    ApplicationServiceInterface applicationService;

    private int index;

    public CustomApplication getApplication() {
        return application;
    }

    public void setApplication(CustomApplication application) {
        this.application = application;
    }

    @AfterViews
    public abstract void init();



    public  BaseFragment getNextFragment(){return null;}

    public  void setNextFragment(BaseFragment nextFragment){}
    private Dialog progressDialogLoading;
    public void showHUD(String... text) {
        if (progressDialogLoading != null)
            progressDialogLoading.dismiss();
        View view = getActivity().getLayoutInflater().inflate(R.layout.layout_loading, null);
        RotateLoading rotateloading = (RotateLoading) view.findViewById(R.id.layout_loading_rotate);
        rotateloading.start();
        TextView tvContent = (TextView)view.findViewById(R.id.layout_loading_tv_content);
        tvContent.setText((text != null && text.length > 0) ? text[0] : getString(R.string.dialog_loading_text));
        progressDialogLoading = new Dialog(getActivity());
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

    protected void hideSoftKeyboard() {
        try {
            if(getActivity().getCurrentFocus()!=null) {
                InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception e) {
        }
    }

    public void setupUI(View view) {

        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard();
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public int convertToPx(float dp){
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        float width = size.x;
        final float scale = (float)width/360;
        return  (int) (dp * width/360 );

    }
    public void showSnackbar(View view,String string)
    {
        Snackbar snack = Snackbar.make(view, string, Snackbar.LENGTH_LONG);
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
