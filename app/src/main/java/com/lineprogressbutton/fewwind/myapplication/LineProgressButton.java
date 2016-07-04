package com.lineprogressbutton.fewwind.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;


/**
 * Created by admin on 2016/6/17.
 */
public class LineProgressButton extends View {

    private Paint mPaint;
    private Paint mPaintTxt;


    private Rect rectTxt;
    private float mPrecent = 0.0f;
    private int mColorBgNormal = Color.GREEN;
    private int mColorBgFirst = Color.GRAY;
    private int mColorBgSecond = Color.GREEN;
    private int mColorBg = mColorBgNormal;

    private String mContentPre;
    private float mContextSize;
    private int mContextColor;


    private String mContent = "下载";
    private Xfermode mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

    private float mProgressWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40f, getResources().getDisplayMetrics());
    private float mPadingLeft = getPaddingLeft();
    private float mPadingRight = getPaddingRight();

    public LineProgressButton(Context context) {
        this(context, null);
    }

    public LineProgressButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineProgressButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomLineProgressBar);
        mColorBgFirst = ta.getColor(R.styleable.CustomLineProgressBar_bg_loading_state_first, Color.GRAY);
        mColorBgSecond = ta.getColor(R.styleable.CustomLineProgressBar_bg_loading_state_seconed, Color.GREEN);
        mColorBgNormal = ta.getColor(R.styleable.CustomLineProgressBar_bg_nromal_state, Color.GREEN);
        mContextColor = ta.getColor(R.styleable.CustomLineProgressBar_text_color, Color.GREEN);

        mContentPre = ta.getString(R.styleable.CustomLineProgressBar_text_content_pre);
        mContextSize = ta.getDimension(R.styleable.CustomLineProgressBar_text_size, 36);
        ta.recycle();

        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);

        mPaintTxt = new Paint(Paint.ANTI_ALIAS_FLAG);
        rectTxt = new Rect();
        mPaintTxt.setTextSize(mContextSize);
        mPaintTxt.setColor(mContextColor);
        mPaintTxt.setFakeBoldText(true);
        mPaintTxt.getTextBounds(mContent, 0, mContent.length(), rectTxt);


        mColorBg = mColorBgNormal;
        mContent = mContentPre;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置画笔的宽度等于自定义view的高度
        mPaint.setStrokeWidth(mProgressWidth =getMeasuredHeight());
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(mColorBg);
        /**
         * 特别注意：：  cap.round 就是断线头、画笔头部的弧形是额外的，不计算在真实line的坐标系的,其实是个半圆，半径正好是画笔高度的1/2
         */
        canvas.drawLine(mPadingLeft + mProgressWidth / 2, getMeasuredHeight() / 2, getMeasuredWidth() - mPadingRight - mProgressWidth / 2, getMeasuredHeight() / 2, mPaint);


        int count = canvas.saveLayer(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint, Canvas.ALL_SAVE_FLAG);
        canvas.drawLine(mPadingLeft + mProgressWidth / 2, getMeasuredHeight() / 2, getMeasuredWidth() - mPadingRight - mProgressWidth / 2, getMeasuredHeight() / 2, mPaint);
        mPaint.setXfermode(mXfermode);


        mPaint.setColor(mColorBgSecond);
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawLine(mPadingLeft - getMeasuredHeight() / 2, getMeasuredHeight() / 2, (getMeasuredWidth() - mPadingRight - mPadingLeft) * mPrecent + mPadingLeft - getMeasuredHeight() / 2, getMeasuredHeight() / 2, mPaint);
        canvas.restoreToCount(count);
        mPaint.setXfermode(null);
        canvas.drawText(mContent, getWidth() / 2 - rectTxt.width() / 2, getHeight() / 2 + rectTxt.height() / 2, mPaintTxt);

        Log.w("tag", "measue" + getMeasuredWidth() + "==paddingleft==" + (getMeasuredWidth() - getPaddingRight() - getPaddingLeft()) * mPrecent);
    }

    public void setPrecent(float precent,String text) {
        this.mPrecent = precent;
        mColorBg = mColorBgFirst;
        this.mContent = text;
        mPaintTxt.getTextBounds(mContent, 0, mContent.length(), rectTxt);
        invalidate();
    }

    public void setContext( String text) {
        this.mContent = text;
        mPaintTxt.getTextBounds(mContent, 0, mContent.length(), rectTxt);
        invalidate();
    }

}
