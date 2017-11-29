package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice5DrawOvalView extends View {

    private Paint mPaint;

    public Practice5DrawOvalView(Context context) {
       this(context,null);
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    public Practice5DrawOvalView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public Practice5DrawOvalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawOval() 方法画椭圆
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawOval(100,100,300,200,mPaint);
        }else {
            RectF rectF = new RectF(100,100,300,200);
            canvas.drawOval(rectF,mPaint);
        }
    }
}
