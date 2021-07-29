package com.example.movieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.movieapp.core.Resource
import com.example.movieapp.repository.MovieRepository
import kotlinx.coroutines.Dispatchers

class MovieViewModel(private val repo:MovieRepository): ViewModel() {

    //Forma optmizada(se retorna una forma mas ordenada, 3)

    fun fetchMainScreenMovies() = liveData(Dispatchers.IO){
        //Informacion se esta cargando
        //Emitar datos a la vista(emitir livedata)
        emit(Resource.Loading())

        try {
            emit(Resource.Success(Triple(repo.getUpcomingMovies(),repo.getTopRateMovies(),repo.getPopularMovies())))
        }catch (e: Exception){
            emit(Resource.Error(e))
        }
    }

    //La formo incorrecta

    /*fun fetchTopRateMovies() = liveData(Dispatchers.IO){
        //Informacion se esta cargando
        //Emitar datos a la vista(emitir livedata)
        emit(Resource.Loading())

        try {
            emit(Resource.Success(repo.getTopRateMovies()))
        }catch (e: Exception){
            emit(Resource.Error(e))
        }
    }

    fun fetchPopularMovies() = liveData(Dispatchers.IO){
        //Informacion se esta cargando
        //Emitar datos a la vista(emitir livedata)
        emit(Resource.Loading())

        try {
            emit(Resource.Success(repo.getPopularMovies()))
        }catch (e: Exception){
            emit(Resource.Error(e))
        }
    }*/
}

class MovieViewModelFactory(private val repo:MovieRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }
}