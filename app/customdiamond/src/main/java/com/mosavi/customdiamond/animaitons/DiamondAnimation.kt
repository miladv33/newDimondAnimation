package com.mosavi.customdiamond.animaitons

import android.animation.Animator
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.constraintlayout.widget.Group
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.mosavi.customdiamond.*
import com.mosavi.customdiamond.listeners.IAnimationDone
import com.mosavi.customdiamond.properties.DiamondAnimationProperties

class DiamondAnimation(var diamondAnimationProperties: DiamondAnimationProperties) {

    private var firstLine: View? = null
    private var secondLine: View? = null
    private var thirdLine: View? = null
    private var iAnimationDone: IAnimationDone? = null

    fun startAnimation(view: View, iAnimationDone: IAnimationDone) {
        this.iAnimationDone = iAnimationDone
        moveDiamond(view, object : IAnimationDone {
            override fun done() {
                view.findViewById<Group>(R.id.text_group).show()
                val ids = view.findViewById<Group>(R.id.text_group).getReferencedViews()
                ids.forEach {
                    changeSizeAnimation(it,FastOutSlowInInterpolator(),0)
                }
            }

        })
//        animateLines(view)
    }

    private fun moveDiamond(view: View, iAnimationDone: IAnimationDone? = null, startFrom: Float = diamondAnimationProperties.startX.toFloat()) {
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
        anim.duration = 1000
        anim.interpolator = diamondAnimationProperties.diamondInterpolator
        view.startAnimation(anim)
    }

    fun Group.getReferencedViews() = referencedIds.map { rootView.findViewById<View>(it) }

    private fun animateLines(view: View) {
        with(view) {
            firstLine = findViewById(R.id.text_group)
            val delay = 100L
            changeSizeAnimation(firstLine!!, diamondAnimationProperties.diamondInterpolator, delay, object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {

                }

                override fun onAnimationEnd(animation: Animator?) {
                    fadeInWhiteDiamond(view)
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {
                }

            })
        }
    }

    private fun fadeInWhiteDiamond(view: View) {
        if (firstLine == null) return
        firstLine?.fadOut(300)
        secondLine?.fadOut(300)
        thirdLine?.fadOut(300)
//        val whiteBackGround = view.findViewById<View>(R.id.white_background)
//        val purpleBackGround = view.findViewById<View>(R.id.purple_background)
//        val whiteDiamond = view.findViewById<View>(R.id.diamond)
//        val purpleDiamond = view.findViewById<View>(R.id.purple_diamond)
//        whiteBackGround.fadeIn()
//        purpleDiamond.fadeIn()
//        whiteDiamond.fadOut()

    }


}