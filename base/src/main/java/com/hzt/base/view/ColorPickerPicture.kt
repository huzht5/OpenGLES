package com.hzt.base.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.hzt.base.util.LogUtil
import java.lang.ref.WeakReference
import java.math.BigDecimal
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

class ColorPickerPicture(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var mCallback: ColorPickerCallback? = null
    private var mThread: HandlerThread? = null
    private var mHandler: Handler? = null
    private lateinit var mCanvas: Canvas
    private lateinit var mCopyCanvas: Canvas
    private lateinit var mBitmap: Bitmap
    private lateinit var mCopyBitmap: Bitmap
    private lateinit var mHueBitmap: Bitmap
    private val mRect = Rect()
    private val mHueRect = Rect()
    private val mColorRectF = RectF()
    private val mPaint = Paint()
    private val mStrokePaint = Paint()
    private val mColorPaint = Paint()
    private var mSideLength = 0.0F
    private val mHSV = FloatArray(3)
    private var mWidth = 0
    private var mHeight = 0
    private var mRed = 0
    private var mGreen = 0
    private var mBlue = 0
    private var mColor = 0xFF000000.toInt()
    private var mCount = 0
    private var mColorStr = "#000000"
    private var mPrepared = false

    companion object {
        private const val TAG = "ColorPickerPicture"
    }

    init {
        mPaint.isAntiAlias = true
        mPaint.isFilterBitmap = true

        mStrokePaint.isAntiAlias = true
        mStrokePaint.style = Paint.Style.STROKE

        mColorPaint.style = Paint.Style.FILL
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        LogUtil.d(TAG, "[onAttachedToWindow]")
        if (mHandler == null || mThread == null) {
            mThread = HandlerThread("ColorPickerThread")
            mThread!!.start()
            mHandler = Handler(mThread!!.looper, ColorPickerHandlerCallback(this))
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        LogUtil.d(TAG, "[onDetachedFromWindow]")
        mHandler?.removeCallbacksAndMessages(null)
        mHandler = null
        mThread?.looper?.quit()
        mThread = null
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(width, min((width.toFloat() * 1.2F).roundToInt(), height))
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event!!.x
        val y = event.y
        if (y < (mWidth + 1)) {
            mHSV[1] = max(min(x / mWidth, 1.0F), 0.0F)
            mHSV[2] = max(min(1.0F - y / mWidth, 1.0F), 0.0F)
            calculateRGB()
            postInvalidate()
            onColorChange()
        } else if ((y > (mHueRect.top - 10)) && (y < (mHueRect.bottom + 10))) {
            mHSV[0] = max(min(x / mWidth * 360.0F, 360.0F), 0.0F)
            calculateRGB()
            mHandler?.sendEmptyMessage(0)
            onColorChange()
        }
        return true
    }

    private fun calculateRGB() {
        val color = Color.HSVToColor(mHSV)
        mRed = Color.red(color)
        mGreen = Color.green(color)
        mBlue = Color.blue(color)
        updateColor()
    }

    private fun updateColor() {
        mColor = Color.rgb(mRed, mGreen, mBlue)
        var redStr = mRed.toString(16)
        if (redStr.length == 1) {
            redStr = "0$redStr"
        }
        var greenStr = mGreen.toString(16)
        if (greenStr.length == 1) {
            greenStr = "0$greenStr"
        }
        var blueStr = mBlue.toString(16)
        if (blueStr.length == 1) {
            blueStr = "0$blueStr"
        }
        mColorStr = "#$redStr$greenStr$blueStr"
    }

    private fun calculateHSV() {
        val hsv = FloatArray(3)
        Color.RGBToHSV(mRed, mGreen, mBlue, hsv)
        if (!isEqual(mHSV[0], hsv[0])) {
            mHSV[0] = hsv[0]
            mHandler?.sendEmptyMessage(0)
        }
        if (!isEqual(mHSV[1], hsv[1]) || !isEqual(mHSV[2], hsv[2])) {
            mHSV[1] = hsv[1]
            mHSV[2] = hsv[2]
            postInvalidate()
        }
    }

    private fun onColorChange() {
        mCallback?.onColorChange()
    }

    fun setHue(hue: Float) {
        if (!isEqual(mHSV[0], hue)) {
            mHSV[0] = hue
            calculateRGB()
            mHandler?.sendEmptyMessage(0)
            onColorChange()
        }
    }

    fun setSaturation(saturation: Float) {
        if (!isEqual(mHSV[1], saturation)) {
            mHSV[1] = saturation
            calculateRGB()
            postInvalidate()
            onColorChange()
        }
    }

    fun setValue(value: Float) {
        if (!isEqual(mHSV[2], value)) {
            mHSV[2] = value
            calculateRGB()
            postInvalidate()
            onColorChange()
        }
    }

    fun setRed(red: Int) {
        if (mRed != red) {
            mRed = red
            updateColor()
            calculateHSV()
            onColorChange()
        }
    }

    fun setGreen(green: Int) {
        if (mGreen != green) {
            mGreen = green
            updateColor()
            calculateHSV()
            onColorChange()
        }
    }

    fun setBlue(blue: Int) {
        if (mBlue != blue) {
            mBlue = blue
            updateColor()
            calculateHSV()
            onColorChange()
        }
    }

    fun setColorStr(colorStr: String) {
        if (mColorStr != colorStr) {
            mColorStr = colorStr
            mColor = Color.parseColor(colorStr)
            mRed = Color.red(mColor)
            mGreen = Color.green(mColor)
            mBlue = Color.blue(mColor)
            calculateHSV()
            onColorChange()
        }
    }

    fun getHue() = mHSV[0].roundToInt()

    fun getSaturation() = (mHSV[1] * 100.0F).roundToInt()

    fun getValue() = (mHSV[2] * 100.0F).roundToInt()

    fun getRed() = mRed

    fun getGreen() = mGreen

    fun getBlue() = mBlue

    fun getColor() = mColor

    fun getColorStr() = mColorStr

    override fun onDraw(canvas: Canvas?) {
        canvas!!.drawColor(0xFF444444.toInt())
        if (!mPrepared) {
            mPrepared = true
            mWidth = width
            mHeight = height
            prepareBitmap()
            prepareHueBitmap()
        }
        canvas.drawBitmap(mCopyBitmap, null, mRect, mPaint)
        val centerX = mHSV[1] * mWidth
        val centerY = mWidth - mHSV[2] * mWidth
        mStrokePaint.color = 0xFFFFFFFF.toInt()
        mStrokePaint.strokeWidth = 3.0F
        canvas.drawCircle(centerX, centerY, 10.0F, mStrokePaint)
        mStrokePaint.color = 0xFF000000.toInt()
        mStrokePaint.strokeWidth = 2.0F
        canvas.drawCircle(centerX, centerY, 13.0F, mStrokePaint)
        canvas.drawBitmap(mHueBitmap, null, mHueRect, mPaint)
        val lineX = mHSV[0] / 360.0F * mWidth
        mStrokePaint.color = 0xFF000000.toInt()
        mStrokePaint.strokeWidth = 8.0F
        canvas.drawLine(lineX, (mHueRect.top - 10).toFloat(), lineX, (mHueRect.bottom + 10).toFloat(), mStrokePaint)
    }

    private fun prepareBitmap() {
        val min = min(mWidth, mHeight)
        mSideLength = min.toFloat() / 101.0F
        mRect.set(0, 0, min, min)
        mBitmap = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888)
        mCopyBitmap = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888)
        mCanvas = Canvas(mBitmap)
        mCopyCanvas = Canvas(mCopyBitmap)
        mHandler?.sendEmptyMessage(0)
    }

    private fun prepareHueBitmap() {
        val min = min(mWidth, mHeight)
        mHueRect.set(0, min + 50, mWidth, min + 50 + mWidth / 10)
        mHueBitmap = Bitmap.createBitmap(mWidth, mWidth / 10, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(mHueBitmap)
        val paint = Paint()
        paint.isAntiAlias = true
        paint.isFilterBitmap = true

        val centerY = mWidth.toFloat() / 10.0F /2.0F
        paint.shader = LinearGradient(0.0F, centerY, mWidth.toFloat(), centerY,
            intArrayOf(0xFFFF0000.toInt(), 0xFFFFFF00.toInt(), 0xFF00FF00.toInt(), 0xFF00FFFF.toInt(), 0xFF0000FF.toInt(), 0xFFFF00FF.toInt(), 0xFFFF0000.toInt()),
            floatArrayOf(0.0F, 1.0F / 6.0F, 1.0F / 3.0F, 1.0F / 2.0F, 2.0F / 3.0F, 5.0F / 6.0F, 1.0F), Shader.TileMode.CLAMP)
        canvas.drawRect(0.0F, 0.0F, mWidth.toFloat(), mWidth.toFloat() / 10.0F, paint)
    }

    private fun generatePicture() {
        mCount++
        mCanvas.drawColor(0x00000000)
        for (i in 0 until 101) {
            for (j in 0 until 101) {
                mColorPaint.color = Color.HSVToColor(floatArrayOf(mHSV[0], j.toFloat() / 100.0F, (100 - i).toFloat() / 100.0F))
                mColorRectF.set(mSideLength * j, mSideLength * i, mSideLength * (j + 1), mSideLength * (i + 1))
                mCanvas.drawRect(mColorRectF, mColorPaint)
            }
        }
        mCount--;
        if (mCount == 0) {
            mCopyCanvas.drawColor(0x00000000)
            mCopyCanvas.drawBitmap(mBitmap, null, mRect, mPaint)
            postInvalidate()
        }
    }

    private fun isEqual(f1: Float, f2: Float): Boolean {
        val a = BigDecimal(f1.toString())
        val b = BigDecimal(f2.toString())
        return a.compareTo(b) == 0
    }

    private class ColorPickerHandlerCallback(view: ColorPickerPicture): Handler.Callback {
        private val mWeakReference: WeakReference<ColorPickerPicture> = WeakReference(view)

        override fun handleMessage(msg: Message): Boolean {
            val view: ColorPickerPicture? = mWeakReference.get()
            view?.generatePicture()
            return false
        }
    }

    fun setColorPickerCallback(callback: ColorPickerCallback?) {
        mCallback = callback
    }

    interface ColorPickerCallback {
        fun onColorChange()
    }
}