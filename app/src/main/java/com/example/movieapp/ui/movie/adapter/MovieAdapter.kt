package com.example.movieapp.ui.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.core.BaseViewHolder
import com.example.movieapp.data.model.Movie
import com.example.movieapp.databinding.MovieItemBinding

class MovieAdapter (private val moviesList:List<Movie>, private val itemClickListener: onMovieClickListener) :RecyclerView.Adapter<BaseViewHolder<*>>() {
    //Agarra cada click
    interface onMovieClickListener{
        fun onMovieClick(movie: Movie)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        //Accede a la vista
        val itemBiding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = MoviesViewHolder(itemBiding, parent.context)

        //Click de cada elemento
        itemBiding.root.setOnClickListener{
            val position = holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            itemClickListener.onMovieClick(moviesList[position])
        }
        return holder
    }
    //Elementos se dibujan en pantalla
    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MoviesViewHolder -> holder.bind(moviesList[position])
        }
    }
    //Cantidad de peliculas
    override fun getItemCount(): Int = moviesList.size

    //Pone la imagen a cada unos de los elementos
    //inner cuando muera la instancia de movieadapter, el muera y no quede una instancia de memoria no usadaz
    private inner class MoviesViewHolder(
        val binding: MovieItemBinding,
        val context: Context
        ): BaseViewHolder<Movie>(binding.root){
        override fun bind(item: Movie) {
            Glide.with(context).load("https://image.tmdb.org/t/p/w500/${item.poster_path}").centerCrop().into(binding.ivMovie)
        }

    }
}