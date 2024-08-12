package com.troxy.autolayout.utils

import android.view.View
import com.troxy.autolayout.AutoLayoutInfo
import com.troxy.autolayout.R
import com.troxy.autolayout.attr.Attrs
import com.troxy.autolayout.attr.AutoAttr
import com.troxy.autolayout.config.AutoLayoutConifg

object AutoUtils {
    /**
     * Directly view LayoutParams width, height Directly move percentage
     *
     * @param view
     */
    fun auto(view: View?) {
        autoSize(view)
        autoPadding(view)
        autoMargin(view)
        autoTextSize(view, AutoAttr.BASE_DEFAULT)
    }

    /**
     * @param view
     * @param attrs #Attrs.WIDTH|Attrs.HEIGHT
     * @param base  AutoAttr.BASE_WIDTH|AutoAttr.BASE_HEIGHT|AutoAttr.BASE_DEFAULT
     */
    fun auto(view: View?, attrs: Int, base: Int) {
        val autoLayoutInfo = view?.let { AutoLayoutInfo.getAttrFromView(it, attrs, base) }
        autoLayoutInfo?.fillAttrs(view)
    }

    fun autoTextSize(view: View?) {
        auto(view, Attrs.TEXTSIZE, AutoAttr.BASE_DEFAULT)
    }

    fun autoTextSize(view: View?, base: Int) {
        auto(view, Attrs.TEXTSIZE, base)
    }

    fun autoMargin(view: View?) {
        auto(view, Attrs.MARGIN, AutoAttr.BASE_DEFAULT)
    }

    fun autoMargin(view: View?, base: Int) {
        auto(view, Attrs.MARGIN, base)
    }

    fun autoPadding(view: View?) {
        auto(view, Attrs.PADDING, AutoAttr.BASE_DEFAULT)
    }

    fun autoPadding(view: View?, base: Int) {
        auto(view, Attrs.PADDING, base)
    }

    fun autoSize(view: View?) {
        auto(view, Attrs.WIDTH or Attrs.HEIGHT, AutoAttr.BASE_DEFAULT)
    }

    fun autoSize(view: View?, base: Int) {
        auto(view, Attrs.WIDTH or Attrs.HEIGHT, base)
    }

    fun autoed(view: View): Boolean {
        val tag = view.getTag(R.id.id_tag_autolayout_size)
        if (tag != null) return true
        view.setTag(R.id.id_tag_autolayout_size, "Just Identify")
        return false
    }

    val percentWidth1px: Float
        get() {
            val screenWidth: Int = AutoLayoutConifg.instance.screenWidth
            val designWidth: Int = AutoLayoutConifg.instance.designWidth
            return 1.0f * screenWidth / designWidth
        }

    val percentHeight1px: Float
        get() {
            val screenHeight: Int = AutoLayoutConifg.instance.screenHeight
            val designHeight: Int = AutoLayoutConifg.instance.designHeight
            return 1.0f * screenHeight / designHeight
        }


    fun getPercentWidthSize(`val`: Int): Int {
        val screenWidth: Int = AutoLayoutConifg.instance.screenWidth
        val designWidth: Int = AutoLayoutConifg.instance.designWidth
        return (`val` * 1.0f / designWidth * screenWidth).toInt()
    }


    @JvmStatic
    fun getPercentWidthSizeBigger(`val`: Int): Int {
        val screenWidth: Int = AutoLayoutConifg.instance.screenWidth
        val designWidth: Int = AutoLayoutConifg.instance.designWidth

        val res = `val` * screenWidth
        return if (res % designWidth == 0) {
            res / designWidth
        } else {
            res / designWidth + 1
        }
    }

    fun getPercentHeightSizeBigger(`val`: Int): Int {
        val screenHeight: Int = AutoLayoutConifg.instance.screenHeight
        val designHeight: Int = AutoLayoutConifg.instance.designHeight

        val res = `val` * screenHeight
        return if (res % designHeight == 0) {
            res / designHeight
        } else {
            res / designHeight + 1
        }
    }

    fun getPercentHeightSize(`val`: Int): Int {
        val screenHeight: Int = AutoLayoutConifg.instance.screenHeight
        val designHeight: Int = AutoLayoutConifg.instance.designHeight

        return (`val` * 1.0f / designHeight * screenHeight).toInt()
    }
}
