package com.example.graduation.yallamana;

/**
 * Created by m_7el on 9/14/2017.
 */

import android.content.Context;
import android.content.Intent;

/**
 * Created by Sharif on 8/1/2017.
 */


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
