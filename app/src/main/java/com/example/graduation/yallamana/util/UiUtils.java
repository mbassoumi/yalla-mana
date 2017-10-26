package com.example.graduation.yallamana.util;

/**
 * Created by Mais

 */
import android.view.View;



public class UiUtils {

    public static void setVisibility(int visibility, View... views) {
        for (View view : views) {
            view.setVisibility(visibility);
        }
    }
    public static void setInVisibility(int invisibility, View... views) {
        for (View view : views) {
            view.setVisibility(invisibility);
        }
    }
}
