package hash.application.managers

object UserManager {
    private var username: String = "Guest"
    private var isLogin: Boolean = false

    fun getUsername(): String {
        return username
    }

    fun setUsername(name: String) {
        username = name
    }

    fun getLoginState(): Boolean {
        return isLogin
    }

    fun changeLoginState(state: Boolean) {
        isLogin = state
    }
}