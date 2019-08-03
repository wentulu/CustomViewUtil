package cn.stu.ruiz.myapplication.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import cn.stu.ruiz.myapplication.utils.DisplayScreenUtil;


/**
 * 关于图层
 * 受用save系列方法保存图层
 * 使用restore合并图层到画布
 *
 * */
public class CanvasLayerView extends View {


    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CanvasLayerView(Context context) {
        super(context);
    }

    public CanvasLayerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasLayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(DisplayScreenUtil.getScreenWidthPixel(getContext()),600);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(300,300,200,mPaint);


        canvas.saveLayerAlpha(0,0,600,600,255,Canvas.ALL_SAVE_FLAG);
        mPaint.setColor(Color.RED);
        canvas.drawCircle(400,400,200,mPaint);
        canvas.restore();


    }
}
