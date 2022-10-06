package rs.raf.jul.lazar_radovanovic_rn8919.presentation.recycler.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Picasso
import rs.raf.jul.lazar_radovanovic_rn8919.data.models.entities.Product
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.activities.ProductActivity
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.recycler.diff.ProductDiffItemCallback
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.recycler.viewholder.ProductViewHolder

class ProductAdapter (productDiffItemCallback: ProductDiffItemCallback, private val onProductClicked: (Product) -> Unit) : ListAdapter<Product, ProductViewHolder>(productDiffItemCallback){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(rs.raf.jul.lazar_radovanovic_rn8919.R.layout.product_vh, parent,false)
        return ProductViewHolder(containerView){
            val productItem = getItem(it)
        }
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val p = getItem(position)
        holder.bind(p)
        val cont=holder.itemView.context;

        holder.itemView.setOnClickListener{
            var v = p.id.toString()
            val i = Intent(cont,ProductActivity::class.java)
            i.putExtra("id",v)
            cont.startActivity(i)
        }
        val imageView: ImageView = holder.itemView.findViewById(rs.raf.jul.lazar_radovanovic_rn8919.R.id.imgv)
        Picasso.with(cont).load(p.thumbnail).into(imageView)
    }
}