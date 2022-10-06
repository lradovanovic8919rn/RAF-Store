package rs.raf.jul.lazar_radovanovic_rn8919.data.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")

data class ProductEntity(
    @PrimaryKey
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
