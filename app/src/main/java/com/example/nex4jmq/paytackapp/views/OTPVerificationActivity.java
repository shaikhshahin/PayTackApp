package com.example.nex4jmq.paytackapp.views;

import android.app.Activity;
import android.app.Service;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nex4jmq.paytackapp.R;
import com.example.nex4jmq.paytackapp.networkcall.PayTackIResult;
import com.example.nex4jmq.paytackapp.networkcall.PayTackNetworkCall;
import com.example.nex4jmq.paytackapp.utility.AppConstant;
import com.example.nex4jmq.paytackapp.utility.Utility;

public class OTPVerificationActivity extends Activity implements View.OnClickListener, View.OnFocusChangeListener {


    private EditText mOtpFirstDigitEditText;
    private EditText mOtpSecondDigitEditText;
    private EditText mOtpThirdDigitEditText;
    private EditText mOtpForthDigitEditText;
    private EditText mPinHiddenEditText;
    private EditText tvMobileNumber;
    private String mobNum = "";
    private String contryCode = "";
    private String UID = "";
    private String OTP = "";
    private PayTackIResult mCallback;
    private PayTackNetworkCall mNetworkService;
    private ImageView imArrow;

    EditText[] editTv;


    public void afterTextChanged(Editable s) {
    }


    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    /**
     * Hides soft keyboard.
     *
     * @param editText EditText which has focus
     */
    public void hideSoftKeyboard(EditText editText) {
        if (editText == null)
            return;

        InputMethodManager imm = (InputMethodManager) getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    /**
     * Initialize EditText fields.
     */
    private void init() {
        mOtpFirstDigitEditText = (EditText) findViewById(R.id.otp_first_edittext);
        mOtpSecondDigitEditText = (EditText) findViewById(R.id.otp_second_edittext);
        mOtpThirdDigitEditText = (EditText) findViewById(R.id.otp_third_edittext);
        mOtpForthDigitEditText = (EditText) findViewById(R.id.otp_forth_edittext);
        mPinHiddenEditText = (EditText) findViewById(R.id.pin_hidden_edittext);

        tvMobileNumber = (EditText) findViewById(R.id.tvmobileNumber);

        imArrow = (ImageView) findViewById(R.id.imArrow);
        imArrow.setOnClickListener(this);


    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_verify_activity);

        init();
        setPINListeners();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mobNum = bundle.getString(AppConstant.SIGN_MOBILE);
            UID = bundle.getString(AppConstant.UID);
            OTP = bundle.getString(AppConstant.OTP);
            contryCode = bundle.getString(AppConstant.SIGN_CC);

            tvMobileNumber.setText("+" + String.valueOf(contryCode) + " " + String.valueOf(mobNum));
            Log.e("OTP IS", OTP);

        }

        editTv = new EditText[4];

        editTv[0] = (EditText) findViewById(R.id.otp_first_edittext);
        editTv[1] = (EditText) findViewById(R.id.otp_second_edittext);
        editTv[2] = (EditText) findViewById(R.id.otp_third_edittext);
        editTv[3] = (EditText) findViewById(R.id.otp_forth_edittext);
        if (OTP != null) {
            for (int i = 0; i < OTP.length(); i++) {
                //String otp = String.valueOf(OTP.charAt(i));
                editTv[i].setText(String.valueOf(OTP.charAt(i)));
            }
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imArrow:
                if (Utility.isNetworkAvailable(this)) {
                    mNetworkService = new PayTackNetworkCall(mCallback, this);
                    mNetworkService.getOTPVerify(UID, OTP);
                } else {
                    Utility.showAlertMessageWithPositiveButton(this, getString(R.string.no_internet_connection), getString(R.string.alert_title));
                }
                break;
        }

    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        final int id = v.getId();
        switch (id) {
            case R.id.otp_first_edittext:
                if (hasFocus) {
                    setFocus(mPinHiddenEditText);
                    showSoftKeyboard(mPinHiddenEditText);
                }
                break;

            case R.id.otp_second_edittext:
                if (hasFocus) {
                    setFocus(mPinHiddenEditText);
                    showSoftKeyboard(mPinHiddenEditText);
                }
                break;

            case R.id.otp_third_edittext:
                if (hasFocus) {
                    setFocus(mPinHiddenEditText);
                    showSoftKeyboard(mPinHiddenEditText);
                }
                break;

            case R.id.otp_forth_edittext:
                if (hasFocus) {
                    setFocus(mPinHiddenEditText);
                    showSoftKeyboard(mPinHiddenEditText);
                }
                break;


            default:
                break;
        }
    }

    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            final int id = v.getId();
            switch (id) {
                case R.id.pin_hidden_edittext:
                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        if (mPinHiddenEditText.getText().length() == 4)
                            mOtpForthDigitEditText.setText("");
                        else if (mPinHiddenEditText.getText().length() == 3)
                            mOtpThirdDigitEditText.setText("");
                        else if (mPinHiddenEditText.getText().length() == 2)
                            mOtpSecondDigitEditText.setText("");
                        else if (mPinHiddenEditText.getText().length() == 1)
                            mOtpFirstDigitEditText.setText("");


                        if (mPinHiddenEditText.length() > 0)
                            mPinHiddenEditText.setText(mPinHiddenEditText.getText().subSequence(0, mPinHiddenEditText.length() - 1));

                        return true;
                    }

                    break;

                default:
                    return false;
            }
        }

        return false;
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {


        if (s.length() == 0) {
            mOtpFirstDigitEditText.setText("");
        } else if (s.length() == 1) {
            mOtpFirstDigitEditText.setText(s.charAt(0) + "");
            mOtpSecondDigitEditText.setText("");
            mOtpThirdDigitEditText.setText("");
            mOtpForthDigitEditText.setText("");
        } else if (s.length() == 2) {
            mOtpSecondDigitEditText.setText(s.charAt(1) + "");
            mOtpThirdDigitEditText.setText("");
            mOtpForthDigitEditText.setText("");
        } else if (s.length() == 3) {
            mOtpThirdDigitEditText.setText(s.charAt(2) + "");
            mOtpForthDigitEditText.setText("");
        }


    }


    /**
     * Sets default PIN background.
     *
     * @param editText edit text to change
     */


    /**
     * Sets focus on a specific EditText field.
     *
     * @param editText EditText to set focus on
     */
    public static void setFocus(EditText editText) {
        if (editText == null)
            return;

        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
    }

    /**
     * Sets focused PIN field background.
     *
     * @param editText edit text to change
     */


    /**
     * Sets listeners for EditText fields.
     */
    private void setPINListeners() {

        mOtpFirstDigitEditText.setOnFocusChangeListener(OTPVerificationActivity.this);
        mOtpSecondDigitEditText.setOnFocusChangeListener(OTPVerificationActivity.this);
        mOtpThirdDigitEditText.setOnFocusChangeListener(OTPVerificationActivity.this);
        mOtpForthDigitEditText.setOnFocusChangeListener(OTPVerificationActivity.this);


    }

    public void showSoftKeyboard(EditText editText) {
        if (editText == null)
            return;

        InputMethodManager imm = (InputMethodManager) getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, 0);
    }
}

