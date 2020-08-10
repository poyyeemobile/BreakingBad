package com.gan.breakingbad.di.module

import com.gan.breakingbad.BreakingBadApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(val breakingBadApp: BreakingBadApp) {

    @Provides
    @Singleton
    fun provideApp() = breakingBadApp

}