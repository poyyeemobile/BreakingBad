package com.gan.breakingbad.di.module

import com.gan.breakingbad.ui.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    fun providesMainPresenter() = MainPresenter()


}