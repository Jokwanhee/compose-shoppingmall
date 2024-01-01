package com.android.shoppingmall

import com.android.data.repository.CategoryRepositoryImpl
import com.android.data.repository.MainRepositoryImpl
import com.android.data.repository.ProductDetailRepositoryImpl
import com.android.data.repository.TempRepositoryImpl
import com.android.domain.repository.CategoryRepository
import com.android.domain.repository.MainRepository
import com.android.domain.repository.ProductDetailRepository
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

    @Binds
    @Singleton
    fun bindCategoryRepository(
        categoryRepositoryImpl: CategoryRepositoryImpl
    ): CategoryRepository

    @Binds
    @Singleton
    fun bindProductDetailRepository(
        productDetailRepositoryImpl: ProductDetailRepositoryImpl
    ): ProductDetailRepository
}