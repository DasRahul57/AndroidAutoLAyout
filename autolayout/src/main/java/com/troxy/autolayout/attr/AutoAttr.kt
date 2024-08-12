package com.troxy.autolayout.attr

import android.view.View
import com.troxy.autolayout.utils.AutoUtils
import com.troxy.autolayout.utils.L
import kotlin.math.max

abstract class AutoAttr

/*
    protected boolean isBaseWidth;
    protected boolean isBaseDefault;

    public AutoAttr(int pxVal)
    {
        this.pxVal = pxVal;
        isBaseDefault = true;
    }

    public AutoAttr(int pxVal, boolean isBaseWidth)
    {
        this.pxVal = pxVal;
        this.isBaseWidth = isBaseWidth;
    }
 */(protected var pxVal: Int, protected var baseWidth: Int, protected var baseHeight: Int) {
    open fun apply(view: View) {
        val log = view.tag != null && view.tag.toString() == "auto"

        if (log) {
            L.e(" pxVal = " + pxVal + " ," + this.javaClass.simpleName)
        }
        var `val`: Int
        if (useDefault()) {
            `val` = if (defaultBaseWidth()) percentWidthSize else percentHeightSize
            if (log) {
                L.e(" useDefault val= $`val`")
            }
        } else if (baseWidth()) {
            `val` = percentWidthSize
            if (log) {
                L.e(" baseWidth val= $`val`")
            }
        } else {
            `val` = percentHeightSize
            if (log) {
                L.e(" baseHeight val= $`val`")
            }
        }

        if (`val` > 0) `val` = max(`val`.toDouble(), 1.0).toInt() //for very thin divider

        execute(view, `val`)
    }

    protected val percentWidthSize: Int
        get() = AutoUtils.getPercentWidthSizeBigger(pxVal)

    protected val percentHeightSize: Int
        get() = AutoUtils.getPercentHeightSizeBigger(pxVal)


    protected fun baseWidth(): Boolean {
        return contains(baseWidth, attrVal())
    }

    protected fun useDefault(): Boolean {
        return !contains(baseHeight, attrVal()) && !contains(baseWidth, attrVal())
    }

    protected fun contains(baseVal: Int, flag: Int): Boolean {
        return (baseVal and flag) != 0
    }

    protected abstract fun attrVal(): Int

    protected abstract fun defaultBaseWidth(): Boolean

    protected abstract fun execute(view: View?, `val`: Int)


    override fun toString(): String {
        return "AutoAttr{" +
                "pxVal=" + pxVal +
                ", baseWidth=" + baseWidth() +
                ", defaultBaseWidth=" + defaultBaseWidth() +
                '}'
    }

    companion object {
        const val BASE_WIDTH: Int = 1
        const val BASE_HEIGHT: Int = 2
        const val BASE_DEFAULT: Int = 3
    }
}
