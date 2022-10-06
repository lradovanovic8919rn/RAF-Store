package rs.raf.jul.lazar_radovanovic_rn8919.presentation.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.jul.lazar_radovanovic_rn8919.data.models.entities.Cart
import rs.raf.jul.lazar_radovanovic_rn8919.data.models.entities.Product
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.recycler.diff.CartDiffItemCallback
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.recycler.diff.ProductDiffItemCallback
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.recycler.viewholder.CartItemViewHolder
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.recycler.viewholder.ProductViewHolder

class CartAdapter(cartDiffItemCallback: CartDiffItemCallback, private val onCartClicked: (Cart) -> Unit) : ListAdapter<Cart, CartItemViewHolder>(cartDiffItemCallback){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(rs.raf.jul.lazar_radovanovic_rn8919.R.layout.item_vh, parent,false)
        return CartItemViewHolder(containerView){
            val cartItem = getItem(it)
        }    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val p = getItem(position)
        holder.bind(p)
    }
}