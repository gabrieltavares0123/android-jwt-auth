package io.github.gabriel.androidjwtauth.auth

interface AuthRepository {
    suspend fun signUp(name: String, password: String): AuthResult<Unit>
    suspend fun signIn(name: String, password: String): AuthResult<Unit>
    suspend fun authenticate(): AuthResult<Unit>
}