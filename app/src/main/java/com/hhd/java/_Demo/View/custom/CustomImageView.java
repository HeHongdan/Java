package com.hhd.java._Demo.View.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.hhd.java.R;
import com.hhd.java._Demo.HHDLogger.MyLogger;

/**
 * 自定义视图2（富文本）
 * 未注释的请查看CustomTextView（自定义视图1）
 *
 * @author hehongdan
 * @date 20180815
 */
public class CustomImageView extends View {
    private MyLogger HHDLog = MyLogger.HHDLog();
    /** 文本 */
    private String mTitle;
    /** 文本的颜色 */
    private int mTextColor;
    /** 文本字体大小 */
    private int mTextSize;
    /** 绘制时控制文本绘制的范围 */
    private Rect rect;
    private Rect mTextBound;
    /** 绘制笔 */
    private Paint mPaint;
    /** 显示的图片 */
    private Bitmap mImage;
    /** 图片显示样式*/
    private int mImageScale;
    /** 将原图进行横方向（即XY方向）的拉伸后绘制 */
    private final int IMAGE_SCALE_FITXY = 0;
    /** 控件宽度 */
    private int mWidth ;
    /** 控件高度 */
    private int mHeight  ;

    public CustomImageView(Context context) {
        this(context,null);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomImageView,defStyleAttr,0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CustomImageView_titleText:
                    mTitle = a.getString(attr);
                    break;
                case R.styleable.CustomImageView_titleTextColor:
                    mTextColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomImageView_titleTextSize:
                    mTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                            16, getResources().getDisplayMetrics()));
                    break;
                    //图片资源
                case R.styleable.CustomImageView_image:
                    //根据给定的资源Id解析成位图（参数：位图资源；位图资源Id）
                    mImage = BitmapFactory.decodeResource(getResources(),a.getResourceId(attr,0));
                    break;
                    //图片显示（）测量类型
                case R.styleable.CustomImageView_imageScaleType:
                    mImageScale = a.getInt(attr,0);
                    break;

                default:
                    break;
            }
        }
        a.recycle();
        rect = new Rect();
        mPaint = new Paint();
        mTextBound = new Rect();
        mPaint.setTextSize(mTextSize);
        // 计算了描绘字体需要的范围
        mPaint.getTextBounds(mTitle,0,mTitle.length(),mTextBound);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        /** 设置控件宽度 */
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        //精确模式（MeasureSpec.UNSPECIFIED：未指定模式）
        if (specMode == MeasureSpec.EXACTLY){
            mWidth = specSize;
        }else {
            //图片及边距的宽
            int desireByImg = getPaddingLeft()+mImage.getWidth()+getPaddingRight();
            //字体及边距的宽
            int desireByTitle = getPaddingLeft() +mTextBound.width()+ getPaddingRight();
            //最大模式
            if (specMode == MeasureSpec.AT_MOST){
                //两个组件较宽的决定控件的宽度
                int desire = Math.max(desireByImg,desireByTitle);
                //父子View较小的决定控件的宽度
                mWidth = Math.min(specSize,desire);
                HHDLog.e("最大模式");
            }
        }
        /** 设置控件高度 */
        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        //精确模式（MeasureSpec.UNSPECIFIED：未指定模式）
        if (specMode == MeasureSpec.EXACTLY){
            mWidth = specSize;
        }else {
            int desire = getPaddingTop()+mImage.getHeight()+mTextBound.height()+getPaddingBottom();
            if (specMode == MeasureSpec.AT_MOST){
                mHeight = Math.min(specSize,desire);
            }
        }
        setMeasuredDimension(mWidth,mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);

        //边界的左边
        rect.left = getPaddingLeft();
        //边界的右边
        rect.right = mWidth-getPaddingRight();
        //边界的上边
        rect.top = getPaddingTop();
        //边界的下边
        rect.bottom = mHeight - getPaddingBottom();

        //绘制文本的画笔的颜色
        mPaint.setColor(mTextColor);
        //绘制文本的画笔实心
        mPaint.setStyle(Paint.Style.FILL);

        /**
         * 宽度小于字体的宽度时，设置省略右边
         */
        if (mWidth<mTextBound.width()){
            TextPaint paint = new TextPaint(mPaint);
            //截取指定长度字符串（参数：文本内容；绘制的画笔；测量文本长度；截断方式）
            String msg = TextUtils.ellipsize(mTitle,paint,mWidth-getPaddingLeft()-getPaddingRight(),
                    TextUtils.TruncateAt.END).toString();
            canvas.drawText(msg,getPaddingLeft(),mHeight-getPaddingBottom(),mPaint);
        }else {
            //字体居中，完整绘制
            canvas.drawText(mTitle,mWidth/2-mTextBound.width()*1.0f/2,mHeight-getPaddingBottom(),mPaint);
        }
        //计数图片的底部（总-文本）
        rect.bottom -= mTextBound.height();
        if (mImageScale == IMAGE_SCALE_FITXY){
            canvas.drawBitmap(mImage, null, rect, mPaint);
        }else {
            //计算图片的矩形范围居中显示（空间大于图片时）
            rect.left = mWidth / 2 - mImage.getWidth() / 2;
            rect.right = mWidth / 2 + mImage.getWidth() / 2;
            rect.top = (mHeight - mTextBound.height()) / 2 - mImage.getHeight() / 2;
            rect.bottom = (mHeight - mTextBound.height()) / 2 + mImage.getHeight() / 2;
            canvas.drawBitmap(mImage,null,rect,mPaint);
        }
    }
}
