package rs.raf.jul.lazar_radovanovic_rn8919.presentation.activities

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.jul.lazar_radovanovic_rn8919.R
import rs.raf.jul.lazar_radovanovic_rn8919.data.models.entities.Cart
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.contracts.StoreContract
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.recycler.adapter.ImageAdapter
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.viewmodel.StoreViewModel

class ProductActivity : AppCompatActivity(R.layout.activity_product){
    private val storeViewModel: StoreContract.ViewModel by viewModel<StoreViewModel>()

    private lateinit var title : TextView
    private lateinit var price : TextView
    private lateinit var discount : TextView
    private lateinit var brand : TextView
    private lateinit var rating : TextView
    private lateinit var desc : TextView
    private lateinit var category : TextView
    private lateinit var recycler : RecyclerView
    private lateinit var imgAdapter: ImageAdapter
    private lateinit var b : Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initObservers()
        makeCalls()

        rating = findViewById<TextView>(R.id.rat)
        price = findViewById<TextView>(R.id.pr)
        discount = findViewById<TextView>(R.id.dis)
        title = findViewById<TextView>(R.id.titl)
        brand = findViewById<TextView>(R.id.brand)
        desc = findViewById<TextView>(R.id.des)
        category = findViewById<TextView>(R.id.cat)
        b=findViewById<Button>(R.id.button)

        b.setOnClickListener{

            if(storeViewModel.wholeCart.value?.size!=null){
                val siz= storeViewModel.wholeCart.value?.size
                var f=0
                for (i in 0..(siz!!-1)){
                    if(storeViewModel.wholeCart.value?.get(i)?.product.toString().equals(storeViewModel.currentProduct.value?.title.toString())){
                        f=1
                    }
                }
                if(f==1){
                    storeViewModel.updateCart(Cart(0,storeViewModel.currentProduct.value?.title.toString(),1,
                        storeViewModel.currentProduct.value?.price!!.toInt()))
                }else{
                    storeViewModel.insertCart(
                        Cart(0,storeViewModel.currentProduct.value?.title.toString(),1,
                            storeViewModel.currentProduct.value?.price!!.toInt())
                    )
                }
            }else{
                storeViewModel.insertCart(
                    Cart(0,storeViewModel.currentProduct.value?.title.toString(),1,
                        storeViewModel.currentProduct.value?.price!!.toInt())
                )
            }

            //storeViewModel.resetCart()
            storeViewModel.getCartInventory()
            println(storeViewModel.wholeCart.value?.toString())
        }



    }
    private fun makeCalls() = with(storeViewModel){
        val extras = intent.extras
        val v= extras?.get("id").toString()
        storeViewModel.getCurrentProduct(v)

    }
    private fun initObservers() = with(storeViewModel) {
        currentProduct.observe(this@ProductActivity){
            rating.setText(storeViewModel.currentProduct.value?.rating.toString())
            price.setText(storeViewModel.currentProduct.value?.price.toString())
            discount.setText(storeViewModel.currentProduct.value?.discountPercentage.toString())
            title.setText(storeViewModel.currentProduct.value?.title.toString())
            brand.setText(storeViewModel.currentProduct.value?.brand.toString())
            desc.setText(storeViewModel.currentProduct.value?.description.toString())
            category.setText(storeViewModel.currentProduct.value?.category.toString())




            if (storeViewModel.currentProduct.value?.rating!! <4.2){
                rating.setTextColor(Color.RED)
            }
            if (storeViewModel.currentProduct.value?.rating!!>=4.2 && storeViewModel.currentProduct.value?.rating!!<4.4){
                rating.setTextColor(Color.rgb(255, 165, 0))
            }
            if (storeViewModel.currentProduct.value?.rating!!>=4.2 && storeViewModel.currentProduct.value?.rating!!<4.4){
                rating.setTextColor(Color.RED)
            }
            if (storeViewModel.currentProduct.value?.rating!!>=4.4 && storeViewModel.currentProduct.value?.rating!!<4.6){
                rating.setTextColor(Color.YELLOW)
            }
            if (storeViewModel.currentProduct.value?.rating!!>=4.6 && storeViewModel.currentProduct.value?.rating!!<4.8){
                rating.setTextColor(Color.rgb(144, 238, 144))
            }
            if (storeViewModel.currentProduct.value?.rating!!>=4.8 && storeViewModel.currentProduct.value?.rating!!<=5.0){
                rating.setTextColor(Color.rgb(50, 164, 49))
            }


            recycler = findViewById<RecyclerView>(R.id.recc)
            val layoutManager = LinearLayoutManager(this@ProductActivity, LinearLayoutManager.HORIZONTAL,true)
            recycler.layoutManager = layoutManager
            imgAdapter = ImageAdapter(storeViewModel.currentProduct.value!!.images)
            recycler.adapter = imgAdapter

            wholeCart.observe(this@ProductActivity) {

            }

        }
    }

}