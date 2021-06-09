package com.mosavi.customdiamond

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.mosavi.customdiamond.animaitons.PlayProAnimation
import com.mosavi.customdiamond.listeners.IAnimationDone
import com.mosavi.customdiamond.listeners.OnGlobalLayoutMeasured
import com.mosavi.customdiamond.properties.DiamondAnimationProperties
import com.mosavi.customdiamond.properties.ProTextProperties

class PlayProCustomView(context: Context, attributeSet: AttributeSet) : ConstraintLayout(context, attributeSet) {
    private var view: View = LayoutInflater.from(context).inflate(R.layout.diamond, this, true)
    private var startTextView = view.findViewById<TextView>(R.id.startTextView)
    private var endTextView = view.findViewById<TextView>(R.id.endTextView)
    private var diamondImageView: DiamondCustomView = view.findViewById(R.id.mainImageView)
    private var backGroundView = view.findViewById<View>(R.id.backgroundView)
    private var playProAnimation: PlayProAnimation? = null
    private var diamondAnimationProperties: DiamondAnimationProperties? = null
    val attributes = context.obtainStyledAttributes(attributeSet, R.styleable.PlayProCustomView)

    init {
        attributes.apply {
            startTextView.text = getString(R.styleable.PlayProCustomView_startText)
            endTextView.text = getString(R.styleable.PlayProCustomView_endText)
            startTextView.textSize = getDimension(R.styleable.PlayProCustomView_sizeOfText, 70F)
            endTextView.textSize = getDimension(R.styleable.PlayProCustomView_sizeOfText, 70F)

        }
    }

    fun setup(diamondAnimationProperties: DiamondAnimationProperties? = null, proTextProperties: ProTextProperties? = null) {
        if (diamondAnimationProperties == null) return // prevent call the method again
        this.diamondAnimationProperties = diamondAnimationProperties
        this.playProAnimation = PlayProAnimation(diamondAnimationProperties!!)
        startTextView.measureViewSizeInRunTIme(object : OnGlobalLayoutMeasured {
            override fun onMeasured(with: Int, height: Int) {
                if (startTextView.tag != null) return
                startTextView.tag = 0
                diamondImageView.layoutParams.height = (height * 1.3F).toInt()
                diamondImageView.requestLayout()
                diamondImageView.setup(diamondAnimationProperties)
                diamondImageView.performAnimation()
                playProAnimation?.startAnimation(view,object :IAnimationDone{
                    override fun done() {
                    }
                })
            }
        })
    }

}