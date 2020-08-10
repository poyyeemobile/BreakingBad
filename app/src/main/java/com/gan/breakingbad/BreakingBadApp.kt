package com.gan.breakingbad

import android.app.Application
import com.gan.breakingbad.di.component.ApplicationComponent
import com.gan.breakingbad.di.component.DaggerApplicationComponent
import com.gan.breakingbad.di.module.ApplicationModule
import com.gan.breakingbad.di.module.HomeModule
import com.gan.breakingbad.di.module.NetworkAPIModule

class BreakingBadApp: Application(){


    val component: ApplicationComponent by kotlin.lazy {

        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .homeModule(HomeModule())
            .networkAPIModule(NetworkAPIModule())
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}