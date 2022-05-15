package com.example.poonamtiwari_itunes.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.poonamtiwari_itunes.R
import com.example.poonamtiwari_itunes.SongDetails
import com.example.poonamtiwari_itunes.model.DBModel
import com.example.poonamtiwari_itunes.model.Result

import com.squareup.picasso.Picasso

// create our ViewHolder
// bind the data to the ViewHolder
class RockMusicAdapter(private val list: List<DBModel>) :
    RecyclerView.Adapter<RockMusicAdapter.MusicViewHolder>() {

    lateinit var context: Context

    // binding our data, to our view
    inner class MusicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(result: DBModel) {
            val tvCollectionName: TextView = itemView.findViewById(R.id.tv_collectionName)
            val tvArtistName: TextView = itemView.findViewById(R.id.tv_artistName)
            val tvPrice: TextView = itemView.findViewById(R.id.tv_price)
            val ivArtImage = itemView.findViewById<ImageView>(R.id.iv_artImage)


            tvCollectionName.text = result.collectionName
            tvArtistName.text = result.artistName
            tvPrice.text = result.trackPrice.toString()
            Picasso.get()
                .load(result.artworkUrl60)
                .placeholder(R.drawable.ic_launcher_foreground)
                .fit()
                .into(ivArtImage)

            itemView.setOnClickListener {
                if (MainActivity.isOnline(context)) {
                    MainActivity.playSong(result.previewUrl)
                } else {
                    MainActivity.makeToastmsg("No Internet!", context)
                }
                val intent= Intent(context,SongDetails::class.java)
                val bundle =Bundle()
                bundle.putString("KEY",result.artworkUrl60)
                bundle.putString("c_name",result.collectionName)
                bundle.putString("aName",result.artistName)
                bundle.putString("art",result.artworkUrl60)
                bundle.putString("price",result.trackPrice.toString())
                bundle.putString("type",result.trackName)
                intent.putExtras(bundle)
                startActivity(context,intent,bundle)
            }

        }
    }

    // we inflate our list item and pass it to our ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.song_list, parent, false)
        Log.d("RV_Call", "CreateViewHolder was called")
        return MusicViewHolder(view)
    }

    // this is where we bind the data to the view
    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.onBind(list[position])

    }

    // return the size of the list
    override fun getItemCount(): Int {
        return list.size
    }
}