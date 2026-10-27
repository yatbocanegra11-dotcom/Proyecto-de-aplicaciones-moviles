package com.example.actividad.model

//archivo para verificar si obtenemos una respuesta del API

data class ITunesResponse(
    val resultCount: Int,
    val results: List<Album>
)
