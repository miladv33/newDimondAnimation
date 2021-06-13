package com.mosavi.customdiamond

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout

class LocalController {
    companion object {
        fun isLayoutRTL(constraintLayout: ConstraintLayout): Boolean {
            return constraintLayout.layoutDirection == View.LAYOUT_DIRECTION_RTL
        }
    }
}