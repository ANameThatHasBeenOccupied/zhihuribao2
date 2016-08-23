package com.example.seaice.zhihuribao.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.seaice.zhihuribao.R;


public class CircleIndicator extends View {

    private int mCount;//设置格数
    private int selectItem;//当前选中
    private float mRadius;//原点半径
    private float mSelectRadius;//选中原点半径
    private float mSpace;//圆形间隔
    private Paint mPaint;//画笔


    public CircleIndicator(Context context) {
        this(context, null);
    }

    public CircleIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        setSize();
    }

    //设置圆点的大小
    private void setSize() {
        DisplayMetrics metrcs = getResources().getDisplayMetrics();
        mRadius = 2 * metrcs.density;
        mSelectRadius = 3 * metrcs.density;
        mSpace = 12 * metrcs.density;
    }

    //初始化画笔
    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(getResources().getColor(R.color.light_grey));
        mPaint.setStyle(Paint.Style.FILL);
    }

    public void setViewPager(ViewPager pager) {
        mCount = pager.getAdapter().getCount();
        invalidate();
    }

    //设置圆点的个数
    public void setCircleNumber(int num){
        mCount = num;
        invalidate();
    }

    //设置选中的圆点
    public void setSelectdItem(int position) {
        selectItem = position;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int widthMeasureSpec) {
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        int result;
        if (specMode != MeasureSpec.EXACTLY) {
            result = (int) (getPaddingLeft() + getPaddingRight() + mSpace * mCount);
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(specSize, result);
            }
        } else {
            result = specSize;
        }
        return result;
    }

    private int measureHeight(int heightMeasureSpec) {
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
        int result;
        if (specMode != MeasureSpec.EXACTLY) {
            result = (int) (getPaddingTop() + getPaddingBottom() + mSelectRadius * 2);
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(specSize, result);
            }
        } else {
            result = specSize;
        }
        return result;
    }

    //画圆点
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float y = getHeight() / 2f;
        float x = mSpace / 2f;
        for (int i = 0; i < mCount; i++) {
            if (i != selectItem) {
                canvas.drawCircle(x, y, mRadius, mPaint);
            } else {
                canvas.drawCircle(x, y, mSelectRadius, mPaint);
            }
            x += mSpace;
        }
    }
}