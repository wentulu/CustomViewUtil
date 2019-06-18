package cn.stu.ruiz.myapplication.utils;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Field;

public class DisplayScreenUtil {

    private static final String TAG = "DisplayScreenUtil";
    
    /**
     * 获取屏幕高度
     * @return 屏幕的高度像素点
     * */
    public static int getScreenHeightPixels(Context context){
        return context.getResources().getDisplayMetrics().heightPixels;
    }


    /**
     * 获取屏幕宽度
     * @return 屏幕的宽度像素点
     * */
    public static int getScreenWidthPixel(Context context){
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取状态栏的高度
     * */
    public static int getStateBarHeight(Context context){
        try {
            Class clz = Class.forName("com.android.internal.R$dimen");
            Field field = clz.getField("status_bar_height");
            Object object = field.get(null);
            int statusBarHeight = context.getResources().getDimensionPixelSize(Integer.parseInt(object.toString()));
            return statusBarHeight;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {

        }
        return 0;
    }

}
