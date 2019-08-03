package cn.stu.ruiz.myapplication.canvas;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import cn.stu.ruiz.myapplication.R;
import cn.stu.ruiz.myapplication.utils.SizeUtil;

/**
 * 旗帜飘扬的感觉
 */
public class FlagView extends View {

    private int DEFAULT_AMPLITUDE = SizeUtil.dip2Pixel(10, getContext());


    //    private Drawable mDrawable;
    private int mAmplitude;

    private int bitMapWidth, bitMapHeight;
    private Bitmap mBitmap;

    private final int MESHWIDTH = 20, MESHHEIGHT = 20;

    int[] origns = new int[20 * 20 * 2];
    int[] verts = new int[20 * 20 * 2];

    public FlagView(Context context) {
        super(context);
        initt(context);
    }


    public FlagView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    public FlagView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initt(Context context) {

    }

    private void init(Context context, AttributeSet attrs) {
        this.initt(context);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FlagView);
        Drawable drawable = typedArray.getDrawable(R.styleable.FlagView_img);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        mBitmap = bitmapDrawable.getBitmap();
        bitMapWidth = mBitmap.getWidth();
        bitMapHeight = mBitmap.getHeight();
        DEFAULT_AMPLITUDE = bitMapHeight / 3;
        mAmplitude = typedArray.getDimensionPixelSize(R.styleable.FlagView_amplitude, DEFAULT_AMPLITUDE);
        typedArray.recycle();
        initpocs();
    }

    private void initpocs() {
        int perwidthPx = mBitmap.getWidth()/MESHWIDTH;
        int perheightPx = mBitmap.getHeight()/MESHHEIGHT;
        for (int indexW = 0; indexW < MESHWIDTH; indexW++) {
            for (int indexH = 0; indexH < MESHHEIGHT; indexH++) {
                
            }

        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = SizeUtil.getMeasureMode(widthMeasureSpec);
        int widthSize = SizeUtil.getMeasureSize(widthMeasureSpec);

        int heightMode = SizeUtil.getMeasureMode(heightMeasureSpec);
        int heightSize = SizeUtil.getMeasureSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY || heightMode == MeasureSpec.EXACTLY) {
            if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
                matrixBitMapFor2Ex(widthSize, heightSize);
                setMeasuredDimension(widthSize, heightSize);
            } else if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.AT_MOST) {
                float widthRation = (float) widthSize / bitMapWidth;
                Matrix matrix = new Matrix();
                matrix.postScale(widthRation, widthRation);
                mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, bitMapWidth, bitMapHeight, matrix, false);
                bitMapWidth = mBitmap.getWidth();
                bitMapHeight = mBitmap.getHeight();
                mAmplitude = bitMapHeight / 3;
                setMeasuredDimension(widthSize, bitMapHeight + 2 * mAmplitude);
            } else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.EXACTLY) {
                if (heightSize < bitMapHeight + 2 * mAmplitude) {
                    float heightRatio = (heightSize - 2f * mAmplitude) / bitMapHeight;
                    Matrix matrix = new Matrix();
                    matrix.postScale(heightRatio, heightRatio);
                    mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, bitMapWidth, bitMapHeight, matrix, false);
                    bitMapWidth = mBitmap.getWidth();
                    bitMapHeight = mBitmap.getHeight();
                    mAmplitude = bitMapHeight / 3;

                }
                setMeasuredDimension(bitMapWidth, heightSize);
            }
        } else {
            setMeasuredDimension(bitMapWidth, bitMapHeight + 2 * mAmplitude);
        }

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        flagwave();
    }

    private void flagwave() {


    }

    private void matrixBitMapFor2Ex(int widthSize, int heightSize) {
        if (bitMapHeight + 2 * mAmplitude <= heightSize && bitMapWidth <= widthSize) {
        } else {
            float heightRatio = (heightSize - 2f * mAmplitude) / bitMapHeight;
            float widthRation = (float) widthSize / bitMapWidth;
            Matrix matrix = new Matrix();
            if (heightRatio < widthRation) {
                matrix.postScale(heightRatio, heightRatio);
                mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, bitMapWidth, bitMapHeight, matrix, false);
                bitMapWidth = mBitmap.getWidth();
                bitMapHeight = mBitmap.getHeight();
                mAmplitude = bitMapHeight / 3;
            } else {
                matrix.postScale(widthRation, widthRation);
                mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, bitMapWidth, bitMapHeight, matrix, false);
                bitMapWidth = mBitmap.getWidth();
                bitMapHeight = mBitmap.getHeight();
                mAmplitude = bitMapHeight / 3;
            }
        }
    }
}
