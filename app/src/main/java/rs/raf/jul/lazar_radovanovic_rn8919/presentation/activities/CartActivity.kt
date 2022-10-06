package rs.raf.jul.lazar_radovanovic_rn8919.presentation.activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.jul.lazar_radovanovic_rn8919.R
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.contracts.StoreContract
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.recycler.adapter.CartAdapter
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.recycler.adapter.ImageAdapter
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.recycler.adapter.ProductAdapter
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.recycler.diff.CartDiffItemCallback
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.recycler.diff.ProductDiffItemCallback
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.viewmodel.StoreViewModel
import timber.log.Timber

class CartActivity : AppCompatActivity(R.layout.activity_cart) {
    private val storeViewModel: StoreContract.ViewModel by viewModel<StoreViewModel>()

    private lateinit var pricet: TextView
    private lateinit var recycler: RecyclerView
    private lateinit var b: Button
    private lateinit var cartAdapter:CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initObservers()
        makeCalls()

        pricet = findViewById<TextView>(R.id.twc)
        recycler = findViewById<RecyclerView>(R.id.rvc)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,true)
        recycler.layoutManager = layoutManager
        cartAdapter= CartAdapter(CartDiffItemCallback()){
            Timber.e("${it.product}")
        }
        recycler.adapter=cartAdapter
        b = findViewById<Button>(R.id.button2)
        b.setOnClickListener{
            storeViewModel.resetCart()
            println(storeViewModel.priceFin.value.toString()+"dddddddddddddddddd")
            println(storeViewModel.wholeCart.value.toString()+"aaaaaaaaaaaaaaaaaaa")

        }


    }

    private fun makeCalls() = with(storeViewModel) {
        getCartInventory()
        getCartPrice()


    }

    private fun initObservers() = with(storeViewModel) {
        priceFin.observe(this@CartActivity) {
            pricet.setText(storeViewModel.priceFin.value.toString())
        }
        wholeCart.observe(this@CartActivity){
            cartAdapter.submitList(it)
        }

    }
}