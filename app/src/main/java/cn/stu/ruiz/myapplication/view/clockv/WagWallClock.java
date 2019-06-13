package cn.stu.ruiz.myapplication.view.clockv;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import cn.stu.ruiz.myapplication.utils.SizeUtil;

public class WagWallClock extends View {

    private static final String TAG = "WagWallClock";

    /**
     * 控件默认的宽高
     * 单位 dp
     */
    private final float[] DEFAULT_SIZE = {300, 300};

    private final int[] SCALES_VALUE = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

    float centerX, centerY;
    int circleWidth = 4;
    int scaleLineLLenght = 40;
    int scaleLineSLenght = 25;

    private Paint circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint scaleLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public WagWallClock(Context context) {
        this(context, null);
    }

    public WagWallClock(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WagWallClock(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initiate();
    }

    private void initiate() {
        circlePaint.setStrokeWidth(circleWidth);
        circlePaint.setColor(Color.LTGRAY);
        circlePaint.setStyle(Paint.Style.STROKE);

        scaleLinePaint.setTextAlign(Paint.Align.CENTER);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = SizeUtil.getMeasureMode(widthMeasureSpec);
        int widthSize = SizeUtil.getMeasureSize(widthMeasureSpec);

        int heightMode = SizeUtil.getMeasureMode(heightMeasureSpec);
        int heightSize = SizeUtil.getMeasureSize(heightMeasureSpec);

        if (heightMode == MeasureSpec.EXACTLY && widthMode == MeasureSpec.EXACTLY) {
            setMeasuredDimension(widthSize, heightSize);
        } else if (heightMode == MeasureSpec.EXACTLY && widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(heightSize, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST && widthMode == MeasureSpec.EXACTLY) {
            setMeasuredDimension(widthSize, widthSize);
        } else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(SizeUtil.dip2Pixel(DEFAULT_SIZE[0], getContext()), SizeUtil.dip2Pixel(DEFAULT_SIZE[1], getContext()));
        }

        centerX = getMeasuredWidth() / 2f;
        centerY = getMeasuredHeight() / 2f;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
        drawScale(canvas);
        drawScaleValue(canvas);
    }

    private void drawScaleValue(Canvas canvas) {
        int left = 0, top = 0;
        int decent = 0, assent = 0;
        for (int scale : SCALES_VALUE) {
            int radius = getMeasuredHeight() / 2;
            if (scale == 12 || scale == 3 || scale == 6 || scale == 9) {
                scaleLinePaint.setTextSize(SizeUtil.sp2Pixel(20f, getContext()));
                decent = (int) scaleLinePaint.getFontMetrics().bottom;
                assent = (int) scaleLinePaint.getFontMetrics().top;
                radius -= (scaleLineLLenght + circleWidth + scaleLinePaint.getTextSize());
            } else {
                scaleLinePaint.setTextSize(SizeUtil.sp2Pixel(15f, getContext()));
                radius -= (scaleLineSLenght + circleWidth + scaleLinePaint.getTextSize());
                decent = (int) scaleLinePaint.getFontMetrics().bottom;
                assent = (int) scaleLinePaint.getFontMetrics().top;
            }
            float degrees = 30f * scale;
            if (degrees <= 90) {
                left = (int) (Math.sin(degrees / 180 * Math.PI) * radius) + (int) centerX;
                top = (int) centerY - (int) (Math.cos(degrees / 180 * Math.PI) * radius);
                if (degrees == 90) {
                    left += scaleLinePaint.getTextSize() / 2;
                    top += decent;
                }
            } else if (degrees <= 180) {
                degrees -= 90;
                left = (int) (Math.cos(degrees / 180 * Math.PI) * radius) + (int) centerX;
                top = (int) centerY + (int) (Math.sin(degrees / 180 * Math.PI) * radius);
                top += scaleLinePaint.getTextSize() - decent;
            } else if (degrees <= 270) {
                degrees -= 180;
                left = (int) centerX - (int) (Math.sin(degrees / 180 * Math.PI) * radius);
                top = (int) centerY + (int) (Math.cos(degrees / 180 * Math.PI) * radius);
                if (degrees == 90) {
                    left -= scaleLinePaint.getTextSize() / 2;
                    top += scaleLinePaint.getTextSize() / 2 - decent;
                } else {
                    top += scaleLinePaint.getTextSize() - decent;
                }

            } else if (degrees <= 360) {
                degrees -= 270;
                left = (int) centerX - (int) (Math.cos(degrees / 180 * Math.PI) * radius);
                top = (int) centerY - (int) (Math.sin(degrees / 180 * Math.PI) * radius);
            }
            Log.e(TAG, "drawScaleValue: " + left + ":" + top);
            canvas.drawText(scale + "", left, top, scaleLinePaint);
        }
    }

    /**
     * 画刻度
     */
    private void drawScale(Canvas canvas) {
        for (int scale : SCALES_VALUE) {
            canvas.rotate(30, centerX, centerY);
            if (scale == 12 || scale == 3 || scale == 6 || scale == 9) {
                scaleLinePaint.setStrokeWidth(6);
                canvas.drawLine(centerX, circleWidth, centerX, scaleLineLLenght, scaleLinePaint);
            } else {
                scaleLinePaint.setStrokeWidth(4);
                canvas.drawLine(centerX, circleWidth, centerX, scaleLineSLenght, scaleLinePaint);
            }
        }


    }

    /**
     * 画圆边
     */
    private void drawCircle(Canvas canvas) {
        float radius = getMeasuredHeight() / 2f - circleWidth;

        canvas.drawCircle(centerX, centerY, radius, circlePaint);

    }
}
