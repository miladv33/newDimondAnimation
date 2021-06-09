package com.mosavi.customdiamond

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.mosavi.customdiamond.animaitons.DiamondAniamton
import com.mosavi.customdiamond.listeners.IAnimationDone
import com.mosavi.customdiamond.properties.DiamondAnimationProperties

class DiamondCustomView(context: Context,attributeSet: AttributeSet?):FrameLayout(context,attributeSet) {
    private var view: View = LayoutInflater.from(context).inflate(R.layout.diamond_image, this, true)
    private var diamondAniamton: DiamondAniamton? = null
    private var diamondAnimationProperties: DiamondAnimationProperties? = null

    fun setup(diamondAnimationProperties: DiamondAnimationProperties? = null) {
        if (diamondAnimationProperties == null) return
        this.diamondAnimationProperties = diamondAnimationProperties
        this.diamondAniamton = DiamondAniamton(diamondAnimationProperties)
    }

    fun performAnimation(iAnimationDone: IAnimationDone? = null) {
        diamondAniamton?.startAnimation(view, iAnimationDone)
    }

}