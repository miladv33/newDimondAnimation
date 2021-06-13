package com.mosavi.customdiamond.animaitons

import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.constraintlayout.widget.Group
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.mosavi.customdiamond.R
import com.mosavi.customdiamond.TextCustomView
import com.mosavi.customdiamond.changeSizeAnimation
import com.mosavi.customdiamond.listeners.IAnimationDone
import com.mosavi.customdiamond.properties.DiamondAnimationProperties
import com.mosavi.customdiamond.show

class PlayProAnimation(var diamondAnimationProperties: DiamondAnimationProperties) {
    private var iAnimationDone: IAnimationDone? = null
    fun startAnimation(view: View, iAnimationDone: IAnimationDone) {
        this.iAnimationDone = iAnimationDone
        moveDiamond(view, object : IAnimationDone {
            override fun done() {
                iAnimationDone.done()
                moveTextView(view)
//                changeSizeAnimation(view.findViewById<TextCustomView>(R.id.customStartTextView),FastOutSlowInInterpolator(),0)
            }
        })
    }

    private fun moveDiamond(view: View, iAnimationDone: IAnimationDone? = null, startFrom: Float = diamondAnimationProperties.startX.toFloat(), startOffset: Long = 0) {
        view.show()
        val anim = TranslateAnimation(startFrom, 0f, 0F, 0f)
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
            }

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {
                iAnimationDone?.done()
            }
        })
        anim.startOffset = startOffset
        anim.duration = 1000
        anim.interpolator = diamondAnimationProperties.diamondInterpolator
        view.startAnimation(anim)
    }

    fun moveTextView(view: View, iAnimationDone: IAnimationDone? = null, startFrom: Float = diamondAnimationProperties.startX.toFloat(), startOffset: Long = 0) {


        val anim = TranslateAnimation(startFrom, 0f, 0F, 0f)
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
            }

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {
                iAnimationDone?.done()
            }
        })
        anim.startOffset = startOffset
        anim.duration = diamondAnimationProperties.duration
        anim.interpolator = diamondAnimationProperties.diamondInterpolator
        with(view.findViewById<TextCustomView>(R.id.customStartTextView)) {
            moveDiamond(this, startFrom = (-(view.width)).toFloat(), startOffset = 0)
        }
    }

    fun Group.getReferencedViews() = referencedIds.map { rootView.findViewById<View>(it) }

}