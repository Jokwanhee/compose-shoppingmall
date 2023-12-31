package com.android.presentation.model

import com.android.domain.model.Banner
import com.android.presentation.delegate.BannerDelegate

class BannerVM(model: Banner, bannerDelegate: BannerDelegate) :
    PresentationVM<Banner>(model),
    BannerDelegate by bannerDelegate