package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice9DrawPathView extends View {

    private Paint mPaint;
    private Path path;

    public Practice9DrawPathView(Context context) {
        this(context,null);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawPath() 方法画心形
        //path.addXxxx 就是添加完整的封闭的子图形  + cavas.drawPath = cavas.drawCircle
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            path.addArc(200, 200, 400, 400, -225, 225);
            //xXXTo画线（直线或者曲线）arcTo() 和 addArc()。它们也是用来画线的，但并不使用当前位置作为弧线的起点。
            //forceMoveTo表示强制拖到弧形起点
            path.arcTo(400, 200, 600, 400, -180, 225, false);
        }else {
            RectF rectF = new RectF(200,200, 400, 400);
            path.addArc(rectF,-225,225);
            RectF rectF1 = new RectF(400, 200, 600, 400);
            path.addArc(rectF1,-180,225);
        }
        path.lineTo(400, 542);
        canvas.drawPath(path,mPaint);



        //path.addXxxx 就是添加完整的封闭的子图形  + cavas.drawPath = cavas.drawCircle
        path.addCircle(600,600,100, Path.Direction.CW);//顺时针 (CW clockwise) 和逆时针 (CCW counter-clockwise)
        canvas.drawPath(path,mPaint);
    }
}
