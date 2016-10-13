package com.beesightsoft.flexibleratingbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by MyPC on 9/27/2016.
 */

public class FlexibleRatingBar extends LinearLayout {

    LayoutInflater inflater;

    private float value = 0f;

    private int space;
    private int numStars;
    private float height;

    public FlexibleRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        getXmlAttrs(context, attrs);
        init();
    }


    public void init() {
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        setValue(value);
    }


    //Set any XML attributes that may have been specified
    private void getXmlAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FlexibleRatingBar, 0, 0);
        try {
            space = (int) a.getDimension(R.styleable.FlexibleRatingBar_space, -1);
            numStars = (int)a.getInt(R.styleable.FlexibleRatingBar_numStars, 6);
            value = a.getFloat(R.styleable.FlexibleRatingBar_value, 0.0f);
            height = a.getDimension(R.styleable.FlexibleRatingBar_height_view, 18.3f);
        } finally {
            a.recycle();
        }
    }


    public void setValue(float value) {
        ColorDrawable colorDrawable = (ColorDrawable) getBackground();
        removeAllViews();
        this.value = value;
        TypedArray colorsFrom = getResources().obtainTypedArray(R.array.colors_from);
        TypedArray colorsTo = getResources().obtainTypedArray(R.array.colors_to);
        for (int i = 0; i < numStars; i++) {
            StartView start = (StartView) inflater.inflate(R.layout.start_view_layout, null);
            start.setHeightView((int)height);
            try {
                start.setColorOutlineFrom(colorsFrom.getColor(i, 0));
                start.setColorOutlineTo(colorsTo.getColor(i, 0));
                start.setColorFillFrom(colorsFrom.getColor(i, 0));
                start.setColorFillTo(colorsTo.getColor(i, 0));
            }catch (Exception ex){
                start.setColorOutlineFrom(Color.BLACK);
                start.setColorOutlineTo(Color.WHITE);
                start.setColorFillFrom(Color.BLACK);
                start.setColorFillTo(Color.WHITE);
            }


            if (colorDrawable != null)
                start.setTransparentBackground(colorDrawable.getColor());

            if (this.value >= 0.5f) {
                start.setValue(0.5f);
            }
            if (this.value >= 1.0f) {
                start.setValue(1.0f);
            }
            addView(start);
            if (i < numStars - 1) {
                View view = new View(getContext());
                view.setLayoutParams(new ViewGroup.LayoutParams((int) start.convertDpToPixel(space), ViewGroup.LayoutParams.MATCH_PARENT));
                addView(view);
            }

            this.value -= 1;
        }
    }

}
