package com.gan.breakingbad.di.module

import com.gan.breakingbad.service.BreakingBadDataService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkAPIModule {

    @Provides
    @Singleton
    fun provideNetworkApiService() = BreakingBadDataService.create()
}