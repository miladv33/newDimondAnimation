package com.mosavi.customdiamond.properties

import android.view.animation.Interpolator
import android.view.animation.OvershootInterpolator
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator

enum class DiamondAnimationProperties(
    var lineDelay: Int,
    var visibleWhiteDiamondDelay: Int,
    var duration: Int,
    var diamondInterpolator: Interpolator,
    var textInterpolator: Interpolator,
    var startX: Int,
    var isRTL:Boolean,
    var endX:Int
) {
    Fast(1000, 1000, 1000, FastOutSlowInInterpolator(), FastOutSlowInInterpolator(), -300,false,0)
}