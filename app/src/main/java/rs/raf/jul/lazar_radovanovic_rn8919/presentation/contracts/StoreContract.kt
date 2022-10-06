package rs.raf.jul.lazar_radovanovic_rn8919.presentation.contracts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jul.lazar_radovanovic_rn8919.data.models.entities.*
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.state.ProductsState

interface StoreContract {
    interface ViewModel {
        val error: LiveData<Boolean>
        val user: LiveData<User?>
        val categories:LiveData<List<String>>
        val allProducts:LiveData<List<Product>>
        val activeCategory:LiveData<List<Product>>
        val currentProduct:LiveData<Product>
        val psLiveData:LiveData<ProductsState>
        val wholeCart: LiveData<List<Cart>?>
        val priceFin:LiveData<Int?>

        fun userLogin(username:String,password:String)
        fun userLogout()
        fun getCategories()
        fun getAll()
        fun getCategoryByName(name:String)
        fun getCurrentProduct(id:String)
        fun getResults()
        fun fetchQuery(s:String)
        fun resetCart()
        fun insertCart(ce: Cart)
        fun getCartPrice()
        fun getCartInventory()
        fun updateCart(ce: Cart)

    }
}