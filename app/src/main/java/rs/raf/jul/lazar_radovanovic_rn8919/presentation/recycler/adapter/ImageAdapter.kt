package rs.raf.jul.lazar_radovanovic_rn8919.presentation.recycler.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.reactivex.annotations.NonNull
import rs.raf.jul.lazar_radovanovic_rn8919.R

internal class ImageAdapter(private var itemsList: List<String>) :
    RecyclerView.Adapter<ImageAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.imgvv)
    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_vh, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        val cont=holder.itemView.context;
        Picasso.with(cont).load(item).into(holder.image)
    }
    override fun getItemCount(): Int {
        return itemsList.size
    }
}