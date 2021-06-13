package com.mosavi.customdiamond.animaitons

import android.animation.Animator
import android.media.Image
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import androidx.constraintlayout.widget.Group
import com.mosavi.customdiamond.*
import com.mosavi.customdiamond.listeners.IAnimationDone
import com.mosavi.customdiamond.properties.DiamondAnimationProperties

class DiamondAniamton(var diamondAnimationProperties: DiamondAnimationProperties) {

    private var iAnimationDone: IAnimationDone? = null

    fun startAnimation(view: View,iAnimationDone: IAnimationDone? = null){
        this.iAnimationDone = iAnimationDone
        fadeInWhiteDiamond(view)
        changeWidthAndFadeShadows(view)
    }

   private fun fadeInWhiteDiamond(view: View) {
        view.findViewById<ImageView>(R.id.white_background).fadeIn(startOffset = 1000)
        view.findViewById<ImageView>(R.id.purple_diamond).fadeIn(startOffset = 1000)
        view.findViewById<ImageView>(R.id.white_purple_diamond).fadOut(startOffset = 1000)
    }
    private fun changeWidthAndFadeShadows(view:View){
        view.findViewById<ImageView>(R.id.line_purple_background).fadOut(startOffset = 300,duration = 800)
    }
}