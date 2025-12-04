package com.example.geradoravatar

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AvatarApiService {
    @GET("avatares")
    fun listarAvatares(): Call<List<Avatar>>

    @POST("avatares")
    fun criarAvatar(@Body request: AvatarRequest): Call<Void>

    @PUT("avatares/{id}")
    fun editarAvatar(@Path("id") id: Int, @Body request: AvatarRequest): Call<Void>

    @DELETE("avatares/{id}")
    fun deletarAvatar(@Path("id") id: Int): Call<Void>
}