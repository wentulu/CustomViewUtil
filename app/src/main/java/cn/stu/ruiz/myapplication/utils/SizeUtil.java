package cn.stu.ruiz.myapplication.utils;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;

/**
 * 关于尺寸工具类
 * 测量时的模式与尺寸获取
 *
 * sp、dp转换pixel
 * pixel转换dp、sp
 * */
public class SizeUtil {

    /**
     * 获取测量宽高时的测量模式
     * @param measureSpec 测量的Sepc数据一般来源于onMeasure方法
     * */
    public static int getMeasureMode(int measureSpec){
        return View.MeasureSpec.getMode(measureSpec);
    }

    /**
     * 获取测量宽高时的尺寸
     * @param measureSpec 测量的Sepc数据一般来源于onMeasure方法
     * */
    public static int getMeasureSize(int measureSpec){
        return View.MeasureSpec.getSize(measureSpec);
    }

    /**
     *将dp转换为pixels
     * @param dp 需要转换的值
     * @param context 上下文
     * @return 转换后的像素值
     */
    public static int dip2Pixel(float dp, Context context){
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,context.getResources().getDisplayMetrics());
    }

    /**
     * 将sp转换为pixel
     * @param sp 需要转换的sp值
     * @param context 上下文
     * @return 转换后的像素值
     * */
    public static int sp2Pixel(float sp, Context context){
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,sp,context.getResources().getDisplayMetrics());
    }

}
