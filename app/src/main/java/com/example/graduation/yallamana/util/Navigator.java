package com.example.graduation.yallamana.util;

/**
 * Created by Mais

 */

import android.content.Context;
import android.content.Intent;

import com.example.graduation.yallamana.presenation.login.LoginActivity;
import com.example.graduation.yallamana.presenation.signup.SignupActivity;

public class Navigator {
    public static void navigateToHome(Context context)
    {
//        Intent intent = new Intent(context, HomeActivity.class);
//        context.startActivity(intent);
    }
    public static void navigateToLogin(Context context)
    {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
    public static void navigateTosignup(Context context)
    {
    Intent intent = new Intent(context, SignupActivity.class);
      context.startActivity(intent);
    }
    public static void navigateToPost(Context context)
    {
//        Intent intent = new Intent(context, PostsActivity.class);
//        context.startActivity(intent);

    }
}
