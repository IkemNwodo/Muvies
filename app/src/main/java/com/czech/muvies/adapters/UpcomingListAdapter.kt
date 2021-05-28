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
import com.czech.muvies.databinding.UpcomingListBinding
import com.czech.muvies.models.Movies
import kotlinx.android.synthetic.main.upcoming_list.view.*

typealias upcomingSItemClickListener = (Movies.MoviesResult) -> Unit

class UpcomingListAdapter(private var list: List<Movies.MoviesResult>, private val clickListener: upcomingSItemClickListener):
    RecyclerView.Adapter<UpcomingListAdapter.UpcomingListViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingListViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return UpcomingListViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: UpcomingListViewHolder, position: Int) {
        val movie: Movies.MoviesResult = list[position]

        holder.bind(movie)
    }

    fun updateUpcomingList(movieList: List<Movies.MoviesResult>) {
        list = movieList
        notifyDataSetChanged()
    }

    inner class UpcomingListViewHolder(inflater: LayoutInflater, parent: ViewGroup):
        RecyclerView.ViewHolder(inflater.inflate(R.layout.upcoming_list, parent, false)), View.OnClickListener {

        private val binding = UpcomingListBinding.inflate(inflater)

        private var poster: ImageView = itemView.upcoming_recycler_image
        private var title: TextView = itemView.upcoming_recycler_text

        fun bind(movie: Movies.MoviesResult) {
            binding.upcomingListViewModel = movie

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
            val upcoming = list[adapterPosition]
            clickListener.invoke(upcoming)
        }
    }
}