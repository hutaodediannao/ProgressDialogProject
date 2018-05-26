package com.app.progressdialogproject;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义环形进度条
 * Created by 胡涛 on 2018/5/26.
 */

public class CustomPregressBar extends View {

    private Paint mPaint, mTextPaint;//绘制画笔
    private int mWidth, //宽度
            mHeight;//高度

    private int mColorRes;//进度条颜色
    private float mProgressBarStrokeWidth;//进度环厚度
    private int mMax;//总进度
    private int mProgress;//进度
    private float mProgressTextSize;
    private int mProgressTextColorRes;

    public CustomPregressBar(Context context) {
        this(context, null);
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(Color.RED);//默认设置为红色

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setStrokeCap(Paint.Cap.ROUND);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
        mTextPaint.setTextSize(getResources().getDimension(R.dimen._defacultTextSize));
        mTextPaint.setColor(Color.BLACK);//默认设置为红色
    }

    public CustomPregressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomPregressBar);

        mColorRes = typedArray.getResourceId(R.styleable.CustomPregressBar_progressBarColor, android.R.color.holo_red_light);
        mProgressBarStrokeWidth = typedArray.getDimension(R.styleable.CustomPregressBar_progressBarStrokeWidth, getResources().getDimension(R.dimen._5dp));

        mProgressTextSize = typedArray.getDimension(R.styleable.CustomPregressBar_progressTextSize, getResources().getDimension(R.dimen._defacultTextSize));
        mProgressTextColorRes = typedArray.getResourceId(R.styleable.CustomPregressBar_progressTextColor, android.R.color.black);

        mMax = typedArray.getInt(R.styleable.CustomPregressBar_progressMax, 100);
        mProgress = typedArray.getInt(R.styleable.CustomPregressBar_progress, 0);

        //设置进度条颜色
        mPaint.setColor(getResources().getColor(mColorRes) == 0 ? getResources().getColor(android.R.color.holo_red_light) : getResources().getColor(mColorRes));
        //设置进度条宽度
        mPaint.setStrokeWidth(mProgressBarStrokeWidth);

        //设置进度条文字大小
        mTextPaint.setTextSize(mProgressTextSize);
        //设置进度条文字颜色
        mTextPaint.setColor(getResources().getColor(mProgressTextColorRes));

        typedArray.recycle();
    }

    public synchronized void setProgress(int progress) {
        this.mProgress = progress;
        this.postInvalidate();
    }

    public synchronized void setMax(int max) {
        this.mMax = max;
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.mWidth = getMeasuredWidth();
        this.mHeight = getMeasuredHeight();
//        RadialGradient gradient = new RadialGradient(mWidth/2, 0, (mWidth>mHeight?mWidth:mHeight)/2, Color.YELLOW, Color.RED, Shader.TileMode.MIRROR);
//        mPaint.setShader(gradient);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mMax == 0) return;
        //开始绘制进度进度条
        canvas.drawArc(0 + mProgressBarStrokeWidth / 2, 0 + mProgressBarStrokeWidth / 2,
                mWidth - mProgressBarStrokeWidth / 2, mHeight - mProgressBarStrokeWidth / 2,
                -90, mProgress * 360 / mMax, false, mPaint);

        //开始设置文字内容
        String progressContent = mProgress * 100 / mMax + "%";
        Rect bounds = new Rect();
        mTextPaint.getTextBounds(progressContent, 0, progressContent.length(), bounds);
        canvas.drawText(progressContent, mWidth / 2 - bounds.width() / 2, mHeight / 2 + bounds.height() / 2, mTextPaint);
    }
}
