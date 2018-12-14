package com.example.nex4jmq.paytackapp.utility;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TypefaceSpan;

import com.example.nex4jmq.paytackapp.R;

public class Utility {

    public static void showAlertMessageWithPositiveButton(Context context
            , String message ,String title) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);

        try{
            SpannableString sTitle = new SpannableString(title);
            sTitle.setSpan(new com.example.nex4jmq.paytackapp.customviews.TypefaceSpan(context), 0, sTitle.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setTitle(sTitle);

            SpannableString sMessage = new SpannableString(message);
            sMessage.setSpan(new com.example.nex4jmq.paytackapp.customviews.TypefaceSpan(context), 0, sMessage.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            builder.setMessage(sMessage);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        builder.setCancelable(false);

        builder.setPositiveButton(context.getString(R.string.alert_ok_button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        if(alertDialog.isShowing()){
            alertDialog.dismiss();
        }

        alertDialog.show();
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public final static Typeface addFont(Context context){
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),  "font/nunito_regular.ttf");
        return typeface;
    }
    public static boolean isNetworkAvailable(Context context) {
        if(context != null){
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
        }
        return true;
    }


}
