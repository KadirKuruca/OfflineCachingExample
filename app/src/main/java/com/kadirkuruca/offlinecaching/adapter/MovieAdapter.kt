package com.kadirkuruca.offlinecaching.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kadirkuruca.offlinecaching.data.model.Movie
import com.kadirkuruca.offlinecaching.databinding.MovieItemBinding

class MovieAdapter: ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = getItem(position)
        if(currentItem != null)
            holder.bind(currentItem)
    }

    class MovieViewHolder(private val binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie){
            binding.apply {
                movie.poster_url?.let {
                    Glide.with(itemView)
                        .load(movie.poster_url)
                        .into(ivLogo)
                }
                
                tvMovieName.text = movie.original_title
                tvOverview.text = movie.overview
                tvRate.text = movie.vote_average.toString()
                tvReleaseDate.text = movie.release_date
            }
        }
    }

    class MovieDiffUtil: DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}