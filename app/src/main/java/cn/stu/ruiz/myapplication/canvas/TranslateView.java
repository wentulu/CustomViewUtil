package cn.stu.ruiz.myapplication.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import cn.stu.ruiz.myapplication.utils.LogUtil;

public class TranslateView extends View {

    private static final String TAG = "TranslateView";

    private Paint mPaint;

    public TranslateView(Context context) {
        super(context);
        init();
    }

    public TranslateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TranslateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.translate(200,200);
        canvas.drawRect(0,0,10,10,mPaint);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        LogUtil.e(TAG,"visibility="+visibility,null);
    }
}
