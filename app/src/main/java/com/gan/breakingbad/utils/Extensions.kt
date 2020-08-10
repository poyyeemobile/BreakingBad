package com.gan.breakingbad.utils

import android.app.Activity
import com.gan.breakingbad.BreakingBadApp

val Activity.breakingBadApp: BreakingBadApp
    get() = application as BreakingBadApp