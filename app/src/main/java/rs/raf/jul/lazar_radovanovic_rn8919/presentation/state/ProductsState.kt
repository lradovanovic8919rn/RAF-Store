package rs.raf.jul.lazar_radovanovic_rn8919.presentation.state

import rs.raf.jul.lazar_radovanovic_rn8919.data.models.entities.Product

sealed class ProductsState{
    object Loading: ProductsState()
    object DataFetched: ProductsState()
    data class Success(val movies: List<Product>): ProductsState()
    data class Error(val message: String): ProductsState()
}
