/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.troxy.autolayout.utils

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import com.troxy.autolayout.AutoLayoutInfo
import com.troxy.autolayout.R
import com.troxy.autolayout.attr.HeightAttr
import com.troxy.autolayout.attr.MarginAttr
import com.troxy.autolayout.attr.MarginBottomAttr
import com.troxy.autolayout.attr.MarginLeftAttr
import com.troxy.autolayout.attr.MarginRightAttr
import com.troxy.autolayout.attr.MarginTopAttr
import com.troxy.autolayout.attr.MaxHeightAttr
import com.troxy.autolayout.attr.MaxWidthAttr
import com.troxy.autolayout.attr.MinHeightAttr
import com.troxy.autolayout.attr.MinWidthAttr
import com.troxy.autolayout.attr.PaddingAttr
import com.troxy.autolayout.attr.PaddingBottomAttr
import com.troxy.autolayout.attr.PaddingLeftAttr
import com.troxy.autolayout.attr.PaddingRightAttr
import com.troxy.autolayout.attr.PaddingTopAttr
import com.troxy.autolayout.attr.TextSizeAttr
import com.troxy.autolayout.attr.WidthAttr
import com.troxy.autolayout.config.AutoLayoutConifg

class AutoLayoutHelper
    (private val mHost: ViewGroup) {
    init {
        if (mAutoLayoutConifg == null) {
            initAutoLayoutConfig(mHost)
        }
    }

    private fun initAutoLayoutConfig(host: ViewGroup) {
        mAutoLayoutConifg = AutoLayoutConifg.instance
        mAutoLayoutConifg!!.init(host.context)
    }


    fun adjustChildren() {
        AutoLayoutConifg.instance.checkParams()

        var i = 0
        val n = mHost.childCount
        while (i < n) {
            val view = mHost.getChildAt(i)
            val params = view.layoutParams

            if (params is AutoLayoutParams) {
                val info =
                    (params as AutoLayoutParams).autoLayoutInfo
                info?.fillAttrs(view)
            }
            i++
        }
    }

    interface AutoLayoutParams {
        val autoLayoutInfo: AutoLayoutInfo?
    }

    companion object {
        private val LL = intArrayOf(
            //
            android.R.attr.textSize,
            android.R.attr.padding,  //
            android.R.attr.paddingLeft,  //
            android.R.attr.paddingTop,  //
            android.R.attr.paddingRight,  //
            android.R.attr.paddingBottom,  //
            android.R.attr.layout_width,  //
            android.R.attr.layout_height,  //
            android.R.attr.layout_margin,  //
            android.R.attr.layout_marginLeft,  //
            android.R.attr.layout_marginTop,  //
            android.R.attr.layout_marginRight,  //
            android.R.attr.layout_marginBottom,  //
            android.R.attr.maxWidth,  //
            android.R.attr.maxHeight,  //
            android.R.attr.minWidth,  //
            android.R.attr.minHeight,  //16843072


        )

        private const val INDEX_TEXT_SIZE = 0
        private const val INDEX_PADDING = 1
        private const val INDEX_PADDING_LEFT = 2
        private const val INDEX_PADDING_TOP = 3
        private const val INDEX_PADDING_RIGHT = 4
        private const val INDEX_PADDING_BOTTOM = 5
        private const val INDEX_WIDTH = 6
        private const val INDEX_HEIGHT = 7
        private const val INDEX_MARGIN = 8
        private const val INDEX_MARGIN_LEFT = 9
        private const val INDEX_MARGIN_TOP = 10
        private const val INDEX_MARGIN_RIGHT = 11
        private const val INDEX_MARGIN_BOTTOM = 12
        private const val INDEX_MAX_WIDTH = 13
        private const val INDEX_MAX_HEIGHT = 14
        private const val INDEX_MIN_WIDTH = 15
        private const val INDEX_MIN_HEIGHT = 16


        /**
         * move to other place?
         */
        private var mAutoLayoutConifg: AutoLayoutConifg? = null

        @JvmStatic
        fun getAutoLayoutInfo(
            context: Context,
            attrs: AttributeSet?
        ): AutoLayoutInfo {
            val info = AutoLayoutInfo()

            val a = context.obtainStyledAttributes(attrs, R.styleable.AutoLayout_Layout)
            val baseWidth = a.getInt(R.styleable.AutoLayout_Layout_layout_auto_basewidth, 0)
            val baseHeight = a.getInt(R.styleable.AutoLayout_Layout_layout_auto_baseheight, 0)
            a.recycle()

            val array = context.obtainStyledAttributes(attrs, LL)

            val n = array.indexCount


            for (i in 0 until n) {
                val index = array.getIndex(i)

                //            String val = array.getString(index);
//            if (!isPxVal(val)) continue;
                if (!DimenUtils.isPxVal(array.peekValue(index))) continue

                var pxVal = 0
                try {
                    pxVal = array.getDimensionPixelOffset(index, 0)
                } catch (ignore: Exception) //not dimension
                {
                    continue
                }
                when (index) {
                    INDEX_TEXT_SIZE -> info.addAttr(TextSizeAttr(pxVal, baseWidth, baseHeight))
                    INDEX_PADDING -> info.addAttr(PaddingAttr(pxVal, baseWidth, baseHeight))
                    INDEX_PADDING_LEFT -> info.addAttr(
                        PaddingLeftAttr(
                            pxVal,
                            baseWidth,
                            baseHeight
                        )
                    )

                    INDEX_PADDING_TOP -> info.addAttr(PaddingTopAttr(pxVal, baseWidth, baseHeight))
                    INDEX_PADDING_RIGHT -> info.addAttr(
                        PaddingRightAttr(
                            pxVal,
                            baseWidth,
                            baseHeight
                        )
                    )

                    INDEX_PADDING_BOTTOM -> info.addAttr(
                        PaddingBottomAttr(
                            pxVal,
                            baseWidth,
                            baseHeight
                        )
                    )

                    INDEX_WIDTH -> info.addAttr(WidthAttr(pxVal, baseWidth, baseHeight))
                    INDEX_HEIGHT -> info.addAttr(HeightAttr(pxVal, baseWidth, baseHeight))
                    INDEX_MARGIN -> info.addAttr(MarginAttr(pxVal, baseWidth, baseHeight))
                    INDEX_MARGIN_LEFT -> info.addAttr(MarginLeftAttr(pxVal, baseWidth, baseHeight))
                    INDEX_MARGIN_TOP -> info.addAttr(MarginTopAttr(pxVal, baseWidth, baseHeight))
                    INDEX_MARGIN_RIGHT -> info.addAttr(
                        MarginRightAttr(
                            pxVal,
                            baseWidth,
                            baseHeight
                        )
                    )

                    INDEX_MARGIN_BOTTOM -> info.addAttr(
                        MarginBottomAttr(
                            pxVal,
                            baseWidth,
                            baseHeight
                        )
                    )

                    INDEX_MAX_WIDTH -> info.addAttr(MaxWidthAttr(pxVal, baseWidth, baseHeight))
                    INDEX_MAX_HEIGHT -> info.addAttr(MaxHeightAttr(pxVal, baseWidth, baseHeight))
                    INDEX_MIN_WIDTH -> info.addAttr(MinWidthAttr(pxVal, baseWidth, baseHeight))
                    INDEX_MIN_HEIGHT -> info.addAttr(MinHeightAttr(pxVal, baseWidth, baseHeight))
                }
            }
            array.recycle()
            L.e(" getAutoLayoutInfo $info")
            return info
        }
    }
}
