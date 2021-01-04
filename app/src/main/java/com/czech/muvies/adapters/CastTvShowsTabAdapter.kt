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
import com.czech.muvies.models.PersonMovies
import com.czech.muvies.models.PersonTvShows
import kotlinx.android.synthetic.main.paged_list.view.*
import kotlinx.android.synthetic.main.similar_list.view.*

class CastTvShowsTabAdapter(private var list: List<PersonTvShows.Cast>):
    RecyclerView.Adapter<CastTvShowsTabAdapter.CastTvShowsTabViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastTvShowsTabViewHolder {
        return CastTvShowsTabViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.paged_list, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CastTvShowsTabViewHolder, position: Int) {
        val movie = list[position]
        holder.bind(movie)
    }

    fun updateList(tvShowList: List<PersonTvShows.Cast>) {
        list = tvShowList
        notifyDataSetChanged()
    }

    inner class CastTvShowsTabViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private var poster: ImageView = itemView.poster_image
        private var title: TextView = itemView.title
        private var date: TextView = itemView.date
        private var rating: TextView = itemView.vote

        fun bind(tvShow: PersonTvShows.Cast) {
            Glide.with(itemView)
                .load("$BASE_IMAGE_PATH${tvShow.posterPath}")
                .placeholder(R.drawable.poster_placeholder)
                .error(R.drawable.poster_error)
                .into(poster)

            title.text = tvShow.originalName
            date.text = tvShow.firstAirDate
            rating.text = tvShow.voteAverage.toString()
        }
    }

}