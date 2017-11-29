package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hencoder.hencoderpracticedraw1.model.Data;

import java.util.ArrayList;
import java.util.List;

public class Practice10HistogramView extends View {

    private Paint mPaint;
    private Path mPath;
    private List<Data> mData = new ArrayList<>();
    private float max = 0;

    public Practice10HistogramView(Context context) {
       this(context,null);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mData.add(new Data("Froyo",5.0f, Color.GREEN));
        mData.add(new Data("GB",10.0f, Color.GREEN));
        mData.add(new Data("ICS",10.0f, Color.GREEN));
        mData.add(new Data("JB",80.0f, Color.GREEN));
        mData.add(new Data("kitKat",120.0f, Color.GREEN));
        mData.add(new Data("L",150.0f, Color.GREEN));
        mData.add(new Data("M",70.0f, Color.GREEN));

        for (int i = 0; i < mData.size(); i++) {
            max = Math.max(max,mData.get(i).getNum());
        }
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(48);
        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("直方图", canvas.getWidth() / 2, canvas.getHeight() * 0.95f, mPaint);
        //移动画布的原点坐标
        canvas.translate(canvas.getWidth() * 0.1f, canvas.getHeight() * 0.80f);
        Log.d("yxl",canvas.getWidth()+"："+canvas.getHeight());//1440  1020
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        canvas.drawLine(0, 0, 0, -canvas.getHeight() * 0.7f, mPaint);
        canvas.drawLine(0, 0, canvas.getWidth() * 0.8f, 0, mPaint);

        float width = (canvas.getWidth() * 0.8f - 100) / mData.size() * 0.8f;
        float space = (canvas.getWidth() * 0.8f - 100) / mData.size() * 0.2f;

        float startX = 0.0f;
        for (Data item : mData) {
            mPaint.setColor(Color.WHITE);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setTextSize(36);
            mPaint.setTextAlign(Paint.Align.CENTER);
            //要想让文字居中，必须在条形图的(横坐标)中点开始
            canvas.drawText(item.getName(), startX + space + width / 2, 40, mPaint);

            mPaint.setColor(item.getColor());
            //canvas.getHeight() * 0.7f是纵坐标的总高度 max是数据中最大的值
            canvas.drawRect(startX + space, -item.getNum() / max * canvas.getHeight() * 0.7f, startX + space + width, 0, mPaint);
            startX = space + width + startX;

        }
    }
}
