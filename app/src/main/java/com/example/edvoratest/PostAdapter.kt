package com.example.edvoratest

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideContext
import java.util.concurrent.Executors


class PostAdapter( val postModel: MutableList<PostModel>):RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.card,parent,false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        return holder.bindView(postModel[position])
    }

    override fun getItemCount(): Int {
        return postModel.size
    }
}
class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val id = itemView.findViewById<TextView>(R.id.ride_id_002)
    private val image = itemView.findViewById<ImageView>(R.id.image_1)
    private val city = itemView.findViewById<TextView>(R.id.city_name)
    private val origin = itemView.findViewById<TextView>(R.id.origin_stat)
    private val station = itemView.findViewById<TextView>(R.id.station_pat)
    private val date = itemView.findViewById<TextView>(R.id.date_15th_f)
    private val distance = itemView.findViewById<TextView>(R.id.distance_0)
    private val state=itemView.findViewById<TextView>(R.id.state_name)
    val executor = Executors.newSingleThreadExecutor()
    val handler = Handler(Looper.getMainLooper())
    var img: Bitmap? = null
    fun bindView(postModel: PostModel) {
        id.text = "Ride ID: " + postModel.id.toString()
        city.text =  postModel.city
        origin.text = "Station Code: " + postModel.origin_station_code
        station.text = "Station path: " + postModel.station_path?.joinToString(
            prefix = "[",
            separator = "-",
            postfix = "]",
        )
        var dist:Int =Integer.MAX_VALUE
                for( item in postModel.station_path!!){
                dist=Math.min(Math.abs(Integer.parseInt(postModel.origin_station_code)-item),dist)
        }
        date.text = "Date: " + postModel.date
        distance.text = "Distance: " + "${dist}"
        state.text=postModel.state
        executor.execute {

            // Image URL
            val imageURL = postModel.map_url

            // Tries to get the image and post it in the ImageView
            // with the help of Handler
            try {
                val `in` = java.net.URL(imageURL).openStream()
                img = BitmapFactory.decodeStream(`in`)

                // Only for making changes in UI
                handler.post {
                    image.setImageBitmap(img)
                }
            }

            // If the URL doesnot point to
            // image or any other kind of failure
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}