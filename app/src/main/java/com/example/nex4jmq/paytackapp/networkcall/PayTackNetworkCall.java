package com.example.nex4jmq.paytackapp.networkcall;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nex4jmq.paytackapp.utility.AppConstant;
import com.example.nex4jmq.paytackapp.utility.ProgressDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class PayTackNetworkCall {

    PayTackIResult mResultCallback = null;
    Context mContext;
    private ProgressDialog customProgress = new ProgressDialog();


    public PayTackNetworkCall(PayTackIResult resultCallback, Context context) {
        mResultCallback = resultCallback;
        mContext = context;
    }

    private void showProgress() {
        customProgress.showCustomDialog(mContext);
    }

    private void hideProgress() {
        customProgress.hideDialog();
    }


    public void getCountry() {

        showProgress();


        try {

            RequestQueue queue = Volley.newRequestQueue(mContext);
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                    AppConstant.GET_COUNTRY, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            if (mResultCallback != null) {

                                mResultCallback.notifySuccess("GET", response);
                                hideProgress();
                                Log.e("RESPONSE COUNTRY", String.valueOf(response));
                            }


                        }

                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Failure Callback
                            String json = null;
                            // Dismiss Progress Dialog
                            // Display Error Message Dialog
                            NetworkResponse response = error.networkResponse;
                            if (response != null && response.data != null) {
                                if (mResultCallback != null) {

                                    json = new String(response.data);
                                    mResultCallback.notifyError("GET", json);
                                    Log.e("Error COUNTRY", "" + json);
                                }

                            }
                        }
                    }) {


                @Override
                public Map getHeaders() throws AuthFailureError {

                    Map<String, String> headers = new HashMap<>();
                    return headers;

                }


            };
// Adding the request to the queue along with a unique string tag
            queue.add(jsonObjReq);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void SignUp(String name, String email, String countryCode, String countryName, String mobNum, String pass) {

        showProgress();
        JSONObject postparams = new JSONObject();

        try {
            postparams.put(AppConstant.SIGN_NAME, name);
            postparams.put(AppConstant.SIGN_EMAIL, email);
            postparams.put(AppConstant.SIGN_MOBILE, mobNum);
            postparams.put(AppConstant.SIGN_MER, "no");
            postparams.put(AppConstant.SIGN_ISSOCIAL, false);
            postparams.put(AppConstant.SIGN_DEVICE, "D4574895734");
            postparams.put(AppConstant.SIGN_PASS, pass);
            postparams.put(AppConstant.SIGN_CC, countryCode);
            postparams.put(AppConstant.SIGN_COUNTRY, countryName);
            postparams.put(AppConstant.SIGN_IDTYPE, "0");
            postparams.put(AppConstant.SIGN_SID, "ankdu@gmail.com");
            postparams.put(AppConstant.SIGN_SOCAILTOKEN, "5436454578467");

            Log.e("POSTPRICE PARAMS", String.valueOf(postparams));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {

            RequestQueue queue = Volley.newRequestQueue(mContext);
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                    AppConstant.SIGN_UP_URL, postparams,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            if (mResultCallback != null) {

                                mResultCallback.notifySuccess("POST", response);
                                hideProgress();
                                Log.e("RESPONSE SIGN UP", String.valueOf(response));
                            }


                        }

                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Failure Callback
                            String json = null;
                            // Dismiss Progress Dialog
                            // Display Error Message Dialog
                            NetworkResponse response = error.networkResponse;
                            if (response != null && response.data != null) {
                                if (mResultCallback != null) {

                                    json = new String(response.data);
                                    mResultCallback.notifyError("POST", json);
                                    Log.e("Error SIGNUP", "" + json);
                                }

                            }
                        }
                    }) {


                @Override
                public Map getHeaders() throws AuthFailureError {

                    Map<String, String> headers = new HashMap<>();
                    return headers;

                }


            };
// Adding the request to the queue along with a unique string tag
            queue.add(jsonObjReq);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getOTPVerify(String UID,String OTP) {

        showProgress();
        JSONObject postparams = new JSONObject();

        try {
            postparams.put(AppConstant.UID, UID);
            postparams.put(AppConstant.OTP, Integer.parseInt(OTP));


            Log.e("POSTPRICE PARAMS", String.valueOf(postparams));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {

            RequestQueue queue = Volley.newRequestQueue(mContext);
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                    AppConstant.OTP_URL, postparams,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            if (mResultCallback != null) {

                                mResultCallback.notifySuccess("POST", response);
                                hideProgress();
                                Log.e("RESPONSE OTP", String.valueOf(response));
                            }


                        }

                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Failure Callback
                            String json = null;
                            // Dismiss Progress Dialog
                            // Display Error Message Dialog
                            NetworkResponse response = error.networkResponse;
                            if (response != null && response.data != null) {
                                if (mResultCallback != null) {

                                    json = new String(response.data);
                                    mResultCallback.notifyError("GET", json);
                                    Log.e("Error VERIFYOTP", "" + json);
                                }

                            }
                        }
                    }) {


                @Override
                public Map getHeaders() throws AuthFailureError {

                    Map<String, String> headers = new HashMap<>();
                    return headers;

                }


            };
// Adding the request to the queue along with a unique string tag
            queue.add(jsonObjReq);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}



