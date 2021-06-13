package com.mosavi.customdiamond

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.mosavi.customdiamond.animaitons.PlayProAnimation
import com.mosavi.customdiamond.listeners.IAnimationDone
import com.mosavi.customdiamond.listeners.OnGlobalLayoutMeasured
import com.mosavi.customdiamond.properties.DiamondAnimationProperties
import com.mosavi.customdiamond.properties.ProTextProperties

class PlayProCustomView(context: Context, attributeSet: AttributeSet) : ConstraintLayout(context, attributeSet) {
    private var view: View = LayoutInflater.from(context).inflate(R.layout.diamond, this, true)
    private var startTextView = view.findViewById<TextCustomView>(R.id.customStartTextView)
    private var diamondImageView: DiamondCustomView = view.findViewById(R.id.mainImageView)
    private var textConstraintLayout = view.findViewById<View>(R.id.constraintText)
    private var playProAnimation: PlayProAnimation? = null
    private var diamondAnimationProperties: DiamondAnimationProperties? = null
    val attributes = context.obtainStyledAttributes(attributeSet, R.styleable.PlayProCustomView)

    init {
        attributes.apply {
            val startText = getString(R.styleable.PlayProCustomView_startText) ?: ""
            val endText = getString(R.styleable.PlayProCustomView_endText) ?: ""
            val textSize = getDimension(R.styleable.PlayProCustomView_sizeOfText, 70F)
            val textColor = getColor(R.styleable.PlayProCustomView_textColor, 0)
            startTextView.setTextAttribute(startText, endText, textSize,textColor)
        }
    }

    fun setup(diamondAnimationProperties: DiamondAnimationProperties? = null, proTextProperties: ProTextProperties? = null) {
        if (diamondAnimationProperties == null) return // prevent call the method again
        this.diamondAnimationProperties = diamondAnimationProperties
        this.playProAnimation = PlayProAnimation(diamondAnimationProperties!!)
        textConstraintLayout.measureViewSizeInRunTIme(object : OnGlobalLayoutMeasured {
            override fun onMeasured(with: Int, height: Int) {
                if (startTextView.tag != null) return
                startTextView.tag = 0
                diamondImageView.layoutParams.height = (height * 1.3F).toInt()
                diamondImageView.requestLayout()
                diamondImageView.setup(diamondAnimationProperties)
                diamondImageView.performAnimation()
                playProAnimation?.startAnimation(view, object : IAnimationDone {
                    override fun done() {
                    }
                })
            }
        })
        diamondImageView.measureViewSizeInRunTIme(object : OnGlobalLayoutMeasured {
            override fun onMeasured(with: Int, height: Int) {
                if (diamondImageView.tag != null) return
                diamondImageView.tag = 0
                startTextView.setStartMargin((with / 1.8).toInt())
            }
        })
    }
}