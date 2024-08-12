package com.troxy.autolayout.attr

/**
 * Created by TROXY on 10/08/2024.
 *
 *
 *
 */
interface Attrs {
    companion object {
        const val WIDTH: Int = 1
        const val HEIGHT: Int = WIDTH shl 1
        const val TEXTSIZE: Int = HEIGHT shl 1
        const val PADDING: Int = TEXTSIZE shl 1
        const val MARGIN: Int = PADDING shl 1
        const val MARGIN_LEFT: Int = MARGIN shl 1
        const val MARGIN_TOP: Int = MARGIN_LEFT shl 1
        const val MARGIN_RIGHT: Int = MARGIN_TOP shl 1
        const val MARGIN_BOTTOM: Int = MARGIN_RIGHT shl 1
        const val PADDING_LEFT: Int = MARGIN_BOTTOM shl 1
        const val PADDING_TOP: Int = PADDING_LEFT shl 1
        const val PADDING_RIGHT: Int = PADDING_TOP shl 1
        const val PADDING_BOTTOM: Int = PADDING_RIGHT shl 1
        const val MIN_WIDTH: Int = PADDING_BOTTOM shl 1
        const val MAX_WIDTH: Int = MIN_WIDTH shl 1
        const val MIN_HEIGHT: Int = MAX_WIDTH shl 1
        const val MAX_HEIGHT: Int = MIN_HEIGHT shl 1
    }
}
