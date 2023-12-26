package com.android.shoppingmall

import com.android.data.repository.MainRepositoryImpl
import com.android.data.repository.TempRepositoryImpl
import com.android.domain.repository.MainRepository
import com.android.domain.repository.TempRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindTempRepository(
        tempRepositoryImpl: TempRepositoryImpl
    ): TempRepository

    @Binds
    @Singleton
    fun bindMainRepository(
        mainRepositoryImpl: MainRepositoryImpl
    ): MainRepository
}