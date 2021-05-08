package com.example.brightwheelchallenge.ui

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt
import com.example.brightwheelchallenge.R
import java.util.*

/**
 * Custom View to show loading dots on screen while request is in progress
 */
class DotLoadingView: LinearLayout {

    // Properties
    var repeatInterval: Long = 200 //default to 3 ms
        private set
    private var loadingCircles: Int = 3
    private var inactiveColor = "#FFBB86FC".toColorInt()
    private var activeColor = "#FF3700B3".toColorInt()

    // constants
    private val layoutParams = LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

    private var prevActiveView: ImageView? = null
    private var nextActiveView: ImageView? = null

    private var timer: Timer = Timer()
    private var timerTask: TimerTask? = null

    var isLoading = false
        private set

    constructor(context: Context): super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER
        drawCircles(context, loadingCircles)
    }

    fun startLoading() {
        // show the view
        visibility = View.VISIBLE

        // Cancel previous TimerTask before scheduling new one
        // and before losing reference to it
        timerTask?.cancel()

        // Schedule new TimerTask
        timerTask = object: TimerTask() {
            override fun run() {
                post {
                    startLoadingAnimation()
                    invalidate()
                }
            }
        }
        timer.schedule(timerTask, 0, repeatInterval)

        isLoading = true
    }

    fun stopLoading() {
        // hide the view
        visibility = View.GONE

        // Cancel currently schedule TimerTask
        timerTask?.cancel()

        isLoading = false
    }

    // Draw loading circles
    private fun drawCircles(context: Context, loadingCircles: Int) {
        var prevView: View? = null
        for (i in 1..loadingCircles) {

            val imageView = ImageView(context)
            imageView.adjustViewBounds = true
            imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.loading_view_image))
            if (prevView != null) {
                imageView.setPadding(16,0, 0, 0)
            }
            addView(imageView, layoutParams)

            prevView = imageView
        }
    }

    /**
     * Animate the circles from left to right.
     */
    private fun startLoadingAnimation() {
        nextActiveView = getNextActiveView(nextActiveView) as ImageView

        prevActiveView?.drawable?.setTint(inactiveColor)

        val currActiveView: ImageView = nextActiveView as ImageView
        currActiveView.drawable.setTint(activeColor)

        prevActiveView = nextActiveView

        isLoading = true
    }

    // returns the view to the right of currently active view.
    // if it is the last view, return the first one.
    private fun getNextActiveView(view: ImageView?): View? {
        if (view == null) {
            return getChildAt(0)
        }

        val index = indexOfChild(view)
        val nextIndex = index + 1

        if (nextIndex <= childCount) {
            return if (nextIndex == childCount) getChildAt(0) else getChildAt(nextIndex)
        }

        return null
    }
}

