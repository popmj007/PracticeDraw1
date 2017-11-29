package com.hencoder.hencoderpracticedraw1.practice;

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

import com.hencoder.hencoderpracticedraw1.R;
import com.hencoder.hencoderpracticedraw1.model.Data;

import java.util.ArrayList;
import java.util.List;

public class Practice11PieChartView extends View {

    private Paint mPaint;
    private Path mPath;
    private List<Data> mDatas = new ArrayList<>();
    private float max;
    private float total;

    public Practice11PieChartView(Context context) {
        this(context,null);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
       this(context,attrs,0);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mDatas.add(new Data("Lollipop", 34.0f, R.color.shapeOne));
        mDatas.add(new Data("Marshmallow", 16.0f, R.color.shapeTwo));
        mDatas.add(new Data("Gingerbread", 3.0f, R.color.shapeThree));
        mDatas.add(new Data("Ice Cream Sandwich", 2.0f, R.color.shapeFour));
        mDatas.add(new Data("Jelly Bean", 16.0f, R.color.shapeFive));
        mDatas.add(new Data("Kitkat", 29.0f, R.color.shapeSix));

        max = Float.MIN_VALUE;
        total = 0;
        for (Data data : mDatas) {
            total += data.getNum();
            max = Math.max(max, data.getNum());
        }

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPath = new Path();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
  /*      mPath.moveTo(600,600);
        mPaint.setColor(Color.RED);
        canvas.drawArc(400,400,800,800,-180,135,true,mPaint);
        mPath.moveTo(620,620);
        mPaint.setColor(Color.YELLOW);
        canvas.drawArc(420,420,820,820,-45,45,true,mPaint);
        mPaint.setColor(Color.argb(100,80,110,120));
        canvas.drawArc(420,420,820,820,0,5,true,mPaint);
        mPaint.setColor(Color.argb(100,156,39,176));
        canvas.drawArc(420,420,820,820,7,10,true,mPaint);
        mPaint.setColor(Color.argb(100,158,158,158));
        canvas.drawArc(420,420,820,820,19,10,true,mPaint);
        mPaint.setColor(Color.argb(100,0,150,136));
        canvas.drawArc(420,420,820,820,32,30,true,mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawArc(420,420,820,820,66,110,true,mPaint);

        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(30);
        canvas.drawText("Lollipop",300,300,mPaint);
        mPath.moveTo(300,300);
        mPath.lineTo(300,450);
        mPaint.setStrokeWidth(10);
        canvas.drawPath(mPath,mPaint);*/

        mPaint.setTextSize(48);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setColor(Color.WHITE);
        canvas.drawText("饼图", canvas.getWidth() * 0.5f, canvas.getHeight() * 0.95f, mPaint);
        //移动画布原点到中心位置
        canvas.translate(canvas.getWidth() * 0.5f, canvas.getHeight() * 0.5f);
        float radius = canvas.getHeight() * 0.35f;

        Log.i("yxl", "半径:" + radius);

        RectF rect = new RectF(-radius, -radius, radius, radius);

        float lineStartX;
        float lineStartY;
        float lineEndX;
        float lineEndY;
        float sweepAngle;
        float halfAngle;

        float angle = 0;
        for (Data item : mDatas) {
            mPaint.setStyle(Paint.Style.FILL);
            sweepAngle = item.getNum() / total * 360;
            halfAngle = sweepAngle * 0.5f + angle;
            mPaint.setColor(getResources().getColor(item.getColor()));

            lineStartX = radius * (float) Math.cos(halfAngle / 180 * Math.PI);
            lineStartY = radius * (float) Math.sin(halfAngle / 180 * Math.PI);
            lineEndX = (radius + 50) * (float) Math.cos(halfAngle / 180 * Math.PI);
            lineEndY = (radius + 50) * (float) Math.sin(halfAngle / 180 * Math.PI);

            Log.i("yxl", "startX = " + lineStartX + "startY = " + lineStartY + "endX = " + lineEndX + "endY = " + lineEndY);

            if (max == item.getNum()) {
                canvas.save();
                canvas.translate(lineStartX * 0.1f, lineStartY * 0.1f);
            }
            canvas.drawArc(rect, angle, sweepAngle - 2, true, mPaint);

            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(Color.WHITE);
            canvas.drawLine(lineStartX, lineStartY, lineEndX, lineEndY, mPaint);
            if (halfAngle > 90 && halfAngle < 270) {
                canvas.drawLine(lineEndX, lineEndY, lineEndX - 50, lineEndY, mPaint);
                mPaint.setTextSize(15);
                mPaint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText(item.getName(), lineEndX - 50, lineEndY, mPaint);
            } else {
                canvas.drawLine(lineEndX, lineEndY, lineEndX + 50, lineEndY, mPaint);
                mPaint.setTextSize(15);
                mPaint.setTextAlign(Paint.Align.LEFT);
                canvas.drawText(item.getName(), lineEndX + 50, lineEndY, mPaint);
            }
            if (max == item.getNum()) {
                canvas.restore();
            }
            angle += sweepAngle;
        }

    }
}
