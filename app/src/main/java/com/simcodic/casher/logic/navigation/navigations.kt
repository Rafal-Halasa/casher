package com.simcodic.casher.logic.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.simcodic.casher.R

fun AppCompatActivity.setNavigation() {
    NavigationState.state.observeForever {
        try {
            findNavController(R.id.nav_host_fragment).apply {
                if (checkIfNewDestination(this, it)) {
                    navigate(
                        it.resId, it.bundle
                    )
                }
            }
        } catch (illegalStateException: IllegalStateException) {
            println("IllegalStateException")
            //TODO something wrong with this checkIfNewDestination when screen is rotating
        }
    }
}

fun checkIfNewDestination(navController: NavController, menuStates: MenuStates) =
    navController.currentDestination?.id != menuStates.resId

