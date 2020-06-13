package com.example.muvies.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.muvies.R
import com.example.muvies.databinding.TopRatedTvListBinding
import com.example.muvies.model.TopRatedTVResult
import kotlinx.android.synthetic.main.top_rated_tv_list.view.*

class TopRatedTvListAdapter(private var list: MutableList<TopRatedTVResult>):
    RecyclerView.Adapter<TopRatedTvListAdapter.TopRatedTvListViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedTvListViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return TopRatedTvListViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: TopRatedTvListViewHolder, position: Int) {
        val tv: TopRatedTVResult = list[position]

        holder.bind(tv)
    }

    fun updateTopRatedTvList(tvList: MutableList<TopRatedTVResult>) {
        list = tvList
        notifyDataSetChanged()
    }

    class TopRatedTvListViewHolder(inflater: LayoutInflater, parent: ViewGroup):
        RecyclerView.ViewHolder(inflater.inflate(R.layout.top_rated_tv_list, parent, false)) {

        val binding = TopRatedTvListBinding.inflate(inflater)

        private var mImageView: ImageView? = null
        private var mTextView: TextView? = null

        init {
            binding.apply {
                mImageView = itemView.top_rated_tv_recycler_image
                mTextView = itemView.top_rated_tv_recycler_text
            }
        }

        fun bind(tv: TopRatedTVResult) {
            binding.topRatedTvViewModel = tv

            mTextView?.text = tv.name
        }
    }

}