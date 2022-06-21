package io.github.gabriel.androidjwtauth.auth

import android.content.SharedPreferences
import retrofit2.HttpException

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val prefs: SharedPreferences,
) : AuthRepository {
    override suspend fun signUp(name: String, password: String): AuthResult<Unit> {
        return try {
            api.signUp(
                request = AuthRequest(
                    name = name,
                    password = password,
                )
            )
            signIn(name, password)
        } catch (e: HttpException) {
            if (e.code() == UNAUTORIZED) {
                AuthResult.Authorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    override suspend fun signIn(name: String, password: String): AuthResult<Unit> {
        return try {
            val response = api.signIn(
                request = AuthRequest(
                    name = name,
                    password = password,
                )
            )

            prefs.edit()
                .putString(JWT_TOKEN, response.token)
                .apply()

            AuthResult.Authorized()
        } catch (e: HttpException) {
            if (e.code() == UNAUTORIZED) {
                AuthResult.Authorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    override suspend fun authenticate(): AuthResult<Unit> {
        return try {
            val token = prefs.getString(JWT_TOKEN, null) ?: return AuthResult.Unauthorized()
            api.authenticate("Bearer $token")
            AuthResult.Authorized()
        } catch (e: HttpException) {
            if (e.code() == UNAUTORIZED) {
                AuthResult.Authorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    private companion object {
        val UNAUTORIZED = 401
        val JWT_TOKEN = "jwt-token"
    }
}