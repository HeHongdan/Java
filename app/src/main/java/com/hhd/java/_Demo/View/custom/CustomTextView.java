package com.hhd.java._Demo.View.custom;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.hhd.java.R;
import com.hhd.java._Demo.HHDLogger.MyLogger;

/**
 * 自定义视图1（带底色的文本）
 *
 * @author hehongdan
 * @date 20180815
 */
public class CustomTextView extends View {

    private MyLogger HHDLog = MyLogger.HHDLog();
    /** 文本 */
    private String mTitleText;
    /** 文本的颜色 */
    private int mTitleTextColor;
    /** 文本字体大小 */
    private int mTitleTextSize;
    /** 绘制时控制文本绘制的范围 */
    private Rect mRect;
    /** 绘制笔 */
    private Paint mPaint;

    /**
     * 构造方法
     *
     * @param context
     */
    public CustomTextView(Context context) {
        //调用带两个参数的构造方法
        this(context, null);
    }

    /**
     * 构造方法
     *
     * @param context
     * @param attrs
     */
    public CustomTextView(Context context, AttributeSet attrs) {
        //调用带三个参数的构造方法
        this(context, attrs, 0);
    }


    /**
     * 构造方法
     *
     * @param context      上下文
     * @param attrs        XML属性数组信息
     * @param defStyleAttr
     */
    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //从attrs.xml获得类型数组（参数：xml中定义的属性集(键值对)；要取出的目标属性(键)；当前theme默认的属性集(建值对)；备用的一个style(键值对)）
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTextView, defStyleAttr, 0);
        //属性数组总数
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                //取到下标为titleText
                case R.styleable.CustomTextView_titleText:
                    //把下标为titleText的值转型为String
                    mTitleText = a.getString(attr);
                    break;
                case R.styleable.CustomTextView_titleTextColor:
                    // 取不到，默认为黑色
                    mTitleTextColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomTextView_titleTextSize:
                    // 取不到，默认16sp，取出值强转sp
                    mTitleTextSize = a.getDimensionPixelSize(attr,
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
                default:
                    break;
            }
        }
        //静态方法从对象池中取出，要进行释放复用
        a.recycle();
        mPaint = new Paint();
        //设置画笔为文本并大小为xml属性指定
        mPaint.setTextSize(mTitleTextSize);
        mRect = new Rect();
        //获取文本矩形最小边界(含原点0,0)（参数：绘制内容，开始位置，结束范围，文本矩形）
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mRect);
    }

    /**
     * 重写测量方法
     *
     * @param widthMeasureSpec  父视图传过来的宽度信息（样式、宽度）
     * @param heightMeasureSpec 父视图传过来的高度信息（样式、高度）
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //拿出父视图宽度的测量方式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        //拿出父视图宽度的值
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //控件最终宽度
        int width;
        //控件最终高度
        int height;
        //如果是精确模式
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mRect);
            //文本的总宽度
            float textWidth = mRect.width();
            //整个控件的宽度
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mRect);
            float textHeight = mRect.height();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = desired;
        }
        //测量view的尺寸
        setMeasuredDimension(width, height);
    }

    /**
     * 重写绘制方法
     *
     * @param canvas 画布
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //重置画笔颜色（黄色）
        mPaint.setColor(Color.YELLOW);
        //绘制矩形背景（左，上，右，下，画笔）
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        mPaint.setColor(mTitleTextColor);
        //绘制文本（左(坐标)，文本基线，画笔）
        canvas.drawText(mTitleText, getWidth() / 2 - mRect.width() / 2, getHeight() / 2 + mRect.height() / 2, mPaint);
    }
}

