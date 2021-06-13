package com.mosavi.customdiamond

import android.content.Context
import android.media.Image
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class TextCustomView(context: Context, attributeSet: AttributeSet) : ConstraintLayout(context, attributeSet) {
    private var view: View = LayoutInflater.from(context).inflate(R.layout.text_play_pro, this, true)
    private var startTextView = view.findViewById<TextView>(R.id.startTextView)
    private var endTextView = view.findViewById<TextView>(R.id.endTextView)
    private var diamondImageView = view.findViewById<ImageView>(R.id.diamondImageView)
    fun setTextAttribute(startText: String, endText: String, textSize: Float, textColor: Int) {
        startTextView.text = startText
        startTextView.setTextColor(textColor)
        endTextView.text = endText
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