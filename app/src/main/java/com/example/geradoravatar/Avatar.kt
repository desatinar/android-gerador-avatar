package com.example.geradoravatar

data class Avatar(
    val id: Int?,
    val prompt: String,
    val caminho_imagem: String
)

data class AvatarRequest(
    val prompt: String
)