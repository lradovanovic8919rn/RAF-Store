package rs.raf.jul.lazar_radovanovic_rn8919.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jul.lazar_radovanovic_rn8919.data.models.entities.*

interface StoreRepository {
    fun postLogin(username:String,password:String):Observable <User>
    fun getCategories():Observable<List<String>>
    fun getAll():Observable<List<Product>>
    fun getCategoryByName(name:String):Observable<List<Product>>
    fun getCurrentProduct(id:String):Observable<Product>
    fun fetchQuery(s:String):Observable<Resource<Unit>>
    fun getResults(): Observable<List<Product>>
    fun resetCart(): Completable
   // fun insertOrUpdate(product: String,price:Int): Completable
    fun getCartPrice():Observable<Int?>
    fun getCartInventory(): Observable<List<Cart>>
    fun insert(c:Cart): Completable
    fun update(c:Cart): Completable


}