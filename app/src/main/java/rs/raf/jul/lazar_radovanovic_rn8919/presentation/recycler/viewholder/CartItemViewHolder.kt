package rs.raf.jul.lazar_radovanovic_rn8919.presentation.recycler.viewholder

import android.graphics.Color
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.extensions.LayoutContainer
import rs.raf.jul.lazar_radovanovic_rn8919.R
import rs.raf.jul.lazar_radovanovic_rn8919.data.models.entities.Cart
import rs.raf.jul.lazar_radovanovic_rn8919.data.models.entities.Product

class CartItemViewHolder (
    override val containerView: View,
    onItemClicked: (Int) -> Unit) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    val name: MaterialTextView = containerView.findViewById(R.id.im)
    val count: MaterialTextView = containerView.findViewById(R.id.br)
    val price: MaterialTextView = containerView.findViewById(R.id.cena)

    init {
        containerView.setOnClickListener {
            onItemClicked.invoke(adapterPosition)
        }
    }
    fun bind(c: Cart){
        name.setText(c.product.toString())
        count.setText(c.count.toString())
        price.setText(c.price.toString())


    }
}