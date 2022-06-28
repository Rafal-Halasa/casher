package com.simcodic.casher.logic.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.simcodic.casher.R

fun AppCompatActivity.setNavigation() {
    NavigationState.state.observe(this) {
        it?.let {
            findNavController(R.id.nav_host_fragment).apply {
                if (checkIfNewDestination(this, it)) {
                    navigate(
                        it.resId, it.bundle
                    )
                    NavigationState.state.value = null
                }
            }
        }
    }
}

fun checkIfNewDestination(navController: NavController, menuStates: MenuStates) =
    navController.currentDestination?.id != menuStates.resId

