package com.troxy.autolayout.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.troxy.autolayout.AutoLayoutInfo
import com.troxy.autolayout.R
import com.troxy.autolayout.utils.AutoLayoutHelper
import com.troxy.autolayout.utils.AutoLayoutHelper.AutoLayoutParams
import com.troxy.autolayout.utils.AutoLayoutHelper.Companion.getAutoLayoutInfo
import com.troxy.autolayout.utils.AutoUtils.getPercentWidthSizeBigger
import java.util.Random

class MetroLayout(context: Context, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    private val mHelper = AutoLayoutHelper(this)

    private class MetroBlock {
        var left: Int = 0
        var top: Int = 0
        var width: Int = 0
    }

    private val mAvailablePos: MutableList<MetroBlock> = ArrayList()
    private var mDivider: Int

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.MetroLayout)
        mDivider = a.getDimensionPixelOffset(R.styleable.MetroLayout_metro_divider, 0)
        mDivider = getPercentWidthSizeBigger(mDivider)
        a.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (true) randomColor()

        if (!isInEditMode) mHelper.adjustChildren()

        measureChildren(widthMeasureSpec, heightMeasureSpec)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    private fun randomColor() {
        val r = Random(255)

        var i = 0
        val n = childCount
        while (i < n) {
            val v = getChildAt(i)

            v.setBackgroundColor(Color.argb(100, r.nextInt(), r.nextInt(), r.nextInt()))
            i++
        }
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        initAvailablePosition()

        var left = 0
        var top = 0
        val divider = mDivider

        var i = 0
        val n = childCount
        while (i < n) {
            val v = getChildAt(i)
            if (v.visibility == GONE) {
                i++
                continue
            }

            val newPos = findAvailablePos(v)
            left = newPos.left
            top = newPos.top

            val childWidth = v.measuredWidth
            val childHeight = v.measuredHeight

            val right = left + childWidth
            val bottom = top + childHeight

            v.layout(left, top, right, bottom)

            if (childWidth + divider < newPos.width) {
                newPos.left += childWidth + divider
                newPos.width -= childWidth + divider
            } else {
                mAvailablePos.remove(newPos)
            }

            val p = MetroBlock()
            p.left = left
            p.top = bottom + divider
            p.width = childWidth
            mAvailablePos.add(p)

            mergeAvailablePosition()

            i++
        }
    }

    private fun mergeAvailablePosition() {
        if (mAvailablePos.size <= 1) return

        val needRemoveBlocks: MutableList<MetroBlock> = ArrayList()

        var one = mAvailablePos[0]
        var two = mAvailablePos[1]

        var i = 1
        val n = mAvailablePos.size
        while (i < n - 1) {
            if (one.top == two.top) {
                one.width = one.width + two.width
                needRemoveBlocks.add(one)
                two.left = one.left
                two = mAvailablePos[i + 1]
            } else {
                one = mAvailablePos[i]
                two = mAvailablePos[i + 1]
            }
            i++
        }

        mAvailablePos.removeAll(needRemoveBlocks)
    }

    private fun initAvailablePosition() {
        mAvailablePos.clear()
        val first = MetroBlock()
        first.left = paddingLeft
        first.top = paddingTop
        first.width = measuredWidth
        mAvailablePos.add(first)
    }

    private fun findAvailablePos(view: View): MetroBlock {
        val p = MetroBlock()
        if (mAvailablePos.size == 0) {
            p.left = paddingLeft
            p.top = paddingTop
            p.width = measuredWidth
            return p
        }
        var min = mAvailablePos[0].top
        var minHeightPos = mAvailablePos[0]
        for (_p in mAvailablePos) {
            if (_p.top < min) {
                min = _p.top
                minHeightPos = _p
            }
        }
        return minHeightPos
    }


    override fun generateLayoutParams(attrs: AttributeSet): LayoutParams {
        return LayoutParams(context, attrs)
    }

    class LayoutParams : MarginLayoutParams, AutoLayoutParams {
        override var autoLayoutInfo: AutoLayoutInfo? = null
            private set

        constructor(c: Context?, attrs: AttributeSet?) : super(c, attrs) {
            autoLayoutInfo = getAutoLayoutInfo(c!!, attrs)
        }

        constructor(width: Int, height: Int) : super(width, height)

        constructor(source: ViewGroup.LayoutParams?) : super(source)

        constructor(source: MarginLayoutParams?) : super(source)

        constructor(source: LayoutParams) : this(source as ViewGroup.LayoutParams) {
            autoLayoutInfo = source.autoLayoutInfo
        }
    }
}
