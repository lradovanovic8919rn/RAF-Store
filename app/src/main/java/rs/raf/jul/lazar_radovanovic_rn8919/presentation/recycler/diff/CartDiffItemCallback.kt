package rs.raf.jul.lazar_radovanovic_rn8919.presentation.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.jul.lazar_radovanovic_rn8919.data.models.entities.Cart

class CartDiffItemCallback : DiffUtil.ItemCallback<Cart>(){
    override fun areItemsTheSame(oldItem: Cart, newItem: Cart): Boolean {
        return oldItem.product == newItem.product
    }

    override fun areContentsTheSame(oldItem: Cart, newItem: Cart): Boolean {
        return oldItem.count == newItem.count
    }
}