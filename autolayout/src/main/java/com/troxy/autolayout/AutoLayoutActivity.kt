package com.troxy.autolayout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class AutoLayoutActivity : AppCompatActivity() {
    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        var view: View? = null
        if (name == LAYOUT_FRAMELAYOUT) {
            view = AutoFrameLayout(context, attrs)
        }

        if (name == LAYOUT_LINEARLAYOUT) {
            view = AutoLinearLayout(context, attrs)
        }

        if (name == LAYOUT_RELATIVELAYOUT) {
            view = AutoRelativeLayout(context, attrs)
        }

        if (view != null) return view

        return super.onCreateView(name, context, attrs)
    }


    companion object {
        private const val LAYOUT_LINEARLAYOUT = "LinearLayout"
        private const val LAYOUT_FRAMELAYOUT = "FrameLayout"
        private const val LAYOUT_RELATIVELAYOUT = "RelativeLayout"
    }
}
