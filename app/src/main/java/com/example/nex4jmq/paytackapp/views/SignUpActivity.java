package com.example.nex4jmq.paytackapp.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.nex4jmq.paytackapp.R;
import com.example.nex4jmq.paytackapp.networkcall.PayTackIResult;
import com.example.nex4jmq.paytackapp.networkcall.PayTackNetworkCall;
import com.example.nex4jmq.paytackapp.utility.AppConstant;
import com.example.nex4jmq.paytackapp.utility.Utility;
import com.example.nex4jmq.paytackapp.viewmodels.AppPreference;
import com.hbb20.CountryCodePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends Activity implements View.OnClickListener {


    private LinearLayout llContinue;
    private EditText name;
    private EditText email;
    private EditText mobileNum;
    private EditText password;
    private EditText confPsw;
    private CheckBox agreeCheck;
    private String strName = "";
    private String strEmail = "";
    private String strMobileNum = "";
    private String strPsw = "";
    private String strConfPsw = "";
    private String strCountry = "";
    private String strCC = "";
    private String strCName = "";
    private PayTackIResult mCallback;
    private PayTackNetworkCall mNetworkService;
    CountryCodePicker ccp;
    private String OTP = "";
    private String UID = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);

        initView();
        setListener();

        initCallBack();


    }

    private void initView() {
        name = (EditText) findViewById(R.id.tvName);
        mobileNum = (EditText) findViewById(R.id.tvMobileNumber);
        email = (EditText) findViewById(R.id.tvEmail);
        password = (EditText) findViewById(R.id.tvPassword);
        confPsw = (EditText) findViewById(R.id.tvConfirmPassword);

        llContinue = (LinearLayout) findViewById(R.id.llContinue);

        agreeCheck = (CheckBox) findViewById(R.id.chbAgreementAceeptance);
        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        strCC = ccp.getSelectedCountryCode();
        strCountry = ccp.getSelectedCountryName();


    }

    void initCallBack() {
        mCallback = new PayTackIResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {

                try {
                    Log.e("SIGNUP SUCCESSFUL", String.valueOf(response));
                    if (response != null) {

                        OTP = response.getString(AppConstant.OTP);
                        UID = response.getString(AppConstant.UID);
                        String success = response.getString(AppConstant.SUCCESS);
                        if (OTP != null) {

                            AppPreference.saveOTP(SignUpActivity.this, OTP);
                            AppPreference.saveUID(SignUpActivity.this, UID);
                            Intent otpVerify = new Intent(SignUpActivity.this, OTPVerificationActivity.class);
                            otpVerify.putExtra(AppConstant.OTP, OTP);
                            otpVerify.putExtra(AppConstant.UID, UID);
                            otpVerify.putExtra(AppConstant.SIGN_MOBILE, strMobileNum);
                            otpVerify.putExtra(AppConstant.SIGN_CC, strCC);
                            startActivity(otpVerify);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void notifyError(String requestType, VolleyError error) {

                Log.e("COUNTRY UNSUCCESSFUL", String.valueOf(error));

            }

            @Override
            public void notifyError(String requestType, String error) {

            }

            @Override
            public void notifyJSONArraySuccess(String requestType, JSONArray response) {

            }

            @Override
            public void notifySuccess(String requestType, String response) {

            }
        };
    }

    private void validate() {
        strName = name.getText().toString().trim();
        strMobileNum = mobileNum.getText().toString().trim();
        strEmail = email.getText().toString().trim();
        strPsw = password.getText().toString().trim();
        strConfPsw = confPsw.getText().toString().trim();
        if (TextUtils.isEmpty(strName)) {
            Utility.showAlertMessageWithPositiveButton(this, getString(R.string.all_mandatory), getString(R.string.alert_title));

        } else if (TextUtils.isEmpty(strMobileNum)) {
            // mobileNum.setBackgroundResource(R.drawable.ups_option_txt_bg_on_error);
            Utility.showAlertMessageWithPositiveButton(this, getString(R.string.all_mandatory), getString(R.string.alert_title));

        } else if (!strEmail.isEmpty()) {
            if (!Utility.isValidEmail(strEmail)) {
                Utility.showAlertMessageWithPositiveButton(this, getString(R.string.alert_email), getString(R.string.alert_title));

            }
        } else if (!agreeCheck.isChecked()) {
            Utility.showAlertMessageWithPositiveButton(this, getString(R.string.accept_user_agreement), getString(R.string.alert_title));

        } else {
            if (strCountry.equals("IN")) {
                if (strMobileNum.length() <= 9) {
                    Utility.showAlertMessageWithPositiveButton(this, getString(R.string.phone_error), getString(R.string.alert_title));
                    return;
                }
            } else if (password.getText().toString().equals(confPsw.getText().toString())) {
                confPsw.setVisibility(View.VISIBLE);
                confPsw.setText(this.getString(R.string.password_error));
            } else if (password.getText().toString().length() <= 6) {
                password.setVisibility(View.VISIBLE);
                password.setText(this.getString(R.string.password_error));
            } else if (!strCountry.equals("IN")) {
                if (strMobileNum.length() <= 12) {
                    Utility.showAlertMessageWithPositiveButton(this, getString(R.string.phone_error), getString(R.string.alert_title));
                    return;
                }
            }
        }
    }


    private void setListener() {

        llContinue.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.llContinue:
                if (Utility.isNetworkAvailable(this)) {
                    validate();
                    mNetworkService = new PayTackNetworkCall(mCallback, this);
                    mNetworkService.SignUp(strName, strEmail, strCC, strCountry, strMobileNum, strPsw);
                } else {
                    Utility.showAlertMessageWithPositiveButton(this, getString(R.string.no_internet_connection), getString(R.string.alert_title));
                }
                break;
        }
    }


}
