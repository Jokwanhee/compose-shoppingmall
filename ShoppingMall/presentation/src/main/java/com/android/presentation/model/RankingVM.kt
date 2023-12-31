package com.android.presentation.model

import com.android.domain.model.Product
import com.android.domain.model.Ranking
import com.android.presentation.delegate.ProductDelegate

class RankingVM(model: Ranking, private val productDelegate: ProductDelegate) :
    PresentationVM<Ranking>(model) {

    fun openRankingProduct(product: Product) {
        productDelegate.openProduct(product)
        sendRankingLog()
        // +@
    }

    private fun sendRankingLog() {

    }
}
