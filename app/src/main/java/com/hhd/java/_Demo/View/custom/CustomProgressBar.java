package com.hhd.java._Demo.View.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.hhd.java.R;
import com.hhd.java._Demo.HHDLogger.MyLogger;

/**
 * 自定义视图3（圆圈进度条）
 * 未注释的请查看CustomTextView（自定义视图1）
 *
 * @author hehongdan
 * @date 20180815
 */
public class CustomProgressBar extends View {
    private MyLogger HHDLog = MyLogger.HHDLog();
    /** 第一圈的颜色 */
    private int mSecondColor;
    /** 第二圈的颜色 */
    private int mFirstColor;
    /** 圈的宽度（直径） */
    private int mCircleWidth;
    /** 画笔 */
    private Paint mPaint;
    /** 当前进度 */
    private int mProgress;
    /** 进度的速度 */
    private int mSpeed = 500;
    /** 是否开始下一圈 */
    private boolean isNext = false;


    public CustomProgressBar(Context context) {
        this(context,null);
    }

    public CustomProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomProgressBar,defStyleAttr,0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                //第一圈的颜色
                case R.styleable.CustomProgressBar_firstColor:
                    mFirstColor = a.getColor(attr, Color.GREEN);
                    break;
                //第二圈的颜色
                case R.styleable.CustomProgressBar_secondColor:
                    mSecondColor = a.getColor(attr, Color.RED);
                    break;
                //圆环的宽度
                case R.styleable.CustomProgressBar_circleWidth:
                    mCircleWidth = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;
                //进度速度
                case R.styleable.CustomProgressBar_speed:
                    mSpeed = a.getInt(attr, 20);
                    break;

                default:
                    break;
            }
        }
        a.recycle();
        mPaint=new Paint();

        //
        new Thread(){
            @Override
            public void run() {
                super.run();
                while (true){
                    mProgress++;
                    //如果跑完一圈
                    if (mProgress == 360){
                        //重置位置
                        mProgress = 0;
                        if (!isNext){
                            isNext = true;
                        } else {
                            isNext = false;
                        }
                    }
                    //刷新视图
                    postInvalidate();
                    try {
                        //设置速度
                        Thread.sleep(mSpeed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //计算圆心的x坐标
        int centre = getWidth() /2;
        //计算半径
        int radius = centre - mCircleWidth / 2;
        //设置圆环的宽度
        mPaint.setStrokeWidth(mCircleWidth);
        //消除锯齿
        mPaint.setAntiAlias(true);
        //画笔空心（圆环）
        mPaint.setStyle(Paint.Style.STROKE);
        //精度更高的（圆外切）矩形（参数：左上右下坐标）
        RectF oval = new RectF(centre - radius,centre-radius,centre+radius,centre+radius);
        //不设置第二圈
        if (!isNext){
            //设置画笔颜色（第一圈进度条底色）
            mPaint.setColor(mFirstColor);
            //空心画笔画圆环（参数：圆心的x坐标；圆心的y坐标；圆的半径；绘制时所使用的画笔）
            canvas.drawCircle(centre,centre,radius,mPaint);
            // 设置画笔颜色（第一圈进度显示颜色）
            mPaint.setColor(mSecondColor);
            //画圆弧（第一圈进度）（参数：椭圆外切矩形；开始角度(3点方向逆时针90度)；扫过角度；是否包含圆心；绘制圆弧的画笔）
            canvas.drawArc(oval, -90, mProgress,false,mPaint);
        }else {
            //第一颜色的圈完整，第二颜色跑
            // 设置画笔颜色（第二圈进度条底色）
            mPaint.setColor(mSecondColor);
            //空心画笔画圆环
            canvas.drawCircle(centre,centre,radius,mPaint);
            // 设置画笔颜色（第二圈进度显示颜色）
            mPaint.setColor(mFirstColor);
            //画圆弧（第二圈进度）
            canvas.drawArc(oval,-90,mProgress,false,mPaint);
        }
    }
}
