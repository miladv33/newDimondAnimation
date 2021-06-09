package com.mosavi.customdiamond

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.mosavi.customdiamond.animaitons.DiamondAnimation
import com.mosavi.customdiamond.listeners.IAnimationDone
import com.mosavi.customdiamond.listeners.OnGlobalLayoutMeasured
import com.mosavi.customdiamond.properties.DiamondAnimationProperties
import com.mosavi.customdiamond.properties.ProTextProperties

class PlayProCustomView(context: Context, attributeSet: AttributeSet) : ConstraintLayout(context, attributeSet) {
    private var view: View = LayoutInflater.from(context).inflate(R.layout.diamond, this, true)
    var startTextView = view.findViewById<TextView>(R.id.startTextView)
    var endTextView = view.findViewById<TextView>(R.id.endTextView)
    var imageView = view.findViewById<DiamondCustomView>(R.id.mainImageView)
    var backGroundView = view.findViewById<View>(R.id.backgroundView)
    private var diamondAnimationProperties: DiamondAnimationProperties? = null
    val attributes = context.obtainStyledAttributes(attributeSet, R.styleable.PlayProCustomView)
    private var diamondAnimation: DiamondAnimation? = null

    init {
        attributes.apply {
            startTextView.text = getString(R.styleable.PlayProCustomView_startText)
            endTextView.text = getString(R.styleable.PlayProCustomView_endText)
            startTextView.textSize = getDimension(R.styleable.PlayProCustomView_sizeOfText, 70F)
            endTextView.textSize = getDimension(R.styleable.PlayProCustomView_sizeOfText, 70F)

        }
    }

    fun setup(diamondAnimationProperties: DiamondAnimationProperties? = null, proTextProperties: ProTextProperties? = null) {
        if (this.diamondAnimationProperties != null) return // prevent call the method again
        this.diamondAnimationProperties = diamondAnimationProperties
        diamondAnimation= DiamondAnimation(this.diamondAnimationProperties!!)
        startTextView.measureViewSizeInRunTIme(object : OnGlobalLayoutMeasured {
            override fun onMeasured(with: Int, height: Int) {
                if (startTextView.tag != null) return
                startTextView.tag = 0
                imageView.layoutParams.height = (height * 1.3F).toInt()
                imageView.requestLayout()
                performAnimation(object :IAnimationDone{
                    override fun done() {

                    }

                })
            }
        })
    }
    fun performAnimation(iAnimationDone: IAnimationDone) {
        if (diamondAnimation == null) return
        diamondAnimation?.startAnimation(view,iAnimationDone)
    }

}