package com.android.presentation.model

import com.android.domain.model.BaseModel

sealed class PresentationVM<T: BaseModel>(val model: T)