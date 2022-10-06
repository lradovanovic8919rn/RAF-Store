package rs.raf.jul.lazar_radovanovic_rn8919.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jul.lazar_radovanovic_rn8919.data.datasources.local.CartDao
import rs.raf.jul.lazar_radovanovic_rn8919.data.datasources.local.StoreDao
import rs.raf.jul.lazar_radovanovic_rn8919.data.datasources.remote.StoreService
import rs.raf.jul.lazar_radovanovic_rn8919.data.models.bodies.LoginRequestBody
import rs.raf.jul.lazar_radovanovic_rn8919.data.models.entities.*
import timber.log.Timber

class StoreRepositoryImplementation (
    private val cartDao: CartDao,
    private val local : StoreDao,
    private val storeService: StoreService
) : StoreRepository {
    override fun postLogin(username: String, password: String): Observable<User> {
        return storeService
            .postLogin(
                LoginRequestBody(
                    username,password
                )
            )
            .map { userResponse->
                User(
                    username = userResponse.username,
                    email = userResponse.email,
                    firstName = userResponse.firstName,
                    gender = userResponse.gender,
                    image = userResponse.image,
                    lastName = userResponse.lastName

                )
            }
        println(username+password)
    }

    override fun getCategories(): Observable<List<String>> {
        return storeService
            .getCategories()

    }

    override fun getAll(): Observable<List<Product>> {
        return storeService
            .getAll()
            .map {
                it.products.map {
                    Product(
                        id=it.id,
                        brand = it.brand,
                        category = it.category,
                        description = it.description,
                        discountPercentage = it.discountPercentage,
                        images = it.images,
                        price = it.price,
                        rating = it.rating,
                        thumbnail = it.thumbnail,
                        title = it.title

                    )
                }


            }
    }

    override fun getCategoryByName(name: String): Observable<List<Product>> {
        return storeService
            .getCategoryByName(name)
            .map {
                it.products.map {
                    Product(
                        id=it.id,
                        brand = it.brand,
                        category = it.category,
                        description = it.description,
                        discountPercentage = it.discountPercentage,
                        images = it.images,
                        price = it.price,
                        rating = it.rating,
                        thumbnail = it.thumbnail,
                        title = it.title

                    )
                }


            }
    }


    override fun getCurrentProduct(id: String): Observable<Product> {
        return storeService
            .getCurrentProduct(id)
            .map { productResponse->
                Product(
                id=productResponse.id,
                brand = productResponse.brand,
                category = productResponse.category,
                description = productResponse.description,
                discountPercentage = productResponse.discountPercentage,
                images = productResponse.images,
                price = productResponse.price,
                rating = productResponse.rating,
                thumbnail = productResponse.thumbnail,
                title = productResponse.title

            )


                }


            }

    override fun fetchQuery(s: String): Observable<Resource<Unit>> {
        return storeService
            .fetchQuery(s)
            .doOnNext {
                Timber.e("Upis u bazu")
                val entities = it.products.map {
                    ProductEntity(
                        id=it.id,
                        brand = it.brand,
                        category = it.category,
                        description = it.description,
                        discountPercentage = it.discountPercentage,
                        images = it.images,
                        price = it.price,
                        rating = it.rating,
                        thumbnail = it.thumbnail,
                        title = it.title
                    )
                }
                local.deleteAndInsertAll(entities)
            }
            .map {
                Resource.Success(Unit)
            }    }

    override fun getResults(): Observable<List<Product>> {
        return local
            .getResults()
            .map {
                it.map {
                    Product(
                        id=it.id,
                        brand = it.brand,
                        category = it.category,
                        description = it.description,
                        discountPercentage = it.discountPercentage,
                        images = it.images,
                        price = it.price,
                        rating = it.rating,
                        thumbnail = it.thumbnail,
                        title = it.title
                    )
                }
            }
    }

    override fun resetCart(): Completable {
        return cartDao.deleteAll()
    }


    override fun getCartPrice(): Observable<Int?> {
        return cartDao.getFinalPrice()
    }

    override fun getCartInventory(): Observable<List<Cart>> {
        return cartDao
            .getCartInventory()
            .map {
                it.map {
                    cartEntity ->
                    Cart(
                        product = cartEntity.product,
                        id=cartEntity.id,
                        count = cartEntity.count,
                        price=cartEntity.price
                    )
                }
            }
    }

    override fun insert(c: Cart): Completable {
        val c=CartEntity(c.id,c.product,1,c.price)
        return cartDao.insert(c)
    }

    override fun update(c:Cart): Completable {
        return cartDao.updateSum(c.product.toString(),c.count.toInt())
    }


}