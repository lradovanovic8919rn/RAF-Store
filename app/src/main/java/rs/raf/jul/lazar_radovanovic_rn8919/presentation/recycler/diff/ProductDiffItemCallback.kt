package rs.raf.jul.lazar_radovanovic_rn8919.presentation.recycler.diff

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import rs.raf.jul.lazar_radovanovic_rn8919.data.models.entities.Product

class ProductDiffItemCallback: DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.title == newItem.title
        return oldItem.rating == newItem.rating

    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.price == newItem.price
        return oldItem.rating == newItem.rating
        return oldItem.images == newItem.images
        return oldItem.thumbnail == newItem.thumbnail
    }
}