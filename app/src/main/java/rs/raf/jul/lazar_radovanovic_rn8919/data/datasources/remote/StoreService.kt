package rs.raf.jul.lazar_radovanovic_rn8919.data.datasources.remote

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*
import rs.raf.jul.lazar_radovanovic_rn8919.data.models.bodies.LoginRequestBody
import rs.raf.jul.lazar_radovanovic_rn8919.data.models.entities.Product
import rs.raf.jul.lazar_radovanovic_rn8919.data.models.responses.ProductDataResponse
import rs.raf.jul.lazar_radovanovic_rn8919.data.models.responses.ProductResponse
import rs.raf.jul.lazar_radovanovic_rn8919.data.models.responses.UserResponse


interface StoreService {
    @POST("auth/login")
    fun postLogin(@Body body: LoginRequestBody):Observable <UserResponse>

    @GET("products/categories")
    fun getCategories(): Observable<List<String>>

    @GET("products")
    fun getAll(): Observable<ProductDataResponse>

    @GET("products/category/{cat}")
    fun getCategoryByName(@Path("cat") cat: String): Observable<ProductDataResponse>

   // @GET("products/search?q={kw}")
   // fun getByKeyword(@Path("kw") cat: String): Observable<ProductDataResponse>

    @GET("products/{id}")
    fun getCurrentProduct(@Path("id") cat: String): Observable<ProductResponse>

    @GET("products/search?")
    fun fetchQuery(@Query("q") q: String): Observable<ProductDataResponse>



}