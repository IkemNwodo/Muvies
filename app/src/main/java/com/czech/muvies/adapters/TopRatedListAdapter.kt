package com.czech.muvies.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.czech.muvies.BASE_IMAGE_PATH
import com.czech.muvies.R
import com.czech.muvies.databinding.TopRatedListBinding
import com.czech.muvies.models.Movies
import kotlinx.android.synthetic.main.top_rated_list.view.*

typealias topRatedSItemClickListener = (Movies.MoviesResult) -> Unit

class TopRatedListAdapter(private var list: MutableList<Movies.MoviesResult>, private val clickListener: topRatedSItemClickListener):
    RecyclerView.Adapter<TopRatedListAdapter.TopRatedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return TopRatedViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        val movie: Movies.MoviesResult = list[position]

        holder.bind(movie)
    }

    fun updateTopRatedList(movieList: MutableList<Movies.MoviesResult>){
        list = movieList
        notifyDataSetChanged()
    }

    inner class TopRatedViewHolder(inflater: LayoutInflater, parent: ViewGroup):
        RecyclerView.ViewHolder(inflater.inflate(R.layout.top_rated_list, parent, false)), View.OnClickListener {

        private val binding = TopRatedListBinding.inflate(inflater)

        private var poster: ImageView = itemView.top_rated_recycler_image
        private var title: TextView = itemView.top_rated_recycler_text

        fun bind(movie: Movies.MoviesResult) {
            binding.topRatedViewModel = movie

            title.text = movie.title
            Glide.with(itemView)
                .load("$BASE_IMAGE_PATH${movie.posterPath}")
                .placeholder(R.drawable.poster_placeholder)
                .error(R.drawable.poster_error)
                .into(poster)
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val topRated = list[adapterPosition]
            clickListener.invoke(topRated)
        }

    }
}