package com.husseinrasti.coinecho

import androidx.navigation.NavController
import com.husseinrasti.detail_page.ModuleDetailNavigation
import com.husseinrasti.market.ModuleMarketNavigation

object Navigator: ModuleMarketNavigation, ModuleDetailNavigation {

    private var navController: NavController? = null

    // bind in onResume for activity implementing the graph
    fun bind(navController: NavController) {
        this.navController = navController
    }

    // bind in onPause for activity implementing the graph
    fun unbind() {
        this.navController = null
    }

    override fun navigateToFragment1() {
        TODO("Not yet implemented")
    }

    override fun navigateToFragment2(arg1: String, arg2: Int) {
        TODO("Not yet implemented")
    }

}