package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice8DrawArcView extends View {

    private Paint mPaint;

    public Practice8DrawArcView(Context context) {
       this(context,null);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs) {
       this(context,attrs,0);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawArc() 方法画弧形和扇形
        //绘制弧形
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawArc(100,100,600,600,20,140,false,mPaint);
            //绘制扇形
            canvas.drawArc(100,100,600,600,-110,100,true,mPaint);
            mPaint.setStyle(Paint.Style.STROKE);
            //绘制不封口的弧形
            canvas.drawArc(100,100,600,600,180,60,false,mPaint);
        }else {
            RectF rectF = new RectF(100, 100, 600, 600);
            canvas.drawArc(rectF,20,140,false,mPaint);
            canvas.drawArc(rectF,-110,100,true,mPaint);
            canvas.drawArc(rectF,180,60,false,mPaint);
        }

    }
}
