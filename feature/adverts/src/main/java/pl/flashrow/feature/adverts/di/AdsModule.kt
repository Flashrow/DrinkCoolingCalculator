package pl.flashrow.feature.adverts.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.flashrow.feature.adverts.util.AppOpenAdManager
import pl.flashrow.feature.adverts.util.AppOpenAdManagerInterface
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AdsModule {

    @Binds
    @Singleton
    abstract fun bindAppOpenAdManager(
        appOpenAdManagerImpl: AppOpenAdManager
    ): AppOpenAdManagerInterface
}
