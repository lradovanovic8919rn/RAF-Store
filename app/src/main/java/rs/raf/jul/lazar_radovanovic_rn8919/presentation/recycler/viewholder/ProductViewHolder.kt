package rs.raf.jul.lazar_radovanovic_rn8919.presentation.recycler.viewholder

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.extensions.LayoutContainer
import rs.raf.jul.lazar_radovanovic_rn8919.R
import rs.raf.jul.lazar_radovanovic_rn8919.data.models.entities.Product

class ProductViewHolder (
    override val containerView: View,
    onItemClicked: (Int) -> Unit) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    val rating: MaterialTextView = containerView.findViewById(R.id.rating)
    val title: MaterialTextView = containerView.findViewById(R.id.title)
    val price: MaterialTextView = containerView.findViewById(R.id.price)
    init {
        containerView.setOnClickListener {
            onItemClicked.invoke(adapterPosition)
        }
    }
    fun bind(p: Product){

        rating.setText(p.rating.toString())
        title.setText(p.title)
        price.setText(p.price.toString())
        if (p.rating<4.2){
            rating.setTextColor(Color.RED)
        }
        if (p.rating>=4.2 && p.rating<4.4){
            rating.setTextColor(Color.rgb(255, 165, 0))
        }
        if (p.rating>=4.2 && p.rating<4.4){
            rating.setTextColor(Color.RED)
        }
        if (p.rating>=4.4 && p.rating<4.6){
            rating.setTextColor(Color.YELLOW)
        }
        if (p.rating>=4.6 && p.rating<4.8){
            rating.setTextColor(Color.rgb(144, 238, 144))
        }
        if (p.rating>=4.8 && p.rating<=5.0){
            rating.setTextColor(Color.rgb(50, 164, 49))
        }

    }
}