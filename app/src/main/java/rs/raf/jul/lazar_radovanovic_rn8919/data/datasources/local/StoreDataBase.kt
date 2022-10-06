package rs.raf.jul.lazar_radovanovic_rn8919.data.datasources.local


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.jul.lazar_radovanovic_rn8919.data.datasources.local.converters.StringListConverter
import rs.raf.jul.lazar_radovanovic_rn8919.data.models.entities.CartEntity

import rs.raf.jul.lazar_radovanovic_rn8919.data.models.entities.ProductEntity

@Database(
    entities = [ProductEntity::class, CartEntity::class],
    version = 2,
    exportSchema = false)
@TypeConverters(StringListConverter::class)
abstract class StoreDataBase :RoomDatabase(){
    abstract fun getStoreDao(): StoreDao
    abstract fun getCartDao():CartDao

}