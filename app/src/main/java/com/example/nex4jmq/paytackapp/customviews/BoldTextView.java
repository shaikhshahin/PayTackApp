package com.example.nex4jmq.paytackapp.customviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.nex4jmq.paytackapp.R;

@SuppressLint("AppCompatCustomView")
public class BoldTextView extends android.support.v7.widget.AppCompatTextView{

    public BoldTextView(Context context) {
        super(context);
        setTypeFace(context);
    }

    public BoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeFace(context);
    }

    public BoldTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setTypeFace(context);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
    private void setTypeFace(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.setTypeface(getResources().getFont(R.font.nunito_semibold));
        }
    }
    private void setFontStyle(Typeface font)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setFontStyle(getResources().getFont(R.font.bold_font));
        }
    }
}

