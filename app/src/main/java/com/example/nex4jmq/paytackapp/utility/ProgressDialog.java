package com.example.nex4jmq.paytackapp.utility;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.nex4jmq.paytackapp.R;

public class ProgressDialog {
    AlertDialog alertDialog;

    public void showCustomDialog(Context context) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        final View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_progress, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        alertDialog = dialogBuilder.create();
        alertDialog.show();
        alertDialog.getWindow().setLayout(360, 160); //Controlling width and height.


    }

    public void hideDialog() {
        if (alertDialog != null) {
            if (alertDialog.isShowing()) {
                alertDialog.dismiss();
            }
        }

    }
}
