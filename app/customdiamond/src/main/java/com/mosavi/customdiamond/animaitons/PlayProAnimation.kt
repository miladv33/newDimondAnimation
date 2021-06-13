package com.mosavi.customdiamond.animaitons

import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
import com.mosavi.customdiamond.*
import com.mosavi.customdiamond.listeners.IAnimationDone
import com.mosavi.customdiamond.properties.DiamondAnimationProperties

class PlayProAnimation(var diamondAnimationProperties: DiamondAnimationProperties) {
    private var iAnimationDone: IAnimationDone? = null
    fun startAnimation(view: View, iAnimationDone: IAnimationDone) {
        this.iAnimationDone = iAnimationDone
        setAnimationDirection(view)
        moveDiamond(view, object : IAnimationDone {
            override fun done() {
                iAnimationDone.done()
                moveTextView(view)
//                changeSizeAnimation(view.findViewById<TextCustomView>(R.id.customStartTextView),FastOutSlowInInterpolator(),0)
            }
        })
    }

    private fun setAnimationDirection(view: View) {
        diamondAnimationProperties.startX =
            if (LocalController.isLayoutRTL(view.findViewById(R.id.root_layout) as ConstraintLayout))
                view.width
            else
                -(view.width)
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
            moveDiamond(this, startFrom = startFrom, startOffset = 0)
        }
    }

    fun Group.getReferencedViews() = referencedIds.map { rootView.findViewById<View>(it) }

}