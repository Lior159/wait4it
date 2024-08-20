package com.example.wait4it.Utilities;


import android.util.Log;

import androidx.annotation.NonNull;

import com.example.wait4it.Interfaces.HttpCallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {
    private static OkHttpClient client = new OkHttpClient();

    public static void get(String url, HttpCallback callback) {
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                if (callback != null) {
                    callback.onFailure(e);
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (callback != null && response.body() != null) {
                    callback.onSuccess(response);
                }
            }
        });
    }

    public static void signup(String email, String username, String password, HttpCallback callback) {
        String url = "http://192.168.7.7:8000/signup";
        RequestBody requestBody = new FormBody.Builder()
                .add("username", username)
                .add("email", email)
                .add("password", password)
                .build();

        Request request = new Request.Builder().url(url).post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                if (callback != null) {
                    callback.onFailure(e);
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (callback != null && response.body() != null) {
                    callback.onSuccess(response);
                }
            }
        });
    }

    public static void login(String userName, String password, HttpCallback callback) {
        String url = "http://192.168.7.7:8000/login";
        RequestBody requestBody = new FormBody.Builder()
                .add("username", userName)
                .add("password", password)
                .build();

        Request request = new Request.Builder().url(url).post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                if (callback != null) {
                    callback.onFailure(e);
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (callback != null && response.body() != null) {
                    callback.onSuccess(response);
                }
            }
        });
    }

    public static void validateToken(String userName, String jwtToken, HttpCallback callback) {
        String url = "http://192.168.7.7:8000/validate";
        RequestBody requestBody = new FormBody.Builder()
                .add("username", userName)
                .add("jwtToken", jwtToken)
                .build();

        Request request = new Request.Builder().url(url).post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                if (callback != null) {
                    callback.onFailure(e);
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (callback != null && response.body() != null) {
                    callback.onSuccess(response);
                }
            }
        });
    }
}