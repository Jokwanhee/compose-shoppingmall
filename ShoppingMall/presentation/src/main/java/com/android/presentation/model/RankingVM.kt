package com.android.presentation.model

import androidx.navigation.NavHostController
import com.android.domain.model.Product
import com.android.domain.model.Ranking
import com.android.presentation.delegate.ProductDelegate

class RankingVM(model: Ranking, private val productDelegate: ProductDelegate) :
    PresentationVM<Ranking>(model) {

    fun openRankingProduct(navHostController: NavHostController, product: Product) {
        productDelegate.openProduct(navHostController, product)
        sendRankingLog()
        // +@
    }

    private fun sendRankingLog() {

    }
}
