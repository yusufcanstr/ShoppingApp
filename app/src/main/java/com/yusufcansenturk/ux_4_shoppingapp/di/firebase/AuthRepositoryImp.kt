package com.yusufcansenturk.ux_4_shoppingapp.di.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.yusufcansenturk.ux_4_shoppingapp.utils.AuthResource
import com.yusufcansenturk.ux_4_shoppingapp.utils.await
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun login(email: String, password: String): AuthResource<FirebaseUser> {
        val result = try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            AuthResource.Success(result.user!!)
        }catch (e:Exception) {
            e.printStackTrace()
            AuthResource.Failure(e)
        }
        return result
    }

    override suspend fun signup(name: String, email: String, password: String ): AuthResource<FirebaseUser> {
        val result = try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result?.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())?.await()
            AuthResource.Success(result.user!!)
        }catch (e:Exception) {
            e.printStackTrace()
            AuthResource.Failure(e)
        }
        return result
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}