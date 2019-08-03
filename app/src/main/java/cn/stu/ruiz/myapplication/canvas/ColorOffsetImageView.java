package cn.stu.ruiz.myapplication.canvas;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import cn.stu.ruiz.myapplication.R;
import cn.stu.ruiz.myapplication.utils.SizeUtil;
/**
 * 关于颜色矩阵的使用
 * 调整颜色的饱和度（saturation）、色调（rotate）、亮度（scale）
 * */
public class ColorOffsetImageView extends View {

    private static final String TAG = "ColorOffsetImageView";


    Drawable drawable;

    public ColorOffsetImageView(Context context) {
        super(context);
    }


    public ColorOffsetImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ColorOffsetImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ColorOffsetImageView);
        initImage(typedArray);
        typedArray.recycle();
    }

    private void initImage(TypedArray typedArray) {
//        int type = typedArray.getType(R.styleable.ColorOffsetImageView_img);
        drawable = typedArray.getDrawable(R.styleable.ColorOffsetImageView_img);
        Log.e(TAG, "initImage: " + drawable.getClass().getSimpleName());
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = SizeUtil.getMeasureMode(widthMeasureSpec);
        int heightMode = SizeUtil.getMeasureMode(heightMeasureSpec);

        int widthSize = SizeUtil.getMeasureSize(widthMeasureSpec);
        int heightSize = SizeUtil.getMeasureSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            setMeasuredDimension(widthSize, heightSize);
        } else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.EXACTLY) {
            setMeasuredDimension(drawable.getIntrinsicWidth(), heightSize);
        } else if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, drawable.getIntrinsicHeight());
        } else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        } else {
            setMeasuredDimension(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
    }

    ColorMatrix mRotateColorMatrix = new ColorMatrix();

    ColorMatrix mSaturationColorMatrix = new ColorMatrix();

    ColorMatrix mScaleColorMatrix = new ColorMatrix();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawable.setBounds((getMeasuredWidth()-drawable.getIntrinsicWidth())/2,(getMeasuredHeight()-drawable.getIntrinsicHeight())/2,(getMeasuredWidth()+drawable.getIntrinsicWidth())/2,(getMeasuredHeight()+drawable.getIntrinsicWidth())/2);
        if (drawable instanceof BitmapDrawable) {
            Paint paint = ((BitmapDrawable) drawable).getPaint();
            ColorMatrix colorMatrix = new ColorMatrix();
            colorMatrix.postConcat(mRotateColorMatrix);
            colorMatrix.postConcat(mSaturationColorMatrix);
            colorMatrix.postConcat(mScaleColorMatrix);
            paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
            drawable.draw(canvas);
        }else {
            drawable.draw(canvas);
        }
    }

    /**
     * 修改色调,即色彩矩阵围绕某种颜色分量旋转
     */
    public void setRotateColor(float degrees) {
        mRotateColorMatrix.setRotate(0, degrees);
//        mRotateColorMatrix.setRotate(1, degrees);
//        mRotateColorMatrix.setRotate(2, degrees);
        postInvalidate();
    }

    /**
     * 饱和度
     * */
    public void setSaturation(float sat){
        mSaturationColorMatrix.setSaturation(sat);
        postInvalidate();
    }



    public void setScale(float scale){
        mScaleColorMatrix.setScale(scale,scale,scale,1);
        postInvalidate();
    }

}
