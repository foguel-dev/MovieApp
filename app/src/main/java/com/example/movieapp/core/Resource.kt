package com.example.movieapp.core

import java.lang.Exception

sealed class Resource <out T> {
    //Loadind con progress bar
    class Loading<out T>: Resource<T>()
    //data class retornara dato
    data class Success<out T>(val data: T):Resource<T>()
    //estado de error
    data class Error(val exception: Exception):Resource<Nothing>()
}