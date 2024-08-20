package com.example.wait4it.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.wait4it.UI.LoginActivity;
import com.example.wait4it.Webview.NewsMainPage;

public class AuthUtil {
    private static final String SHARED_PREFS_NAME = "my_shared_prefs";
    private static final String JWT_KEY = "jwt_token";
    private static final String USERNAME = "username";

    public static void saveJwtToken(Context context, String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(JWT_KEY, token);
        editor.apply();
    }

    public static void saveUsername(Context context, String username) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME, username);
        editor.apply();
    }

    public static String getJwtToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(JWT_KEY, null);
    }

    public static String getUsername(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USERNAME, null);
    }
}
