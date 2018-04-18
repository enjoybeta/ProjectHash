package hash.application.managers

import hash.application.dataType.User

object UserManager {
    private val user : User = User("Guest","", "", "")
    private var isLogin: Boolean = false

    fun getUser(): User {
        return user
    }

    fun getUsername(): String {
        return user.username
    }

    fun setUsername(name: String) {
        user.username = name
    }

    fun setPassword(password: String) {
        user.password = password
    }

    fun getLoginState(): Boolean {
        return isLogin
    }

    fun changeLoginState(state: Boolean) {
        isLogin = state
    }
}