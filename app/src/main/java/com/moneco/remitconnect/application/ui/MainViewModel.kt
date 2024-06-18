package com.moneco.remitconnect.application.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneco.remitconnect.application.domaine.entites.User
import com.moneco.remitconnect.application.domaine.useCase.user.UserUseCase
import com.moneco.remitconnect.application.navigation.AppNavigator
import com.moneco.remitconnect.helpers.NavigationConstant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class for the main screen of the app.
 * This class is responsible for handling user interactions and updating the UI.
 *
 * @property appNavigator Handles navigation within the app.
 * @property userUseCase Executes user-related business logic.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val userUseCase: UserUseCase) : ViewModel() {

    // Exposes the navigation channel from the app navigator.
    val navigationChannel = appNavigator.navigationChannel

    /*
     * Initializes the ViewModel.
     */
    init {
        // Launches a coroutine in the ViewModel's scope to execute the user use case.
        viewModelScope.launch {
            userUseCase.execute(input = User(id = 1,
                                             name = "Jean Luc",
                                             email = "hrluc13@gmail.com",
                                             balance =  320.00)
            )
        }
    }

    /**
     * Navigates to the home screen.
     */
    fun navigationHome() {
        viewModelScope.launch {
            appNavigator.navigateBack(NavigationConstant.HOME)
        }
    }

}