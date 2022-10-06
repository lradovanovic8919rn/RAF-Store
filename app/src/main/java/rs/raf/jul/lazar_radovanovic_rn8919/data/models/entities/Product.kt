package rs.raf.jul.lazar_radovanovic_rn8919.data.models.entities

data class Product (
    val id: Int,
    val brand: String,
    val category: String,
    val description: String,
    val discountPercentage: Double,
    val images: List<String>,
    val price: Int,
    val rating: Double,
    val thumbnail: String,
    val title: String
)