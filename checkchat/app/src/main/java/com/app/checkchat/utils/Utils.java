package com.app.checkchat.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by MyPC on 14/8/2016.
 */
public class Utils {

    private static Context context;

    public static void init(Context context){
        Utils.context = context;
    }

    public static float convertSpToPixel(float sp) {
        Resources resources = context.getResources();
        float scaledDensity = resources.getDisplayMetrics().scaledDensity;
        return sp * scaledDensity;
    }


    public static float convertPixelsToSp(float px) {
        Resources resources = context.getResources();
        float scaledDensity = resources.getDisplayMetrics().scaledDensity;
        return px/scaledDensity;
    }



    public static float convertDpToPixel(float dp){
        if (context == null)
            throw new RuntimeException("Context is null, init(Context)");
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    public static float convertPixelsToDp(float px){
        if (context == null)
            throw new RuntimeException("Context is null, init(Context)");
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }


    public static void showKeyBoard(View view) {
        if (context == null)
            throw new RuntimeException("Context is null, init(Context)");
        InputMethodManager mgr = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, 0);

    }

    public static void hideKeyBoard(View view) {
        if (context == null)
            throw new RuntimeException("Context is null, init(Context)");
        InputMethodManager mgr = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void hideKeyBoard(Activity ctx) {
        if (context == null)
            throw new RuntimeException("Context is null, init(Context)");
        View viewFocus = ctx.getCurrentFocus();
        if (viewFocus != null) {
            InputMethodManager mgr = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            mgr.hideSoftInputFromWindow(viewFocus.getWindowToken(), 0);
        }
    }


    public static void showKeyboardInDialog(Dialog dialog, EditText target) {
        if (dialog == null || target == null) {
            return;
        }
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        target.requestFocus();
    }

}
