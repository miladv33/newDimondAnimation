package com.mosavi.customdiamond

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class TextCustomView(context: Context, attributeSet: AttributeSet) : ConstraintLayout(context, attributeSet) {
    private var view: View = LayoutInflater.from(context).inflate(R.layout.text_play_pro, this, true)
    private var whiteBackground = view.findViewById<View>(R.id.backgroundView)
    private var startTextView = view.findViewById<TextView>(R.id.startTextView)
    private var endTextView = view.findViewById<TextView>(R.id.endTextView)
    private var diamondImageView = view.findViewById<ImageView>(R.id.diamondImageView)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
    fun setTextAttribute(startText: String, endText: String, textSize: Float, textColor: Int, layoutRTL: Boolean) {
        if (layoutRTL) {
            endTextView.text = startText
        } else {
            startTextView.text = startText
            endTextView.text = endText
        }
        startTextView.setTextColor(textColor)
        endTextView.setTextColor(textColor)
        startTextView.textSize = textSize
        endTextView.textSize = textSize
        diamondImageView.setColorFilter(textColor)
    }

    fun setStartMargin(textPadding: Int) {
        with(view.findViewById<TextView>(R.id.startTextView)) {
            setPaddingRelative(textPadding, this.paddingTop, paddingEnd, paddingBottom)
        }
        setPaddingRelative(paddingStart, this.paddingTop, textPadding, paddingBottom)
    }

}