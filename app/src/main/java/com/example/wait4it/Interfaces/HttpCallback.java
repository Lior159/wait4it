package com.example.wait4it.Interfaces;

import java.io.IOException;

import okhttp3.Response;

public interface HttpCallback {
    void onSuccess(Response response);
    void onFailure(IOException e);
}