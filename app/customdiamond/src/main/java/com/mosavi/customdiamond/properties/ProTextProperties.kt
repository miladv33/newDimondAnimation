package com.mosavi.customdiamond.properties

import android.view.animation.Interpolator
import android.view.animation.OvershootInterpolator
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator

enum class ProTextProperties(
    var startText: String,
    var endText: String,
   var textSize:Float,
   var viewWith:Float,
   var screenSize: Float,
   var startTextMargin: Float
) {
    RELATIVE_TO_SCREEN("","",0.032F, 0.78F,1080F,100F)
}