package com.mosavi.customdiamond

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout

class DiamondCustomView(context: Context,attributeSet: AttributeSet?):FrameLayout(context,attributeSet) {
    private var view: View = LayoutInflater.from(context).inflate(R.layout.diamond_image, this, true)

}