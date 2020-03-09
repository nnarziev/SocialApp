package com.nematjon.socialapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.sql.Blob

class ProfileViewModel : ViewModel() {

    private val _userData = MutableLiveData<UserData>().apply {
        value = loadUserData()
    }

    fun getUserData(): LiveData<UserData> {
        return _userData
    }

    private fun loadUserData(): UserData {
        //TODO: load user data from server
        return UserData("John", "Male", "1995-09-09", "Incheon","My temporary Bio", null)
    }

    class UserData(
        val username: String?,
        val gender: String?,
        val birthday: String?,
        val location: String?,
        val bio: String?,
        val photo: Blob?
    )
}