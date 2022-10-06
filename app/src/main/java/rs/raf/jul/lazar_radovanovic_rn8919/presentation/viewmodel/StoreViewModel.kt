package rs.raf.jul.lazar_radovanovic_rn8919.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.jul.lazar_radovanovic_rn8919.data.models.entities.*
import rs.raf.jul.lazar_radovanovic_rn8919.data.repository.StoreRepository
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.contracts.StoreContract
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.state.ProductsState
import timber.log.Timber
import java.util.concurrent.TimeUnit

class StoreViewModel (
    private val storeRepository: StoreRepository
) : ViewModel(), StoreContract.ViewModel{

    override val user: MutableLiveData<User?> = MutableLiveData()
    override val categories: MutableLiveData<List<String>> = MutableLiveData()
    override val allProducts: MutableLiveData<List<Product>> = MutableLiveData()
    override val activeCategory: MutableLiveData<List<Product>> = MutableLiveData()
    override val currentProduct: MutableLiveData<Product> = MutableLiveData()
    override val psLiveData: MutableLiveData<ProductsState> = MutableLiveData()
    override val wholeCart: MutableLiveData<List<Cart>?> = MutableLiveData()
    override val priceFin: MutableLiveData<Int?> = MutableLiveData()


    override val error: MutableLiveData<Boolean> = MutableLiveData()

    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    private val subscriptions = CompositeDisposable()

    init {
        val subscription = publishSubject
            .debounce(400, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                storeRepository
                    .fetchQuery(it)
                    .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> psLiveData.value = ProductsState.Loading
                        is Resource.Success -> psLiveData.value = ProductsState.DataFetched
                        is Resource.Error -> psLiveData.value = ProductsState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    psLiveData.value = ProductsState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
    override fun userLogin(username: String, password: String) {
        val sub = storeRepository
            .postLogin(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    error.value=false
                    user.value = it
                },
                {
                    error.value=true

                },
                {
                    Timber.e("Completed")
                }
            )
        subscriptions.add(sub)

    }

    override fun getCategories() {
        val sub = storeRepository
            .getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    categories.value = it

                },
                {
                    Timber.e(it)
                },
                {
                    Timber.e("Completed")

                }
            )
        subscriptions.add(sub)

    }

    override fun getAll() {
        val sub = storeRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    allProducts.value=it
                    println(allProducts.value)
                    println("1111111111sssss111111111111111")

                },
                {
                    Timber.e(it)
                },
                {
                    Timber.e("Completed")

                }
            )
        subscriptions.add(sub)

    }

    override fun getCategoryByName(name: String) {
        val sub = storeRepository
            .getCategoryByName(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    activeCategory.value=it
                    println(activeCategory.value)
                    println("1111111111sssss111111111111111")

                },
                {
                    Timber.e(it)
                },
                {
                    Timber.e("Completed")

                }
            )
        subscriptions.add(sub)
    }

    override fun getCurrentProduct(id: String) {
        val sub = storeRepository
            .getCurrentProduct(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    currentProduct.value=it
                    println(currentProduct.value)
                    println("1111111111sssss111111111111111")

                },
                {
                    Timber.e(it)
                },
                {
                    Timber.e("Completed")

                }
            )
        subscriptions.add(sub)
    }

    override fun getResults() {
        val subscription = storeRepository
            .getResults()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    psLiveData.value = ProductsState.Success(it)
                },
                {
                    psLiveData.value = ProductsState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun fetchQuery(s: String) {
        publishSubject.onNext(s)
    }

    override fun resetCart() {
        val sub = storeRepository
            .resetCart()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    wholeCart.value=null
                    priceFin.value=0

                    Timber.e("UPDATED")
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(sub)
    }

    override fun insertCart(ce: Cart) {
        val sub = storeRepository
            .insert(ce)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("UPDATED")
                    println()
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(sub)
    }

    override fun getCartPrice() {
        val sub = storeRepository
            .getCartPrice()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    priceFin.value=it
                    Timber.e("UPDATED")
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(sub)
    }

    override fun getCartInventory() {
        val sub = storeRepository
            .getCartInventory()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    wholeCart.value=it
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(sub)
    }

    override fun updateCart(ce: Cart) {
        val sub = storeRepository
            .update(ce)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("UPDATED")
                    println()
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(sub)
    }


    override fun userLogout() {
        user.value=null
    }

    override fun onCleared() {
        subscriptions.clear()
        super.onCleared()
    }



}
