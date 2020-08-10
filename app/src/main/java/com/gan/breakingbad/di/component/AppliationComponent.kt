package com.gan.breakingbad.di.component

import com.gan.breakingbad.BreakingBadApp
import com.gan.breakingbad.di.module.ApplicationModule
import com.gan.breakingbad.di.module.HomeModule
import com.gan.breakingbad.di.module.NetworkAPIModule
import com.gan.breakingbad.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApplicationModule::class),(HomeModule::class),(NetworkAPIModule::class)])
interface ApplicationComponent {

    fun inject(application: BreakingBadApp)
    fun inject(activity: MainActivity)
}