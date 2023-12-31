package com.android.presentation.model

import com.android.domain.model.BannerList
import com.android.presentation.delegate.BannerDelegate

class BannerListVM(model: BannerList, private val bannerDelegate: BannerDelegate) :
    PresentationVM<BannerList>(model), BannerDelegate by bannerDelegate {
    fun openBannerList(bannerId: String) {
        bannerDelegate.openBanner(bannerId)
    }
}