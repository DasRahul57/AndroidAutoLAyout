package com.troxy.autolayout

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import com.troxy.autolayout.utils.AutoLayoutHelper
import com.troxy.autolayout.utils.AutoLayoutHelper.AutoLayoutParams
import com.troxy.autolayout.utils.AutoLayoutHelper.Companion.getAutoLayoutInfo

class AutoFrameLayout : FrameLayout {
    private val mHelper = AutoLayoutHelper(this)

    constructor(context: Context?) : super(context!!)

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    )

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!, attrs, defStyleAttr
    )

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(
        context!!, attrs, defStyleAttr, defStyleRes
    )

    override fun generateLayoutParams(attrs: AttributeSet): LayoutParams {
        return LayoutParams(context, attrs)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (!isInEditMode) {
            mHelper.adjustChildren()
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    class LayoutParams : FrameLayout.LayoutParams, AutoLayoutParams {
        override var autoLayoutInfo: AutoLayoutInfo? = null
            private set

        constructor(c: Context?, attrs: AttributeSet?) : super(c!!, attrs) {
            autoLayoutInfo = getAutoLayoutInfo(c, attrs)
        }

        constructor(width: Int, height: Int) : super(width, height)

        constructor(width: Int, height: Int, gravity: Int) : super(width, height, gravity)

        constructor(source: ViewGroup.LayoutParams?) : super(source!!)

        constructor(source: MarginLayoutParams?) : super(source!!)

        constructor(source: FrameLayout.LayoutParams) : super((source as MarginLayoutParams)) {
            gravity = source.gravity
        }

        constructor(source: LayoutParams) : this(source as FrameLayout.LayoutParams) {
            autoLayoutInfo = source.autoLayoutInfo
        }
    }
}
