package com.hhd.java._Demo.View.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.hhd.java.R;
import com.hhd.java._Demo.HHDLogger.MyLogger;

/**
 * 自定义视图4（视频音量调控）
 * 未注释的请查看CustomTextView（自定义视图1）
 *
 * @author hehongdan
 * @date 20180815
 */
public class CustomVolumControlBar extends View {

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
    private int mCurrentCount = 3;
    /** 中间的图片 */
    private Bitmap mImage;
    /** 每个块间隙（占用度数） */
    private int mSplitSize;
    /** 个数 */
    private int mCount;
    /** 矩形 */
    private Rect mRect;
    /** 手指碰到屏幕时y轴坐标 */
    private int yDown;
    private int yUp;


    public CustomVolumControlBar(Context context) {
        this(context,null);
    }

    public CustomVolumControlBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomVolumControlBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomVolumControlBar,defStyleAttr,0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr){
                case R.styleable.CustomVolumControlBar_firstColor:
                    mFirstColor = a.getColor(attr, Color.GREEN);
                    break;
                case R.styleable.CustomVolumControlBar_secondColor:
                    mSecondColor = a.getColor(attr, Color.CYAN);
                    break;
                case R.styleable.CustomVolumControlBar_bg:
                    mImage = BitmapFactory.decodeResource(getResources(), a.getResourceId(attr, 0));
                    break;
                case R.styleable.CustomVolumControlBar_circleWidth:
                    mCircleWidth = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomVolumControlBar_dotCount:
                    mCount = a.getInt(attr, 20);
                    break;
                case R.styleable.CustomVolumControlBar_splitSize:
                    mSplitSize = a.getInt(attr, 20);
                    break;
                default:
                    break;
            }
        }
        a.recycle();
        mPaint = new Paint();
        mRect = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mCircleWidth);
        // 定义线段断电形状为圆头
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        int centre = getWidth() / 2;
        int radius = centre - mCircleWidth/2;
        drawOval(canvas,centre,radius);

        /**
         * 计算内切正方形的位置
         */
        // 获得内圆的半径
        int relRadius = radius - mCircleWidth / 2;
        //矩形左边x坐标（内切正方形的距离左边= mCircleWidth + relRadius - √2 / 2）
        mRect.left = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mCircleWidth;
        //矩形上边y坐标
        mRect.top = mRect.left;
        //mRect.top = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mCircleWidth;
        //矩形右边x坐标
        mRect.right = (int) (mRect.left+Math.sqrt(2)*relRadius);
        //矩形下边y坐标
        mRect.bottom = (int) (mRect.top + Math.sqrt(2) * relRadius);

        /**
         * 如果图片比较小，那么根据图片的尺寸放置到正中心
         */
        if (mImage.getWidth() < (mRect.right - mRect.left)){
            mRect.left = (int) (mRect.left+Math.sqrt(2)*relRadius*1.0f/2-mImage.getWidth()*1.0f/2);
            mRect.top = (int)(mRect.top+Math.sqrt(2)*relRadius*1.0f/2-mImage.getHeight()*1.0f/2);
            mRect.right = mRect.left+mImage.getWidth();
            mRect.right = mRect.top+mImage.getHeight();
        }
        canvas.drawBitmap(mImage,null,mRect,mPaint);
    }

    /**
     * 根据参数画出每个小块
     *
     * @param canvas    画布
     * @param centre    圆心
     * @param radius    半径
     */
    private void drawOval(Canvas canvas, int centre, int radius){
        //每块进度块的度数（除开间隙）
        float itemSize = (360 * 1.0f - mCount * mSplitSize) / mCount;
        // 定义圆形状的外切矩形（界限）
        RectF oval = new RectF(centre -radius,centre- radius,centre +radius,centre +radius);
        // 设置圆环的背景颜色
        mPaint.setColor(mFirstColor);
        for (int i = 0; i < mCount; i++) {
            // 根据进度画圆弧（跳过间隙度数）
            canvas.drawArc(oval,i*(itemSize+mSplitSize),itemSize,false,mPaint);
        }
        // 设置圆环的前景颜色
        mPaint.setColor(mSecondColor);
        for (int i = 0; i < mCurrentCount; i++) {
            // 根据进度画圆弧（跳过间隙度数）
            canvas.drawArc(oval,i*(itemSize+mSplitSize),itemSize,false,mPaint);
        }
    }

    /**
     * 当前数量+1
     */
    public void up(){
        if (mCurrentCount < mCount) {
            //增加一级
            mCurrentCount++;
            //刷新视图
            postInvalidate();
        }
    }

    /**
     * 当前数量+1
     */
    public void down(){
        if (mCurrentCount > 0){
            //增加一级
            mCurrentCount--;
            //刷新视图
            postInvalidate();
        }
    }

    /**
     * 重写触摸事件
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);
        switch (event.getAction()){
            //手指碰到屏幕时
            case MotionEvent.ACTION_DOWN:
                yDown = (int) event.getY();
                break;
            //手指离开屏幕时
            case MotionEvent.ACTION_UP:
                yUp = (int) event.getY();
                //手指下滑动作
                if (yUp > yDown){
                    down();
                }else {
                    up();
                }
                break;
            default:
                break;
        }
        //不向下传递事件
        return true;
    }
}
