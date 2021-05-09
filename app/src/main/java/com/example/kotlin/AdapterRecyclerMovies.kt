package com.example.kotlin


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.Model.MovieModel
import com.squareup.picasso.Picasso


class AdapterRecyclerMovies(private var data: List<MovieModel>, var context: Context): RecyclerView.Adapter<AdapterRecyclerMovies.MainViewHolder>(){
    var list = data
    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var movieImage: ImageView = itemView.findViewById(R.id.movieImage)
        var movieName: TextView = itemView.findViewById(R.id.movieName)
        var movieGenres: TextView = itemView.findViewById(R.id.movieGenres)
        var movieYear: TextView = itemView.findViewById(R.id.movieYear)
        var cardViewMoviesList: CardView = itemView.findViewById(R.id.cardViewMoviesList)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val view =LayoutInflater.from(parent.context)
            .inflate(R.layout.item_detail_rcycler,parent, false)
        return MainViewHolder(view)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        list[position].medium_cover_image?.let {
            Picasso.get().load(it).into(holder.movieImage)
        }
        list[position].title?.let {
            holder.movieName.text= it
        }
        list[position].genres?.toString().let {
               holder.movieGenres.text= it
        }
        list[position].year?.toString().let {
            holder.movieYear.text=it
        }



        holder.cardViewMoviesList.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, MovieDetailPageActivity::class.java)
            intent.putExtra(    "id",list[position].id)
            context.startActivity(intent)
        })





    }
}