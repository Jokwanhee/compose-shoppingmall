package com.android.di

import com.android.data.datasource.TempDataSource
import com.android.data.repository.TempRepositoryImpl
import com.android.domain.repository.TempRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Singleton
    @Binds
    fun bindTempRepository(
        tempRepositoryImpl: TempRepositoryImpl
    ): TempRepository
}