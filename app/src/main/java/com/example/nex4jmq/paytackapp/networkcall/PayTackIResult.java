package com.example.nex4jmq.paytackapp.networkcall;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

public interface PayTackIResult {

    public void notifySuccess(String requestType, JSONObject response);
    public void notifyError(String requestType, VolleyError error);
    public void notifyError(String requestType, String error);
    public void notifyJSONArraySuccess(String requestType, JSONArray response);
    public void notifySuccess(String requestType, String response);



}
