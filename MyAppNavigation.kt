package com.example.loginactivityshared

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.loginactivityshared.Sign.HomePage
import com.example.loginactivityshared.Sign.MainPage
import com.example.loginactivityshared.Sign.SignInPage
import com.example.loginactivityshared.Sign.SignUpPage
import androidx.compose.runtime.getValue


@Composable
fun MyAppNavigation(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel
) {
    val navController = rememberNavController()

    // Add this to prevent multiple recompositions
    val currentRoute by navController.currentBackStackEntryAsState()
    val currentRouteName = currentRoute?.destination?.route

    LaunchedEffect(authViewModel.authState) {
        authViewModel.authState.observeForever { state ->
            when (state) {
                is AuthState.Authenticated -> {
                    if (currentRouteName != Route.MainPage.route) {
                        navController.navigate(Route.MainPage.route) {
                            popUpTo(Route.Home.route) { inclusive = true }
                        }
                    }
                }
                is AuthState.Unauthenticated -> {
                    if (currentRouteName != Route.Home.route) {
                        navController.navigate(Route.Home.route) {
                            popUpTo(0)
                        }
                    }
                }
                else -> Unit
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Route.Home.route,
        modifier = modifier
    ) {
        composable(Route.Home.route) {
            HomePage(
                navController = navController,
                authViewModel = authViewModel,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(Route.SignUp.route) {
            SignUpPage(
                navController = navController,
                authViewModel = authViewModel,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(Route.SignIn.route) {
            SignInPage(
                navController = navController,
                authViewModel = authViewModel,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(Route.MainPage.route) {
            MainPage(
                navController = navController,
                authViewModel = authViewModel,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}