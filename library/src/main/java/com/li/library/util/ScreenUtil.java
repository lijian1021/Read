package com.li.library.util;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;

/**
 * Created by Li on 2017/3/2.
 * get info about screen
 */

public class ScreenUtil {

    /**
     * getScreenSize
     * @param context   context
     * @return  point.x:screenWidth;point.y:screenHeight
     */
    public static Point getScreenSize(Context context){
        DisplayMetrics displayMetrics=context.getApplicationContext().getResources().getDisplayMetrics();
        Point point=new Point(displayMetrics.widthPixels,displayMetrics.heightPixels);
        return point;
    }
}
