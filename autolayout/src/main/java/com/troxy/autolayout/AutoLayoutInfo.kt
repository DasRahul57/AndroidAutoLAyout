package com.troxy.autolayout

import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.widget.TextView
import com.troxy.autolayout.attr.Attrs
import com.troxy.autolayout.attr.AutoAttr
import com.troxy.autolayout.attr.HeightAttr
import com.troxy.autolayout.attr.MarginBottomAttr
import com.troxy.autolayout.attr.MarginLeftAttr
import com.troxy.autolayout.attr.MarginRightAttr
import com.troxy.autolayout.attr.MarginTopAttr
import com.troxy.autolayout.attr.MaxHeightAttr
import com.troxy.autolayout.attr.MaxHeightAttr.Companion.getMaxHeight
import com.troxy.autolayout.attr.MaxWidthAttr
import com.troxy.autolayout.attr.MaxWidthAttr.Companion.getMaxWidth
import com.troxy.autolayout.attr.MinHeightAttr
import com.troxy.autolayout.attr.MinHeightAttr.Companion.getMinHeight
import com.troxy.autolayout.attr.MinWidthAttr
import com.troxy.autolayout.attr.MinWidthAttr.Companion.getMinWidth
import com.troxy.autolayout.attr.PaddingBottomAttr
import com.troxy.autolayout.attr.PaddingLeftAttr
import com.troxy.autolayout.attr.PaddingRightAttr
import com.troxy.autolayout.attr.PaddingTopAttr
import com.troxy.autolayout.attr.TextSizeAttr
import com.troxy.autolayout.attr.WidthAttr

class AutoLayoutInfo {
    private val autoAttrs: MutableList<AutoAttr?> = ArrayList()

    fun addAttr(autoAttr: AutoAttr?) {
        autoAttrs.add(autoAttr)
    }


    fun fillAttrs(view: View?) {
        for (autoAttr in autoAttrs) {
            autoAttr!!.apply(view!!)
        }
    }


    override fun toString(): String {
        return "AutoLayoutInfo{" +
                "autoAttrs=" + autoAttrs +
                '}'
    }

    companion object {
        fun getAttrFromView(view: View, attrs: Int, base: Int): AutoLayoutInfo? {
            val params = view.layoutParams ?: return null
            val autoLayoutInfo = AutoLayoutInfo()

            // width & height
            if ((attrs and Attrs.WIDTH) != 0 && params.width > 0) {
                autoLayoutInfo.addAttr(WidthAttr.generate(params.width, base))
            }

            if ((attrs and Attrs.HEIGHT) != 0 && params.height > 0) {
                autoLayoutInfo.addAttr(HeightAttr.generate(params.height, base))
            }

            //margin
            if (params is MarginLayoutParams) {
                if ((attrs and Attrs.MARGIN) != 0) {
                    autoLayoutInfo.addAttr(MarginLeftAttr.generate(params.leftMargin, base))
                    autoLayoutInfo.addAttr(MarginTopAttr.generate(params.topMargin, base))
                    autoLayoutInfo.addAttr(MarginRightAttr.generate(params.rightMargin, base))
                    autoLayoutInfo.addAttr(MarginBottomAttr.generate(params.bottomMargin, base))
                }
                if ((attrs and Attrs.MARGIN_LEFT) != 0) {
                    autoLayoutInfo.addAttr(MarginLeftAttr.generate(params.leftMargin, base))
                }
                if ((attrs and Attrs.MARGIN_TOP) != 0) {
                    autoLayoutInfo.addAttr(MarginTopAttr.generate(params.topMargin, base))
                }
                if ((attrs and Attrs.MARGIN_RIGHT) != 0) {
                    autoLayoutInfo.addAttr(MarginRightAttr.generate(params.rightMargin, base))
                }
                if ((attrs and Attrs.MARGIN_BOTTOM) != 0) {
                    autoLayoutInfo.addAttr(MarginBottomAttr.generate(params.bottomMargin, base))
                }
            }

            //padding
            if ((attrs and Attrs.PADDING) != 0) {
                autoLayoutInfo.addAttr(PaddingLeftAttr.generate(view.paddingLeft, base))
                autoLayoutInfo.addAttr(PaddingTopAttr.generate(view.paddingTop, base))
                autoLayoutInfo.addAttr(PaddingRightAttr.generate(view.paddingRight, base))
                autoLayoutInfo.addAttr(PaddingBottomAttr.generate(view.paddingBottom, base))
            }
            if ((attrs and Attrs.PADDING_LEFT) != 0) {
                autoLayoutInfo.addAttr(MarginLeftAttr.generate(view.paddingLeft, base))
            }
            if ((attrs and Attrs.PADDING_TOP) != 0) {
                autoLayoutInfo.addAttr(MarginTopAttr.generate(view.paddingTop, base))
            }
            if ((attrs and Attrs.PADDING_RIGHT) != 0) {
                autoLayoutInfo.addAttr(MarginRightAttr.generate(view.paddingRight, base))
            }
            if ((attrs and Attrs.PADDING_BOTTOM) != 0) {
                autoLayoutInfo.addAttr(MarginBottomAttr.generate(view.paddingBottom, base))
            }

            //minWidth ,maxWidth , minHeight , maxHeight
            if ((attrs and Attrs.MIN_WIDTH) != 0) {
                autoLayoutInfo.addAttr(MinWidthAttr.generate(getMinWidth(view), base))
            }
            if ((attrs and Attrs.MAX_WIDTH) != 0) {
                autoLayoutInfo.addAttr(MaxWidthAttr.generate(getMaxWidth(view), base))
            }
            if ((attrs and Attrs.MIN_HEIGHT) != 0) {
                autoLayoutInfo.addAttr(MinHeightAttr.generate(getMinHeight(view), base))
            }
            if ((attrs and Attrs.MAX_HEIGHT) != 0) {
                autoLayoutInfo.addAttr(MaxHeightAttr.generate(getMaxHeight(view), base))
            }

            //textsize
            if (view is TextView) {
                if ((attrs and Attrs.TEXTSIZE) != 0) {
                    autoLayoutInfo.addAttr(TextSizeAttr.generate(view.textSize.toInt(), base))
                }
            }
            return autoLayoutInfo
        }
    }
}