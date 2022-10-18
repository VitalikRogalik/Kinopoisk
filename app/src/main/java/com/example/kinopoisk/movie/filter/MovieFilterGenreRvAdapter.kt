package com.example.kinopoisk.movie.filter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kinopoisk.R
import com.example.kinopoisk.network.response.GetFilterResponse

class MovieFilterGenreRvAdapter(private val filterList: List<Any>):
    RecyclerView.Adapter<MovieFilterGenreRvAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie_filter_genre_selection_item, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (filterList[position] is GetFilterResponse.Genre) {
            holder.textView.text = (filterList[position] as GetFilterResponse.Genre).genre
        }
        else{
            holder.textView.text = (filterList[position] as GetFilterResponse.Country).country
        }
    }

    override fun getItemCount(): Int {
        return filterList.size
    }

}