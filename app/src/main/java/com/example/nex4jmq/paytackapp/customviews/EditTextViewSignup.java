package com.example.nex4jmq.paytackapp.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.EditText;

import com.example.nex4jmq.paytackapp.R;
import com.example.nex4jmq.paytackapp.utility.TypefaceUtil;

public class EditTextViewSignup extends android.support.v7.widget.AppCompatEditText {
    public EditTextViewSignup(Context context) {
        super(context);
    }

    public EditTextViewSignup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextViewSignup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void setTypeFace(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.setTypeface(getResources().getFont(R.font.nunito_semibold));
        } else {
            TypefaceUtil.overrideFont(context, "NUNITO", "font/nunito_regular.ttf");

        }

    }

}
