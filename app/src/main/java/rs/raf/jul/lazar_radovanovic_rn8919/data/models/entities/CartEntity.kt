package rs.raf.jul.lazar_radovanovic_rn8919.data.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")

data class CartEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val product : String,
    val count: Int = 1,
    val price : Int

)
