package rs.raf.jul.lazar_radovanovic_rn8919.data.models.responses

import com.squareup.moshi.JsonClass
import rs.raf.jul.lazar_radovanovic_rn8919.data.models.entities.Product
import rs.raf.jul.lazar_radovanovic_rn8919.data.models.entities.ProductData

@JsonClass(generateAdapter = true)

data class ProductDataResponse(
    val products: List<ProductResponse>

)